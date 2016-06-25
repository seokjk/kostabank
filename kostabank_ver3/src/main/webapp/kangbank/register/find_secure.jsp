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
		$("#cancle").click(function(){
			var c = confirm("비밀번호 변경을 취소하시겠습니까?");
			if(c){
				location.href="${initParam.root}home.bank";
			}else{
				return false;
			}
		});
		$("#loanBtn").click(function(){
			var c = confirm("비밀번호를 변경하시겠습니까?");
			if(c){
				$.ajax({
		            type : "post",
		            url : "passwordsecurecheck.bank",
		            data : $("#passwordsecurecheck").serialize(),
		            dataType : "json",
		            success : function(result){
		              	if(result.address=="noexistsecurecard"){
		              		alert("보안카드가 존재하지 않습니다");
		              		location.href="${initParam.root}home.bank";
		              	}else if(result.address=="loan_ok"){	
		              		$("#passwordChange").submit();
		              	}else if(result.address=="loannum_fail"){
		              		alert("오류횟수 5번 이체실패");
		              		alert("보안카드가 해지됩니다");
		              		location.href="deleteSecureCard.bank";
		              	}
		              	else{
		              		alert("보안카드가 일치하지 않습니다 [오류횟수 : "+result.cnt+"]");
		              		window.location.reload();
		              	}
		            }
		         });
			}else{
				return false;
			}
		});
		$("#dlf").keyup(function(){
			if($("#dlf").val().length==1){
				$("#dl").focus();
			}
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

<br>
<h2>보안카드 확인</h2>
<br><br>

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
<form action="passwordChange.bank" id="passwordChange" method="post">
	<input type="hidden" name="email" value="${param.email}">
	<input type="hidden" name="password" value="${param.password}">
</form>
<form id="passwordsecurecheck" method="post">
<input type="hidden" name="email" value="${param.email}">
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
<input type="button" id="loanBtn" value="비밀번호 변경하기">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="cancle" value="대출취소">
</form>
    