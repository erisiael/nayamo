package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map;



import com.opensymphony.xwork2.ActionSupport;

import vo.Ajaxuser;
import websocket.BroadsocketHtml;

public class AjaxAction extends ActionSupport {

	private Ajaxuser ajaxuser;
	private String canvas;
	private static String saved;
	

	/////////////////������		

	//ä���� ���� ���̸� -- ������
	private static String roomName;
	private String temp_roomName;
	////////////////////////////////////////////////////////test�� ��
	//private ArrayList<Session> sessionList1 = new ArrayList<>();
	//private ArrayList<Session> sessionList2 = new ArrayList<>();
	//private static ArrayList<Session> sessionListHtml1 = new ArrayList<>();
	//private static ArrayList<Session> sessionListHtml2 = new ArrayList<>();
	/////////////////////////////////////////////////////////////////////
	//message�� ���� ��
	//private static HashMap<String, ArrayList<Session>> rooms = new HashMap<>();
	//html�� websocket���� ������� ������ �����ϱ����� ����
	//private static HashMap<String, ArrayList<Session>> rooms_html = new HashMap<>();

	
	//ä���� ���� ���̸� -- ������
	
	
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
	
	/*public String emailChk() throws Exception
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
	}*/
	
	


	@Override
	public String execute() throws Exception {
		System.out.println("canvas : " + canvas);
		return SUCCESS;
	}
	

}
