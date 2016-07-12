<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${initParam.root}kangbank/kangcss/home.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${initParam.root}kangbank_admin/kangbank_admin_css/w2ui-1.4.3.min.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="${initParam.root}kangbank_admin/kangbank_admin_js/w2ui-1.4.3.js"></script>
<link rel="stylesheet" type="text/css"
   href="${initParam.root}kangbank/kangcss/home.css">
<script>
$(document).ready(function(){
	$('#close3').click(function(){
		$('#pop_background').fadeOut();
		$('#pop_accountList').fadeOut();
		$('#grid').fadeIn();
		return false;
	 });
	$('.kang').click(function() {
		var accountNo=$(this).text();
		if(confirm("계좌 내역을 확인하시겠습니까?")){
			$.ajax({
		         type : "post",
		         url : "${initParam.root}adminAccountDeal.bank",
		         datatype : 'json',
		         data : {
		        	 accountNo : accountNo
		         },
		         success : function(result) {
			        $('#accountList').text("");
			        $('#accountList').append(result);
	        		$('#pop_box').fadeOut();
	        		$('#pop_accountList').fadeIn();
	        		return false;
		         }
		      });
		}
	});
});
</script>
<div class="accountTotalListtwo">
<br>
<h2 align = "center">예금조회</h2>
<br><br>
<div class="accountTotal">
<table>
	<tr>
		<th>계좌번호</th>
		<th>계좌명</th>
		<th>잔액</th>
	</tr>
	<c:forEach var="accountTotalList" items="${requestScope.accountTotalList }">
		<tr>
			<td><a class = "kang" href="#">${accountTotalList.accountNo}</a></div>
			</td>		
			<td>
				${accountTotalList.accountTypeVO.accountName } <br>
				신규일 : ${accountTotalList.issueDate }
			</td>
			<td>${accountTotalList.balance }</td>
		</tr>
		</c:forEach>
</table>
</div>
</div>