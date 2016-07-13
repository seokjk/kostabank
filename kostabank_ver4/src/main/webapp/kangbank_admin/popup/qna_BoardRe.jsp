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
<div class="KqnaRe">
<h2>답글</h2><br><br>
	<form method = "post" action = "rePosting.bank" id = "rePostingForm">
		<table class="re">
			<tr>
				<th>질문타입</th>
				<td>
					<input type = "text" name = "qnaType" value ="${qvo.qnaType}" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<td>
					<input type = "text" name = "qnaNo" value="${qvo.qnaNo}" size="3" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type = "text" name = "customerVO.name" value="${qvo.customerVO.name }" readonly="readonly">
				</td>
			</tr>
			<tr>                                
				<th>이메일</th>
				<td>
					<input type ="text" name = "customerVO.email" value = "${qvo.customerVO.email }" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<td>
					<input type="text" name = "customerVO.tel" size="12" value = "${qvo.customerVO.tel }" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type = "text" name = "qnaTitle" value = "└Re:" size="95">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea cols="70" rows="15" name="qnaContent"></textarea>
				</td>
			</tr>
		</table>
		<br><br>
		<div id="location">
			<input type = "button" id = "subBtn" value = "전송">
		</div>
	</form>
</div>