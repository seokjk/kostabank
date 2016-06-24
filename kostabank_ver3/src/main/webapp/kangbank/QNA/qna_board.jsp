<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
<c:when test="${empty loginInfo}">
<script type="text/javascript">
alert("로그인 하셔야 이용 가능합니다");
location.href="home.bank";
</script>
</c:when>
</c:choose>
<div class="qnaList" >
<br>
<h2>Q & A</h2>
<br><br>
<div class="qnaListIn">
<table id="qnatable">
<tr id="tr">
<td align="center" width = "10%">번호</td>
<td align="center" width = "20%">종류</td>
<td align="center" width = "60%">제목</td>
<td align="center" width = "10%">이름</td>
<td align="center" width = "10%">시간</td>
</tr>

<c:forEach items="${requestScope.lvo.qnaList}" var="c">
<tr>
<td align="center">${c.qnaNo}</td>
<td align="center">${c.qnaType}</td>
<td align="center"><a href="showContent.bank?fileName=${c.qnaFileAddress}&qnaNo=${c.qnaNo}">${c.qnaTitle}</a></td>
<td align="center">${c.customerVO.name}</td>
<td align="center">${c.qnaTime}</td>
<tr>
</c:forEach>
</table>
</div>
<br><br>

<p class="paging">
	<c:set var="pb" value="${requestScope.lvo.pagingBean}"></c:set>

	<c:if test="${pb.previousPageGroup}">
	<a href="qnaListRoad.bank?page=${pb.startPageOfPageGroup-1}&email=${loginInfo.email}">
	◀&nbsp; </a>	
	</c:if>
	<c:forEach var="i" begin="${pb.startPageOfPageGroup}" 
	end="${pb.endPageOfPageGroup}">
	<c:choose>
	<c:when test="${pb.nowPage!=i}">
	<a href="qnaListRoad.bank?page=${i}&email=${loginInfo.email}">${i}</a> 
	</c:when>
	<c:otherwise>
	${i}
	</c:otherwise>
	</c:choose>
	&nbsp;
	</c:forEach>	 

	<c:if test="${pb.nextPageGroup}">
	<a href="qnaListRoad.bank?page=${pb.endPageOfPageGroup+1}&email=${loginInfo.email}">
	▶</a>
	</c:if>
   </p>	
   
</div>   