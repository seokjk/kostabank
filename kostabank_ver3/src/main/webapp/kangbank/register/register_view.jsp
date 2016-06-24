<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
   $(":input[name='email']").keyup(function(){
      var email = $(":input[name='email']").val();
      $.ajax({
         type : "post",
         url : "checkEmail.bank",
         datatype : 'json',
         data : {
            email : email
         },
         success : function(result) {
            if(result){
               $("#emailCheck").text("사용 불가능한 email입니다").css("color", "red");
            } else {
               $("#emailCheck").text("");
            }
         }
      });
   });
   $(":input[value='인증하기']").click(function(){
      if($("#emailCheck").text() == "사용 불가능한 email입니다"){
         alert("email을 확인해주세요");
      } else {
         $.ajax({
            type : "post",
            url : "certificateEmail.bank?email="+$(":input[name='email']").val(),
            datatype : 'json',
            success : function(result) {
               window.open("kangbank/register/certificate_email.jsp?email="+$(":input[name='email']").val(), "email 인증", "width = 400, height = 200");
            }
         });
      }
   });
   $('#kangbank').click(function() {
      var email = $(":input[name='email']").val();
      var password = $(":input[name='password']").val();
      var password2 = $(":input[name='password2']").val();
      var name = $(":input[name='name']").val();
      var birth = $(":input[name='birth']").val();
      var tel = $(":input[name='tel']").val();
      var address = $(":input[name='address']").val();
      var checking = $(":input[name='checking']").val();
      if(email == ""){
         alert("email을 입력하세요");
         return false;
      }
      if(password == ""){
         alert("password를 입력하세요");
         return false;
      }
      if(password2 == ""){
         alert("check password를 입력하세요");
         return false;
      }
      if(password!=password2){
         alert("password가 다릅니다 확인해주세요");
         return false;
      }
      if(name == ""){
         alert("name을 입력하세요");
         return false;
      }
      if(birth == ""){
         alert("birth를 입력하세요");
         return false;
      }
      if(tel == ""){
         alert("tel을 입력하세요");
         return false;
      }
      if(address == ""){
         alert("address를 입력하세요");
         return false;
      }
      if(email != checking){
         alert("email 인증을 확인해주세요");
         return false;
      }
      if($("#emailCheck").text() == "사용 불가능한 email입니다"){
         alert("email을 확인해주세요");
         return false;
      }
      $("#registerForm").submit();
   });
   $(":input[name='home']").click(function() {
     location.href="home.bank";
   });
});
</script>
<div class="register">
   <h2>회원가입</h2><br>
   <div class="boardWrite">
   <form action="customerRegister.bank" method="post" id = "registerForm" name = "registerForm" >
      <input type = "hidden" name = "checking">
      <table border="1">
      <tbody>
      <tr>
         <th>email</th>
         <td>
            <input type = "email" name = "email">
            <input type = "button" value = "인증하기" id="emailBtn"><br>
            <span id="emailCheck"></span>
         </td>
      </tr>
      <tr>
         <th>password</th>
         <td><input type = "password" name = "password" id = "password"></td>
      </tr>
      <tr>
         <th>check password</th>
         <td><input type = "password" name = "password2" id = "password2"></td>
      </tr>
      <tr>
         <th>name</th>
         <td><input type = "text" name = "name" id = "name"></td>
      </tr>
      <tr>
         <th>birth</th>
         <td><input type = "text" name = "birth" id = "birth">&nbsp;&nbsp;ex) 911211</td>
      </tr>
      <tr>
         <th>tel</th>
         <td><input type = "text" name = "tel" id = "tel">&nbsp;&nbsp;ex) 01058722302</td>
      </tr>
      <tr>
         <th>address</th>
         <td><input type = "text" name = "address" id = "address"></td>
      </tr>
      </tbody>
      </table>
      <br>
      <div id="btn">
      <img src="${initParam.root}kangbank/img/signupbtn1.png" id="kangbank" style="cursor:pointer">
      <a href="home.bank"><img src="${initParam.root}kangbank/img/signupbtn2.png" ></a>
     </div>
   </form>
   </div>
</div>