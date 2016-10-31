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

@ServerEndpoint("/exclude/broadcastingHtml")
public class BroadsocketHtml{

	private static HashMap<String, String> allHtml = new HashMap<>();
	private String roomName_web = null;
	
	
	
	

	@OnMessage
	public void onMessage(String canvas, Session session) {
		//분기처리 canvas에 메시지 일경우와 html의 경우
		try{
		System.out.println(canvas);
		//str[0] 내용처리 str[1]내용 str[3] entercode
		String[] str = canvas.split("#haha");

		ArrayList<Session> rom = AjaxAction.getRooms().get(roomName_web);//arrayList
		if(str[0].equals("html")){
			////////////////////////////////html
			System.out.println(roomName_web + "          canvas");
			allHtml.put(roomName_web, "html#haha"+str[1]);
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
			System.out.println(AjaxAction.getRooms().containsKey(roomName_web));
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
		//������ message�ϰ� �ٸ��� ���������.
		//messageó�� ����ƽ�� �� �ϳ� �ҷ��� ����
		// Add session to the connected sessions set
		System.out.println("afdhkafjlsdajflkdsajflkdsjalkf");
		System.out.println(session);
		//clients.add(session);
		//static ���� ������ roomName���� �����ͼ� string ���� ����?
		roomName_web = AjaxAction.getRoomName();

		System.out.println(roomName_web+"!     html Onopen");
		//System.out.println(AjaxAction.getRooms_html().get(roomName_web));
		//같은 세션이 들어가면 헤쉬맵을 두개 만들필요가 없을듯?????????????
		//메시지를 위한 해쉬맵에 세션넣기
		AjaxAction.getRooms().get(roomName_web).add(session);//session 해당 방에 넣기
		System.out.println("객체 확인"+AjaxAction.getRooms().get(roomName_web));
		//html을 위한 해쉬멥에 세션넣기
		//AjaxAction.getRooms_html().get(roomName_web).add(session);



		try {
			if(allHtml.get(roomName_web) != null){
				System.out.println(allHtml.get(roomName_web)+"가져오는곳");
				session.getBasicRemote().sendText(allHtml.get(roomName_web));
			}
			System.out.println("�ȵ�" + allHtml.size());
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
		forClose = AjaxAction.getRooms().get(roomName_web);
		AjaxAction.getRooms().get(roomName_web).remove(session);
		//방사람들이 다 나갔을 때 html지우기	
		if(forClose.size() == 0){
			allHtml.remove(roomName_web);
		}
	
	}
	
}