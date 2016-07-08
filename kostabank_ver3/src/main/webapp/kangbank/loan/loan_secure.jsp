<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
		int num=1;
		Random random = new Random();
		int f = random.nextInt(30);
		int s = random.nextInt(30);
		while(f==s){
			s=random.nextInt(30);
			if(f!=s){
				break;
			}
		}
%>	
<c:if test="${empty sessionScope.loginInfo}">
	<script type="text/javascript">
		location.href = "home.bank";
	</script>
</c:if>
	
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#cancle").click(function(){
			var c = confirm("대출을 취소하시겠습니까?");
			if(c){
				location.href="${initParam.root}home.bank";
			}else{
				return false;
			}
		});
		$("#loanBtn").click(function(){
			var c = confirm("대출하시겠습니까?");
			if(c){
				$.ajax({
		            type : "post",
		            url : "loanSecureCardCheck.bank",
		            data : $("#loansecurecheck").serialize(),
		            dataType : "json",
		            success : function(result){
		              	if(result.address=="noexistsecurecard"){
		              		alert("보안카드가 존재하지 않습니다");
		              		location.href="${initParam.root}home.bank";
		              	}else if(result.address=="loan_ok"){	
		              		alert("대출성공!");
		              		$("#loanForm").submit();
		              	}else if(result.address=="loannum_fail"){
		              		alert("오류횟수 5번 이체실패");
		              		alert("보안카드가 해지됩니다");
		              		location.href="deleteSecureCard.bank";
		              	}
		              	else{
		              		alert("보안카드가 일치하지 않습니다 [오류횟수 : "+result.cnt+"]");
		              		window.location.reload();
		              	}
		            }
		         });
			}else{
				return false;
			}
		});
		$("#dlf").keyup(function(){
			if($("#dlf").val().length==1){
				$("#dl").focus();
			}
		});
		$("#dl").keyup(function(){
			if($("#dl").val().length==1){
				$("#tka").focus();
			}
		});
		$("#tka").keyup(function(){
			if($("#tka").val().length==1){
				$("#tk").focus();
			}
		});
		$("#tk").keyup(function(){
			if($("#tk").val().length==1){
				$("#tk").blur();
			}
		});
	});
</script>
<div class="account_secure">
<div class="secure">
	<form id="loanForm" action="loansuccess.bank" method="post">
		<h2>대출 정보</h2>
		<br>
		<table>
			<tr>
				<th>선택상품</th>
				<th>입금계좌</th>
				<th>출금계좌</th>
				<th id="loan_balance_th">대출금액</th>
				<th id="loan_term_th">상환기간</th>
				<th id="loan_term_th">거치기간</th>
			</tr>
			<tr>
				<td><input type="hidden" name="accountName" value="${param.goods}">${param.goods}</td>
				<td><input type="hidden" name="inAccountNo" value="${param.inAccountNo}">${param.inAccountNo}</td>
				<td><input type="hidden" name="outAccountNo" value="${param.outAccountNo}">${param.outAccountNo}</td>
				<td id="loan_balance_td"><input type="hidden" name="balance" value="${param.overdue}">${param.overdue}</td>
				<td id="loan_term_td"><input type="hidden" name="repayTerm" value="${param.repayTerm}">${param.repayTerm}</td>
				<td id="loan_term_td"><input type="hidden" name="stayTerm" value="${param.stayTerm}">${param.stayTerm}</td>
			</tr>
		</table>
	</form>
	<br>
	<p style="color:red;,font-weight: bold;">
		※고객님이 입력하신 대출정보입니다.<br>
	    최종거래 전에 입금 은행 계좌번호와 이체금액, 받는분 성함을 다시 확인하여 주시기 바랍니다. 
	</p>
	<br>
	<div>
		<table class="secure_table1">
			<tr>
				<th colspan="10">
					KANGBANK&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NO.123456789
				</th>
			</tr>
			<c:forEach begin="1" end="6" step="1">
				<tr>
					<c:forEach begin="1" end="5" step="1">
						<td><%=num%></td>
							<%if(f+1==num){ %>
								<td bgcolor="yellow">□□**</td>
							<%}else if(s+1==num){ %>
								<td  bgcolor="yellow">**□□</td>
							<%}else{%>
								<td>* * * *</td>
							<%} %>
								<%num=num+1; %>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br><br>
	<form id="loansecurecheck" method="post">
		<input type="hidden" name="f" value="<%=f+1%>">
		<input type="hidden" name="s" value="<%=s+1%>">
		<div class="secure_table2">
			<table>
				<tr>
				<th><%=f+1%>번째 암호 중 앞 두자리</th>
				<td ><input  class="blueborder" type="text" id="dlf" name="dlf" size="1"></td>
				<td ><input  class="blueborder"  type="text" id="dl" name="dl" size="1"></td>
				<td><input class="no-border" type="text" size="1"  value="    *" readonly></td>
				<td><input class="no-border" type="text" size="1" value="    *" readonly></td>
				</tr>
				<tr>
					<th><%=s+1%>번째 암호 중 뒤 두자리</th>
					<td><input class="no-border" type="text" size="1" value="    *" readonly></td>
					<td><input class="no-border" type="text" size="1" value="    *" readonly></td>
					<td><input class="blueborder"  type="text" id="tka" name="tka" size="1"></td>
					<td><input class="blueborder"  type="text" id="tk" name="tk" size="1"></td>
				</tr>
			</table>
		</div>
		<br><br>
		<div class="button_location">
			<input type="button" id="loanBtn" value="대출하기">&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" id="cancle" value="대출취소">
		</div>
	</form>
</div>
</div>