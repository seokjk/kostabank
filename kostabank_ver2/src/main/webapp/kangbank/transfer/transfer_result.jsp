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
</script>
<br>
<h2>계좌 이체</h2>
<br><br>
<p style="color:red; font-size:16px;">아래와 같은 내용으로 이체가 정상적으로 처리되었습니다.!</p><br>
<hr>
<table id="resultTable">

		<tr id="tr">
			<td align="center">출금계좌</td><td align="center">입금은행</td><td align="center">입금계좌</td><td align="center">받는분</td><td align="center">이체금액</td><td align="center">이체 후 잔액</td>
		</tr>
		<tr>
			<td align="center">${sessionScope.tvo.account}</td>
			<td align="center">${sessionScope.tvo.bank}</td>
			<td align="center">${sessionScope.tvo.otheraccountNo}</td>
			<td align="center">${youName}</td>
			<td align="center">${sessionScope.tvo.money}</td>
			 <td align="center">${afterMoney}</td>
		</tr>

</table><br><br>
<input type="button"  value="홈"  id="home">
<input type="button"  value="추가이체" id="transfer_view">




