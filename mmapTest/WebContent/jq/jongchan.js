/**
 * javascript from jongchan
 */
var topnum = 5;
var leftnum = 5;
var saved;

/**
 * 1. 추가 버튼을 누르거나, 2. 불러오기 실행 시 실행되는 펑션
 * 1. 의 경우, 한 개의 div를 추가하고, 해당 div에 dblclick 이벤트를 걸어준다.
 * 그리고 dblclick 시 등장할 <div class=div-dialog>를 생성하고 추가한다.
 * 2. 의 경우 불러오기 완료 후 모든 <div class=div-map>에 위의 행동을 한다.
 */
/*function makeMap() {
	var mapclone;
	if (arguments.length != 0) { // 매개변수의 유무에 따라 펑션의 실행이 달라진다
		mapclone = $('div.div-map'); // 매개변수가 있다면 로드된 모든 <div class=div-map>
		console.log('매개변수있다');
		console.log(mapclone);
	} else {
		mapclone = $('<div class=div-map><span>내용을 입력하세요.</span></div>'); // 없으면 한개의 div를 생성 
		console.log('매개변수없다');
	}
	// 다이얼로그
	 var mapdialog = $('<div class=div-dialog><input type=text><button class=btn-update>입력</button><button class=btn-close>닫기</button></div>'); 
	var mapdialog = $('<div class=div-dialog><input type=text><br><img class=btn-update src=image/pencil.png title=입력></img>&nbsp;<img class=btn-close src=image/close-cross.png title=닫기></img></div>');
	
	mapclone.each(function(index, data) { // each start
		$(data).on('dblclick', function() { // 더블 클릭 이벤트
			$(dialogclone).css('display', 'block');
		});
		var dialogclone = mapdialog.clone();
		$('div.div-dialogs').append(dialogclone);
		dialogclone.children().filter('img.btn-close').on('click', function() { // 닫기 버튼에 이벤트를 추가
			$(this).parent().css('display', 'none');
			dialogclone.children().filter('input').val('');
		});
		dialogclone.children().filter('img.btn-update').on('click', function() { // 입력 버튼에 이벤트를 추가
			var text = dialogclone.children().filter('input').val();
			$(data).find('span').text(text);
			dialogclone.children().filter('img.btn-close').trigger('click');
		});
	}); // each end
	return mapclone; 
}*/
var mapdialog = $('<div class=div-dialog><input type=text><br><span id=rangevalue></span><input type=range><br><img class=btn-update src=assets/images/pencil.png title=입력></img>&nbsp;<img class=btn-close src=assets/images/close-cross.png title=닫기></img></div>');
var dialogclosebtn = mapdialog.children().filter('img.btn-close');
var modal = $("div.modal-div");

$('window').ready(function() {

	$('div.div-dialogs').append(mapdialog);
	mapdialog.draggable();
	
	var rangelistener = function() {
		var values = mapdialog.children().filter('input[type=range]').val();
		mapdialog.children().filter('span#rangevalue').text(values);
	}
	
	mapdialog.children().filter('input[type=range]').attr('min', '10');
	mapdialog.children().filter('input[type=range]').attr('max', '200');
	mapdialog.children().filter('input[type=range]').on('change', rangelistener);
	
	dialogclosebtn.on('click', function() { // 닫기 버튼에 이벤트를 추가
		//$(this).parent().css('display', 'none');
		modal.css('display','none');
		mapdialog.children().filter('input[type=text]').val('');
	});
	mapdialog.children().filter('img.btn-update').on('click', function() { // 입력 버튼에 이벤트를 추가
		var text = mapdialog.children().filter('input[type=text]').val();
		var values = mapdialog.children().filter('input[type=range]').val();
		editData(text, values);
		dialogclosebtn.trigger('click');
	});
	
	/**
	 * modal window
	 */
	modal.css('display', 'none');
	
	$('img#add').on('click', function() {
		
		/*var mapclone = makeMap();
		$('#canvas').append(mapclone);*/
		
		//var mapclone = $('<div class=div-map><span>내용을 입력하세요.</span></div>');
		
 		/* var dialogclone = mapdialog.clone();
		$('div.div-dialogs').append(dialogclone);

		
		mapclone.on('dblclick', function() {
				$('div.div-dialog').css('display', 'block');
		});
	
		 dialogclone.children().filter('button.btn-close').on('click', function() {
			$(this).parent().css('display', 'none');
			dialogclone.children().filter('input').val('');
		});
		dialogclone.children().filter('button.btn-update').on('click', function() {
			var text = dialogclone.children().filter('input').val();
			mapclone.find('span').text(text);
			dialogclone.children().filter('button.btn-close').trigger('click');
		}); */
		
		/*// 추가할 때마다 5px씩 우/하단으로 다음 위치를 조정해주는 코드
		var top = parseInt(mapclone.css('top').split('p')[0]) + topnum; 
		var left = parseInt(mapclone.css('left').split('p')[0]) + leftnum;
		topnum += 5;
		leftnum += 5;
		mapclone.css('top', top + 'px');
		mapclone.css('left', left + 'px');*/
		addData("테스트용");
	});
	 
	$('img#save').click(function() { // 저장 버튼의 이벤트
		/*saved = $('div#main').html(); // div#main 내부의 html 코드를 string 형태로 저장한다.*/
		/*console.log(JSON.stringify(node));
		$.ajax({
			url : 'save.action',
			dataType: 'json',
			data : {'canvas' : JSON.stringify(node)},
			success : function(data) {
				console.log(data);
			}
		});*/
		send("html");
		//webSocketHtml.send(JSON.stringify(node));
	});
	
	$('img#load').click(function() { // 불러오기 버튼의 이벤트
		$.ajax({
			url : 'load.action',
			dataType: 'json',
			success : function(data) {
				//데이터를 불러오기, 그리고 새로 그리기 웹소켓에 그대로 들어가야함
				node = JSON.parse(data.canvas);
				reDraw();
				// 여기까지 불러오기
				
				
				/*$('div#canvas').remove(); // 일단 캔버스를 제거하고
				$('div#main').append(load); // 서버에서 불러온 html 태그를 추가한 후
				makeMap($(load)); // 이벤트를 추가한다. */
			}
		});
	});
	
	$('img#update').click(function() {
		if (!isSelected()) {
			return;
		}
		rangelistener();
		mapdialog.children().filter('input[type=text]').val(getNode().text);
		$(mapdialog).css('display', 'block');
		connectModal();
	});
	
	$('img#move').click(function() {
		setViewpoint();
	});
	$('img#auction').click(function() {
		alert("차후 구현 예정입니다");
	});
	
});

function showModal() {
	$("div.modal-div").css('display', 'block');
}
function connectModal() {
	showModal();
	var func = function() {
		var isModal = document.getElementById("modal-content");
		if (event.target == isModal) {
			modal.css('display','none');
			$("div.modal-div").off("click", this);
		}
	};
	$("div.modal-div").on("click", func);
}
