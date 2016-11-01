/**
 * javascript from seokgi
 */
//웹소켓
//var webSocketMessage = new WebSocket('ws://localhost:8888/MagisterTest/exclude/broadcastingMessage');
var webSocketHtml = new WebSocket('ws://localhost:8888/mmapTest/exclude/broadcastingHtml');

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
	var div_array = divide.split('#haha');
	console.log(div_array[0])
	
	if(div_array[0] == 'message'){
		receiveMessage("상대 : " + div_array[1]);
	}else if(div_array[0] == 'html'){
		console.log(div_array[1]+":@@@@@onHtml");
		node = JSON.parse(div_array[1]);
		reDraw();
	
	}
};

/*function onHtml(event){
	$('div#canvas').remove();
			$('div#main').append(event.data);
			makeMap($(load));
	console.log(event.data);
	node = JSON.parse(event.data);
	reDraw();
}

function onMessage(event) {
	console.log(event.data+"onMessage")
	$("#messageWindow").append("상대 : " + event.data + "\n");
}*/
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
		webSocketHtml.send("html#haha"+JSON.stringify(node));
	}else{//html의 경우
		
		sendMessage("나 : " + $('#btn-input').val());
		webSocketHtml.send("message#haha"+$('#btn-input').val());
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




