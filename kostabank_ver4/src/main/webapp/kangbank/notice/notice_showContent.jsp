<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#listImg").click(function(){    		
			location.href="notice_list.bank";
    	});
    	$("#deleteImg").click(function(){ 
    		if(confirm("게시물을 삭제하시겠습니까?"))
    		 location.href="notice_deleteNotice.bank?no=${requestScope.nvo.no}"; 
    	});
    	$("#updateImg").click(function(){  
    		if(confirm("게시물을 수정하시겠습니까?"))
    		location.href="notice_updateView.bank?no=${requestScope.nvo.no }";
    	});
    });	
</script>
<div class="noticeShowContents">
	<h2>공지사항</h2><br><br>
	<table id="showcontents">
		<%-- <tr>
			<td>NO : ${requestScope.nvo.no }</td>
			<td rowspan="2">${requestScope.nvo.title} </td>
			<td width="20%">조회수 : ${requestScope.nvo.hits }</td>
		</tr>
		<tr id="noticetr">
			<td>작성자 :  ${requestScope.nvo.customerVO.name }</td>
			<td>${requestScope.nvo.timePosted }</td>
		</tr> --%>
		<tr>
			<th>NO.${requestScope.nvo.no }</th>
			<th colspan="2">${requestScope.nvo.title }</th>
		</tr>
		<tr>
			<th>작성자 : ${requestScope.nvo.customerVO.name } </th>
			<th>작성일 : ${requestScope.nvo.timePosted }</th>
			<th>조회수 : ${requestScope.nvo.hits }</th>
		</tr>
		<tr>
			<td colspan="3" >
			<pre>${requestScope.nvo.content}</pre>
			</td>
		</tr>
	</table>
	<br>
		<div class="select">
			 <img id="listImg" class="action" src="${initParam.root}img/list_btn.jpg" onclick="sendList()" style="cursor:pointer">&nbsp;&nbsp;
			 <c:if test="${requestScope.nvo.customerVO.email==sessionScope.loginInfo.email}">
			 <img id="deleteImg" class="action"  src="${initParam.root}img/delete_btn.jpg" style="cursor:pointer">&nbsp;&nbsp;
			 <img id="updateImg" class="action"  src="${initParam.root}img/modify_btn.jpg" style="cursor:pointer">&nbsp;&nbsp;
			 </c:if>	
		 </div>
</div>		