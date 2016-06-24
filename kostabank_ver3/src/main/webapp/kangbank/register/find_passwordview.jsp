 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   $("#findPasswordForm :input[name=findId]").click(function(){
      location.href="find_idview.bank"
   });
   $("#findPasswordForm").submit(function(){
      var id = $("#findPasswordForm :input[name=email]").val();
      var accountNo = $("#findPasswordForm :input[name=accountNo]").val();
      var accountPass = $("#findPasswordForm :input[name=accountPass]").val();
      var password = $("#findPasswordForm :input[name=password]").val();
      var passwordCheck = $("#findPasswordForm :input[name=passwordCheck]").val();
      if(id==""){
         alert("메일을 입력하세요");
         return false;
      }if(accountNo==""){
         alert("계좌번호를 입력하세요");
         return false;
      }if(accountPass==""){
         alert("계좌 비밀번호를 입력하세요");
         return false;
      }if(password==""){
         alert("변경 비밀번호를 입력하세요");
         return false;
      }if(passwordCheck==""){
         alert("변경 비밀번호 확인을 입력하세요");
         return false;
      }if(password!=passwordCheck){
         alert("변경 비밀번호와 확인값이 일치하지 않습니다");
         return false;
      }
   })
});
</script> 
<div class="findPassword">
<h2>비밀번호 찾기</h2>
<br>
<p>비밀번호를 잊으셨나요?<br>
아래의 개인정보를 입력하시면 비밀번호 확인이 가능합니다.</p>
<form id="findPasswordForm" action="passwordChange.bank">
<div class="findPass">
<table>
      <tr>
         <th>이메일</th>
         <td colspan="3"><input type ="email" name ="email">
         <input type = "button" value = "이메일찾기" name="findId" id="emailBtn"></td>
      </tr>
      <tr>
         <th>출금 계좌번호</th>
         <td><input type ="text" name ="accountNo"></td>
         <th>계좌 비밀번호</th>
         <td><input type ="password" name ="accountPass" > (4자리 입력)</td>
      </tr>
      <tr>
         <th>새 비밀번호</th>
         <td><input type ="password" name ="password" ></td>
         <th>새 비밀번호 확인</th>
         <td><input type ="password" name ="passwordCheck" ></td>
      </tr>
</table>
</div>
<br>
<div id="passBtn">
<input type="image" src="${initParam.root}kangbank/img/passBtn.png">
</div>
</form>
</div>