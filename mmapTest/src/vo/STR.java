package vo;

public class STR 
{
	private int STR_no;
	private String OKS_email;
	private String name;
	private String savepath;
	private String enter_code;
	private String regdate;
	
	public STR() {
		// TODO Auto-generated constructor stub
	}

	public STR(int sTR_no, String oKS_email, String name, String savepath, String enter_code, String regdate) {
		super();
		STR_no = sTR_no;
		OKS_email = oKS_email;
		this.name = name;
		this.savepath = savepath;
		this.enter_code = enter_code;
		this.regdate = regdate;
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

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
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

	@Override
	public String toString() {
		return "STR [STR_no=" + STR_no + ", OKS_email=" + OKS_email + ", name=" + name + ", savepath=" + savepath
				+ ", enter_code=" + enter_code + ", regdate=" + regdate + "]";
	}
}
