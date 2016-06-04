<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 	<c:if test="${empty sessionScope.loginInfo}">
 	<script type="text/javascript">
		location.href="home.bank";
	</script>
 	</c:if>
 	
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
<a href="${initparam.root}home.bank">홈으로</a>



