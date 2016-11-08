package vo;

import java.util.HashMap;

import javax.websocket.Session;

public class STR 
{
	private int STR_no;
	private String OKS_email;
	private String name;
	//////////////////////seokgi
	private String keyword;
	private String category;
	////////////////////////////
	private String enter_code;
	private String regdate;
	
	private String svgdata;
	private String htmldata;
	
	private HashMap<Session,String> session_list = new HashMap<>();
	
	public STR() {
		
	}
	
	
	
	
	
	

	
	public STR(int sTR_no, String oKS_email, String name, String savepath, String enter_code, String regdate) {
		super();
		STR_no = sTR_no;
		OKS_email = oKS_email;
		this.name = name;
		this.enter_code = enter_code;
		this.regdate = regdate;
	}
	
	
	
	
	///seokgi

	//OKS_email,name, keyword, category, enter_code
	public STR(String oKS_email, String name, String keyword, String category, String enter_code) {
		super();
		OKS_email = oKS_email;
		this.name = name;
		this.keyword = keyword;
		this.category = category;
		this.enter_code = enter_code;
	}
	
	public STR(String oKS_email, String name, String keyword, String category,
			String enter_code, String regdate) {
		super();
		OKS_email = oKS_email;
		this.name = name;
		this.keyword = keyword;
		this.category = category;
		this.enter_code = enter_code;
		this.regdate = regdate;
	}
///////////////////////////////////////////

	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getSTR_no() {
		return STR_no;
	}

	public void setSTR_no(int sTR_no) {
		STR_no = sTR_no;
	}

	public String getOKS_email() {
		return OKS_email;
	}

	public void setOKS_email(String oKS_email) {
		OKS_email = oKS_email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnter_code() {
		return enter_code;
	}

	public void setEnter_code(String enter_code) {
		this.enter_code = enter_code;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public HashMap<Session,String> getSession_list() {
		return session_list;
	}
	public void setSession_list(HashMap<Session,String> session_list) {
		this.session_list = session_list;
	}
	public String getSvgdata() {
		return svgdata;
	}
	public void setSvgdata(String svgdata) {
		this.svgdata = svgdata;
	}
	public String getHtmldata() {
		return htmldata;
	}
	public void setHtmldata(String htmldata) {
		this.htmldata = htmldata;
	}

	@Override
	public String toString() {
		return "STR [STR_no=" + STR_no + ", OKS_email=" + OKS_email + ", name=" + name + ", keyword=" + keyword
				+ ", category=" + category + ", enter_code=" + enter_code + ", regdate="
				+ regdate + "]";
	}

	/*@Override
	public String toString() {
		return "STR [STR_no=" + STR_no + ", OKS_email=" + OKS_email + ", name=" + name + ", savepath=" + savepath
				+ ", enter_code=" + enter_code + ", regdate=" + regdate + "]";
	}*/
	

	
	
}
