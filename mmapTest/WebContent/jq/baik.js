/**
 * javascript from baik
 */
$(document).ready(function(){
	
	var filepath_add_enter = "assets/images/plus-sign2.png";
	var filepath_add_leave = "assets/images/plus-sign1.png";
	var filepath_move_enter = "assets/images/point-location2.png";
	var filepath_move_leave = "assets/images/point-location1.png";
	var filepath_update_enter = "assets/images/writing2.png";
	var filepath_update_leave = "assets/images/writing1.png";
	var filepath_save_enter = "assets/images/save-icon-silhouette2.png";
	var filepath_save_leave = "assets/images/save-icon-silhouette1.png";
	var filepath_load_enter = "assets/images/open-folder-with-document2.png";
	var filepath_load_leave = "assets/images/open-folder-with-document1.png";
	var filepath_auction_enter = "assets/images/businessman-talking-about-money2.png";
	var filepath_auction_leave = "assets/images/businessman-talking-about-money1.png";
	
	var filepath_btnclose_enter = "assets/images/toxic-cross2.png";
	var filepath_btnclose_leave = "assets/images/toxic-cross1.png";
	var filepath_btnupdate_enter = "assets/images/right-tick2.png";
	var filepath_btnupdate_leave = "assets/images/right-tick1.png";
	
	var filepath_enters = [];
	var filepath_leaves = [];
	var buttons = [];
	
	filepath_enters.push(filepath_add_enter);
	filepath_enters.push(filepath_update_enter);
	filepath_enters.push(filepath_move_enter);
	filepath_enters.push(filepath_save_enter);
	filepath_enters.push(filepath_load_enter);
	filepath_enters.push(filepath_auction_enter);
	filepath_enters.push(filepath_btnclose_enter);
	filepath_enters.push(filepath_btnupdate_enter);
	
	filepath_leaves.push(filepath_add_leave);
	filepath_leaves.push(filepath_update_leave);
	filepath_leaves.push(filepath_move_leave);
	filepath_leaves.push(filepath_save_leave);
	filepath_leaves.push(filepath_load_leave);
	filepath_leaves.push(filepath_auction_leave);
	filepath_leaves.push(filepath_btnclose_leave);
	filepath_leaves.push(filepath_btnupdate_leave);
	
	buttons.push('#add');
	buttons.push('#update');
	buttons.push('#move');
	buttons.push('#save');
	buttons.push('#load');
	buttons.push('#auction');
	buttons.push('#btn-close');
	buttons.push('#btn-update');
	
	var setButtons = function(index) {
		$(buttons[index]).attr("src", filepath_leaves[index]);
		$(buttons[index]).css("cursor", "pointer");
		$(buttons[index]).on('mouseenter', function() {
			changeSrc(this, filepath_enters[index]);
		});
		$(buttons[index]).on('mouseleave', function() {
			changeSrc(this, filepath_leaves[index]);
		});
	}
	
	for (var int = 0; int < buttons.length; int++) {
		setButtons(int);
	}
	
	$('#nav').css('text-align', 'right');
	$('#nav').css('background-color', 'rgba(255, 0, 0, 0)');
	$('#toggle').attr('src','assets/images/toggle.png');
	$('#nav').children().attr('width', '60px');
	
	function changeSrc(dom, filename) {
		$(dom).attr('src', filename);
	}
});