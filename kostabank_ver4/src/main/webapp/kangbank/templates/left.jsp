<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
   $('#loginForm').submit(function() {
      var email = $(":input[name='email1']").val();
      var password = $(":input[name='password1']").val();
      if(email == ""){
         alert("email을 입력하세요");
         return false;
      }
      if(password == ""){
         alert("password를 입력하세요");
         return false;
      }   });
});
</script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
   <script type="text/javascript">
      var i = 9;
      var j = 59;
      function countDown(){
         if(j == -1){
            j = 59;
         }
         if(j == 0){
            i = i-1;
         }
         if(j < 10){
            $("#timeText").val("0"+i+":0"+j);
         } else{
            $("#timeText").val("0"+i+":"+j);
         }
         j = j-1;
         if(i == 0 && j == 0){
            alert("장시간 미접속으로 인하여 로그아웃되셨습니다.");
            location.href="customerLogout.bank";
         } else {
            setTimeout("countDown()",1000);
         }
      }
   </script>

<c:choose>
<c:when test="${empty sessionScope.loginInfo}">
   <form action="customerLogin.bank" method = "post" id = "loginForm">
   <table cellpadding='5'>
      <tr><td>
      email : <input type="email" name="email1">&nbsp;&nbsp;&nbsp;
      password : <input type="password" name="password1">&nbsp;&nbsp;&nbsp;
      </td>
         <td>
            <input type = "submit" value = "로그인" id="loginBtn">
         </td>
         <td>&nbsp;&nbsp;
            <a href = "find_idview.bank">아이디찾기</a>&nbsp;&nbsp;
         </td>
         <td>&nbsp;&nbsp;
            <a href = "find_passwordview.bank">비밀번호찾기</a>&nbsp;&nbsp;
         </td>
      </tr>
      </table>
   </form>
</c:when>
<c:otherwise>
<table cellpadding='5'>
<tr>
   <td>
         ${sessionScope.loginInfo.name}님 환영합니다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   </td>
   <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <body onload = "countDown()">
         남은시간 :&nbsp;&nbsp;&nbsp;<input type = "text"  style="background-color:#FFDC00; width:100px; height:35px; text-align:center;" id = "timeText" size=4>
      
         </body>&nbsp;&nbsp;&nbsp;
   </td>
   <td>&nbsp;&nbsp;&nbsp;
         <a href = "customerLogout.bank">로그아웃</a>&nbsp;&nbsp;&nbsp;
   </td>
   <td>&nbsp;&nbsp;&nbsp;
         <a href= "customer_updatePassCheck.bank">회원정보수정</a>&nbsp;&nbsp;&nbsp;
   </td>
</tr>
</table>
</c:otherwise>
</c:choose>