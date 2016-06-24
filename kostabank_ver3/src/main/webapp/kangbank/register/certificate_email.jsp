<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="${initParam.root}kangbank/kangcss/home.css">
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(":input[value='인증확인']").click(function(){
		if(<%=session.getAttribute("certificate")==null%>){
			alert("만료된 인증번호입니다.");
		} else if(<%=session.getAttribute("certificate")%> != $(":input[name='certificate']").val()){
			alert("잘못된 인증번호입니다.");
		} else {
			opener.document.registerForm.checking.value = "<%=request.getParameter("email")%>";
			self.close();
		}
	});
});
</script>
<form>
<div class="certificate_btn">
	<input type = "text" name = "certificate" id="certificate_input">
	<input type = "button" value = "인증확인" id="certificateBtn">
</div>
</form>