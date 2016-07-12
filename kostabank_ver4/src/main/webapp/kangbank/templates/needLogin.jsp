<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty loginInfo}">
<script type="text/javascript">
alert("로그인 하셔야 사용 할 수 있습니다");
location.href="${initParam.root}home.bank";
</script>
</c:if>