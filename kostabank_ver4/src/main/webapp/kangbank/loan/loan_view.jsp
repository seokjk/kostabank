<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>

<script type="text/javascript">
   $(document).ready(function() { 
      
      /*상품관련...표시*/
      var overdueTemp=${lvo.maximumMoney};
      var repayTermTemp=${lvo.term};
      var stayTermTemp=${lvo.term/2};
      /*최대한도vs입력금액*/
      $("#loanForm :input[name='overdue']").keyup(function(){
         var overdue="";
         overdue=$(this).val().trim();
         if(isNaN(overdue)==true){
    			alert("숫자만 입력가능합니다.");
    			$("#loanForm :input[name='overdue']").val("");
    			return false;
    			}
         if(overdue>overdueTemp){
            alert("대출 가능한 금액을 넣어주세요.");
            $("#loanForm :input[name='overdue']").val("");
            return false;
         }
      });
      /*상환기간한도vs입력기간*/
      $("#loanForm :input[name='repayTerm']").keyup(function(){
         var repayTerm="";
         repayTerm=$(this).val().trim();
  	   if(isNaN(repayTerm)==true){
			alert("숫자만 입력가능합니다.");
			$("#loanForm :input[name='repayTerm']").val("");
			return false;
			}
         if(repayTerm>repayTermTemp){
            alert("가능한 상환기간을 넣어주세요.");
            $("#loanForm :input[name='repayTerm']").val("");
            return false;
         }
      });
      /*거치기간한도vs입력기간*/
      $("#loanForm :input[name='stayTerm']").keyup(function(){
         var stayTerm="";
         stayTerm=$(this).val().trim();
         if(isNaN(stayTerm)==true){
    			alert("숫자만 입력가능합니다.");
    			$("#loanForm :input[name='stayTerm']").val("");
    			return false;
    			}
         if(stayTerm>stayTermTemp){
            alert("가능한 거치기간을 넣어주세요.");
            $("#loanForm :input[name='stayTerm']").val("");
            return false;
         }
      });
         /*form 입력란*/
         $("#securecheckBtn").click(function(){
            if ($(":input[name='goods']").val().trim()=="") {
               alert("상품을 선택하세요");
               return false;
            }
            if ($(":input[name='inAccountNo']").val().trim()=="") {
               alert("입금될 계좌를 선택하세요");
               return false;
            }
            if ($(":input[name='outAccountNo']").val().trim()=="") {
               alert("지출될 계좌를 선택하세요");
               return false;
            }
            if ($(":input[name='overdue']").val().trim()=="") {
               alert("대출금액을 선택하세요");
               return false;
            }
            if ($(":input[name='repayTerm']").val().trim()=="") {
               alert("상환기간을 제대로 적어주세요");
               return false;
            }
            if ($(":input[name='stayTerm']").val().trim()=="") {
               alert("거치기간을 제대로 적어주세요");
               return false;
            }
            $("#loanForm").submit();
         });
   });
</script> 
<div class="loanTypeShow">
<div class="loanView">
   <h2>대출</h2>
   <br>
<form action="loan_secure.bank" method="post" id="loanForm" >
   <div class="loanView2">
   <table>
      <tr>
         <th>상품제목</th>
         <td>
               <input type="hidden" name="goods" value="${param.temp}">
               ${param.temp}
         </td>
      </tr>
      <tr>
         <th>입금될 계좌</th>
         <td>
            <select  class="no-border" id="inAccountNo" name="inAccountNo" >
               <option value="" >계좌선택</option>
                  <c:forEach items="${requestScope.alist }" var="ac">
                     <option value="${ac.accountNo}">${ac.accountNo}</option>
                  </c:forEach>
            </select>
         </td>
      </tr>
      <tr>
         <th>출금 계좌</th>
         <td>
            <select  class="no-border" id="outAccountNo" name="outAccountNo" >
               <option value="" >계좌선택</option>
                  <c:forEach items="${requestScope.alist2 }" var="ac">
                     <option value="${ac.accountNo}">${ac.accountNo}</option>
                  </c:forEach>
            </select>
         </td>
      </tr>
      <tr>
         <th>대출금액</th>
         <td colspan=3><input  class="no-border" type="text" name="overdue" id="overdue" size=20>원
               <br>최고 대출금액은 ${lvo.maximumMoney} 원입니다.
         </td>
      </tr>
      <tr>
         <th>상환기간</th>
         <td><input class="no-border" type="text" name="repayTerm" id="repayTerm" size=10 >개월
               <br>최대 상환기간은 ${lvo.term} 개월 입니다.
         </td>
      </tr>
      <tr>
         <th>거치기간</th>
         <td><input class="no-border" type="text" name="stayTerm" id="stayTerm" size=10 >개월
               <br>최대 거치기간은 <fmt:formatNumber value="${lvo.term/2}" pattern=""/>개월 입니다.            
         </td>
      </tr>
   </table>
   </div>
   <br>
   <div id="loanBtn">
   <input type="button" id="securecheckBtn" value="보안카드확인">
   </div>
</form>
</div>
</div>