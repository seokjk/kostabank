<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${initParam.root}kangbank/kangcss/notice.css" type="text/css">    
<script src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<c:if test="${empty sessionScope.loginInfo}">
	<script type="text/javascript">
		location.href = "home.bank";
	</script>
</c:if>
<script type="text/javascript">
	$(document).ready(function(){
		$("#writeBtn").click(function(){ 
			if($("#title").val()==""){
				alert("제목을 입력하세요!");
			}else if($("#content").val()==""){
    			alert("본문을 입력하세요!");
    		}else{
    			$("#write_form").submit();
    		}
    	});
    	$("#resetBtn").click(function(){    		
    		if(confirm("취소하시겠습니까?"))
          	location.href="notice_list.bank"; 
    	});
    });	
</script>
<div class="noticeWrite">
	<h2>글쓰기</h2>
	<br><br>
	<c:choose>
		<c:when test="${empty loginInfo}">
   			<script type ="text/javascript">
   				alert("로그인을 하셔야만 이용가능합니다");
   				location.href="home.bank";
   			</script>
   		</c:when>
	</c:choose>
	<form action="${initParam.root}notice_write.bank" method="post" id="write_form">
		<table class="inputForm" >
			<tr>
				<td width="20%">제목</td>
				<td width="80%">
					<input type="text" id="title" name="title" size="80">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${sessionScope.loginInfo.name}</td>     
			</tr>
    		<tr>
				<td colspan="2" align="left">&nbsp;&nbsp;
					<textarea cols="105" rows="13" id="content" name="content"></textarea>
				</td>
			</tr> 
			<tr>
				<td colspan="2" align="center" >
      				<img id="writeBtn" class="action" src="${initParam.root}img/write_btn.jpg" alt="글입력"  onclick="content_submit()">
      				<img id="resetBtn" class="action" src="${initParam.root}img/cancel.gif" onclick="cancel()">      
     			</td>  
    		</tr>
		</table>
	</form>
</div>