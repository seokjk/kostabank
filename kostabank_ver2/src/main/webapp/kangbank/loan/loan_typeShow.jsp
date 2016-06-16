<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#loanName").change(function(){
			var loanName = $("#loanName").val();
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
						location.href="loan_view.bank";
					});
	            }
	         });
		});
	});
</script>
<table border="1">
	<tr>
		<td>대출 상품 이름</td>
		<td>
			<select name="loanName" id="loanName"  class="no-border" >
				<c:forEach items="${nameList}" var="name">
					<option value="${name}">${name}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
</table><br>
<div id="loanTable"></div>
