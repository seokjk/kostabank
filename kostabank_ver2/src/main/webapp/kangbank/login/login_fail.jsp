<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
if("${requestScope.failCount}" <= 3) {
	alert("로그인 ${requestScope.failCount}회 실패입니다.");
	location.href = "home.bank";
} else {
	alert("로그인 3회이상 실패입니다.");
	location.href = "find_passwordview.bank";
}
</script>
</body>
</html>