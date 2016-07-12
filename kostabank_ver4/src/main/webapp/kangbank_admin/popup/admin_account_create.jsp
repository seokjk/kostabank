<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   $("#conBtn").click(function(){
      if($("#createForm :input[name=accountPass]").val()==""){
         alert("패스워드를 입력해주세요");
         return false;
      }if($("#createForm :input[name=accountPass]").val().length!=4){
         alert("패스워드는 4자리여야 합니다");
         $("#createForm :input[name=accountPass]").val("");
         return false;
      }if(parseInt($("#createView").html())>$("#createForm :input[name=balance]").val()){
         alert("최소금액보다 작습니다.");
         $("#createForm :input[name=balance]").val("");
         return false;
      }if(isNaN($("#createForm :input[name=accountPass]").val())){
         alert("비밀번호는 숫자 4자리로만 입력 할 수 있습니다.");
         $("#createForm :input[name=accountPass]").val("");
         return false;
      }if(isNaN($("#createForm :input[name=balance]").val())){
         alert("숫자로 입력하십시오.");
         $("#createForm :input[name=balance]").val("");
         return false;
      }if($("#createForm :input[name=balance]").val()==""){
         alert("기본금을 입력해주십시오.(0원 일시 0원으로 입력하세요)");
         return false;
      }
      $.ajax({
	         type : "post",
	         url : "${initParam.root}adminCreateAccount2.bank",
	         datatype : 'json',
	         data : {
	        	 email : $(":input[name='email']").val(),
	        	 password : $(":input[name='password']").val(),
	        	 accountName : $(":input[name='accountName']").val(),
	        	 balance : $(":input[name='balance']").val()
	         },
	         success : function(result) {
			        $('#accountList').text("");
			        $('#accountList').append(result);
	        		return false;
	         }
	      });
   });
});
</script>
<h2 align = "center">계좌생성</h2>
<div class="Kaccount_create_table">
	<form method = "post" action = "account_secure.bank" id = "createForm">
		<table>
			<tr>
               <th>E-Mail</th>
               <td><input type = "email" name="email" id="email" value = "${email}" readonly="readonly" class="email"></td>
            </tr>
            <tr>
               <th>계좌 비밀번호</th>
               <td><input type ="password" name="accountPass"></td>
            </tr>
            <tr>
               <th>계좌 종류</th>
               <td>
                  <select name="accountName" id="accountName">
                     <option value=""></option>
                     <c:forEach items="${tlist}" var="c">
                        <option value="${c.accountName}">${c.accountName}</option>
                     </c:forEach>
                  </select>
               </td>
            </tr>
            <tr>
               <th>기본금</th>
               <td><input type = "text" name = "balance"></td>
            </tr>
		</table>
		<br>
		<div id="button1">
            <input type ="button" id = "conBtn" value="가입">
		</div>
	</form>
</div>