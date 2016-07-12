<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#listBtn").click(function(){
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
		$("#reContent").click(function(){
			$.ajax({
			         type : "post",
			         url : "${initParam.root}qnaRePostingRoad.bank",
			         datatype : 'json',
			         data : {
			            qnaNo : "${qvo.qnaNo}",
			            qnaType : "${qvo.qnaType}"
			         },
			         success : function(result) {
			        	$('#accountList').text("");
			        	$('#accountList').append(result);
		        		return false;
			         }
			      });
		});
		$("#cancle").click(function(){
			if(confirm("삭제하시겠습니까?")){
				$.ajax({
			         type : "post",
			         url : "${initParam.root}adminDeleteRe.bank",
			         datatype : 'json',
			         data : {
			            email : "${email}",
			            qnaNo : "${qvo.qnaNo}"
			         },
			         success : function(result) {
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
			         }
			      });
			}
		});
	});
</script>
<div class="Kqna_showContent">
<h2 align = "center"><a class = "listBtn" href = "#">Q & A</a> / <a class = "newList" href = "#">새로운 Q & A</a></h2>
	<br><br>
	<form method = "post" action = "rePosting.bank" id = "rePostingForm">
		<table>
			<tr>
				<th>질문종류 : ${qvo.qnaType}</th>
				<th>작성시간 : ${qvo.qnaTime}</th>
			</tr>
			<tr>
				<th colspan = "2">제목 : ${qvo.qnaTitle}</th>
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
			<input type = "button" id = "listBtn" value = "목록" style="cursor:pointer">
			<input type = "button" id = "cancle" value ="삭제" style="cursor:pointer">
			<input type = "button" id = "reContent" value ="답글" style="cursor:pointer">
		</div>
	</form>
</div>