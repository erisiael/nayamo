<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="assets/css/tool.css" rel="stylesheet" type="text/css">
<link href="assets/css/chat.css" rel="stylesheet" type="text/css">
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="assets/css/navbar.css" type="text/css">
<link rel="stylesheet" href="assets/css/login.css" type="text/css">
<link href="assets/css/loader.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="assets/css/loader.css" type="text/css">
<link rel="stylesheet" href="assets/css/carousel.css" type="text/css">


<script type="text/javascript" src="jq/jquery-3.1.1.js"></script>
<script src="assets/js/jquery-ui.min.js"></script> 
<script src="assets/js/jquery.ui.touch.js"></script>
<script src="assets/js/loader.js?ver=1"></script>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/clipboard.js"></script>
<script type="text/javascript" src="assets/js/login.js?ver=1"></script>

	
</head>
<body>
<script type="text/javascript">
setLoader(); // setting loader.js
</script>
		
<section>

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
      	<li><a href="#" onclick="location.href='index.action'" class="a-scrolling">메인으로<span class="sub_icon glyphicon glyphicon-link"></span></a></li>
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

<div class="chat-templete">
			<div class="row msg_container base_sent">
	                        <div class="col-xs-10 col-md-10">
	                            <div class="messages msg_sent">
	                                <p>message</p>
	                                <!-- <time datetime="2009-11-13T20:00">Timothy • 51 min</time> -->
	                            </div>
	                        </div>
	                        <div class="col-md-2 col-xs-2 avatar">
	                            <img src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg" class=" img-responsive ">
	                        </div>
	         </div>
	         <div class="row msg_container base_receive">
	                        <div class="col-md-2 col-xs-2 avatar">
	                            <img src="http://www.bitrebels.com/wp-content/uploads/2011/02/Original-Facebook-Geek-Profile-Avatar-1.jpg" class=" img-responsive ">
	                        </div>
	                        <div class="col-md-10 col-xs-10">
	                            <div class="messages msg_receive">
	                                <p>message</p>
	                                <!-- <time datetime="2009-11-13T20:00">Timothy • 51 min</time> -->
	                            </div>
	                        </div>
	          </div>
		</div>
		
	    <div class="row chat-window col-xs-5 col-md-3" id="chat_window_1" style="margin-left:10px;">
	        <div class="col-xs-12 col-md-12">
	        	<div class="panel panel-default">
	                <div class="panel-heading top-bar">
	                    <div class="col-md-8 col-xs-8">
	                        <h3 class="panel-title"><span class="glyphicon glyphicon-comment"></span>Chat</h3>
	                    </div>
	                    <div class="col-md-4 col-xs-4" style="text-align: right;">
	                        <a href="#"><span id="minim_chat_window" class="glyphicon glyphicon-minus icon_minim"></span></a>
	                    </div>
	                </div>
	                <div class="panel-body msg_container_base">
	                <!-- chat message here -->
	                </div>
	                <div class="panel-footer">
	                    <div class="input-group">
	                        <input id="btn-input" type="text" class="form-control input-sm chat_input" placeholder="Write your message here..." />
	                        <span class="input-group-btn">
	                        <button class="btn btn-primary btn-sm" id="btn-chat">Send</button>
	                        </span>
	                    </div>
	                </div>
	    		</div>
	        </div>
	    </div>


<div class="modal-div" id="modal-content">
	<div class="div-dialogs">
	</div>
</div>
<div class="modal-div" id="modal-content2">
	<div class="div-urldialogs">
	</div>
</div>
<div id="main">
</div>

<div class="nav">
	<div class="labelbar">&nbsp;</div>
	<img id="add" title="추가"></img>
	<img id="update" title="수정"></img>
	<img id="delete" title="삭제"></img>
	<img id="move" title="초기위치로"></img>
	<img id="save" title="저장"></img>
	<img id="load" title="불러오기"></img>
	<img id="auction" title="경매"></img>
	<img id="adduser" title="초대URL"></img>
</div>

<!-- content end here -->
   

</div>
</div>
</div>
</div>
</div>
<!-- navbar end here -->

</section>
<footer>
<div>
&nbsp;
</div>
</footer>

<input type="hidden" id="entercode" value="<s:property value="roomName_web" />">
<input type="hidden" id="username" value="<s:property value="#session.OKS.email" />">

<!-- test -->
<script type="text/javascript" src="jq/talk_design.js"></script>
<script type="text/javascript" src="jq/jongchan.js?ver=2"></script>
<!-- script for d3-lib -->
<script type="text/javascript" src="jq/d3.v3.min.js"></script>
<script type="text/javascript" src="jq/tool-js.js?ver=3"></script>
<script type="text/javascript" src="jq/talk.js?ver=3"></script>
<!-- script for image load -->
<script type="text/javascript" src="jq/baik.js"></script>
<!-- script for etc -->
<script src="assets/js/navbar.js"></script>
<script src="assets/js/loader.js?ver=1"></script>
<script src="assets/js/carousel.js?ver=1"></script>

<script>
	
</script>
</body>
</html>