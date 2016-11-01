<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="assets/script/jquery-3.1.1.min.js"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sergey Pozhilov (GetTemplate.com)">

<title>Magister - Free html5 template by GetTemplate</title>

<link rel="shortcut icon" href="assets/images/gt_favicon.png">

<!-- Bootstrap itself -->
<link
   href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"
   rel="stylesheet" type="text/css">

<!-- Load js libs only when the page is loaded. -->
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>  -->
<script
   src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="assets/js/modernizr.custom.72241.js"></script>

<!-- Fonts -->
<link
   href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"
   rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Wire+One'
   rel='stylesheet' type='text/css'>

<script src="assets/js/magister.js"></script>
<script src="assets/js/login.js"></script>
<!-- Custom styles -->
<link rel="stylesheet" href="assets/css/magister.css" type="text/css">
<link rel="stylesheet" href="assets/css/login.css" type="text/css">

</head>
<script>

   function register()
   {
      if(document.getElementById('pw1').value == document.getElementById('pw2').value)
      {
         document.getElementById('regForm').submit();
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
				$('#h5_id').text("이메일 사용 가능");
				$('#emailDupChk').text('사용하기');
				$('#emailDupChk').on('click', function(){
					$('#email').attr('value', emaVal);
					$('#closeBtn').trigger('click');
				});
			},// success
			error : function(data)
			{
				$('#h5_id').text("중복된 이메일이 존재한다")
			}
		});
	}
	
});   
   
</script>

<body class="theme-invert">

