<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"
	href="${initParam.root}kangbank/kangcss/home.css">
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(":input[value='인증확인']").click(function(){
		var f = "<%=request.getParameter("flag")%>";
		if(f=="register"){
			if(<%=session.getAttribute("certificate")==null%>){
				alert("만료된 인증번호입니다.");
			} else if(<%=session.getAttribute("certificate")%> != $(":input[name='certificate']").val()){
				alert("잘못된 인증번호입니다.");
			} else {
				alert("보안카드가 메일로 발송되었습니다");
				opener.location.href="${initParam.root}registerSecureCard.bank";
				self.close();
			}
		}
		if(f=="reissue"){
			if(<%=session.getAttribute("certificate")==null%>){
				alert("만료된 인증번호입니다.");
			} else if(<%=session.getAttribute("certificate")%> != $(":input[name='certificate']").val()){
				alert("잘못된 인증번호입니다.");
			} else {
				alert("보안카드가 메일로 발송되었습니다");
				opener.location.href="${initParam.root}reissueSecureCard.bank";
				self.close();
			}
		}if(f=="deletecard"){
			if(<%=session.getAttribute("certificate")==null%>){
				alert("만료된 인증번호입니다.");
			} else if(<%=session.getAttribute("certificate")%> != $(":input[name='certificate']").val()){
				alert("잘못된 인증번호입니다.");
			} else {
				alert("보안카드가 해지되었습니다");
				opener.location.href="${initParam.root}deleteSecureCard.bank";
				self.close();
			}
		}
	});
});
</script>
<form>
<div class="certificate_btn">
	<input type = "text" name = "certificate">
	<input type = "button" value = "인증확인">
</div>
</form>