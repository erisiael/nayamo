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
var mapdialog = $('<div class=div-dialog><input type=text><br><span id=rangevalue></span><input type=range><br><img class=btn-update id=btn-update title=입력></img>&nbsp;<img class=btn-close id=btn-close title=닫기></img></div>');
var dialogclosebtn = mapdialog.children().filter('img.btn-close');
var modal = $("div.modal-div");

var urldialog = $('<div class=div-urldialog><span></span></div>');

$('window').ready(function() {

	$('div.div-dialogs').append(mapdialog);
	$('div.div-urldialogs').append(urldialog);
	
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
	mapdialog.children().filter('input[type=text]').on('keypress', function(event) {
		if (event.which == 13) {
			$(this).parent().children().filter("img.btn-update").trigger("click");
		}
	});

	mapdialog.children().filter('img.btn-update').on('click', function() { // 입력 버튼에 이벤트를 추가
		if (isRoot()) {
			alert("최상위 노드는 수정할 수 없습니다");
		} else {
			var text = mapdialog.children().filter('input[type=text]').val();
			var values = mapdialog.children().filter('input[type=range]').val();
			editData(text, values);
			dialogclosebtn.trigger('click');
		}
	});
	
	/**
	 * modal window
	 */
	modal.css('display', 'none');
	
	$('img#add').on('click', function() {
		
		addData("새로운 노드");
	});
	 
	$('img#save').click(function() { // 저장 버튼의 이벤트
		send("html");
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
			}
		});
	});
	
	$('img#update').click(function() {
		if (!isSelected()) {
			return;
		}
		mapdialog.children().filter('input[type=text]').val(getNode().text);
		mapdialog.children().filter('input[type=range]').val(getNode().values);
		rangelistener();
		connectModal($(mapdialog).parent().parent());
	});
	
	$('img#move').click(function() {
		setViewpoint();
	});
	$('img#delete').click(function() {
		if (!isSelected()) {
			return;
		}
		if (!isRoot()) {
			deleteData();
		} else {
			alert("최상위 노드는 삭제할 수 없습니다.");
		}
	});
	$('img#auction').click(function() {
		alert("차후 구현 예정입니다");
	});
	
	var url = document.location.toString().split("?roomName_web=")[0] + "?roomName_web=";
	var urlinput = $("<input type=text readonly=readonly />");
	
	$('img#adduser').click(function() {
		connectModal(urldialog.parent().parent());
		urldialog.children("span").text("Entercode : " + entercode);
		urldialog.append(urlinput);
		urldialog.children("input").prop("value", url + entercode);
		console.log("urlinput : " + document.location);
	});

	
});

function connectModal(dom) {
	dom.css('display', 'block');
	var isModal = document.getElementById(dom.attr("id"));
	var func = function() {
		if (event.target == isModal) {
			dom.css('display', 'none');
			dom.off("click", this);
		}
	};
	dom.on("click", func);
}
