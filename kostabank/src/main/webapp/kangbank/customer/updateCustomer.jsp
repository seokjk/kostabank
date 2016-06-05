<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">	
	$(document).ready(function(){
		$("#updateForm").submit(function(){
			var password = $("#password").val();
			var passCheck = $("#passCheck").val();
			var address = $("#address").val();
			if(password == "") {
				alert("비밀번호를 입력하세요");
				return false;
			}
			if(passCheck == "") {
				alert("비밀번호 확인을 입력하세요");
				return false;
			}
			if(address == "") {
				alert("주소를 입력하세요");
				return false;
			}
			if(password != passCheck) {
				alert("비밀번호와 비밀번호 확인이 다릅니다.");
				return false;
			}
		});
	});
</script>
<c:if test="${sessionScope.loginInfo != null }">
	${sessionScope.loginInfo.name }님 회원정보수정
	<form id="updateForm" name="updateForm" action="customer_updateCustomerResult.bank">
		<input type="hidden" name="email" value="${sessionScope.loginInfo.email }">
		<table border="1">
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td>비밀번호확인</td>
				<td><input type="password" name="passCheck" id="passCheck"></td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address" id="address"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정">
				</td>
			</tr>
		</table>
	</form>
</c:if>