package vo;

public class LETTER {
	private int no;
	private String title;
	private String contents;
	private String from_nick;
	private String nick;
	private String indate;
	private String read;
	
	public LETTER(){}

	public LETTER(int no, String title, String contents, String from_nick, String nick, String indate, String read) {
		super();
		this.no = no;
		this.title = title;
		this.contents = contents;
		this.from_nick = from_nick;
		this.nick = nick;
		this.indate = indate;
		this.read = read;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getFrom_nick() {
		return from_nick;
	}

	public void setFrom_nick(String from_nick) {
		this.from_nick = from_nick;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	@Override
	public String toString() {
		return "LETTER [no=" + no + ", title=" + title + ", contents=" + contents + ", from_nick=" + from_nick
				+ ", nick=" + nick + ", indate=" + indate + ", read=" + read + "]";
	}

	
}
