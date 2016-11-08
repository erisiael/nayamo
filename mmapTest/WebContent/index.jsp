<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Sergey Pozhilov (GetTemplate.com)">

<title>Magister - Free html5 template by GetTemplate</title>

<link rel="shortcut icon" href="assets/images/gt_favicon.png">

<script src="assets/script/jquery-3.1.1.min.js"></script>

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
<script src="assets/js/login.js?ver=1"></script>
<script src="assets/js/navbar.js?ver=1"></script>
<script src="assets/js/carousel.js"></script>
<script src="assets/js/loader.js?ver=1"></script>

<!-- Custom styles -->
<link rel="stylesheet" href="assets/css/magister.css" type="text/css">
<link rel="stylesheet" href="assets/css/login.css" type="text/css">
<link rel="stylesheet" href="assets/css/navbar.css" type="text/css">
<link rel="stylesheet" href="assets/css/carousel.css" type="text/css">
<link rel="stylesheet" href="assets/css/loader.css" type="text/css">


</head>
<body class="theme-invert">

<!-- navbar start from here -->
   	<script>
   		var message = "<%= request.getAttribute("errorMessage") %>";
   		if (message != 'null') {
	   		$(document).ready(function() {
		   		alert(message);
			});
   		}
   	</script>
<div id="wrapper">
      
      <!-- Sidebar -->
            <!-- Sidebar -->
      <div id="sidebar-wrapper">
      <ul id="sidebar_menu" class="sidebar-nav">
      
     <!--  로그인 관련 표시 -->
<s:if test="#session.OKS == null">
      	   <li><a><span>&nbsp;</span></a></li>
      	   <li><span class="loginstring"></span></li>
           <li><a href="#" class="btn btn-default btn-lg"
                     onclick="document.getElementById('id01').style.display='block'">Login</a></li>
      	   <li><a><span>&nbsp;</span></a></li>
           <li class="sidebar-brand"><a id="menu-toggle" href="#">Menu<span id="main_icon" class="glyphicon glyphicon-align-justify"></span></a></li>
</s:if>
<s:else>
      	   <li><a><span>&nbsp;</span></a></li>
		   <li><span class="loginstring">안녕하세요. <s:property value="#session.OKS.nick"/>님</span></li>
		   <li><a href="#" onclick="">정보수정</a></li>
		   <li><a href="#" onclick="location.href='OKS_logout.action'">로그아웃</a></li>
      	   <li><a><span>&nbsp;</span></a></li>
           <li class="sidebar-brand"><a id="menu-toggle" href="#">Menu<span id="main_icon" class="glyphicon glyphicon-align-justify"></span></a></li>
</s:else>
      <!--  로그인 관련 표시 -->
           
      </ul>
      
        <ul class="sidebar-nav" id="sidebar">     

      <!-- 로그인시 보여줄 메뉴 분기 -->
<s:if test="#session.OKS != null">
		<li class="sidebar">
        <a href="#" onclick="document.getElementById('myModal').style.display='block'">새로만들기</a>
        </li>
        <li><a href="#" id="strlist_carousel">참여한 목록<span class="sub_icon glyphicon glyphicon-link"></span></a></li>
</s:if>
      	<li><a href="#head" class="a-scrolling">메인으로<span class="sub_icon glyphicon glyphicon-link"></span></a></li>
		<li><a href="#about" class="a-scrolling">만든 사람들<span class="sub_icon glyphicon glyphicon-link"></span></a></li>
		<li><a href="#themes" class="a-scrolling">생각툴 이란<span class="sub_icon glyphicon glyphicon-link"></span></a></li>
        <!-- <li><a>Link1<span class="sub_icon glyphicon glyphicon-link"></span></a></li>
        <li><a>link2<span class="sub_icon glyphicon glyphicon-link"></span></a></li> -->
		<!-- 로그인시 보여줄 메뉴 분기 -->
           
      </ul>
      
      </div>
          
		<!-- 로그인 상태에서만 사용할 modal div -->          
