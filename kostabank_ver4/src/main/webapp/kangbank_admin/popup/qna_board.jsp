<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${initParam.root}kangbank/kangcss/home.css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${initParam.root}kangbank_admin/kangbank_admin_css/w2ui-1.4.3.min.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="${initParam.root}kangbank_admin/kangbank_admin_js/w2ui-1.4.3.js"></script>
<link rel="stylesheet" type="text/css"
   href="${initParam.root}kangbank/kangcss/home.css">
<script>
$(document).ready(function(){
	$(".listBtn").click(function(){
		$.ajax({
		         type : "post",
		         url : "${initParam.root}adminQnaListRoad.bank",
		         datatype : 'json',
		         data : {
		            email : "${email}",
		            page : 1
		         },
		         success : function(result) {
		        	$('#accountList').text("");
		        	$('#accountList').append(result);
	        		return false;
		         }
		      });
	});
	$(".newList").click(function(){
		$.ajax({
		         type : "post",
		         url : "${initParam.root}adminQnaNoListRoad.bank",
		         datatype : 'json',
		         data : {
		            email : "${email}",
		            page : 1
		         },
		         success : function(result) {
		        	$('#accountList').text("");
		        	$('#accountList').append(result);
	        		return false;
		         }
		      });
	});
	$('.showKang').click(function() {
 			$.ajax({
		         type : "post",
		         url : "${initParam.root}adminShowContent.bank",
		         datatype : 'json',
		         data : {
		        	 qnaNo : $(":input[name='"+$(this).parent().parent().children('.showKang1').text()+"']").val(),
		        	 email : "${email}"
		         },
		         success : function(result) {
			        $('#accountList').text("");
			        $('#accountList').append(result);
	        		return false;
		         }
		      });
	});
});
</script>
<form name = "qnaList">
<div class="KqnaList">	
	<h2 align = "center"><a class = "listBtn" href = "#">Q & A</a> / <a class = "newList" href = "#">새로운 Q & A</a></h2>
	<br><br>
	<div>
		<table>
			<tr>
				<th id="QnAList_th">번호</th>
				<th id="QnAList_th">질문 종류</th>
				<th>제목</th>
				<th id="QnAList_th">이름</th>
				<th id="QnAList_th">시간</th>
			</tr>
			<c:forEach items="${requestScope.lvo.qnaList}" var="c">
				<tr>
					<td class = "showKang1" id="QnAList_td"><input class = "kangHidden" type = "hidden" name = "${c.no}" value = "${c.qnaNo }">${c.no}</td>
					<td id="QnAList_td">${c.qnaType}</td>
					<td>
						<a class = "showKang" href = "#" <%-- href="showContent.bank?fileName=${c.qnaFileAddress}&qnaNo=${c.qnaNo}" --%>>${c.qnaTitle}</a>
					</td>
					<td id="QnAList_td">${c.customerVO.name}</td>
					<td id="QnAList_td">${c.qnaTime}</td>
				<tr>
			</c:forEach>
		</table>
	</div>
<br><br>
	<div id="paging_location">
	<p>
		<c:set var="pb" value="${requestScope.lvo.pagingBean}"></c:set>
		<c:if test="${pb.previousPageGroup}">
			<a href="qnaListRoad.bank?page=${pb.startPageOfPageGroup-1}&email=${email}">◀&nbsp;</a>   
		</c:if>
		<c:forEach var="i" begin="${pb.startPageOfPageGroup}" end="${pb.endPageOfPageGroup}">
	   		<c:choose>
	   			<c:when test="${pb.nowPage!=i}">
	   				<a href="qnaListRoad.bank?page=${i}&email=${email}">${i}</a> 
	   			</c:when>
	   			<c:otherwise>${i}</c:otherwise>
	   		</c:choose>&nbsp;
		</c:forEach>    
   		<c:if test="${pb.nextPageGroup}">
   			<a href="qnaListRoad.bank?page=${pb.endPageOfPageGroup+1}&email=${email}"> ▶</a>
		</c:if>
	</p> 
	</div>  
</div>   
</form>