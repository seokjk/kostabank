<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="${initParam.root}kangbank/kangcss/home.css">
<div class="transferBestList">
	<form>
	<table>
		<tr>
			<th id="transferBestList_th1">이름</th>
			<th id="transferBestList_th2">계좌번호</th>
		</tr>
		<c:forEach items="${blist}" var="b">
			<tr>
				<td id="transferBestList_td1">
					<a href = "javascript:opener.document.transferForm.otheraccountNo.value='${b.otheraccountNo}'; self.close();" id = "account">${b.otheraccountName}</a>
				</td>
				<td id="transferBestList_td2">${b.otheraccountNo}</td>
			</tr>
		</c:forEach>
	</table>
	</form>
	<input type = "button" value = "닫기" onclick = "javascript:self.close()">
</div>