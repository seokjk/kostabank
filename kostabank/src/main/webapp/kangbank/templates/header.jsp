<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${empty sessionScope.loginInfo}">
<h2><a href="${initParam.root}home.bank">KANG BANK</a></h2>
</c:when>
<c:otherwise>
<a href="${initParam.root}home.bank">KANG BANK</a>
<a href = "passwordCheck.bank">계좌 만들기</a>&nbsp;
<a href="${initParam.root}transfer_view.bank">계좌이체</a> &nbsp;
<a href="secure_view.bank">보안카드</a>&nbsp;
<a href = "accountTypeList.bank">계좌종류 &nbsp;</a>
<a href="accountTotalList.bank">계좌조회</a> &nbsp;
</c:otherwise>
</c:choose>
