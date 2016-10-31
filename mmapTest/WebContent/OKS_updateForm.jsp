<%@ page language = "java" contentType = "text/html; charset = utf-8" 
   pageEncoding = "utf-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="utf-8">
   <title>SESbank</title>


</head>

<body>

      <div id="contents"><!--내용-->
         <h1>회원정보 수정<span></span></h1><!-- 내용제목 -->
         <div >
            <form action = "OKS_update" method = "post" id = "OKS_update">
               <ul class="member_info"><!-- 회원탈퇴 -->
                           
                  <li><label for="wr_id">email</label><input type="text" id="email" name = "oks.email" value = "${oks.email}" class="login_write" readonly/></li>
                  <li><label for="wr_pass">pass</label><input type="password" id="pwd1" name = "oks.pass" class="login_write" /></li>
                  <li><label for="wr_passchk">pass check</label><input type="password" id="pwd2" class="login_write" /></li>
                  <li><label for="wr_name">nick</label><input type="text" id="nick" name = "oks.nick" class="login_write" value="${oks.nick}"/></li>
                  
               </ul>
            <input type="submit" value="수정">  
            </form>
         </div>
      </div>
      <div id="footer"><!-- 하단 -->
         서울시 강남구 삼성동 59-1 COEX 4F, 개인정보관리책임자: 홍길동<br />
         Copyright©2016 KITA-SESchool ICT Educational Center All rights reserved.         
      </div>

      </div>

   </div>

</body>

</html>