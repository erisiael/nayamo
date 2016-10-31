<%@ page language = "java" contentType = "text/html; charset = utf-8" 
	pageEncoding = "utf-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<title>SESbank</title>
	<link rel="stylesheet" type="text/css" href="assets/css/style.css" />
	<script type = "text/javascript">
function formCheck()
{
	document.getElementById('dupliForm').submit();
	return true;
}
function idSelect(id)
{
	opener.document.getElementById("email").value = id;
	this.close();
}
</script>
</head>

<body>

<div class = "popwrap">

<h2 id = "title_popup">E-MAIL 중복확인</h2>
<h1><font color = "red" size = "10" face = "궁서체">E-MAIL 중복 확인하자</font></h1>

	<s:if test = "oks == null">
	<form action = "emailChk" id = "dupliForm" method = "post" onsubmit = "return formCheck()" theme = "simple">
		<p class = "list_btn">
			<input type="text" class="wr_idcheck" name = "oks.email"/> <a onclick = "return formCheck()">중 복 확 인</a>
		</p>
	</form>
	</s:if>
	
	<s:if test = "dupChk == true">
	<div style="display:;">
		<span>[${oks.email}]</span> 이메일은 사용가능합니다.
		<p class="list_btn">
		<a onclick = "idSelect('${oks.email}')">적 용</a>
		</p>
	</div>
	</s:if>
	
	<s:if test = "oks != null && dupChk == false">
	<form action = "emailChk" id = "dupliForm" method = "post" onsubmit = "return formCheck()" theme = "simple">
	<div style="display:;">
		<span>[<s:property value = "#session.email"/>]</span> 이메일은 이미 사용중입니다.
		<p class="list_btn">
			<input type="text" name = "oks.email" class="wr_idcheck"/> <a onclick = "return formCheck()">중 복 확 인</a>
		</p>
	</div>
	</form>
	</s:if>
</div>
</body>
</html>