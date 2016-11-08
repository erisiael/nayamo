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
         <h1>고객센터<span>아이디, 패스워드 찾기</span></h1><!-- 내용제목 -->
         <h1><font color = "red" size = "10" face = "궁서체">비밀번호 찾아보자</font></h1>
         <div class="login_area">
               <form action="OKS_find.action" id="OKS_find" name="OKS_find">
               <ul style="display;"><!-- 비밀번호찾기 -->
                  <li><input type="email" placeholder="이메일주소@도메인" name="oks.email" /></li>
               </ul>
                  <input type="submit" value="찾기">
            </form>
         </div>
      </div>
</body>
</html>