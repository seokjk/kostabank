<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#listBtn").click(function(){
		location.href="qnaListRoad.bank?page=1";
	});
});

</script>
<title>Insert title here</title>
</head>
<body>
 <div class="container">
<table border="1">
<tr>
<td>글번호 : ${qvo.qnaNo}</td><td>이름 : ${qvo.customerVO.name} </td>
</tr>
<tr>
<td> 이메일 : ${qvo.customerVO.email}</td> <td>번호 : ${qvo.customerVO.tel}</td>
</tr>
<tr>
<td>작성타입:${qvo.qnaType}</td><td>작성시간:${qvo.qnaTime}</td>
</tr>
<tr>
<td colspan="2">${qvo.qnaTitle}</td>
</tr>
<tr>
<td colspan="3"><pre>${qvo.qnaContent}</pre><br><img src="${initParam.root}${qvo.qnaFileAddress}"></td>
</tr>
<tr>
<td colspan="3" align="center">
<input type = "button" id= "listBtn" value = "목록" >
<input type = "button" id= "subBoardBtn" value = "답글">
</td>
</tr>
</table>
</div>
</body>
</html>