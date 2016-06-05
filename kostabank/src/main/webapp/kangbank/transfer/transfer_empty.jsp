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

	
	$("#emptyForm").submit(function(){

    	
    });
	
});
    
    
</script>

이체정보
<hr>
<table border=1>
	<thead>
		<tr>
			<td>출금계좌</td><td>입금은행</td><td>입금계좌</td><td>받는분</td><td>이체금액</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td></td> <td></td> <td></td> <td></td> <td></td>
		</tr>
	</tbody>
</table>
<hr>
<form action="transfer_result.bank" method="post" id = "emptyForm" name = "emptyForm">
	<input type = "submit" value = "확인">
</form>


