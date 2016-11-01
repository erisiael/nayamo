<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>MindMap-Mind</title>
<script type="text/javascript" src="jq/jquery-3.1.1.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/modernizr.custom.72241.js"></script>

<link href="assets/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div class="header">
		<div class="wrap">
			<div class="logo">
				<a href="index.html"><img src="assets/images/title.png"
					alt="Mind Map" /></a>
			</div>
			<div class="headerbox">
				<div class="login">
					<ul>
						<li><a href="OKS_updateForm.action">info</a></li> |
						<li><a href="OKS_logout.action">logout</a></li>
					</ul>
				</div>
				<!-- <div class="search">
                    <form>
                        <input type="text" value="Search" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'Search';}">
                        <input type="submit" value="Go" />
                    </form>
                </div> -->
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<div class="wrap">
		<div class="main">
			<div class="nav">
				<ul>
					<li><a href="home.html">Home</a></li>
					<li><a href="myPage.html">Mypage</a></li>
					<li><a href="Go_board.action">Auction</a></li>
					<li><a href="#">etc</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
			</div>
			<div class="content_update">
				<div id="contents">
					<!--내용-->
					<h1>
						회원정보 수정<span></span>
					</h1>
					<div>
						<form action="OKS_update" method="post" id="OKS_update">
							<ul class="member_info">

								<li><label for="wr_id">email</label><input type="text"
									id="email" name="oks.email" value="${oks.email}"
									class="login_write" readonly /></li>
								<li><label for="wr_pass">pass</label><input type="password"
									id="pwd1" name="oks.pass" class="login_write" /></li>
								<li><label for="wr_passchk">pass check</label><input
									type="password" id="pwd2" class="login_write" /></li>
								<li><label for="wr_name">nick</label><input type="text"
									id="nick" name="oks.nick" class="login_write"
									value="${oks.nick}" /></li>
								<li class="center">							
									<p><button type="submit" id="submit" class="btn btn-success" style="width: 100px; font-size: 15px;">수정</button>&nbsp;
									<button type="reset" id="reset" class="btn btn-danger" style="width: 100px; font-size: 15px;">취소</button></p>
								</li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>