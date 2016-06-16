<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>

<c:if test="${empty sessionScope.loginInfo}">
	<script type="text/javascript">
		location.href = "home.bank";
	</script>
</c:if>

<script type="text/javascript">
	$(document).ready(function() { 

		/*잔액표시*/
		var balanceView;
		var bartemp;
		balanceView = document.getElementById("balanceView");
		$("#account").change(function(){
			var a= $("#account").val();
			if(a==""){
				alert("계좌를 선택해주세요!!!");
				return false;
			}else{
				$.ajax({
					type:"post",
					url:"checkBalance.bank",
					data:"account="+$("#account").val(),
					dataType:"json",
					success:function(jsonData){
							$("#balanceView").html("현재 잔액은 "+jsonData);
							bartemp = jsonData;
							
					}
				}); 
			}
			
			/*잔액vs이체금액*/
			$("#transferForm :input[name='money']").keyup(function(){
				var money="";
				money=$(this).val().trim();
				if(money>bartemp){
					alert("이체 가능한 금액을 넣어주세요.");
					$("#transferForm :input[name='money']").val("");
					return false;
				}
			});
 			
		});
		
		/*form 입력란*/
		$("#transferForm").submit(function(){
			var passCheck=false;
			var otherAccountCheck=false;
			var accountCheck=false;
			
			/*계좌확인*/
			$.ajax({
				type:"post",
				url:"checkOtherAccount.bank",
				data:"otheraccountNo="+$("#otheraccountNo").val(),
				async:false,
				success:function(name){
					if(name==""){
						alert("보낼 계좌가 없습니다.");
						otherAccountCheck=true;
						return false;
					}else{
						otherAccountCheck=false;
					}
					if ($(":input[name='otheraccountNo']").val().trim()==$(":input[name='account']").val().trim()) {
						alert("자기자신에게 이체를 할 수 없습니다.");
						otherAccountCheck=true;
						return false;
					}else{
						otherAccountCheck=false;
					}			
				}
			});
			
			/*비밀번호체크*/
			if($("#myaccountPass").val()==""){
				var myaccountPass="1";
			}else{
				var myaccountPass=$("#myaccountPass").val();
			} 
			$.ajax({
				type:"post",
				url:"checkPassword.bank",
				data:"accountPass="+myaccountPass+"&accountNo="+$("#account").val(),
				async:false,
				success:function(flag){
					if(!flag){
						alert("비밀번호가 틀립니다.");
						passCheck=true;
						return false;
					}	
				}
			});
			
			if ($(":input[name='account']").val().trim()=="") {
				alert("출금계좌번호를 선택하세요");
				account=true;
				return false;
			}else{
				account=false;
			}
			if ($(":input[name='myaccountPass']").val().trim()=="") {
				alert("계좌비밀번호를 입력하세요");
				return false;
			}
 			if ($(":input[name='myaccountPass']").val().trim().length!=4) {
				alert("계좌비밀번호는 4자리입니다.");
				return false;
			} 
			if ($(":input[name='money']").val().trim()=="") {
				alert("이체금액을 입력하세요");
				return false;
			} 
			if ($(":input[name='bank']").val()=="" && $(":input[name='bank']").val()==null ) {
				alert("입금은행을 입력하세요");
				return false;
			}
			if ($(":input[name='otheraccountNo']").val().trim()=="") {
				alert("입금계좌번호를 입력하세요");
				return false;
			}
			if(passCheck){
				return false;
			}
			if(otherAccountCheck){
				return false;
			}
			if(accountCheck){
				return false;
			}

		});
		
	});
</script>

<form action="transfer_transfer.bank" method="post" id="transferForm" >
<br>
	<h2>계좌이체</h2>
	<br><br>
	<table id="transfer">
		<tr id="tr">
			<td colspan=4 align="center">출금정보</td>
		</tr>
		<tr>
			<td>출금계좌번호</td>
			<td>
				<select  class="no-border" id="account" name="account" >
					<option value="">계좌선택</option>
						<c:forEach items="${requestScope.accountList }" var="ac">
							<option value="${ac.accountNo}">${ac.accountNo}</option>
						</c:forEach>
				</select>
				<br>
				<span id="balanceView"></span>
			</td>
			<td>계좌비밀번호</td>
			<td><input class="no-border" type="password" name="myaccountPass" id="myaccountPass" size=25>
					<span id="passwordView"></span>
			</td>
		</tr>
		
		<tr>
			<td>이체금액</td>
			<td colspan=3><input  class="no-border" type="text" name="money" id="money" size=55>
					<br><span id="moneyView"></span>
			</td>
		</tr>
		<tr id="tr">
			<td colspan=4 align="center">입금정보</td>
		</tr>
		<tr>
			<td>입금은행</td>
			<td>
					<select class="no-border"  id="bank" name="bank">
					<option value="">은행선택</option>
					<option value="캉뱅">캉뱅으로 입금하기</option>
					<option value="문뱅">문뱅으로 입금하기</option>
					<option value="소뱅">소뱅으로 입금하기</option>
					<option value="지뱅">지뱅으로 입금하기</option>
					</select>
			</td>
			<td>입금계좌번호</td> 
			<td><input class="no-border" type="text" name="otheraccountNo" id="otheraccountNo" size=25></td>
		</tr>
	</table>
	<br> <br><br>
	<input type="submit" id="securecheckBtn" value="보안카드확인">
</form>