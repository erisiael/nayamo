/**
 * 
 */
var loadertimer;
function setLoader() {
	$("body").append($("<div class=modal-loader><div class=loader></div></div>"));
	loadertimer = setTimeout(function() {
		alert("server timeout. please try again");
		hideLoader();
	}, 5000);
}
function hideLoader() {
	$("body").children("div.modal-loader").hide();
	clearTimeout(loadertimer);
}