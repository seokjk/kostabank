<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#listBtn").click(function(){
			location.href="qnaListRoad.bank?page=1&email=${qvo.customerVO.email}";
		});
		$("#subBoardBtn").click(function(){
			location.href="qnaRePostingRoad.bank?qnaNo=${qvo.qnaNo}&qnaType=${qvo.qnaType}";
		});
		$("#cancle").click(function(){
			if(confirm("삭제하시겠습니까?")){
				location.href="deleteRe.bank?qnaNo=${qvo.qnaNo}&email=${qvo.customerVO.email}";
			}
		});
	});
</script>
<div class="qna_showContent">
	<h2>Q & A</h2>
	<br><br>
	<c:choose>
		<c:when test="${empty loginInfo}">
   			<script type ="text/javascript">
   				alert("로그인을 하셔야만 이용가능합니다");
   				location.href="home.bank";
   			</script>
   		</c:when>
	</c:choose>
	<form method = "post" action = "rePosting.bank" id = "rePostingForm">
		<table>
			<tr>
				<th>NO.${qvo.no}</th>
				<th>작성시간 : ${qvo.qnaTime}</th>
			</tr>
			<tr>
				<th>질문종류 : ${qvo.qnaType}</th>
				<th>제목 : ${qvo.qnaTitle}</th>
			</tr>
			<tr>
				<td colspan="3"><pre id="qna_content_text">${qvo.qnaContent}</pre></td>
			</tr>
		</table>
		<c:if test="${!empty qvo.qnaFileAddress}">
			<img src="${initParam.root}${qvo.qnaFileAddress}">
		</c:if>
		<br><br>
		<div id="qna_button">
			<input type = "button" id = "listBtn" value = "목록" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type = "button" id = "cancle" value ="삭제">
		</div>
	</form>
</div>