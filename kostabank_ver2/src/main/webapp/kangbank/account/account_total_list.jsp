<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<br>
<h2>예금조회</h2>
<br><br>
<table id="accountsearch">
	<tr id="tr">
		<td>계좌번호</td>
		<td>계좌명</td>
		<td>잔액</td>
	</tr>
	<c:forEach var="accountTotalList" items="${requestScope.accountTotalList }">
		<tr>
			<td><a href="#">${accountTotalList.accountNo }</a><br>
					<!-- <input type="button" value="상세내역" id="detailBtn"> -->
					<a href="deal_dealDetailByDate.bank?accountNo=${accountTotalList.accountNo }">상세내역</a>
			</td>		
			<td>
				${accountTotalList.accountTypeVO.accountName } <br>
				신규일 : ${accountTotalList.issueDate }
			</td>
			<td>${accountTotalList.balance }</td>
		</tr>
		</c:forEach>
</table>