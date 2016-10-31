package vo;

public class SKA_LIST 
{
	private int SKA_l_no;
	private int SKA_no;
	private int OKS_no;
	private String comt;
	private String regdate;
	
	public SKA_LIST() {
		// TODO Auto-generated constructor stub
	}

	public SKA_LIST(int sKA_l_no, int sKA_no, int oKS_no, String comt, String regdate) {
		super();
		SKA_l_no = sKA_l_no;
		SKA_no = sKA_no;
		OKS_no = oKS_no;
		this.comt = comt;
		this.regdate = regdate;
	}

	public int getSKA_l_no() {
		return SKA_l_no;
	}

	public void setSKA_l_no(int sKA_l_no) {
		SKA_l_no = sKA_l_no;
	}

	public int getSKA_no() {
		return SKA_no;
	}

	public void setSKA_no(int sKA_no) {
		SKA_no = sKA_no;
	}

	public int getOKS_no() {
		return OKS_no;
	}

	public void setOKS_no(int oKS_no) {
		OKS_no = oKS_no;
	}

	public String getComt() {
		return comt;
	}

	public void setComt(String comt) {
		this.comt = comt;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "SKA_LIST [SKA_l_no=" + SKA_l_no + ", SKA_no=" + SKA_no + ", OKS_no=" + OKS_no + ", comt=" + comt
				+ ", regdate=" + regdate + "]";
	}
}
