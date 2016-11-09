/**
 * 
 */
$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});

function register()
{
   if(document.getElementById('pw1').value == document.getElementById('pw2').value)
   {
      document.getElementById('register-form').submit();
      return true;
   }
   else
   {
      alert('비밀 번호를 다시 확인해라. 사요나라');
      document.getElementById('pw1').focus();
      return false;
   }
} 

/*function writeLetter(){
	$('#writeLetter').submit();
}*/

$(document).ready(function()
{
	$('#emailDupChk').on('click', function(){
		Duli();
	});
	function Duli()
	{
		var emaVal = $('#EmailDupVal').val();
		
		$.ajax({
			url : 'emailChk',
			type : 'post',
			data : {
				"oks.email" : emaVal
			},
			success : function(data)
			{
				if(data.dupChk == false)
				{
					$('#h5_id').text("중복된 이메일이 존재한다");
				}//if
				else
				{
					$('#h5_id').text("이메일 사용 가능");
					$('#emailDupChk').text('사용하기');
					$('#emailDupChk').on('click', function()
					{
						$('#email').attr('value', emaVal);
						$('#closeBtn').trigger('click');
						$('#emailDupChk').text('검색');
					});	
				}//else
			}// success
		});//ajax
	}
});   

/*function writeLetter_clk(){
	document.getElementById('writeLetter').submit();
}*/ 

$(document).ready(function() {
	$('#writeLetter').on('click',function() {
		
		var nick = $('#nick').val();
		var title = $('#title1').val();
		var contents = $('#contents').val();
		
		$.ajax({
			url : 'writeLetter',
			type : 'post',
			data : {
				"nick" : nick,
				"title" : title,
				"contents" : contents
			},
			success : function(data) {
				alert('전송 완료');
			}// success
		});//ajax
	});
});

$(document).ready(function() {
	$('#readLetter').on('click',function() {
		var no = $(this).parent().parent().children().first().text();
		$.ajax({
			url : 'readLetterForm',
			type : 'post',
			data : {
				"no" : no
			},
			success : function(data) {
				alert('읽기 완료');
					$('#letter_nick').text(data.letter.from_nick);
					$('#letter_indate').text(data.letter.indate);
					$('#letter_title').text(data.letter.title);
					$('#letter_contents').text(data.letter.contents);
			}// success
			, error: function() {
				alert("error");
			}
		});//ajax
	});
});