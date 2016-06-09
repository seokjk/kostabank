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
 	
아래와 같은 내용으로 이체가 정상적으로 처리되었습니다.!
<hr>

<table border=1>
	<thead>
		<tr>
			<td>출금계좌</td><td>입금은행</td><td>입금계좌</td><td>받는분</td><td>이체금액</td><td>이체 후 잔액</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td></td> <td></td> <td></td> <td></td> <td></td> <td></td>
		</tr>
	</tbody>
</table>
<input type="button"  value="홈"  id="home">
<input type="button"  value="추가이체" id="transfer_view">




