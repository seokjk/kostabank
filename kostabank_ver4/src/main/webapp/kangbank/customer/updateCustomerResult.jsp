<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- email, password, name, birth, tel, address, security_card-->
회원정보
<table border="1">
	<tr>
		<td>이메일</td>
		<td>${loginInfo.email }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${loginInfo.name }</td>
	</tr>
	<tr>
		<td>생일</td>
		<td>${loginInfo.birth }</td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td>${loginInfo.tel }</td>
	</tr>
	<tr>
		<td>주소</td>
		<td>${loginInfo.address }</td>
	</tr>
</table>