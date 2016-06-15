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
<br>
<h2>Q & A</h2>
<br><br>
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
<br><br>
<%-- 코드를 줄이기 위해 pb 변수에 pagingBean을 담는다. --%>
	<c:set var="pb" value="${requestScope.lvo.pagingBean}"></c:set>
	<!-- 
			step2 1) 이전 페이지 그룹이 있으면 이미지 보여준다. (img/left_arrow_btn.gif)
				   		페이징빈의 previousPageGroup 이용 
				   2)  이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
				   	    hint)   startPageOfPageGroup-1 하면 됨 		 
	 -->      
	<c:if test="${pb.previousPageGroup}">
	<a href="qnaListRoad.bank?page=${pb.startPageOfPageGroup-1}&email=${loginInfo.email}">
	<!-- <img src="img/left_arrow_btn.gif"> -->
	◀&nbsp; </a>	
	</c:if>
	<!-- step1. 1)현 페이지 그룹의 startPage부터 endPage까지 forEach 를 이용해 출력한다
				   2) 현 페이지가 아니면 링크를 걸어서 서버에 요청할 수 있도록 한다.
				      현 페이지이면 링크를 처리하지 않는다.  
				      PagingBean의 nowPage
				      jstl choose 를 이용  
				      예) <a href="list.do?pageNo=...">				   
	 -->		
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
	<!-- 
			step3 1) 다음 페이지 그룹이 있으면 이미지(img/right_arrow_btn.gif) 보여준다. 
				   		페이징빈의 nextPageGroup 이용 
				   2)  이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
				   	    hint)   endPageOfPageGroup+1 하면 됨 		 
	 -->   
	<c:if test="${pb.nextPageGroup}">
	<a href="qnaListRoad.bank?page=${pb.endPageOfPageGroup+1}&email=${loginInfo.email}">
	▶<!-- <img src="img/right_arrow_btn.gif"> --></a>
	</c:if>