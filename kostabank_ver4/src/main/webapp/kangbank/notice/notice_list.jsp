<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>

<div class="noticePage">
	<h2>공지사항</h2>
	<br><br>
	<table>
		<tr>
			<th>NO</th>
         	<th id="notice_th">제목</th>
         	<th>이름</th>
         	<th>작성일</th>
         	<th>HIT</th>
		</tr>
		<c:forEach var="nvo" items="${requestScope.nlvo.noticeList}">   
			<tr>
				<td align="center">${nvo.no }</td>            
				<td align="center" id="notice_td">
					<c:choose>
						<c:when test="${sessionScope.loginInfo!=null && sessionScope.loginInfo.name==nvo.customerVO.name}">
	               			<a href="${initParam.root}notice_showContentNoHit.bank?no=${nvo.no }">${nvo.title }</a>
		           		</c:when>
		            	<c:when test="${sessionScope.loginInfo!=null && sessionScope.loginInfo.name!=nvo.customerVO.name}">
		               		<a href="${initParam.root}notice_showContent.bank?no=${nvo.no }">${nvo.title }</a>
		            	</c:when>
		            	<c:otherwise>
		            		<a href="${initParam.root}notice_showContent.bank?no=${nvo.no }">${nvo.title }</a>
		            	</c:otherwise>
	            	</c:choose>
            	</td>
            	<td align="center">${nvo.customerVO.name }</td>
            	<td align="center">${nvo.timePosted }</td>
            	<td align="center">${nvo.hits }</td>
			</tr>   
		</c:forEach>
 		<caption align="bottom">
			<c:if test="${sessionScope.loginInfo!=null}">
				<a href="${initParam.root}notice_write.bank">
					<img  id="write" src="${initParam.root}img/write_btn.jpg" border="0"  align="right">
				</a>
			</c:if>
		</caption>
	</table>
	<br><br>
	<div id="paging_location">   
		<p>
			<c:set var="pb" value="${requestScope.nlvo.pagingBean}"></c:set>
			<c:if test="${pb.previousPageGroup}">
	   			<a href="notice_list.bank?pageNo=${pb.startPageOfPageGroup-1}">◀&nbsp;</a>   
	  		</c:if>
	   		<c:forEach var="i" begin="${pb.startPageOfPageGroup}" end="${pb.endPageOfPageGroup}">
				<c:choose>
					<c:when test="${pb.nowPage!=i}">
						<a href="notice_list.bank?pageNo=${i}">${i}</a> 
					</c:when>
					<c:otherwise>${i}</c:otherwise>
	   			</c:choose>
	   			&nbsp;
	   		</c:forEach>    
	   		<c:if test="${pb.nextPageGroup}">
	   			<a href="list.do?pageNo=${pb.endPageOfPageGroup+1}"> ▶</a>
			</c:if>
		</p>
	</div>
</div>   
