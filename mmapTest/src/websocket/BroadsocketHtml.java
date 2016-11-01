package websocket;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
			System.out.println(canvas);
			//str[0] 내용처리 str[1]내용 str[3] entercode
			String[] str = canvas.split("#haha");
			
			System.out.println(rooms.size()+"       onMessage size");
			ArrayList<Session> rom =rooms.get(roomName_web).getSession_list();//arrayList
			//ArrayList<Session> rom = AjaxAction.getRooms().get(roomName_web);//arrayList
			if(str[0].equals("html")){
				////////////////////////////////html
				System.out.println(roomName_web + "          canvas");
				allHtml.put(roomName_web, "html#haha"+str[1]);
				System.out.println("체크 나는 새로 방을 만든다?");
				System.out.println(allHtml.size()+"html?");
				//ArrayList<Session> rom = AjaxAction.getRooms_html().get(roomName_web);//arrayList
				System.out.println(rom);
				synchronized (rom) {
					// Iterate over the connected sessions
					// and broadcast the received message
					for (Session client : rom) { 
						System.out.println("들어오냐?");
						System.out.println(str[1]);
						client.getBasicRemote().sendText("html#haha"+str[1]);
					}
				}
			}else if(str[0].equals("message")){//message일 경우
				////////////////////////////////message
				System.out.println(str[1]+"else if (message)");
				System.out.println(roomName_web+"      messageRoom");
				//allMessage.add(message);
				//�濡 �ִ� ����� �ҷ�����
				//$$$�濡 �ִ� ����� �������´�
				//ArrayList<Session> rom = AjaxAction.getRooms().get(roomName_web);//arrayList
				//System.out.println(AjaxAction.getRooms().containsKey(roomName_web));
				System.out.println(rom.size());
				/*for(Session session1 : rom){
											int i = 0;
											System.out.println(session1+"    "+i);
											i++;
										}*/
				synchronized (rom) {
					// Iterate over the connected sessions
					// and broadcast the received message
					for (Session client : rom) {
						//�����״� ���� ����

						if (!client.equals(session)) {
							System.out.println(str[1]+"session equlals");
							client.getBasicRemote().sendText("message#haha"+str[1]);
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
		System.out.println("어디가 먼저?222");
		System.out.println(session);
		//clients.add(session);

		//메시지와 html보낼때  필요한 html키값(방이름)
		roomName_web = StrutsAction.getRoomName_web();
		System.out.println(roomName_web);


			//메시지를 위한 해쉬맵에 세션넣기
			rooms.get(roomName_web).getSession_list().add(session);//session 해당 방에 넣기


		System.out.println("객체 확인"+rooms.size());

		//html을 위한 해쉬멥에 세션넣기
		//AjaxAction.getRooms_html().get(roomName_web).add(session);
		//종찬형
		// 세션을 vo에 집어넣기 테스트 시작
		// 코드 이대로 쓰면 안되고 수정 필수
		/*if (!rooms.containsKey(roomName_web)) {
			rooms.put("testkey", new STR());
		}
		rooms.get("testkey").getSession_list().add(session);
		System.out.println("맵 내부의 어레이" + rooms.get("testkey").getSession_list());*/
		// 세션을 vo에 집어넣기 테스트 종료
		//



		try {
			if(allHtml.get(roomName_web) != null){
				//방별로 저장된 html을 onopen이 될때 보내주는 메소드
				System.out.println(allHtml.get(roomName_web)+"가져오는곳");
				session.getBasicRemote().sendText(allHtml.get(roomName_web));
			}
			System.out.println("html" + allHtml.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@OnClose
	public void onClose(Session session){
		System.out.println(session+"종료!!");
		// Remove session from the connected sessions set
		ArrayList<Session> forClose = null;

		//forClose = AjaxAction.getRooms().get(roomName_web);
		//AjaxAction.getRooms().get(roomName_web).remove(session);
		//방사람들이 다 나갔을 때 html지우기	
		if(forClose.size() == 0){
			allHtml.remove(roomName_web);
		}

	}

	public static HashMap<String, STR> getRooms(){
		return rooms;
	}



}