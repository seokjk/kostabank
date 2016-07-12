<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
 	<c:if test="${empty sessionScope.loginInfo}">
 	<script type="text/javascript">
		location.href="home.bank";
	</script>
 	</c:if>
<script type="text/javascript">
	$(document).ready(function(){
		$("#home").click(function(){
			location.href="home.bank";
		});
		$("#transfer_view").click(function(){
			location.href="transfer_view.bank";
		});
	});
    function noEvent(){
        if (event.keyCode == 116) {
            event.keyCode = 2;
            return false;  
        }  
        else if(event.ctrlKey && (event.keyCode == 78 || event.keyCode ==82))
        {
            return false; 
        }   
    }  
   document.onkeydown = noEvent;
</script>
<br>
<div class="transfer_view">
	<h2>계좌 이체</h2>
	<br><br>
	<p style="color:red; font-size:16px;">아래와 같은 내용으로 이체가 정상적으로 처리되었습니다.!</p><br>
	<table class="transfer_result">
		<tr>
			<th>출금계좌</th>
			<th id="bank">입금은행</th>
			<th>입금계좌</th>
			<th id="youName">받는분</th>
			<th>이체금액</th>
			<th>이체 후 잔액</th>
		</tr>
		<tr>
			<td>${tvo.account}</td>
			<td id="bank">${tvo.bank}</td>
			<td>${tvo.otheraccountNo}</td>
			<td id="youName">${youName}</td>
			<td>${tvo.money}</td>
			<td>${afterMoney}</td>
		</tr>
	</table><br><br>
<div id="transfer_button">
	<input type="button"  value="홈"  id="home">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button"  value="추가이체" id="transfer_view">
</div>
</div>



