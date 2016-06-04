<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#loginForm').submit(function() {
		var email = $(":input[name='email1']").val();
		var password = $(":input[name='password1']").val();
		if(email == ""){
			alert("email을 입력하세요");
			return false;
		}
		if(password == ""){
			alert("password를 입력하세요");
			return false;
		}	});
});
</script>
<c:choose>
<c:when test="${empty sessionScope.loginInfo}">
	<form action="customerLogin.bank" method = "post" id = "loginForm">
		email : <input type="email" name="email1"><br>
		password : <input type="password" name="password1"><br>
		<input type = "submit" value = "로그인">
		<hr>
		<a href = "register_view.bank">회원가입</a><br>
		<a href = "find_idview.bank">아이디찾기</a><br>
		<a href = "find_passwordview.bank">비밀번호찾기</a><br>
	</form>
</c:when>
<c:otherwise>
${sessionScope.loginInfo.name}님 환영합니다<br>
<a href = "customerLogout.bank">로그아웃</a>
</c:otherwise>
</c:choose>