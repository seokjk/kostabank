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
<br>
<h2>적금생성 완료</h2>
<br><br>
<table>
<tbody>
<tr><th>계좌비밀번호</th><td>${accountVO.accountPass}</td></tr>
<tr><th>자동이체계좌</th><td>${accountVO.accountNo}</td></tr>
<tr><th>환급 계좌</th><td>${savingsVO.paybackNo}</td></tr>
<tr><th>상품</th><td>${accountVO.accountTypeVO.accountName}</td></tr>
<tr><th>월당이체금액</th><td>${savingsVO.monthlyPayment}</td></tr>
<tr><th>계약기간</th><td><span id="term"></span></td></tr>
<tr><th>금리(%)</th><td>${savingsVO.accountRatesVO.rates }</td></tr>
</tbody>
</table><br>
<input type="button" value="흠으로" id="home">