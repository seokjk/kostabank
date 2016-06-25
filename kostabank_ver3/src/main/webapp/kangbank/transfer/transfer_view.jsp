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
   $(document).ready(function(){ 
      /*잔액표시*/
      var balanceView;
      var bartemp;
      balanceView = document.getElementById("balanceView");
      if($("#account").val()!=""){
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
            if(isNaN(돈)==true){
               alert("숫자만 입력가능합니다.");
               $("#transferForm :input[name='money']").val("");
               return false;
            }
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
            
            if ($(":input[name='account']").val().trim()=="") {
               alert("출금계좌번호를 선택하세요");
               return false;
            }
            
                         
             /*비밀번호체크*/
            if($("#myaccountPass").val()==""){
               var myaccountPass="ps check";
            }else{
               var myaccountPass=$("#myaccountPass").val();
            } 
             if(isNaN(myaccountPass)==true){
                alert("숫자만 입력해주세요.");
                $(":input[name='myaccountPass']").val("");
                passCheck=true;
               return false;
             }
            $.ajax({
               type:"post",
               url:"checkPassword.bank",
               data:"accountPass="+myaccountPass+"&accountNo="+$("#account").val(),
               async:false,
               success:function(flag){                  
                  if(!flag){
                     alert("비밀번호가 틀렸습니다. 다시 입력해주세요.");
                     passCheck=true;
                     return false;
                  }   
               }
            });
            
             if ($(":input[name='myaccountPass']").val().trim().length!=4) {
               alert("계좌비밀번호는 4자리입니다.");
               return false;
            }
             if(isNaN($(":input[name='money']").val().trim())==true){
               alert("숫자만 입력가능합니다.");
               $("#transferForm :input[name='money']").val("");
               return false;
            }
            if ($(":input[name='money']").val().trim()=="") {
               alert("이체금액을 입력하세요");
               return false;
            }   
            
            
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
                  $(":input[name='otheraccountNo']").val("");
                  otherAccountCheck=true;
                  return false;
               }else{
                  otherAccountCheck=false;
               }         
            }
         });
            
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
      $(":input[value=최근계좌]").click(function(){
         if($("#account").val() == ""){
            alert("출금 계좌번호를 선택하세요");
            return false;
         }
         window.open("recentAccountNo.bank?accountNo="+$("#account").val(), "최근계좌", "width = 450, height = 450");
      });
   });
</script>
<div class="transfer_view">
<form name = "transferForm" action="transfer_transfer.bank" method="post" id="transferForm" >
<br>
	<h2>계좌이체</h2>
	<br>
	<h1>출금정보</h1><br>
	<div class="transfer_view_table">
	<table>
		<tr>
			<th>출금계좌번호</th>
			<td><select id="account" name="account" >
					<option value="">계좌선택</option>
						<c:forEach items="${requestScope.accountList }" var="ac">
						<c:choose>
						<c:when test="${withdrawAccount == ac.accountNo}">
							<option value="${ac.accountNo}" selected="selected">${ac.accountNo}</option>
						</c:when>
						<c:otherwise>
							<option value="${ac.accountNo}">${ac.accountNo}</option>
						</c:otherwise>
						</c:choose>
						</c:forEach>
				</select><br>
				<span id="balanceView"></span>
			</td>
			<th>계좌비밀번호</th>
				<td><input type="password" name="myaccountPass" id="myaccountPass">
				<span id="passwordView"></span>
			</td>
		</tr>
		
		<tr>
			<th>이체금액</th>
			<td colspan="3">
				<input type="text" name="money" id="money" size="40%">
				<span id="moneyView"></span>
			</td>
		</tr>
		</table>
		
		<h1>입금정보</h1><br>
		
		<table>
		<tr>
			<th>입금은행</th>
			<td>
				<select id="bank" name="bank">
					<option value="">은행선택</option>
					<option value="KANG">KANG BANK으로 입금하기</option>
					<option value="SON">SON BANK으로 입금하기</option>
					<option value="LA">LA BANK으로 입금하기</option>
					<option value="YOO">YOO BANK으로 입금하기</option>
				</select>
			</td>
			<th>입금계좌번호</th> 
			<c:choose>
			<c:when test="${requestScope.depositAccount!=null}">
			<td><input type="text" name="otheraccountNo" id="otheraccountNo" value="${requestScope.depositAccount }">
				<input id="buttonAccount" type="button" value="최근계좌">
			</td>
			</c:when>
			<c:otherwise>
			<td>
				<input type="text" name="otheraccountNo" id="otheraccountNo">&nbsp;&nbsp;
				<input id="buttonAccount" type="button" value="최근계좌">
			</td>
			</c:otherwise>
			</c:choose>
			
		</tr>
	</table>
	</div>
	<br> 
	<div class="transfer_view_button">
	<input type="submit" id="securecheckBtn" value="보안카드확인">
	</div>
</form>
</div>