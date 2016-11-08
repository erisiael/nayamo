package vo;

public class OKS 
{
	private int OKS_no;
	private String email;
	private String pass;
	private String nick;
	private int point;
	private String regdate;
	
	public OKS() {
		// TODO Auto-generated constructor stub
	}
	
	

	public OKS(String email) {
		super();
		this.email = email;
	}



	public OKS(int oKS_no, String email, String pass, String nick, int point, String regdate) {
		super();
		OKS_no = oKS_no;
		this.email = email;
		this.pass = pass;
		this.nick = nick;
		this.point = point;
		this.regdate = regdate;
	}

	public int getOKS_no() {
		return OKS_no;
	}

	public void setOKS_no(int oKS_no) {
		OKS_no = oKS_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "OKS [OKS_no=" + OKS_no + ", email=" + email + ", pass=" + pass + ", nick=" + nick + ", point=" + point
				+ ", regdate=" + regdate + "]";
	}
}
