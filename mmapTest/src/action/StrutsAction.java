package action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.OKS_DAO;
import properties.PageNavigator;
import vo.OKS;
import vo.SKA_LIST;

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
	
	private int currentPage = 1;
	private String searchField; // boardlist에서 검색 value 받아오는 변수
	private String searchText;
	
	private PageNavigator pagenavi;
	
	public String enter_test() throws Exception
	{
		return SUCCESS;
	}
	
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
	
	public String board() throws Exception // SKA_LIST에 저장되어 있는 모든 경매 목록을 불러오는 함수
	{
		OKS_DAO dao = new OKS_DAO();
		skaList = dao.selectAllBoardList();
		return SUCCESS;
	}
	
	public String emailDuplicate() throws Exception
	{
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
	
	/*김동현 작업시작*/
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
	/*김동현 작업 끝*/
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////
	
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

	public boolean isDupChk() {
		return dupChk;
	}

	public void setDupChk(boolean dupChk) {
		this.dupChk = dupChk;
	}

	public List<SKA_LIST> getSkaList() {
		return skaList;
	}

	public void setSkaList(List<SKA_LIST> skaList) {
		this.skaList = skaList;
	}
	
	
}
