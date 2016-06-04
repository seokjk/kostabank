<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="${initParam.root }resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(":input[value='닫기']").click(function(){
		self.close();
	});
});
</script>

<form action="">
	${sessionScope.email} <input type = "button" value = "닫기">
</form>
