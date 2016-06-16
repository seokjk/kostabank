<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#accountName").change(function(){
		$.ajax({
			type:"post",
			url:"accountNameFindAccountList.bank",
			data:"accountName="+$("#accountName").val(),
			dataType:"json",
			success:function(vo){
				var str = "<br>";
				str += "<table>";
				str+= "<tr><td>적금상품이름</td><td>"+vo.accountTypeVO.accountName+"</td></tr>";
				str+="<tr><td>설명</td><td>"+vo.accountTypeVO.accountExplanation+"</td></tr>"
				str+="<tr><td>월당이체금액</td><td>"+vo.accountTypeVO.minMoney+"</td></tr>"
				str+="<tr><td>최대계약기간</td><td>"+vo.term+"</td></tr>"
				str+="<tr><td>금리</td><td>"+vo.rates+"</td></tr>"
				str+="</table>"
				str+="<input type =button value =가입 id = savingsBtn>";
				$("#accountDetail").html(str);
			}
		});
	});
});
</script>
<h1>적금 상품</h1>
<c:choose>
<c:when test="${empty loginInfo}">
<script type="text/javascript">
alert("로그인 하셔야 이용할 수 있습니다");
location.href="home.bank";
</script>
</c:when>
<c:otherwise>
<select id = "accountName">
<option value=""></option>
<c:forEach items="${slist}" var = "s">
<option value="${s.accountName}">${s.accountName}</option>
</c:forEach>
</select>
<br>
<span id = "accountDetail"></span>
</c:otherwise>
</c:choose>