<s:if test="#session.OKS != null">
          <div class="modal" id="myModal">
				<span onclick="document.getElementById('myModal').style.display='none'"
            class="close" title="Close Modal">&times;</span>
            	
			<div class="modal-dialog">
			      <div class="modal-content">
			      <form action="newRoom" method = "post">
			        <div class="modal-header">
			          <h3 class="modal-title">새로 만들기</h3>
			        </div>
			        <div class="modal-body">
					  <h5 class="text-center">Creativity can solve almost any problem. The creative act, the defeat of habit by originality, overcomes everything.<br> - George Lois -</h5>
			          <table class="table table-striped" id="tblGrid">
			            <tbody>
			              <tr><td>Title</td>
			                <td><input type="text" name = "str.name" id="title" class="form-control"></td>
			                <!-- <td class="text-right">45001</td> -->
			              </tr>
			              <tr><td>Keyword</td>
			                <td><input type="text" name ="str.keyword" id="keyword" class="form-control"></td>
			                <!-- <td class="text-right">76455</td> -->
			              </tr>
			              <tr><td>Category</td>
								<td><select name = "str.category" class="form-control">
										<option>IT</option>
										<option>Food</option>
										<option>Science</option>
										<option>Sport</option>
										<option>Music</option>
								</select></td>
										<!-- <td class="text-right">39097</td> -->
			              </tr>
			            </tbody>
			          </table>
			          <div class="form-group">
			          <!-- button submit :seokgi -->
			            <input type="submit" class="btn btn-success btn-sm pull-right" value="생성">
			            <div class="clearfix"></div>
			          </div>
					</div>
					</form><!-- 방 만들때의 정보를 보내기 위한 from -->
			      </div><!-- /.modal-content -->
			    </div><!-- /.modal-dialog -->
			  </div><!-- /.modal -->
</s:if>
		<!-- 로그인 상태에서만 사용할 modal div -->
		
		
		
		<!-- 로그인 상태에서만 사용할 modal div (방 목록 보여주기) -->
		<div class="carousel-template">
				<div align="center"><a href="#"><img src="http://lorempixel.com/1920/1080/nature/" class="img-rounded" alt="Blu-ray Lens reparatie"></a>
                    <div class="carousel-caption">
                        <h3>Cupcake ipsum dolor sit amet</h3>
                         <p>Pudding fruitcake chocolate pastry caramels dessert powder cupcake. Marzipan sweet roll jelly macaroon brownie.</p>
                    </div>
                </div>
                <ol class="indicators-template" style="padding-bottom: 42px;">
	                <li data-target="#myCarousel"></li>
                </ol>
			</div>
		<div class="modal" id="modal-carousel">
			<div class="modal-dialog">
			      <div class="modal-content">
			<div class="container">
	<div class="row">
		<div id="myCarousel" class="carousel slide">
            <div class="carousel-inner">
            </div>
            <ol class="carousel-indicators hidden-xs hidden-sm" style="padding-bottom: 42px;">
            </ol>
            <a class="left carousel-control">‹</a>
            <a class="right carousel-control">›</a>
        </div>
	</div>
	</div>
	</div>
</div>
			
		</div>
<s:else>

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
                                                for="remember">아이디 기억하기</label>
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
                                                         class="forgot-password">비밀번호 찾기</a>
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


</s:else>        
          
<!-- modal js -->
   <script>
// Get the modal

