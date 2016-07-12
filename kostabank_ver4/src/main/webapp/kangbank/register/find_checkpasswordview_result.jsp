<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:choose>
    <c:when test="${requestScope.a==1}">
    <script type="text/javascript">
    alert("비밀번호가 수정이 완료되었습니다");
	location.href="home.bank";
</script>
    </c:when>
    <c:otherwise>
    <script type="text/javascript">
    alert("이메일주소와 계좌번호 계좌비밀번호를 확인하세요")
	location.href="find_passwordview.bank";
</script>
    </c:otherwise>
    </c:choose>
    