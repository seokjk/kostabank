`<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   $("#accountName").change(function(){
      if($("#accountName").val()=="적금상품선택"){
         alert("적금상품을 선택하세요");
         $("#accountDetail").html("");
      }
      $.ajax({
         type:"post",
         url:"accountNameFindAccountList.bank",
         data:"accountName="+$("#accountName").val(),
         dataType:"json",
         success:function(vo){
            var str = "";
            str+="<table>";
            str+= "<tr><th>적금상품이름</th><td>"+vo.accountTypeVO.accountName+"</td></tr>";
            str+="<tr><th>설명</th><td>"+vo.accountTypeVO.accountExplanation+"</td></tr>"
            str+="<tr><th>월당이체금액</th><td>"+vo.accountTypeVO.minMoney+"</td></tr>"
            str+="<tr><th>최대계약기간</th><td>"+vo.term+"</td></tr>"
            str+="<tr><th>금리</th><td>"+vo.rates+"</td></tr>"
            str+="</table>"
            str+="<div id='savingsBtn'><input type ='image' src='${initParam.root}kangbank/img/savingsBtn.png'></div>";
            $("#accountDetail").html(str);
            $("#minMoney").html(vo.accountTypeVO.minMoney);
            $("#savingsBtn").click(function(){
               if(confirm("적금 생성 하시겠습니까?")){
                  location.href="savings_passCheck.bank?accountName="+$("#accountName").val()+"&minMoney="+vo.accountTypeVO.minMoney;  
               }
            });
         }
      });
   });
});
</script>
<div class="savingsView">
<c:choose>
   <c:when test="${empty loginInfo}">
      <script type="text/javascript">
         alert("로그인 하셔야 이용할 수 있습니다");
         location.href="home.bank";
      </script>
   </c:when>
<c:otherwise>
<h2>적금 상품</h2>
<br>
<div class="savingsTable">
   <table>
      <tr>
         <th>적금상품이름</th>
         <td>
            <select id = "accountName">
               <option value="적금상품선택">적금 상품 선택</option>
               <c:forEach items="${slist}" var = "s">
                  <option value="${s.accountName}">${s.accountName}</option>
               </c:forEach>
            </select>
         </td>
      </tr>
   </table>
   <br>
   </div>
   <span id = "accountDetail"></span>
</c:otherwise>
</c:choose>
</div>