/**
 * 
 */
$(document).ready(function() {
	
	var sent = $(".chat-templete").children(".base_sent");
	var receive = $(".chat-templete").children(".base_receive");
	
	$(document).on('click', '.panel-heading span.icon_minim', function (e) {
	    var $this = $(this);
	    if (!$this.hasClass('panel-collapsed')) {
	        $this.parents('.panel').find('.panel-body').slideUp();
	        $this.addClass('panel-collapsed');
	        $this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
	    } else {
	        $this.parents('.panel').find('.panel-body').slideDown();
	        $this.removeClass('panel-collapsed');
	        $this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
	    }
	});
	$(document).on('focus', '.panel-footer input.chat_input', function (e) {
	    var $this = $(this);
	    if ($('#minim_chat_window').hasClass('panel-collapsed')) {
	        $this.parents('.panel').find('.panel-body').slideDown();
	        $('#minim_chat_window').removeClass('panel-collapsed');
	        $('#minim_chat_window').removeClass('glyphicon-plus').addClass('glyphicon-minus');
	    }
	});
	$(document).on('click', '#new_chat', function (e) {
	    var size = $( ".chat-window:last-child" ).css("margin-left");
	     size_total = parseInt(size) + 400;
	    alert(size_total);
	    var clone = $( "#chat_window_1" ).clone().appendTo( ".container" );
	    clone.css("margin-left", size_total);
	});
	$(document).on('click', '.icon_close', function (e) {
	    //$(this).parent().parent().parent().parent().remove();
	    $( "#chat_window_1" ).remove();
	});
	
	var appendMessage = function(text, type) {
		var msg;
		if (type == 'sent') {
			msg = sent.clone();
		} else if (type == 'receive') {
			msg = receive.clone();
		}
		$(".msg_container_base").append(msg);
		msg.children().children().children("p").text(text);
		console.log(msg.children().children().children("p"));
	}
	
	$("#btn-chat").on("click", function() {
		appendMessage("테스트용메세지", "receive");
	});
	$("#btn-input").on('keypress', function(event) {
		if (event.which == 13) {
			$("#btn-chat").trigger("click");
		}
	});
	

});