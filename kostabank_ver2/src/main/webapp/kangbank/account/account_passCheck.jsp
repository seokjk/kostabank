<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#checkBtn").click(function(){
		$.ajax({
			type : "post",
			url : "checkMemberPass.bank",
			data : "checkPass="+$(":input[name=checkPass]").val(),
			success : function(flag){
				if(flag==true){
					location.href="memberCreateAccount.bank";
				}else{
					alert("비밀번호가 일치하지 않습니다.");
					$(":input[name=checkPass]").val("");
				}
			}
		})
	});
});
</script>

<c:choose>
<c:when test="${empty loginInfo}">
<script type ="text/javascript">
alert("로그인을 하셔야만 이용가능합니다");
location.href="home.bank";
</script>
</c:when>
</c:choose>
<br>
<h2>비밀번호 체크</h2>
<br><br>
<input id="passwordcheck"type = "password" name = "checkPass">&nbsp;&nbsp;&nbsp;&nbsp;
<input type = "button" id = "checkBtn" value = "확  인">
