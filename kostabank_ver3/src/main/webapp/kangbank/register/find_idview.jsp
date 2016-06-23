<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(document).ready(function() {
   $("#btn").click(function(){
      var accountNo = $(":input[name='accountNo']").val();
      var accountPass = $(":input[name='accountPass']").val();
      var birth = $(":input[name='birth']").val();
      if(accountNo == ""){
         alert("계좌번호를 입력하세요");
      } else if(accountPass == ""){
         alert("계좌 비밀번호를 입력하세요");
      } else if(birth == ""){
         alert("생년월일을 입력하세요");
      } else {
         $.ajax({
            type : "post",
            url : "findId.bank",
            datatype : 'json',
            data : {
               accountNo : accountNo,
               accountPass : accountPass,
               birth : birth
            },
            success : function(result) {
               if(result[0]){
                  window.open("kangbank/register/findId_result.jsp?email="+result[1], "email 찾기", "width = 400, height = 200");
                  location.href = "customerLogout.bank";
               } else {
                  alert("일치하는 정보가 없습니다.");
                  $(":input[name='accountNo']").val("");
                  $(":input[name='accountPass']").val("");
                  $(":input[name='birth']").val("");
               }
            }
         });
      }
   });
});
</script>
<div class="findId">
<h2>email 찾기</h2><br>
<form>

<p>아이디를 잊으셨나요?<br>
가입자 유형을 선택하신 후, 아래의 개인정보를 입력하시면 아이디 확인이 가능합니다.</p>

<div class="findIdTable">
<table>
   <tr>
      <th>계좌번호</th>
      <td><input type = "text" name = "accountNo"></td>
   </tr>
   <tr>
      <th align="center">계좌 비밀번호</th>
      <td>
         <input type = "password" name = "accountPass" > (4자리 입력)
      </td>
   </tr>
   <tr>
      <th align="center">생년월일</th>
      <td>
         <input type = "text" name = "birth" > (예 : 1991년 1월 1일일 경우 910101)
      </td>
   </tr>
</table>
</div>
<br>
<div id="emailBtn">
   <img src="${initParam.root}kangbank/img/emailBtn.png"  id="btn" style="cursor:pointer">
</div>
</form>
</div>