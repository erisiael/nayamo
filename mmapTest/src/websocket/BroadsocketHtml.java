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

	private static HashMap<String, String> allHtml = new HashMap<>();
	private String roomName_web = null;

	private static HashMap<String, STR> rooms = new HashMap<>();



	@OnMessage
	public void onMessage(String canvas, Session session) {
		//분기처리 canvas에 메시지 일경우와 html의 경우
		try{
			//str[0] 내용처리 str[1]내용 str[3] entercode
			String[] str = canvas.split("#haha");
			
			HashMap<Session, String> rom =rooms.get(roomName_web).getSession_list();//arrayList
			//ArrayList<Session> rom = AjaxAction.getRooms().get(roomName_web);//arrayList
			if(str[0].equals("html")){
				////////////////////////////////html
				allHtml.put(roomName_web, "html#haha"+str[1]);
				synchronized (rom) {
					// Iterate over the connected sessions
					// and broadcast the received message
					for (Entry<Session, String> client : rom.entrySet()) {
						
						client.getKey().getBasicRemote().sendText("html#haha"+str[1]+"#haha"+roomName_web);
					}
				}
			}else if(str[0].equals("message")){//message일 경우
				////////////////////////////////message
				synchronized (rom) {
					// Iterate over the connected sessions
					// and broadcast the received message
					for (Entry<Session, String> client : rom.entrySet()) {
						if (!client.getKey().equals(session)) {
							client.getKey().getBasicRemote().sendText("message#haha"+rooms.get(roomName_web).getSession_list().get(session)+"#haha"+str[1]);
						}
					}
				}//synchronized
			}//if
		}catch(IOException e){
			System.out.println("io터짐");
		}

	}

	@OnOpen
	public void onOpen(Session session) {
		//메시지와 html보낼때  필요한 html키값(방이름)
		roomName_web = StrutsAction.getRoomName_web();

			//메시지를 위한 해쉬맵에 세션넣기
			rooms.get(roomName_web).getSession_list().put(session,StrutsAction.getEmail_socket());//session 해당 방에 넣기

		try {
			if(allHtml.get(roomName_web) != null){
				//방별로 저장된 html을 onopen이 될때 보내주는 메소드
				session.getBasicRemote().sendText(allHtml.get(roomName_web)+"#haha"+roomName_web);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@OnClose
	public void onClose(Session session){
		
	}

	public static HashMap<String, STR> getRooms(){
		return rooms;
	}



}