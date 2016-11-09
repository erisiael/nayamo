package action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.OKS_DAO;
import vo.LETTER;
import vo.OKS;

public class AjaxTempAction extends ActionSupport implements SessionAware
{
	private Map<String, Object> session;
	private boolean dupChk = false; // 이메일 중복 함수를 위한 변수
	private OKS oks;
	private LETTER letter;
	private int no;
	private String nick;
	private String title;
	private String contents;
	private String indate;
	
	public String emailChk() throws Exception // 이메일 중복 확인을 하는 함수
	{
		OKS_DAO dao = new OKS_DAO();
		OKS tmp = dao.selectOne(oks.getEmail());
		if(tmp != null)
		{
			session.put("email", tmp.getEmail());
			dupChk = false;
		}
		else
		{
			dupChk = true;
		}
		return SUCCESS;
	}
	
	public String writeLetter() throws Exception{
		int no = 0; //DB에서 작업
		OKS oks = (OKS) session.get("OKS");
		String from_nick = oks.getNick();
		String indate = ""; //DB에서 작업
		String read = "";//DB에서 작업
		letter = new LETTER(no, title, contents, from_nick, nick, indate, read);
		OKS_DAO dao = new OKS_DAO();
		int re = dao.writeLetter(letter);
		if(re == 1){
			System.out.println("성공");
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String readLetterForm() throws Exception{
		OKS_DAO dao = new OKS_DAO();
		dao.updateLetter(no);//read 읽음으로 바꿈
		letter = dao.readLetterForm(no);
		return SUCCESS;
	}

	//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
	
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public boolean isDupChk() {
		return dupChk;
	}

	public void setDupChk(boolean dupChk) {
		this.dupChk = dupChk;
	}

	public OKS getOks() {
		return oks;
	}

	public void setOks(OKS oks) {
		this.oks = oks;
	}

	public LETTER getLetter() {
		return letter;
	}

	public void setLetter(LETTER letter) {
		this.letter = letter;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}
	
	
}
