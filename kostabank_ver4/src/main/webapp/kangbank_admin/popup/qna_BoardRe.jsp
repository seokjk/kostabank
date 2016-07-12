<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#subBtn').click(function(){
			if(confirm("등록하시겠습니까?")){
				$.ajax({
			         type : "post",
			         url : "${initParam.root}rePosting.bank",
			         datatype : 'json',
			         data : {
			            qnaType : "${qvo.qnaType}",
			            qnaNo : "${qvo.qnaNo}",
			            email : "${qvo.customerVO.email}",
			            tel : "${qvo.customerVO.tel}",
			            qnaTitle : $(":input[name='qnaTitle']").val(),
			            qnaContent : $(":input[name='qnaContent']").val(),
			            qnaFileAddress : "${qvo.qnaFileAddress}"
			         },
			         success : function(result) {
			        	 $.ajax({
					         type : "post",
					         url : "${initParam.root}adminShowContent.bank",
					         datatype : 'json',
					         data : {
					        	 qnaNo : result
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
	<h1>답글</h1>
	<form method = "post" action = "rePosting.bank" id = "rePostingForm">
		<div class="container">
		<table>
			<tr>
				<td>질문타입</td>
				<td>
					<input type = "text" name = "qnaType" value ="${qvo.qnaType}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>번호</td>
				<td>
					<input type = "text" name = "qnaNo" value="${qvo.qnaNo}" size="3" readonly="readonly">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type = "text" name = "customerVO.name" value="${qvo.customerVO.name }" readonly="readonly"></td>
			</tr>
			<tr>                                
				<td>메일</td>
				<td><input type ="text" name = "customerVO.email" value = "${qvo.customerVO.email }" readonly="readonly"></td>
			</tr>
			<tr>
				<td>번호</td>
				<td><input type="text" name = "customerVO.tel" size="12" value = "${qvo.customerVO.tel }" readonly="readonly"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type = "text" name = "qnaTitle" value = "└Re:" size="95"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea cols="100" rows="15" name="qnaContent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type = "button" id = "subBtn" value = "전송"></td>
			</tr>
		</table>
		</div>
	</form>
