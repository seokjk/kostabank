<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#updateForm").submit(function(){
			alert(2);
		});
	});
</script>
<c:if test="${sessionScope.loginInfo != null }">
	${sessionScope.loginInfo.name }님 회원정보수정
	<form name="updateForm" action="customer_updateCustomerResult.bank">
		<input type="hidden" name="email" value="${sessionScope.loginInfo.email }">
		<table border="1">
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="password" id="password"></td>
			</tr>
			<tr>
				<td>비밀번호확인</td>
				<td><input type="text" name="passCheck" id="passCheck"></td>
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