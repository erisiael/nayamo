/**
 * javascript from seokgi
 */
//웹소켓
//var webSocketMessage = new WebSocket('ws://localhost:8888/MagisterTest/exclude/broadcastingMessage');
var webSocketHtml = new WebSocket('ws://'+ location.host + '/mmapTest/exclude/broadcastingHtml');

var entercode = document.getElementById("entercode").value;
var username = document.getElementById("username").value;
var splicekey = "#cmamxlsk#";

var svg;

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
	var divide = event.data;
	var div_array = divide.split(splicekey);
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
		break;
	case 'initializeClient':
		if (username == '') {
			username = 'guest';
		}
		$(document).ready(function() {
			node = JSON.parse(div_array[1]);
			reDraw();
			send("svg");
		});
		webSocketHtml.send("initializeClient" + splicekey + username);
		hideLoader();
		console.log("initializeClient");
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
	switch (kind) {
	case "html":
		sendBuffer(JSON.stringify(node), "html");
		break;
	case "svg":
		var svgxml = d3.select("svg")
					.attr("version", 1.1)
					.attr("xmlns", "http://www.w3.org/2000/svg")
					.node().parentNode.innerHTML;
		var imgsrc = window.btoa(encodeURIComponent(svgxml));
		sendBuffer(imgsrc, "svg");
		break;
	case "message":
		sendMessage("나 : " + $('#btn-input').val());
		webSocketHtml.send("message" + splicekey + $('#btn-input').val());
		$('#btn-input').val("");
		break;
	default:
		break;
	}
}

function sendBuffer(data, key) {
	webSocketHtml.send(key + "_start" + splicekey + "#start of "+ key +"#");
	var buffer;
	console.log("sendBuffer about " + key);
	do {
		buffer = data.substr(0, 2048);
		data = data.substring(2048, data.length);
		webSocketHtml.send(key + "_data" + splicekey + buffer);
	} while (buffer.length == 2048);
	webSocketHtml.send(key + "_end" + splicekey + "#end of "+ key + "#");
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




