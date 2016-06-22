<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(document).ready(function() {
   $(":input[value='아이디찾기']").click(function(){
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
<form>
<h1>email 찾기</h1><hr>
<p style="color:blue; font-size:16px;">아이디를 잊으셨나요?<br>
가입자 유형을 선택하신 후, 아래의 개인정보를 입력하시면 아이디 확인이 가능합니다.</p>
<hr>
<table id="resultTable">
      <tr>
         <th align="center" width = "25%">계좌번호</th>
         <td width = "75%"><input type = "text" name = "accountNo"></td>
      </tr>
      <tr>
         <th align="center">계좌 비밀번호</th>
         <td><input type = "password" name = "accountPass" size = 7> (4자리 입력)</td>
      </tr>
      <tr>
         <th align="center">생년월일</th>
         <td><input type = "text" name = "birth" size = 10> (예 : 1991년 1월 1일일 경우 910101)</td>
      </tr>
</table>
<h3 align = "center"><input type = "button" value = "아이디찾기"></h3>
</form>