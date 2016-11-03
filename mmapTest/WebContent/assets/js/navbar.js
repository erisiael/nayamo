/**
 * 
 */


$(document).ready(function() {
	$("#sidebar-wrapper").click(function(e) {
	    e.preventDefault();
	    if ($(this).attr("class") == "sidebar-wrapper-active") {
			var sidebar = document.getElementById("sidebar-wrapper");
			var menubtn = document.getElementById("menu-toggle");
			var main_icon = document.getElementById("main_icon");
		    if (event.target == sidebar || event.target == menubtn || event.target == main_icon) {
			    $("#sidebar-wrapper").toggleClass("sidebar-wrapper-active");
			    $("#wrapper").toggleClass("active");
		    }
	    } else {
		    $("#sidebar-wrapper").toggleClass("sidebar-wrapper-active");
		    $("#wrapper").toggleClass("active");
	    }
	   
	});
	
		$(".a-scrolling").on('click',function (e) {
		    e.preventDefault();
		    var target = this.hash;
		    var $target = $(target);

		    $('html, body').stop().animate({
		        'scrollTop': $target.offset().top
		    }, 900, 'swing', function () {
		        window.location.hash = target;
		    });
		});
});