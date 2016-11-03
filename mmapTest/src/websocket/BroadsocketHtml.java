package websocket;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import action.AjaxAction;
import action.StrutsAction;
import vo.STR;

@ServerEndpoint("/exclude/broadcastingHtml")
public class BroadsocketHtml{

	private String roomName_web = null;

	private static HashMap<String, STR> rooms = new HashMap<>();

	private String splicekey = "#cmamxlsk#";
	private static int guest = 0;


	@OnMessage
	public void onMessage(String canvas, Session session) {
		//분기처리 canvas에 메시지 일경우와 html의 경우
		System.out.println("canvas : " + canvas);
		try{
			//str[0] 내용처리 str[1]내용 str[3] entercode
			String[] str = canvas.split(splicekey);
			
			HashMap<Session, String> rom = null;
			synchronized (rooms) {
				if (roomName_web != null) {
					rom = rooms.get(roomName_web).getSession_list();//arrayList
				}
			}
			//ArrayList<Session> rom = AjaxAction.getRooms().get(roomName_web);//arrayList
			
			switch (str[0]) {
			case "html":
				synchronized (rooms) {
					rooms.get(roomName_web).setSavepath("html" + splicekey + str[1]);
					// Iterate over the connected sessions
					// and broadcast the received message
					for (Entry<Session, String> client : rom.entrySet()) {
						
						client.getKey().getBasicRemote().sendText("html" + splicekey +str[1]+ splicekey +roomName_web);
					}
				}
				break;
			case "message":
				synchronized (rom) {
					// Iterate over the connected sessions
					// and broadcast the received message
					for (Entry<Session, String> client : rom.entrySet()) {
						if (!client.getKey().equals(session)) {
							client.getKey().getBasicRemote().sendText("message" + splicekey +rooms.get(roomName_web).getSession_list().get(session)+ splicekey +str[1]);
						}
					}
				}//synchronized
				break;
			case "initializeServer":
				roomName_web = str[1];
				synchronized (rooms) {
					session.getBasicRemote().sendText("initializeClient" + splicekey + rooms.get(roomName_web).getSavepath());
				}
				break;
			case "initializeClient":
				String[] div_username = null;
				if (str[1].equals("guest")) {
					div_username = new String[1];
					div_username[0] = "guest" + guest;
					guest++;
				} else {
					div_username = str[1].split("@");
				}
				rooms.get(roomName_web).getSession_list().put(session, div_username[0]);//session 해당 방에 넣기
				System.out.println("initialized");
				break;
			default:
				break;
			}
		}catch(IOException e){
			
		}

	}

	@OnOpen
	public void onOpen(Session session) {
		//메시지와 html보낼때  필요한 html키값(방이름)
			//메시지를 위한 해쉬맵에 세션넣기

		try {
//			if(allHtml.get(roomName_web) != null){
				//방별로 저장된 html을 onopen이 될때 보내주는 메소드
//				session.getBasicRemote().sendText(allHtml.get(roomName_web)+ splicekey + roomName_web);
				session.getBasicRemote().sendText("initializeServer" + splicekey + "requestEntercode");
	//		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@OnClose
	public void onClose(Session session){
		// outer if의 조건문은 DB에 roomName_web이 있느냐? 로 차후 수정되어야 한다.
		if (rooms.get(roomName_web).getSession_list().size() != 1) {
			if (rooms.get(roomName_web).getSession_list().containsKey(session)) {
				synchronized (rooms) {
					rooms.get(roomName_web).getSession_list().remove(session);
					System.out.println("rooms 내부 str의 세션 삭제 완");
				}
			}
		} else {
			synchronized (rooms) {
				rooms.remove(roomName_web);
				System.out.println("rooms의 세션 삭제 완");
			}
		}
	}

	public static HashMap<String, STR> getRooms(){
		return rooms;
	}



}