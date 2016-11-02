package action;

import com.opensymphony.xwork2.ActionSupport;

import vo.Ajaxuser;

public class AjaxAction extends ActionSupport {

	private Ajaxuser ajaxuser;
	private String canvas;
	private static String saved;
	
	private static String roomName;
	private String temp_roomName;
	/*
	 * getter setter
	 *  */
	public static String getRoomName() {
		return roomName;
	}
	public static void setRoomName(String roomName) {
		AjaxAction.roomName = roomName;
	}
	public String getTemp_roomName() {
		return temp_roomName;
	}
	public void setTemp_roomName(String temp_roomName) {
		this.temp_roomName = temp_roomName;
	}
	/*public static HashMap<String, ArrayList<Session>> getRooms() {
			return rooms;
		}
		public static void setRooms(HashMap<String, ArrayList<Session>> rooms) {
			AjaxAction.rooms = rooms;
		}*/
	/*public static HashMap<String, ArrayList<Session>> getRooms_html() {
			return rooms_html;
		}
		public static void setRooms_html(HashMap<String, ArrayList<Session>> rooms_html) {
			AjaxAction.rooms_html = rooms_html;
		}*/
	////////////////////

	public Ajaxuser getAjaxuser() {
		return ajaxuser;
	}

	public void setAjaxuser(Ajaxuser ajaxuser) {
		this.ajaxuser = ajaxuser;
	}
	public String getCanvas() {
		return canvas;
	}
	public void setCanvas(String canvas) {
		this.canvas = canvas;
	}
	public static String getSaved() {
		return saved;
	}
	public static void setSaved(String saved) {
		AjaxAction.saved = saved;
	}


	/////////////////////////	








	public String save() throws Exception {
		setSaved(getCanvas());
		return SUCCESS;
	}
	public String load() throws Exception {
		setCanvas(getSaved());
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	

}
