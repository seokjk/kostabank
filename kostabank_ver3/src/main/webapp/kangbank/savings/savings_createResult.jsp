<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
            type : "post",
            url : "selectRatesBySeq.bank",
            data : "seq="+${savingsVO.accountRatesVO.accountSeq},
            dataType : "json",
            success : function(result){
              	$("#term").text(result);
            }
         });
		  $("#home").click(function(){
		         location.href="home.bank";
		 });
	});

</script>
<div class="result_css">
	<h2>적금생성 완료</h2>
	<br><br>
	<table>
		<tr>
			<th>적금 계좌</th>
			<th>자동 이체 계좌</th>
			<th>환급 계좌</th>
			<th>상품</th>
			<th>월당이체금액</th>
			<th id="term_th">계약기간</th>
			<th id="term_th">금리(%)</th>
		</tr>
		<tr>
			<td>${accountVO.accountNo}</td>
			<td>${savingsVO.automaticNo }</td>
			<td>${savingsVO.paybackNo}</td>
			<td>${accountVO.accountTypeVO.accountName}</td>
			<td>${savingsVO.monthlyPayment}</td>
			<td id="term_td"><span id="term"></span></td>
			<td id="term_td">${savingsVO.accountRatesVO.rates }</td>
		</tr>
	</table><br><br>
	<div id="result_button">
		<input type="button" value="흠으로" id="home">
	</div>
</div>