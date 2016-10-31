package vo;

public class SKA 
{
	private int SKA_no;
	private String OKS_email;
	private String name;
	private int pointl;
	private int winner;
	private String date;
	
	public SKA() {
		// TODO Auto-generated constructor stub
	}

	public SKA(int sKA_no, String oKS_email, String name, int pointl, int winner, String date) {
		super();
		SKA_no = sKA_no;
		OKS_email = oKS_email;
		this.name = name;
		this.pointl = pointl;
		this.winner = winner;
		this.date = date;
	}

	public int getSKA_no() {
		return SKA_no;
	}

	public void setSKA_no(int sKA_no) {
		SKA_no = sKA_no;
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

	public int getPointl() {
		return pointl;
	}

	public void setPointl(int pointl) {
		this.pointl = pointl;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "SKA [SKA_no=" + SKA_no + ", OKS_email=" + OKS_email + ", name=" + name + ", pointl=" + pointl
				+ ", winner=" + winner + ", date=" + date + "]";
	}
}
