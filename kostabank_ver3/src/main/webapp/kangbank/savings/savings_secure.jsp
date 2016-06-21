<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	    
		int num=1;
		Random random = new Random();
		int f = random.nextInt(30);
		int s = random.nextInt(30);
		while(f==s){
			s=random.nextInt(30);
			if(f!=s){
				break;
			}
		}
%>	
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#cancel").click(function(){
			var c = confirm("적금생성을 취소하시겠습니까?");
			if(c){
				location.href="${initParam.root}home.bank";
			}else{
				return false;
			}
		});
		$("#createBtn").click(function(){
			var c = confirm("적금 생성하시겠습니까?");
			if(c){
				$.ajax({
		            type : "post",
		            url : "creatingSavingsSecureCardCheck.bank",
		            data : $("#securecheck").serialize(),
		            dataType : "json",
		            success : function(result){
		              	if(result.address=="noexistsecurecard"){
		              		alert("보안카드가 존재하지 않습니다");
		              		location.href="${initParam.root}home.bank";
		              	}else if(result.address=="creating_ok"){	
		              		alert("생성 성공!");
		              		location.href="${initParam.root}transfer_result.bank";
		              	}else if(result.address=="creating_fail"){
		              		alert("오류횟수 5번 이체실패");
		              		alert("보안카드가 해지됩니다");
		              		location.href="deleteSecureCard.bank";
		              	}else{
		              		alert("보안카드가 일치하지 않습니다 [오류횟수 : "+result.cnt+"]");
		              		window.location.reload();
		              	}}
		         });
			}else{
				return false;
			}
		});
		$("#dlf").keyup(function(){
			if($("#dlf").val().length==1){
				$("#dl").focus();}
		});
		$("#dl").keyup(function(){
			if($("#dl").val().length==1){
				$("#tka").focus();
			}
		});
		$("#tka").keyup(function(){
			if($("#tka").val().length==1){
				$("#tk").focus();
			}
		});
		$("#tk").keyup(function(){
			if($("#tk").val().length==1){
				$("#tk").blur();
			}
		});
	});
</script>

<table>
<tr>
<td>자동이체계좌</td>
<td>환급 계좌</td>
<td>상품 이름</td>
<td>월당이체금액</td>
<td>계약기간</td>
<td>금리(%)</td>
</tr>
<tr>
<td><%=request.getParameter("automaticNo") %></td>
<td><%=request.getParameter("paybackNo") %></td>
<td><%=request.getParameter("savingsName") %></td>
<td><%=request.getParameter("monthlyPayment") %></td>
<td><%=request.getParameter("savingsTerm") %></td>
<td><%=request.getParameter("rates") %></td>
</tr>
</table>
<p style="color:red;,font-weight: bold;">
※고객님이 입력하신 적금 상품 생성정보입니다.<br>
    최종생성 전에 자동이체계좌와 금리달에 환급 받을 계좌, 선택한 상품 이름, 월마다 빠져 나오는 금액, 계약기간, 금리를
    확인해주세요. 
</p>
<hr>
<br>
<table class="securecheck">
	<tr id="tr"><td colspan="10">KANGBANK&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NO.123456789</td></tr>
	<c:forEach begin="1" end="6" step="1">
		<tr>
			<c:forEach begin="1" end="5" step="1">
				<td><%=num%></td>
					<%if(f+1==num){ %>
						<td bgcolor="yellow">□□**</td>
					<%}else if(s+1==num){ %>
						<td  bgcolor="yellow">**□□</td>
					<%}else{%>
						<td>* * * *</td>
					<%} %>
						<%num=num+1; %>
			</c:forEach>
		</tr>
	</c:forEach>
</table>
<br><br>
<form id="securecheckForm" action="savingsSecureCardCheck.bank" method="post">
<input type="hidden" name="f" value="<%=f+1%>">
<input type="hidden" name="s" value="<%=s+1%>">
<table id="securecheckinput">
	<tr>
		<td><%=f+1%>번째 암호 중 앞 두자리</td>
		<td ><input  class="blueborder" type="text" id="dlf" name="dlf" size="1"></td>
		<td ><input  class="blueborder"  type="text" id="dl" name="dl" size="1"></td>
		<td><input class="no-border" type="text" size="1"  value="    *" readonly></td>
		<td><input class="no-border" type="text" size="1" value="    *" readonly></td>
	</tr>
	<tr>
		<td><%=s+1%>번째 암호 중 뒤 두자리</td>
		<td><input class="no-border" type="text" size="1" value="    *" readonly></td>
		<td><input class="no-border" type="text" size="1" value="    *" readonly></td>
		<td><input class="blueborder"  type="text" id="tka" name="tka" size="1"></td>
		<td><input class="blueborder"  type="text" id="tk" name="tk" size="1"></td>
	</tr>
</table>
<br><br>
<input type="button" id="createBtn" value="적금생성">&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" id="cancel" value="적금생성취소">
</form>
</html>