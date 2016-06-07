<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<c:if test="${empty sessionScope.loginInfo}">
	<script type="text/javascript">
		location.href = "home.bank";
	</script>
</c:if>

<script type="text/javascript">
	$(document).ready(function() { 
		/*비밀번호체크*/
		$("#transferForm :input[name='myaccountPass']").keyup(function(){


			$.ajax({
				type:"post",
				url:"checkPassword.bank",
				data:"accountPass="+$("#myaccountPass").val()+"&accountNo="+$("#account").val(),
				success:function(flag){
					if(flag==true){
						$("#passwordView").html("Correct").css("background","green");
					}else{
						$("#passwordView").html("False").css("background","red");
					}

					
			}
				
			});
		});
	
		/*form 입력란*/
		$("#transferForm").submit(function(){
			
			if ($("#transferForm :input[name='account']").val().trim()=="") {
				alert("출금계좌번호를 선택하세요");
				return false;
			}
			if ($("#transferForm :input[name='myaccountPass']").val().trim()=="") {
				alert("계좌비밀번호를 입력하세요");
				return false;
			}
			if ($("#transferForm :input[name='myaccountPass']").val().trim().length()!=4) {
				alert("계좌비밀번호는 4자리입니다.");
				return false;
			}
			if ($("#transferForm :input[name='money']").val().trim()=="") {
				alert("이체금액을 입력하세요");
				return false;
			}
			if ($("#transferForm :input[name='bank']").val().trim()=="") {
				alert("입금은행을 입력하세요");
				return false;
			}
			if ($("#transferForm :input[name='otheraccountNo']").val().trim()=="") {
				alert("입금계좌번호를 입력하세요");
				return false;
			}
			if($("#passwordView").html()=="false"){
				alert("11111111");
			}

		});
		
		
		/*잔액표시*/
		var balanceView;
		balanceView = document.getElementById("balanceView");
		$("#account").change(function(){
			var a= $("#account").val();
 			$.ajax({
				type:"post",
				url:"checkBalance.bank",
				data:"account="+$("#account").val(),
				dataType:"json",
				success:function(jsonData){
						$("#balanceView").html("현재 잔액은 "+jsonData);
						
						/*잔액vs이체금액*/
						$("#transferForm :input[name='money']").keyup(function(){
							var money="";
							money=$(this).val().trim();
							if(money>jsonData){
								alert("이체 가능한 금액을 넣어주세요.");
								return false;
							}
						});
						
				}
			}); 
		});
	});
</script>

<form action="transfer_transfer.bank" method="post" id="transferForm" >
	<h2>계좌이체</h2>
	<table border=1>
		<tr>
			<td colspan=2 align="center">출금정보</td>
		</tr>
		<tr>
			<td>출금계좌번호</td>
			<td>
				<select id="account" name="account">
					<option value="">계좌선택</option>
						<c:forEach items="${requestScope.accountList }" var="ac">
							<option value="${ac.accountNo}">${ac.accountNo}</option>
						</c:forEach>
				</select>
				<br>
				<span id="balanceView"></span>
			</td>
		</tr>
		<tr>
			<td>계좌비밀번호</td>
			<td><input type="password" name="myaccountPass" id="myaccountPass" size=1>
					<span id="passwordView"></span>
			</td>
		</tr>
		<tr>
			<td>이체금액</td>
			<td><input type="text" name="money" id="money">
					<br><span id="moneyView"></span>
			</td>
		</tr>
		<tr>
			<td colspan=2 align="center">입금정보</td>
		</tr>
		<tr>
			<td>입금은행</td>
			<td>
					<select id="bank" name="bank">
					<option value="">은행선택</option>
					<option value="캉뱅">캉뱅</option>
					<option value="문뱅">문뱅</option>
					<option value="소뱅">소뱅</option>
					<option value="지뱅">지뱅</option>
					</select>
			</td>
		</tr>
		<tr>
			<td>입금계좌번호</td> 
			<td><input type="text" name="otheraccountNo" id="otheraccountNo"></td>
		</tr>
	</table>
	<br> 
	<input type="submit" value="보안카드확인">
</form>


