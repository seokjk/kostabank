<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 	<c:if test="${empty sessionScope.loginInfo}">
 	<script type="text/javascript">
		location.href="home.bank";
	</script>
 	</c:if>   

<script type="text/javascript"> 

$(document).ready(function(){
	$().keyup(function(){
		
	});
	
	$("#transferForm").submit(function(){
    	var account=$(":input[name='account']").val().trim();
    	var password=$(":input[name='password']").val().trim();
    	var money=$(":input[name='money']").val().trim();
    	var bank=$(":input[name='bank']").val().trim();
    	var otherAccount=$(":input[name='otherAccount']").val().trim();
    	
    	if(account == ""){
			alert("출금계좌번호를 입력하세요");
			return false;
		}
    	if(password == ""){
			alert("계좌비밀번호를 입력하세요");
			return false;
		}
    	if(money == ""){
			alert("이체금액을 입력하세요");
			return false;
		}
    	if(bank == ""){
			alert("입금은행을 입력하세요");
			return false;
		}
    	if(otherAccount == ""){
			alert("입금계좌번호를 입력하세요");
			return false;
		}

    	
    });
	
});
    
    
</script>
<form action="transfer_result.bank" method="post" id = "transferForm" name = "transferForm">
	<h2>계좌이체</h2>
	<input type = "hidden" name = "choose">
	<table border=1>
	<tr>
		<td colspan=2 align="center">출금정보</td>
	</tr>
	<tr>
		<td>출금계좌번호</td>
		<td>
<%-- 		 	<c:if test="${empty sessionScope.loginInfo}">
		 		<select name="account">
		 		<c:forEach items="${sessionScope.loginInfo.accountNo}" var="list" >
		 			
		 		</c:forEach>

		 			<option value="">계좌선택</option>
		 			
    				<option value="학생">학생</option>
		 		</select>
 			</c:if>    --%>

			<input type = "text" name = "account" id="account">
			
		</td>
	</tr>
	<tr>
		<td>계좌비밀번호</td><td><input type = "text" name = "password" id = "password"></td>
	</tr>
	<tr>
		<td>이체금액</td><td><input type = "text" name = "money" id = "money"></td>
	</tr>
	<tr>
		<td colspan=2 align="center">입금정보</td>
	</tr>
	<tr>
		<td>입금은행</td><td>캉뱅</td>
	</tr>
	<tr>
		<td>입금계좌번호</td><td><input type = "text" name = "otherAccount" id = "otherAccount"></td>
	</tr>

	</table>
	<span id="deposit"></span>
	<br>
	<input type = "submit" value = "계좌이체하기">
	
</form>    


