<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${initParam.root}kangbank/kangcss/notice.css" type="text/css"> 
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
</head>
<body>
	<table class="content">
		<tr>
			<td>NO : ${requestScope.nvo.no } </td>
			<td colspan="2">${requestScope.nvo.title} </td>
		</tr>
		<tr>
			<td>작성자 :  ${requestScope.nvo.customerVO.name }</td>
			<td> ${requestScope.nvo.timePosted }</td>
			<td>조회수 : ${requestScope.nvo.hits }</td>
		</tr>
		<tr>
			<td colspan="3">
			<pre>${requestScope.nvo.content}</pre>
			</td>
		</tr>
		<tr>
			<td valign="middle" align="center" colspan="3">
			 <img id="listImg" class="action" src="${initParam.root}img/list_btn.jpg" onclick="sendList()" >
			 <c:if test="${requestScope.nvo.customerVO.email==sessionScope.loginInfo.email}">
			 <img id="deleteImg" class="action"  src="${initParam.root}img/delete_btn.jpg" > 
			 <img id="updateImg" class="action"  src="${initParam.root}img/modify_btn.jpg" >
			 

			 </c:if>
			 <br><br>			
			 </td>
		</tr>
	</table>