<nav class="mainmenu">
      <div class="container">
         <div class="dropdown">
            <button type="button" class="navbar-toggle" data-toggle="dropdown">
               <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                  class="icon-bar"></span>
            </button>
            <!-- <a data-toggle="dropdown" href="#">Dropdown trigger</a> -->
            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
               <li><a href="#head" class="active">Hello</a></li>
               <li><a href="#about">About me</a></li>
               <li><a href="#themes">Themes</a></li>
               <li><a href="#contact">Get in touch</a></li>
            </ul>
         </div>
      </div>
   </nav>

   <!-- Main (Home) section -->
   <section class="section" id="head">

      <div class="container">

         <div class="row">
            <div
               class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">

               <!-- Site Title, your name, HELLO msg, etc. -->
               <h1 class="title">사요나라</h1>
               <h2 class="subtitle">BrainStorming Tool</h2>

               <!-- Short introductory (optional) -->

               <form id="enter_test" method="post" action="enter_test">
                  <h3 class="tagline">
                     sipal : <input type="text" id="move"><br>
                  </h3>
               </form>

               <!-- Nice place to describe your site in a sentence or two -->
               <p>
                  <a href="#" class="btn btn-default btn-lg"
                     onclick="document.getElementById('id01').style.display='block'">Login</a>
               </p>



               <!-- The Modal -->
               <div id="id01" class="modal">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                  
                  <div class="container" id="container-login" >
                     <div class="row">
                        <!-- <div class="col-lg-7"> -->
                        <div>
                           <div class="panel panel-login">
                              <div class="panel-heading">
                                 <div class="row">
                                    <div class="col-xs-6">
                                       <a href="#" class="active" id="login-form-link">로그인</a>
                                    </div>
                                    <div class="col-xs-6">
                                       <a href="#" id="register-form-link">회원가입</a>
                                    </div>
                                 </div>
                                 <hr>
                              </div>

                              <div class="panel-body">
                                 <div class="row">
                                    <div>
                                       <!-- <div class = "col-lg-12"> -->

                                       <form action="OKS_Login" method="post" id="login-form"
                                          role="form" style="display: block;">

                                          <div class="form-group">
                                             <input type="text" name="oks.email" id="username"
                                                tabindex="1" class="form-control"
                                                placeholder="Email Address" value="" required>
                                          </div>
                                          <div class="form-group">
                                             <input type="password" name="oks.pass" id="password"
                                                tabindex="2" class="form-control" placeholder="Password"
                                                required>
                                          </div>
                                          <div class="form-group text-center">
                                             <input type="checkbox" tabindex="3" class=""
                                                name="remember" id="remember"><label
                                                for="remember">기억해라. 사요나라</label>
                                          </div>


                                          <div class="form-group">
                                             <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                   <input type="submit" name="login-submit"
                                                      id="login-submit" tabindex="4"
                                                      class="form-control btn btn-login" value="로그인">
                                                </div>
                                             </div>
                                          </div>


                                          <div class="form-group">
                                             <div class="row">
                                                <div class="col-lg-12">
                                                   <div class="text-center">
                                                      <a data-toggle="modal" href="#forgotPass" tabindex="5"
                                                         class="forgot-password">비번을 몰라? 사요나라</a>
                                                   </div>
                                                </div>
                                             </div>
                                          </div>
                                       </form>
                                    </div>


                                    <!-- second modal -->
                                    <div class="modal" id="forgotPass" aria-hidden="true"
                                       style="display: none; z-index: 1060;">
                                       <div class="modal-dialog modal-lg">
                                          <div class="modal-content">
                                             <div class="modal-header">
                                                <h4 class="modal-title">비밀번호 찾기</h4>
                                             </div>
                                             <div class="container"></div>
                                             <div class="modal-body">
                                                가입한 이메일을 작성 후 확인버튼을 눌러주세요 <br> <br> <br>
                                                <br>
                                                <div class="form-group">
                                                   <input type="text" name="oks.email" id="username"
                                                      tabindex="1" class="form-control"
                                                      placeholder="Email Address" value="" required>
                                                </div>

                                                <a data-toggle="modal" href="#myModal3"
                                                   class="btn btn-success">Find</a>
                                                <!-- 액션 다녀온 후 결과값 출력 -->
                                             </div>
                                             <div class="modal-footer">
                                                <a href="#" data-dismiss="modal" class="btn btn-primary">Close</a>
                                             </div>
                                          </div>
                                       </div>
                                    </div>

                                    <form id="register-form" action="register" name="regForm"
                                       method="post" role="form" style="display: none;">

                                       <div class="form-group">
                                          <input type="text" name="oks.nick" tabindex="1"
                                             class="form-control" id="nickname" placeholder="Nickname"
                                             value="" required>
                                       </div>
                                       <div class="form-group">
                                          <input type="email" name="oks.email" tabindex="1"
                                             class="form-control" id="email"
                                             placeholder="Email Address" value="" required readonly>
                                       </div>
                                       <div class="form-group">
                                          <input type="password" name="oks.pass" tabindex="2"
                                             class="form-control" id="pw1" placeholder="Password"
                                             required>
                                       </div>
                                       <div class="form-group">
                                          <input type="password" tabindex="2" class="form-control"
                                             id="pw2" placeholder="Confirm Password" required>
                                       </div>
                                       <div class="form-group">
                                          <div class="row">
                                             <!-- <div class="col-sm-6 col-sm-offset-3"> -->
                                             <a data-toggle="modal" href="#" tabindex="5"
                                                class="btn btn-success" onclick="return register();">가입하기</a>
                                             <a data-toggle="modal" href="#checkEmail" tabindex="5"
                                                class="btn btn-primary">이메일 중복 확인</a>
                                             <!-- </div> -->
                                          </div>
                                       </div>

                                       <!-- second modal  ,,,   '이메일 중복 확인'버튼을 눌렀을 경우 활성화되는 모달 -->
                                       <div class="modal" id="checkEmail" aria-hidden="true"
                                          style="display: none; z-index: 1060;">
                                          <div class="modal-dialog modal-lg">
                                             <div class="modal-content">
                                                <div class="modal-header">
                                                   <h4 class="modal-title">이메일 중복 확인</h4>
                                                </div>
                                                <div class="container"></div>
                                                <div class="modal-body">
                                                   <div class="form-group">
                                                      <input type="text" id="EmailDupVal"
                                                         tabindex="1" class="form-control"
                                                         placeholder="Email Address" value="" required>
                                                   </div>
                                                   
                                                   <h5 style="color: red;" id = "h5_id"></h5>
                                                  
                                                   <br> 
                                                   <a data-toggle="modal" href="#myModal4"
                                                      class="btn btn-success" id = "emailDupChk">검색</a>
                                                   <!-- 액션 다녀온 후 결과값 출력 -->
                                                   
                                                  <%--  <h5 style="color: red;"><span>중복이다</span></h5>
                                                   <br> 
                                                   <a data-toggle="modal" href="#myModal4"
                                                      class="btn btn-success" id = "emailDupChk">검색</a>
                                                   <!-- 액션 다녀온 후 결과값 출력 --> --%>
                                                   
                                                </div>
                                                <div class="modal-footer">
                                                   <a href="#" data-dismiss="modal"
                                                      class="btn btn-primary" id = "closeBtn">Close</a>
                                                </div>
                                             </div>
                                          </div>
                                       </div>
                                    </form>
                                 </div>
                              </div>
                           </div>

                        </div>
                     </div>
                  </div>
               </div>
               <!-- login -->

            </div>
            <!-- /col -->
         </div>
         <!-- /row -->
      </div>
   </section>

   <!-- Second (About) section -->
   <section class="section" id="about">
      <div class="container">

         <h2 class="text-center title">About us</h2>
         <div class="row">
            <div class="col-sm-4 col-sm-offset-2">
               <h5>
                  <strong>fuck you<br></strong>
               </h5>
               <p>c8</p>
            </div>
            <div class="col-sm-4">
               <h5>
                  <strong>sibal! sibal!<br></strong>
               </h5>
               <p>what the fuck?!</p>
               <h5>
                  <strong>조 원<br></strong>
               </h5>
               <p>
                  <a href="http://be.net/pozhilov9409">링크이동1</a> / <a
                     href="https://twitter.com/serggg">링크이동2</a> / <a
                     href="http://linkedin.com/pozhilov">링크이동3</a> / <a
                     href="https://www.facebook.com/pozhilov">링크이동4</a>
               </p>
            </div>
         </div>
      </div>
   </section>

   <!-- Third (Works) section -->
   <section class="section" id="themes">
      <div class="container">

         <h2 class="text-center title">More Themes</h2>
         <p class="lead text-center">
            Huge thank you to all people who publish<br> their photos at <a
               href="http://unsplash.com">Unsplash</a>, thank you guys!
         </p>
         <div class="row">
            <div class="col-sm-4 col-sm-offset-2">
               <div class="thumbnail">
                  <img src="assets/screenshots/sshot1.jpg" alt="">
                  <div class="caption">
                     <h3>Thumbnail label</h3>
                     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                        Neque doloribus enim vitae nam cupiditate eius at explicabo
                        eaque facere iste.</p>
                     <p>
                        <a href="#" class="btn btn-primary" role="button">Button</a> <a
                           href="#" class="btn btn-default" role="button">Button</a>
                     </p>
                  </div>
               </div>
            </div>
            <div class="col-sm-4">
               <div class="thumbnail">
                  <img src="assets/screenshots/sshot4.jpg" alt="">
                  <div class="caption">
                     <h3>Thumbnail label</h3>
                     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                        Neque doloribus enim vitae nam cupiditate eius at explicabo
                        eaque facere iste.</p>
                     <p>
                        <a href="#" class="btn btn-primary" role="button">Button</a> <a
                           href="#" class="btn btn-default" role="button">Button</a>
                     </p>
                  </div>
               </div>
            </div>
            <div class="col-sm-4 col-sm-offset-2">
               <div class="thumbnail">
                  <img src="assets/screenshots/sshot5.jpg" alt="">
                  <div class="caption">
                     <h3>Thumbnail label</h3>
                     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                        Neque doloribus enim vitae nam cupiditate eius at explicabo
                        eaque facere iste.</p>
                     <p>
                        <a href="#" class="btn btn-primary" role="button">Button</a> <a
                           href="#" class="btn btn-default" role="button">Button</a>
                     </p>
                  </div>
               </div>
            </div>
            <div class="col-sm-4">
               <div class="thumbnail">
                  <img src="assets/screenshots/sshot3.jpg" alt="">
                  <div class="caption">
                     <h3>Thumbnail label</h3>
                     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                        Neque doloribus enim vitae nam cupiditate eius at explicabo
                        eaque facere iste.</p>
                     <p>
                        <a href="#" class="btn btn-primary" role="button">Button</a> <a
                           href="#" class="btn btn-default" role="button">Button</a>
                     </p>
                  </div>
               </div>
            </div>

         </div>

      </div>
   </section>

   <!-- Fourth (Contact) section -->
   <section class="section" id="contact">
      <div class="container">

         <h2 class="text-center title">Get in touch</h2>

         <div class="row">
            <div class="col-sm-8 col-sm-offset-2 text-center">
               <p class="lead">질문 있으면 나한테 연락해라</p>
               <p>썅 연락하라고</p>
               <p>
                  <b>썅@gmail.com</b><br>
                  <br>
               </p>
               <ul class="list-inline list-social">
                  <li><a href="https://twitter.com/serggg" class="btn btn-link"><i
                        class="fa fa-twitter fa-fw"></i> 링크이동1</a></li>
                  <li><a href="https://github.com/pozhilov"
                     class="btn btn-link"><i class="fa fa-github fa-fw"></i> 링크이동2</a></li>
                  <li><a href="http://linkedin/in/pozhilov"
                     class="btn btn-link"><i class="fa fa-linkedin fa-fw"></i>
                        링크이동3</a></li>
               </ul>
            </div>
         </div>

      </div>
   </section>

   <!-- modal js -->
   <script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

</script>

</body>
</html>