package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.websocket.Session;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.OKS_DAO;
import properties.PageNavigator;
import properties.RandomNumberGenerator;
import vo.OKS;
import vo.SKA_LIST;
import vo.STR;
import websocket.BroadsocketHtml;

public class StrutsAction extends ActionSupport implements SessionAware
{
	private OKS oks;
	private Map<String, Object> session;
	private boolean l_chk = true;

	private String email;
	private String pass;
	private String nick;

	private boolean dupChk = false;
	private List<SKA_LIST> skaList;

	private String Board_List_Form;

	private int currentPage = 1;
	private String searchField; // boardlist에서 검색 value 받아오는 변수
	private String searchText;

	private PageNavigator pagenavi;

	////윤석기
	private STR str;
	private String roomName_web;

	//private static String email_socket;
	//private static int i = 0;//메시지에 회원수
	////
	private String errorMessage;



	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

/*	public static String getEmail_socket() {
		return email_socket;
	}

	public static void setEmail_socket(String email_socket) {
		StrutsAction.email_socket = email_socket;
	}*/

	public String OKS_Login() throws Exception
	{
		OKS tmp = null;
		OKS_DAO dao = new OKS_DAO();
		tmp = dao.selectOne(oks);
		if(tmp != null)
		{
			session.put("OKS", tmp);
			return SUCCESS;
		}
		session.put("l_chk", false);
		return ERROR;		
	}

	/*빽 시작*/
	public String register() throws Exception // 회원 가입 함수
	{
		OKS_DAO dao = new OKS_DAO();
		int res = dao.insertOKS(oks);
		if(res == 1)
		{
			return SUCCESS;
		}
		return INPUT;
	}




	public String Go_board() throws Exception // 메인에서 버튼 눌러서 auction.jsp로 이동
	{
		//user.properties에서 지정한 페이징 관련 상수들 읽기
		int countPerPage = Integer.parseInt(getText("board.countperpage"));
		int pagePerGroup = Integer.parseInt(getText("board.pagepergroup"));

		OKS_DAO dao = new OKS_DAO();

		int total = dao.getTotal(searchField, searchText);//전체 글수 구하기
		pagenavi = new PageNavigator(countPerPage, pagePerGroup, currentPage, total);//pagenavigator 객체 생성
		skaList = dao.listBoard(searchField, searchText, pagenavi.getStartRecord(), pagenavi.getCountPerPage());
		//현재 페이지에 해당하는 글 목록 읽기
		return SUCCESS;
	}

	public String Board_List_Form() throws Exception // 게시판의 세 가지 종류에 따른 게시판 목록을 불러온다  
	{
		oks = (OKS) session.get("OKS");
		//user.properties에서 지정한 페이징 관련 상수들 읽기
		int countPerPage = Integer.parseInt(getText("board.countperpage"));
		int pagePerGroup = Integer.parseInt(getText("board.pagepergroup"));
		OKS_DAO dao = new OKS_DAO();

		if(Board_List_Form.equals("all") || Board_List_Form.equals("OKS_no"))
		{
			int total = dao.getTotal2(Board_List_Form, oks); //전체 글수 구하기
			pagenavi = new PageNavigator(countPerPage, pagePerGroup, currentPage, total);//pagenavigator 객체 생성
			skaList = dao.listBoard2(Board_List_Form, oks, pagenavi.getStartRecord(), pagenavi.getCountPerPage());
		}
		else // Board_List_Form값이 SKA_no가 넘어옴. 내가 참여한 경매 목록 불러오기
		{
			int total = dao.getTotal3(Board_List_Form, oks); //전체 글수 구하기
			pagenavi = new PageNavigator(countPerPage, pagePerGroup, currentPage, total);//pagenavigator 객체 생성
			skaList = dao.listBoard3(Board_List_Form, oks, pagenavi.getStartRecord(), pagenavi.getCountPerPage());
		}

		return SUCCESS;
	}

	public String board() throws Exception // SKA_LIST에 저장되어 있는 모든 경매 목록을 불러오는 함수
	{
		OKS_DAO dao = new OKS_DAO();
		skaList = dao.selectAllBoardList();
		return SUCCESS;
	}

	public String emailChk() throws Exception
	{
		OKS_DAO dao = new OKS_DAO();
		OKS tmp = dao.selectOne(oks.getEmail());
		if(tmp != null)
		{
			session.put("email", tmp.getEmail());
			return SUCCESS;
		}
		dupChk = true;
		return INPUT;
	}
	/*빽 끝*/

