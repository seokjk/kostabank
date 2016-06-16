<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() { 

		/*상품관련...표시*/
		var goodsTemp;
		$("#goods").change(function(){
			var a= $("#goods").val();
			if(a==""){
				alert("대출상품을 선택해주세요!");
				return false;
			}else{
				$.ajax({
					type:"post",
					url:"checkLoan.bank",
					data:"goods="+$("#goods").val(),
					dataType:"json",
					success:function(jsonData){
						$("#overdueView").html("최고 한도: "+jsonData[0]);
						$("#repayTermView").html("최고 상환기간: "+jsonData[1]);
						$("#stayTermView").html("최고 거치기간: "+jsonData[2]);
						goodsTemp = jsonData;
					}
				}); 
			}
			
			/*최대한도vs입력금액*/
			$("#loanForm :input[name='overdue']").keyup(function(){
				var overdue="";
				overdue=$(this).val().trim();
				if(overdue>goodsTemp){
					alert("대출 가능한 금액을 넣어주세요.");
					$("#loanForm :input[name='overdue']").val("");
					return false;
				}
			});
			
		});
		
		
		/*상환기간표시*/

		var repayTermTemp;
		$("#repayTerm").change(function(){
			var a= $("#repayTerm").val();
			if(a==""){
				alert("상환기간을 적어주세요!");
				return false;
			}else{
				$.ajax({
					type:"post",
					url:"checkPeriod.bank",
					data:"repayTerm="+$("#repayTerm").val(),
					dataType:"json",
					success:function(jsonData){
						$("#repayTermView").html("최고 상환기간: "+jsonData);
						repayTermTemp = jsonData;
					}
				}); 
			}
			
			/*상환기간vs입력기간*/
			$("#loanForm :input[name='repayTerm']").keyup(function(){
				var repayTerm="";
				repayTerm=$(this).val().trim();
				if(repayTerm>repayTermTemp){
					alert("상환 가능한 기간을 넣어주세요.");
					$("#loanForm :input[name='repayTerm']").val("");
					return false;
				}
			});
			
		});
		
		
		/*form 입력란*/
		$("#loanForm").submit(function(){
			if("#inAccountNo"){
				
			}

		});
		
	});
</script>    
    
<form action="loan_insert.bank" method="post" id="loanForm" >
<br>
	<h2>대출</h2>
	<br><br>
	<table id="transfer">
		<tr id="tr">
			<td colspan=2 align="center">대출정보</td>
		</tr>
		<tr>
			<td>상품제목</td>
			<td>
				<select  class="no-border" id="goods" name="goods" >
					<option value="" >상품선택</option>
						<c:forEach items="${requestScope.atlist2 }" var="ac">
							<option value="${ac.accountName}">${ac.accountName}</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>입금될 계좌</td>
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
			<td>출금 계좌</td>
			<td>
				<select  class="no-border" id="outAccountNo" name="outAccountNo" >
					<option value="" >계좌선택</option>
						<c:forEach items="${requestScope.alist }" var="ac">
							<option value="${ac.accountNo}">${ac.accountNo}</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>대출금액</td>
			<td colspan=3><input  class="no-border" type="text" name="overdue" id="overdue" size=25>원
					<br><span id="overdueView" style="color:red;"></span>
			</td>
		</tr>
		<tr>
			<td>상환기간</td>
			<td><input class="no-border" type="text" name="repayTerm" id="repayTerm" size=25 >개월
					<br><span id="repayTermView" style="color:red;"></span>
			</td>
		</tr>
		<tr>
			<td>거치기간</td>
			<td><input class="no-border" type="text" name="stayTerm" id="stayTerm" size=25 >개월
					<br><span id="stayTermView" style="color:red;"></span>
			</td>
		</tr>
	</table>
	<br> <br><br>
	<input type="submit" id="securecheckBtn" value="보안카드확인">
</form>
    
    