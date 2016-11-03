/**
 * javascript from seokgi
 */
//웹소켓
//var webSocketMessage = new WebSocket('ws://localhost:8888/MagisterTest/exclude/broadcastingMessage');
var webSocketHtml = new WebSocket('ws://localhost:8888/mmapTest/exclude/broadcastingHtml');

var entercode = document.getElementById("entercode").value;
var username = document.getElementById("username").value;
var splicekey = "#cmamxlsk#";

/*webSocketMessage.onerror = function(event) {
	onError(event)
};*/
webSocketHtml.onerror = function(event) {
	
};


/*webSocketMessage.onopen = function(event) {
	onOpen(event)
};*/
webSocketHtml.onopen = function(event) {
	onOpen("message");
};


/*webSocketMessage.onmessage = function(event) {
	onMessage(event)
};*/
webSocketHtml.onmessage = function(event) {
	//message받을때와 html받을떄 분기처리
	console.log(typeof event.data);
	
	var divide = event.data;
	var div_array = divide.split(splicekey);
	console.log(div_array[0]+div_array[1]+div_array[2])
	switch (div_array[0]) {
	case 'message':
		receiveMessage(div_array[1]+" : " + div_array[2]);
		break;
	case 'html':
		node = JSON.parse(div_array[1]);
		reDraw();
		break;
	case 'initializeServer':
		webSocketHtml.send("initializeServer" + splicekey + entercode);
		console.log("ini server");
		break;
	case 'initializeClient':
		if (username == '') {
			username = 'guest';
		}
		$(document).ready(function() {
			node = JSON.parse(div_array[1]);
			console.log("웹소켓의 노드다 :" + node);
			reDraw();
		});
		webSocketHtml.send("initializeClient" + splicekey + username);
		console.log("ini client");
		break;
	default:
		break;
	}
};

function onOpen(event) {
	if(event == "message"){
		$(document).ready(function() {
			receiveMessage("연결 성공");
		});
	}
}
function onError(event) {
	alert(event.data);
}

//메시지를 위한 
function send(kind) {
	//메시지를 받을경우 String 앞줄에 message, HTML일때는 Html을 붙여서 가져오기
	if(kind == "html"){//메시지의 경우
		webSocketHtml.send("html" + splicekey +JSON.stringify(node));
	}else{//html의 경우
		
		sendMessage("나 : " + $('#btn-input').val());
		console.log("채팅내용" + $('#btn-input').val());
		webSocketHtml.send("message" + splicekey + $('#btn-input').val());
		$('#btn-input').val("");
		
	}
}
//html을 위한
/*function sendHtml(){
			console.log(saved);
			webSocketHtml.send($('div#main').html());
		}*/

function disconnect(){
	alert("종료");
	
	webSocketHtml.close();
	$(window).close();
}