// When the user clicks anywhere outside of the modal, close it
$(document).ready(function() {
	$(window).click(function(event) {
		var modal = document.getElementById('id01');
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	});
	
	
	$(window).click(function(event) {
		var myModal = document.getElementById('myModal');
	    if (event.target == myModal) {
	        myModal.style.display = "none";
	    }
	});
});
</script>
      <!-- Page content -->
      <div id="page-content-wrapper">
        <!-- Keep all page content within the page-content inset div! -->
        <div class="page-content inset">
          <div class="row">
              <div class="col-md-12">
              
              <!-- content from here -->



   <!-- Main (Home) section -->
   <div class="section" id="head">
      <div class="container">
         <div class="row">
            <div
               class="col-md-10 col-lg-10 col-md-offset-1 col-lg-offset-1 text-center">

               <!-- Site Title, your name, HELLO msg, etc. -->
               <h1 class="title">SC-MMAP</h1>
               <h2 class="subtitle">Need Idea?</h2>

               <!-- Short introductory (optional) -->

               <form id="enter_test" method="get" action="checkRoom">
                  <h3 class="tagline">
                     Enter Code : <input type="text" id="move" name = "roomName_web">&nbsp;<button type="submit" class="btn btn-success">입장하기</button><br>
                  </h3>
               </form>

               <!-- Nice place to describe your site in a sentence or two -->
               <p>
                  
               </p>

            </div>
            <!-- /col -->
         </div>
         <!-- /row -->
      </div>
   </div>

   <!-- Second (About) section -->
   <div class="section" id="about">
      <div class="container">

         <h2 class="text-center title">About us</h2>
         <div class="row">
            <div class="col-sm-4 col-sm-offset-2">
               <h4><strong>조원 소개<br></strong></h4>
               <table class="memberInfo">
               		<thead>
               			<tr>
               				<th><img src="assets/images/11.png"></th>
               				<th><img src="assets/images/22.png"></th>
               				<th><img src="assets/images/33.png"></th>
               				<th><img src="assets/images/44.png"></th>
               			</tr>
               		</thead>
               		<tbody>
               			<tr>
               				<th class="memType">조장</th>
               				<th colspan="3" class="memType">조원</th>
               			</tr>
               			<tr>
               				<td class="member">이종찬</td>
               				<td class="member">윤석기</td>
               				<td class="member">백승훈</td>
               				<td class="member">김동현</td>
               			</tr>
               		</tbody>
               </table>
            </div>
            <%-- <div class="col-sm-4">
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
            </div> --%>
         </div>
      </div>
   </div>

   <!-- Third (Works) section -->
   <div class="section" id="themes">
      <div class="container">

         <h2 class="text-center title">How to use??</h2>
         <p class="lead text-center" style="color: white;">
            After Login, Make new Project or write Enter code<br>If you want new idea.. Just Sign in!!<br>
         </p>
         <div class="row">
            <!-- <div class="col-sm-4 col-sm-offset-2"> -->
               <div class="thumbnail">
                  <img src="assets/screenshots/make_new2.PNG">
                  <div class="caption">
                     <h3>새로 만들기</h3>
                     <p>로그인 한 사용자는 새로운 프로젝트 생성 가능.<br>
                     Title, Keyword, Category 입력 후 생성</p>
                  </div>
               </div>
            <!-- </div> -->
            <!-- <div class="col-sm-4"> -->
               <div class="thumbnail">
                  <img src="assets/screenshots/enter1.PNG">
                  <div class="caption">
                     <h3>Guest 접속</h3>
                     <p>로그인 하지 않은 사용자는 프로젝트를 생성한 사용자에게서<br>
                     전송받은 Enter Code를 메인페이지에서 작성하여 접속</p>
                  </div>
               </div>
            <!-- </div> -->
            <!-- <div class="col-sm-4 col-sm-offset-2"> -->
               <div class="thumbnail">
                  <img src="assets/screenshots/test1.PNG">
                  <div class="caption">
                     <h3>마인드맵 작성</h3>
                     <p>추가, 수정, 삭제, 초기위치, 저장, 경매, 초대버튼으로 메뉴가 구성되어 있습니다.</p>
                  </div>
               </div>
            <!-- </div> -->
            <!-- <div class="col-sm-4"> -->
               <div class="thumbnail">
                  <img src="assets/screenshots/test2.PNG">
                  <div class="caption">
                     <h3>활용 예시</h3>
                  </div>
               </div>
            <!-- </div> -->

         </div>

      </div>
   </div>

</div>
</div>
</div>
</div>
</div>
<!-- navbar end here -->
</body>
</html>