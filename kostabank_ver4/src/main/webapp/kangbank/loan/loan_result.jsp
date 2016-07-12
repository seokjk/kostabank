<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
   $(document).ready(function(){
      $("#home").click(function(){
         location.href="home.bank";
      });
   });
</script>
<br>
<div class="result_css">
	<h2>대출완료</h2>
	<br><br>
	<table>
		<tr>
			<th>선택상품</th>
			<th>대출계좌</th>
			<th>입금계좌</th>
	        <th>출금계좌</th>
	        <th id="term_th">대출금액</th>
	        <th id="term_th">상환기간</th>
	        <th id="term_th">거치기간</th>
		</tr>
		<tr>
			<td>${requestScope.lvo.accountName}</td>
	        <td>${requestScope.lvo.loanAccountNo}</td>
	        <td>${requestScope.lvo.inAccountNo}</td>
	        <td>${requestScope.lvo.outAccountNo}</td>
	        <td id="term_td">${requestScope.lvo.balance}</td>
	        <td id="term_td">${requestScope.lvo.repayTerm}</td>
	        <td id="term_td">${requestScope.lvo.stayTerm}</td>
		</tr>
	</table>
	<br><br>
	<div id="result_button">
		<input type="button" value="흠으로" id="home">
	</div>
</div>