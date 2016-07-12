<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#secure_register").click(function(){
			var a = ${sessionScope.loginInfo.security_card};
			if(a !=0){
				alert("이미 보안카드가 존재합니다");
				return;
			}else{
				var c = confirm("보안카드를 신청하시겠습니까?");
				if(c==false){
					return false;
				}else {
					$.ajax({
						type : "post",
						url : "certificateEmail.bank?email=${sessionScope.loginInfo.email}",
						datatype : 'json',
						success : function(result) {
							window.open("kangbank/secure/certificate_email.jsp?email=${sessionScope.loginInfo.email}&flag=register", "email 인증", "width = 400, height = 200");
						}
					});
				}
			}
			
		});
		$("#secure_reissue").click(function(){		
			var a = ${sessionScope.loginInfo.security_card};
			if(a ==0){
				alert("소지하고 계신 보안카드가 없습니다 보안카드를 생성해주세요");
				return;
			}else{
				var c = confirm("보안카드를 재발급 받으시겠습니까?");
				if(c==false){
					return false;
				}else {
					$.ajax({
						type : "post",
						url : "certificateEmail.bank?email=${sessionScope.loginInfo.email}",
						datatype : 'json',
						success : function(result) {
							window.open("kangbank/secure/certificate_email.jsp?email=${sessionScope.loginInfo.email}&flag=reissue", "email 인증", "width = 400, height = 200");
						}
					});
				}
			}
		});
		$("#secure_delete").click(function(){
			var a = ${sessionScope.loginInfo.security_card};
			if(a ==0){
				alert("소지하고 계신 보안카드가 없습니다 보안카드를 생성해주세요");
				return;
			}else{
				var c = confirm("보안카드를 해지 하시겠습니까?");
				if(c==false){
					return false;
				}else {
					$.ajax({
						type : "post",
						url : "certificateEmail.bank?email=${sessionScope.loginInfo.email}",
						datatype : 'json',
						success : function(result) {
							window.open("kangbank/secure/certificate_email.jsp?email=${sessionScope.loginInfo.email}&flag=deletecard", "email 인증", "width = 400, height = 200");
						}
					});
				}		
			}
		});
	});
</script>
${sessionScope.loginInfo.name}님 환영합니다<br>
<a href = "customerLogout.bank">로그아웃</a><br><br>
<input type="button" value="보안카드신청하기" id="secure_register"><br><br>
<input type="button" value="보안카드재발급" id="secure_reissue"><br><br>
<input type="button" value="보안카드해지" id="secure_delete"><br>
