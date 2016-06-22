<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#loanName").change(function(){
			var loanName = $("#loanName").val();
			if(loanName=="대출상품선택") {
     	    	  alert("대출상품을 선택하세요");
     	    	 $("#loanTable").html("");
     	    	  return false;
     	    }
			$.ajax({
	            type : "post",
	            url : "loanList.bank",
	            data : "accountName="+loanName,
	            success : function(result){
	              str="";
	              str+="<table><tr><td>대출 상품 이름</td><td>"+result.accountName+"</td></tr>";
	              str+="<tr><td>설명</td><td>"+result.accountExplanation+"</td></tr>";
	              str+="<tr><td>최고한도</td><td>"+result.maximumMoney+"</td></tr>";
	              str+="<tr><td>최고기간</td><td>"+result.term+"</td></tr>";
	              str+="<tr><td>추가이자</td><td>"+result.additionalRates+"</td></tr></table><br>";
	              str+="<input type='button' id='loan' value='대출하기'>";
	              $("#loanTable").html(str);
	              $("#loan").click(function(){
	                    temp=result.accountName;
	                  location.href="loan_viewpre.bank?temp="+temp;
	               });
	            }
	         });
		});
	});
	
</script>
<c:choose>
	<c:when test="${empty loginInfo}">
		<script type="text/javascript">
			alert("로그인 하셔야 이용할 수 있습니다");
			location.href="home.bank";
		</script>
	</c:when>
	<c:otherwise>
		<h1>대출</h1>
		<table border="1">
			<tr>
				<td>대출 상품 이름</td>
				<td>
					<select name="loanName" id="loanName"  class="no-border" >
						<option value="대출상품선택">대출상품선택</option>
						<c:forEach items="${nameList}" var="name">
							<option value="${name}">${name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table><br>
		<div id="loanTable"></div>
	</c:otherwise>
</c:choose>