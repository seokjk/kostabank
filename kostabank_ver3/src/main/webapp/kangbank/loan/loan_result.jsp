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
<h2>대출완료</h2>
<br><br>
<table class="transferinfo" >
      <tr id="tr">
         <td>선택상품</td>
         <td>대출계좌</td>
         <td>입금계좌</td>
         <td>출금계좌</td>
         <td>대출금액</td>
         <td>상환기간</td>
         <td>거치기간</td>
      </tr>
         <tr>
            <td>${requestScope.lvo.accountName}</td>
            <td>${requestScope.lvo.loanAccountNo}</td>
            <td>${requestScope.lvo.inAccountNo}</td>
            <td>${requestScope.lvo.outAccountNo}</td>
            <td>${requestScope.lvo.balance}</td>
            <td>${requestScope.lvo.repayTerm}</td>
            <td>${requestScope.lvo.stayTerm}</td>
         </tr>
</table>
<br><br>
<input type="button" value="흠으로" id="home">