	/*김DH 작업시작*/
	//회원정보 수정작업을 위한 회원정보 찾기 (뷰단에 회원정보 뿌려줌)
	public String OKS_updateForm() throws Exception{
		if(session.containsKey("OKS")){
			oks = (OKS) session.get("OKS");
			email = oks.getEmail();
			nick = oks.getNick();
			return SUCCESS;
		}
		return ERROR;
	}

	public String OKS_update() throws Exception{
		OKS oks2 = (OKS) session.get("OKS");
		int oKS_no = oks2.getOKS_no();
		int point = oks2.getPoint();
		String regdate2 = oks2.getRegdate();
		String regdate = regdate2.substring(0, 10);
		OKS noks = new OKS(oKS_no, oks.getEmail(), oks.getPass(), oks.getNick(), point, regdate);
		OKS_DAO dao = new OKS_DAO();
		int result = dao.OKS_update(noks);

		if(result>0){
			return SUCCESS;
		}
		return ERROR;
	}

	public String OKS_find() throws Exception{
		String email = oks.getEmail();
		OKS_DAO dao = new OKS_DAO();
		oks = dao.OKS_find(email);
		if(oks != null){			
			System.out.println("비밀번호 : " + oks.getPass());
			return SUCCESS;
		}
		return ERROR;
	}

	public String OKS_logout() throws Exception{
		session.clear();
		return SUCCESS;
	}
	//
	/*김DH 작업 끝*/
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////


	/* 
	 * 윤석기 작업
	 * */	
	//2
	public String checkRoom(){

		//roomName_web=str.getEnter_code();
		
		System.out.println("entercode from checkRoom : " + roomName_web);

		STR check = null;

		check = BroadsocketHtml.getRooms().get(roomName_web);
		if(check == null){
			errorMessage = "코드를 찾을 수 없습니다.";
			return ERROR;
		}else{
			/*//회원일 경우와 아닐경우
			if(session.get("OKS") == null){
				//회원이 아닐경우
				email_socket = "GUEST"+i;
				i++;
			}else{
				//회원일 경우
				OKS clerk= (OKS)session.get("OKS");
				String name=clerk.getEmail();
				
				String[] div_email = name.split("@");
				email_socket = div_email[0];
			}*/
			return SUCCESS;
		}
	}

	//1
	//방만들기
	public String newRoom(){
		//test
		RandomNumberGenerator r = new RandomNumberGenerator(((OKS)session.get("OKS")).getEmail());
		//////
		roomName_web = r.generateEntercode();
		//
		str.setEnter_code(roomName_web);
		ActionContext.getContext().getValueStack().setValue("roomName_web", roomName_web);
		//
		
		System.out.println("entercode from newRoom : " + str.getEnter_code());
		
		/*String email_socket =((OKS)session.get("OKS")).getEmail();
		String[]temp = email_socket.split("@");
		email_socket = temp[0];*/
		
		if(!BroadsocketHtml.getRooms().containsKey(roomName_web)){
			//같은방 이름이 없을때
			//방에 넣기
			BroadsocketHtml.getRooms().put(roomName_web, new STR(str.getOKS_email(), str.getName(), str.getKeyword(), str.getCategory(),roomName_web));
			String rootnode = "[{\"text\":\""+ str.getKeyword() +"\",\"root\":true,\"values\":100}]";
			BroadsocketHtml.getRooms().get(roomName_web).setSavepath(rootnode);
		}else{
			//같은 이름의 방이 있을떄
			errorMessage = "죄송합니다! 코드가 중복되었습니다. 다시 실행해주세요.";
			return ERROR;
		}
		return SUCCESS;
	}







	////////////////////////////////////////////////////////



	public OKS getOks() {
		return oks;
	}

	public void setOks(OKS oks) {
		this.oks = oks;
	}

	public boolean isL_chk() {
		return l_chk;
	}

	public void setL_chk(boolean l_chk) {
		this.l_chk = l_chk;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public PageNavigator getPagenavi() {
		return pagenavi;
	}

	public void setPagenavi(PageNavigator pagenavi) {
		this.pagenavi = pagenavi;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public List<SKA_LIST> getSkaList() {
		return skaList;
	}

	public void setSkaList(List<SKA_LIST> skaList) {
		this.skaList = skaList;
	}

	public String getBoard_List_Form() {
		return Board_List_Form;
	}

	public void setBoard_List_Form(String board_List_Form) {
		Board_List_Form = board_List_Form;
	}

	public STR getStr() {
		return str;
	}

	public void setStr(STR str) {
		this.str = str;
	}
	public String getRoomName_web() {
		return roomName_web;
	}
	public void setRoomName_web(String roomName_web) {
		this.roomName_web = roomName_web;
	}
	

}
