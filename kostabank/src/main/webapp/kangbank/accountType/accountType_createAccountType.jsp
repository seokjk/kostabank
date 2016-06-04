<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>상품</h1>
<form action = "accountType_CreateAccountType.bank">
<table border="1">
<tr>
<td>상품 이름</td><td><input type = "text" name = "accountName"></td>
</tr>
<tr>
<td>상품 종류</td><td><select name = "accountType"><option value=""></option>
<option value="적금">적금</option>
<option value="예금">예금</option>
<option value="자녀출생기념적금">자녀출생기념적금</option>
</select></td>
</tr>
<tr>
<td>상품 설명</td><td> <textarea name = "accountExplanation" cols = "22" rows = "20"></textarea></td>
</tr>
<tr>
<td>최저금액</td><td><select name = "minMoney"><option value =""></option>
<c:forEach begin="1000" step="1000" end="100000" var="m">
<option value="${m}">${m}</option>
</c:forEach>
</select></td>
</tr>
</table>
<hr>
<h2>금리</h2>
<table>
<tr>
<td>
금리
</td>
<td><select name="rates">
<option value=""></option>
<c:forEach begin="1" end = "5" var = "i">
<option value="${i}">${i}</option>
</c:forEach>
</select></td></tr>
<tr>
<td>계약기간</td>
<td>
<select name="term">
<option value=""></option>
<c:forEach begin="1" end="12" var = "j">
<option value="${j}">${j}</option>
</c:forEach>
</select>
</td>
</tr>
</table>
<input type ="submit" id = "createBtn" value="검색">
</form>
</body>
</html>