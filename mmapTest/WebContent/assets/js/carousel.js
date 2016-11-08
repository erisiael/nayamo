/**
 * 
 */
$(document).ready(function() {
   /*$(".carousel-inner").swiperight(function() {
       $(this).parent().carousel('prev');
   });
   $(".carousel-inner").swipeleft(function() {
       $(this).parent().carousel('next');
   });*/
	$(".carousel").carousel();
   var modal_carousel = $("#modal-carousel");
   $("#strlist_carousel").on("click", function() {
	   showSTRs(modal_carousel);
	   modal_carousel.modal();
   });
   
});

function showSTRs() {
	$.ajax({
		url : "showSTRs.action",
		success : function(data) {
			console.log(data);
			console.log(data.str_list);
			if (data.str_list.length) {
				setLoader();
				$(".carousel-indicators").children().remove();
				$(".carousel-inner").children().remove();
				for (var index in data.str_list ) {
					var STR_no = data.str_list[index].STR_no;
					var category = data.str_list[index].category;
					var enter_code = data.str_list[index].enter_code;
					var keyword = data.str_list[index].keyword;
					var regdate = data.str_list[index].regdate;
					var svgdata = data.str_list[index].svgdata;
					
					var carouselclone = $(".carousel-template").children("div").clone();
					$(".carousel-inner").append(carouselclone);
					//carouselclone.addClass("item",true);
					carouselclone.attr("class", "item");
					if (index == 0) {
						console.log("클래스 추가");
						carouselclone.attr("class", "item active");
						//carouselclone.addClass("active",true);
					}
					console.log(carouselclone.parent().html());
					carouselclone.children("a").attr("href", "http://203.233.194.218:8888/mmapTest/checkRoom?roomName_web=" + enter_code);
					var caption = carouselclone.children(".carousel-caption");
					caption.children("h3").text(regdate);
					caption.children("p").text(keyword);
					
					
					carouselclone.children("a").children("img").attr("width", "70%");
					carouselclone.children("a").children("img").attr("height", "50%");
					if (svgdata != undefined) {
						carouselclone.children("a").children("img").attr("src", "data:image/svg+xml;UTF-8," + decodeURIComponent(window.atob(svgdata)));
					}
						
					
					var indicator = $(".carousel-template").children("ol").children().clone();
					$(".carousel-indicators").append(indicator);
					indicator.attr("data-slide-to", index);
					indicator.attr("class", "item" + index);
					//indicator.addClass("item" + index ,true);
					if (index == 0) {
						indicator.attr("class", "item" + index + " active");
						//indicator.addClass("active", true);
					}
				}
					//$('.carousel').carousel();
					$('.carousel').children("a.left").off("click");
					$('.carousel').children("a.left").on("click", function(){
				        $(this).parent().carousel("prev");
				    });
					$('.carousel').children("a.right").off("click");
					$('.carousel').children("a.right").on("click", function(){
				        $(this).parent().carousel("next");
				    });
					console.log("카로셀 레뒤");
					console.log($(".carousel").html());
					hideLoader();
			} else {
				alert("아직 만든 생각들이 없습니다!");
			}
		}
	});
}