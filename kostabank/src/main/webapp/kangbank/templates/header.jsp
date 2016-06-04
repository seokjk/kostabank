<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${empty sessionScope.loginInfo}">
<h2><a href="${initParam.root}home.bank">KANG BANK</a></h2>
</c:when>
<c:otherwise>
<a href="${initParam.root}home.bank">KANG BANK</a>
계좌생성 &nbsp;
계좌이체 &nbsp;
<a href="secure_view.bank">보안카드</a>&nbsp;
계좌종류 &nbsp;
</c:otherwise>
</c:choose>
