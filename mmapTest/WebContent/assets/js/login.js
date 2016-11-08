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