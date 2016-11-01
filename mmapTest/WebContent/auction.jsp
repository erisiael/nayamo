<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src = "assets/script/jquery-3.1.1.min.js"></script> 
<link rel="stylesheet" href="assets/css/style.css" type = "text/css"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%-- <script>

$(document).ready(function()
{
	/* $.ajaxSettings.traditional = true; */
	var output = '';
	$.ajax({
		url : 'board',
		method : 'get',
		
		dataType : 'json',
		/* contentType : 'application/json', */
		/* data : JSON.stringify(form) */ 
		success : function(data)
		{
		 	for(var key in data.skaList)
			{
				output += '<tr>' + '<td>' + key.SKA_l_no + '</td><td>' + key.SKA_no + '</td><td>' + key.OKS_no + '</td><td>' + key.comt + '</td><td>' + key.regdate + '</td>' + '</tr>';
				$('table').append(output);
			} 
			/* $(r.skaList).each(function(index, item){
				console.log(this);
				console.log(JSON.stringify(this));
				output += '<tr>' + '<td>' + this.SKA_l_no + '</td><td>' + this.SKA_no + '</td><td>' + this.OKS_no + '</td><td>' + this.comt + '</td><td>' + this.regdate + '</td>' + '</tr>';
				$('table').append(output);
			}) */
		},
		error : function(data, stateCode) {
			alert(stateCode)
		}
	});
});

</script> --%>

<script language = "javascript">
function ListChk()
{
	if(document.getElementById("sText").value.length > 0)
	{
		document.getElementById("ListForm").action = 'boardList.action';
		document.getElementById("ListForm").submit();
		return false;
	}
	alert('검색 내용을 입력하라');
}

function Board_List_Form()
{
	document.getElementById('Board_List_Form').action = 'Board_List_Form.action';
	document.getElementById('Board_List_Form').submit();
	return false;
}

</script>




</head>
<body>
<div class="header">
    <div class="wrap">
            <div class="logo"><a href="index.html"><img src="assets/images/title.png" alt="Mind Map" /></a></div>
            <div class="headerbox">
                <div class="login">
                    <ul>
                        <li><a href="OKS_updateForm.action">info</a></li> 
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
            <li class="active"><a href="Go_board.action">Auction</a></li>
            <li><a href="#">etc</a></li>
            <li><a href="#">Contact</a></Mli>
        </ul>
        <div class="clear"></div>
    </div>
       
       
       <div id="contents"><!--내용-->
			<h1>고객문의 게시판sipal<span></span></h1><!-- 내용제목 -->
			
			<form id = "Board_List_Form" method = "post">
				<s:select name = "Board_List_Form" list = "#{'all':'전체 경매 목록', 'SKA_no':'내가 참여한 경매', 'OKS_no':'내가 쓴 경매'}"/>
				<a href = "javascript:Board_List_Form()" style="color: olive;">검색</a>
			</form>
						
			<table>
				<tr>
					<th>SKA_L_NO</th>
					<th>COMT</th>
					<th>OKS_NO</th>
					<th>SKA_NO</th>
					<th>REGDATE</th>
				</tr>
				
				 <s:iterator value = "skaList" status = "status">
					 <tr>
						<td>
							<%-- <s:property value = "pagenavi.totalRecordsCount"/> --%>
							<s:property value = "pagenavi.totalRecordsCount-pagenavi.startRecord-#status.index" />
						</td>
						<td class = "board_title">
							<s:url id = "readurl" value = "../board/readBoard.action">
							<s:param name = "comt" value = "%{comt}"/>
							</s:url>
							<s:a href = "%{readurl}"><s:property value = "comt"/></s:a>
						</td>
						<td><s:property value = "OKS_no"/></td>
						<td><s:property value = "SKA_no"/></td>
						<td><s:property value = "regdate"/></td>
					</tr> 
				</s:iterator>
				
			</table>
			
			<p class="paging">
				<a href = "#" onclick = "document.getElementById('page').value='<s:property value = "pagenavi.currentPage-1"/>';document.getElementById('ListForm').submit();">&lt</a>
				<s:iterator begin = "pagenavi.startPageGroup" end = "pagenavi.endPageGroup" var = "count">
				<a href = "#" 
					<s:if test = "#count==pagenavi.currentPage">class = "select"</s:if>
					onclick = "document.getElementById('page').value='<s:property value = "count"/>';
					document.getElementById('ListForm').submit();">
					<s:property value = "count"/></a>
				</s:iterator>
				<a href = "#" onclick = "document.getElementById('page').value='<s:property value = "pagenavi.currentPage+1"/>';document.getElementById('ListForm').submit();">&gt</a>
				<a href = "../board/boardwriteInitial.action" class = "btn_write">글쓰기</a>
			</p>
			
			<p class="board_search">
			<form id = "ListForm" method = "post">
				
				<s:select name = "searchField" list = "#{'all':'전체', 'SKA_no':'생각 번호', 'OKS_no':'고객 번호', 'comt':'내용'}"/>
					<input type = "hidden" name = "currentPage" value = "1" id = "page">
				<s:textfield id = "sText" name = "searchText"/><a href = "javascript:ListChk()" style="color: olive;">검색</a>
			</form>
			</p>
			
		</div>

        
        </div>	
        <div class="sidebar">
            <div class="recent">
                <h3>About</h3>
                <button type="button" class="newP btn btn-primary">My Auction List</button>
                <button type="button" class="newP btn btn-primary">All List</button>
                <button type="button" class="newP btn btn-primary">Search</button>
            </div>
            <div class="add">
            	<a href="#"><img src="assets/images/add.png" align="300x250" /></a>
            </div>
        </div>
     <div class="clear"></div>
   </div>
<div class="f-menu">
    <ul>
    	<h4>Our Sponcers</h4>
        <li><a href="index.html">simply dummy text of the printing</a></li>
        <li><a href="#">simply dummy text of the printing</a></li>
        <li><a href="#">simply dummy text of the printing</a></li>
    </ul>
    <ul>
    	<h4>Follow on</h4>
        <li><a href="index.html">simply dummy text of the printing</a></li>
        <li><a href="#">simply dummy text of the printing</a></li>
        <li><a href="#">simply dummy text of the printing</a></li>
    </ul>
    <ul>
    	<h4>Contact</h4>
        <li><input type="text" value=""  /></li>
        <li><textarea></textarea></li>
        <li><input type="submit" value="Send" /></li>
    </ul>
        <div class="clear"></div>
    <div class="copy">� 2012 All rights reserved. Designed by <a href="http://w3layouts.com">W3Layouts</a> Powered by <a href="http://bigw3.com">Bigw3</a></div>
</div>

</body>
</html>