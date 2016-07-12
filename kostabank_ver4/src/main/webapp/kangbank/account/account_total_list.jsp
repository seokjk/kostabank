<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="accountTotalList">
<br>
<h2>예금조회</h2>
<br><br>
<div class="accountTotal">
<table>
	<tr>
		<th>계좌번호</th>
		<th>계좌명</th>
		<th>잔액</th>
	</tr>
	<c:forEach var="accountTotalList" items="${requestScope.accountTotalList }">
		<tr>
			<td><a href="transfer_view.bank?withdrawAccount=${accountTotalList.accountNo}">${accountTotalList.accountNo }</a><br>
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
</div>
</div>