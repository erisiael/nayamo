package action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.websocket.Session;

import com.opensymphony.xwork2.ActionSupport;

import vo.Ajaxuser;
import vo.STR;

public class AjaxAction extends ActionSupport {

	private Ajaxuser ajaxuser;
	private String canvas;
	private static String saved;
	private STR str;
	
	/////////////////������		
	
	//ä���� ���� ���̸� -- ������
		private static String roomName;
		private String temp_roomName;
		////////////////////////////////////////////////////////test�� ��
		private ArrayList<Session> sessionList1 = new ArrayList<>();
		private ArrayList<Session> sessionList2 = new ArrayList<>();
		//private static ArrayList<Session> sessionListHtml1 = new ArrayList<>();
		//private static ArrayList<Session> sessionListHtml2 = new ArrayList<>();
		/////////////////////////////////////////////////////////////////////
		//message�� ���� ��
		private static HashMap<String, ArrayList<Session>> rooms = new HashMap<>();
		//html�� websocket���� ������� ������ �����ϱ����� ����
		//private static HashMap<String, ArrayList<Session>> rooms_html = new HashMap<>();
		
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
		public static HashMap<String, ArrayList<Session>> getRooms() {
			return rooms;
		}
		public static void setRooms(HashMap<String, ArrayList<Session>> rooms) {
			AjaxAction.rooms = rooms;
		}
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
	
	public STR getStr() {
		return str;
	}
	public void setStr(STR str) {
		this.str = str;
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
	public String checkRoom(){
		////////////////////test ���� DAO���� ��������ȴ�
		/*ArrayList<Session> t1 = new ArrayList<>();
		ArrayList<Session> t2 = new ArrayList<>();*/
		roomName = getTemp_roomName();//static���δ� ���� �޾ƿ��� ���ؼ� ����
//		roomName_html = getTemp_roomName()+" ";
		//rooms.put("123", t1);
		//rooms.put("234", t2);
		rooms.put("123", sessionList1);
		rooms.put("234", sessionList2);
		//html�� �����ֱ� ���� ���� ��������� html�� ���� �浵 ���θ����
		//
		/*rooms_html.put("123", sessionListHtml1);
		rooms_html.put("234", sessionListHtml2);*/
		//////////////////////
		ArrayList<Session> check = null;
		check = rooms.get(roomName);
		if(check == null){
			System.out.println("�����ϴ� ���� ����");
			return ERROR;
		}
		return SUCCESS;
	}
	//방만들기
	public String newRoom(){
		rooms.put(str.getName(),new ArrayList<Session>());
		
		return SUCCESS;
	}
	
	
	
	@Override
	public String execute() throws Exception {
		System.out.println("canvas : " + canvas);
		return SUCCESS;
	}
	
}
