<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#checkBtn").click(function(){
		$.ajax({
			type : "post",
			url : "checkMemberPass.bank",
			data : "checkPass="+$(":input[name=checkPass]").val(),
			success : function(flag){
				if(flag==true){
					location.href="customer_updateCustomer.bank";
				}else{
					alert("비밀번호가 일치하지 않습니다.");
					$(":input[name=checkPass]").val("");
				}
			}
		})
	});
});
</script>

	<h2>비밀번호 체크</h2>
	<input type = "password" name = "checkPass"><br>
	<input type = "button" id = "checkBtn" value = "확인">
