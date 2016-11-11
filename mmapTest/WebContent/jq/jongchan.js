/**
 * javascript from jongchan
 */
var topnum = 5;
var leftnum = 5;
var saved;

var updatetext;
var updaterange;

/**
 * 1. 추가 버튼을 누르거나, 2. 불러오기 실행 시 실행되는 펑션
 * 1. 의 경우, 한 개의 div를 추가하고, 해당 div에 dblclick 이벤트를 걸어준다.
 * 그리고 dblclick 시 등장할 <div class=div-dialog>를 생성하고 추가한다.
 * 2. 의 경우 불러오기 완료 후 모든 <div class=div-map>에 위의 행동을 한다.
 */
/*var mapdialog = $('<div class=div-dialog><input type=text><br><span id=rangevalue></span><input type=range><br><img class=btn-update id=btn-update title=입력></img>&nbsp;<img class=btn-close id=btn-close title=닫기></img></div>');
var dialogclosebtn = mapdialog.children().filter('img.btn-close');*/
var modal = $("div.modal-div");

$('window').ready(function() {

	/*$('div.div-dialogs').append(mapdialog);*/
	
	/**
	 * modal window
	 */
	modal.css('display', 'none');
	
	$('img#img-add').on('click', function() {
		addData("새로운 노드");
	});
	 
	$('img#img-save').click(function() { // 저장 버튼의 이벤트
		send("html");
		send("svg");
	});
	
	$('img#img-move').click(function() {
		setViewpoint();
	});
	$('img#img-delete').click(function() {
		if (!isSelected()) {
			return;
		}
		if (!isRoot()) {
			deleteData();
		} else {
			alert("최상위 노드는 삭제할 수 없습니다.");
		}
	});
	$('img#img-auction').click(function() {
		alert("차후 구현 예정입니다");
	});
	$('img#img-keyword').click(function() {
		if (!isSelected()) {
			alert("먼저 노드를 선택해주세요.");
			return;
		}
		$.ajax({
			url : "searchKeyword.action",
			data : {
				keyword : getNode().text
			},
			success : function(data) {
				
				var keyword = "from... " + data.keyword;
				var word1 = data.result[0];
				var word2 = data.result[1];
				var word3 = data.result[2];
				var datas = [keyword, word1, word2, word3];
				
				$('.sub-menu').children().children('a').each(function(index, data) {
					if (index == 0) {
						$(data).attr('href', '#');
						$(this).text(datas[index]);
					} else {
						$(data).attr('href', 'javascript:addData("'+ datas[index] +'");');
						$(this).text("#"+ index +" -> " + datas[index]);
					}
				});
				
				$('.open').trigger('click');
			}
		});
	});
	
	var url = document.location.toString().split("?roomName_web=")[0] + "?roomName_web=";
	
	$('img#img-adduser').click(function() {
		var urldialog = $(".div-urldialog");
		connectModal(urldialog.parent().parent());
		urldialog.children().children("span#span-entercode").text(entercode);
		urldialog.children("a#a-entercode").on("click", function() {
			clipboard.copy(entercode);
			alert("복사되었습니다.");
		});
		urldialog.children("a#a-enterurl").on("click", function() {
			clipboard.copy({"text/plain": url + entercode});
			alert("복사되었습니다.");
		});
	});
	
	var rangelistener = function() {
		var values = updaterange.val();
		mapdialog.children().filter('span#rangevalue').text(values);
	}
	
	var keyword = $("<span></span>");
	updatetext = $("<input type=text>");
	var range = $("<span></span>");
	updaterange = $("<input type=range>");
	var btnupdate = $("<img id=btn-update>");
	var btnclose = $("<img id=btn-close>");
	
	$("footer").append("<div class=div-footer>");
	$("footer").children("div").append(keyword);
	$("footer").children("div").append(updatetext);
	$("footer").children("div").append(range);
	$("footer").children("div").append(updaterange);
	$("footer").children("div").append(btnupdate);
	$("footer").children("div").append(btnclose);
	
	keyword.text("Keyword : ");
	range.text("Size : ");
	updaterange.attr('min', '10');
	updaterange.attr('max', '200');
	
	btnupdate.on('click', function() {
		if (isSelected) {
			if (isRoot()) {
				alert("최상위 노드는 수정할 수 없습니다");
			} else {
				var text = updatetext.val();
				var values = updaterange.val();
				editData(text, values);
			}
		}
	});
	var isFocused;
	$("footer").hover(function() {
		$(this).addClass('in');
	}, function() {
		if (!isFocused) {
			$(this).removeClass('in');
		}
	});
	updatetext.focusin(function() {
		isFocused = true;
		$(this).parent().parent().addClass('in');
	});
	updatetext.on('focusout', function() {
		isFocused = false;
		$(this).parent().parent().removeClass('in');
	});
	updatetext.on('keypress', function(event) {
		if (event.which == 13) {
			btnupdate.trigger("click");
		}
	});
	btnclose.on('click', function() {
		if (isSelected()) {
			updatetext.val(getNode().text);
			updaterange.val(getNode().values);
		}
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

function renewFooter(text, range) {
	updatetext.val(text);
	updaterange.val(range);
}