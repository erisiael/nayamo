package action;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.websocket.Session;

import org.apache.struts2.interceptor.SessionAware;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
		
		
		// keyword 추천 기능 시작 // 
				// myPage에서 입력한 keyword 값을 이용하여 db에 저장되어 있는 값들의 리스트를 가져온다
				List<String> dic = new ArrayList<String>(); // 오라클에서 받아온 단어를 담을 리스트
			    List<Integer> total = new ArrayList<Integer>(); // 검색해서 받아온 스트링에서 DB단어와 일치하는 개수를 담을 리스트
			    
			    int tmp = 0;
			    int count = 0;
			    String tmp_str = "";
			    
			    String keyword = str.getKeyword(); // myPage.html에서 입력한 keyword
			    String category = str.getCategory(); // myPage.html에서 입력한 category. db에서 table을 선택한다
			    OKS_DAO dao = new OKS_DAO();
			    dic = dao.searchDB(category); // 선택한 category에 해당하는 db테이블을 찾은 후, 담겨 있는 모든 name 들을 가져온다
			    
			    String resSearch = Search(keyword); // myPage.html에서 입력한 keyword를 이용하여, 네이버 페이지에서 검색한 결과를 가져온다
			    
			    for (int j = 0; j < dic.size(); j++) {
			         Pattern pattern = Pattern.compile(dic.get(j)); //오라클에서 받아온 단어 나중에 dic.... 변경
			         Matcher matcher = pattern.matcher(resSearch); //검색결과
			         while (matcher.find()) {
			            count++; //검색결과가 오라클에서 받아온 단어와 일치하면 count증가
			         }
			         total.add(count);
			         System.out.println("[" + resSearch + "] 의 " + dic.get(j) + "의 개수 : " + count);
			         count = 0;
			      }

			      for (int i = 0; i < dic.size(); i++) {
			         int reee = total.get(i);
			         System.out.println(dic.get(i)+"의 갯수 : " +reee);
			         
			         
			      }
			      
			      for(int i = 0; i < total.size(); i++) 
			      {
			         for (int j = 0; j < total.size() - 1; j++) 
			         {
			            count++;
			            if(total.get(j) < total.get(j+1)) {
			               
			               tmp = total.get(j);
			               total.set(j, total.get(j+1));
			               total.set(j+1, tmp);
			               
			               tmp_str = dic.get(j);
			               dic.set(j, dic.get(j+1));
			               dic.set(j+1, tmp_str);
			            }
			         }
			      }
			      for(int i = 0; i < 3; i++)
			      {
			    	  System.out.println(i + 1 + " : " + dic.get(i));
			      }
			   
			    // keyword 추천 기능 끝 // 



		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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


	private String Search(String keyword) // 네이버 페이지에서 category로 검색한 결과를 긁어오는 함수
	{
		String str = ""; // 검색결과 읽어온 스트링값을 담기 위한 변수
		try {
			String utf8 = URLEncoder.encode(keyword, "UTF-8");// 쿼리문에들어갈 한글인코딩
			String url = "http://search.naver.com/search.naver?" + "where=nexearch&query=" + utf8
					+ "&display=10&start=1&target=webkr&sm=top_hty&fbm=1&ie=utf8&display=10&start=1&target=webkr";
			Document doc = Jsoup.connect(url)
					.header( // 헤더
							"Accept",
							"image/gif, image/xxbitmap, image/jpeg, image/pjpeg,application/xshockwaveflash, application/vnd.msexcel,application/vnd.mspowerpoint, application/msword, */*")
					.get();

			Elements links = doc.getElementsByTag("a"); // a태그 모두
			Elements links1 = doc.getElementsByTag("dd"); // dd태그 모두w

			for (Element link : links) {
				str += link.text(); // a태그에 텍스트
			}
			for (Element link : links1) {
				str += link.text(); // dd태그에 텍스트
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str; // 결과값 반환
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
