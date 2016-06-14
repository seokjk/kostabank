<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$("#subBtn").click(function(){
	alert("답글 완료");
});
</script>
<c:choose>
<c:when test="${empty loginInfo}">
<script type="text/javascript">
alert("로그인하셔야 이용 가능합니다.");
location.href="home.bank";
</script>
</c:when>
<c:otherwise>
<h1>답글</h1>
<form method = "post" action = "rePosting.bank" id = "rePostingForm">
 <div class="container">
<table>
<tr>
<td>질문타입</td><td><input type = "text" name = "qnaType" value ="${qnaType}" readonly="readonly"></td>
</tr>
<tr>
<td>번호</td><td><input type = "text" name = "qnaNo" value="${qnaNo}" size="3" readonly="readonly"></td>
</tr>
<tr>
<td>
이름</td><td><input type = "text" name = "customerVO.name" value="관리자" readonly="readonly"></td>
</tr>
<tr>                                
<td>메일</td><td><input type ="text" name = "customerVO.email" value = "sungyounet@naver.com" readonly="readonly">
</td>
</tr>
<tr>
<td>번호</td><td><input type="text" name = "customerVO.tel" size="12" value = "01041982978" readonly="readonly"></td>
</tr>
<tr>
<td>
제목</td><td><input type = "text" name = "qnaTitle" value = "└Re:" size="95"></td></tr>
<tr>
<td>
내용</td><td><textarea cols="100" rows="15" name="qnaContent"></textarea></td></tr>
<tr>
<tr>
<td colspan="2" align="center"><input type = "submit" id = "subBtn" value = "전송"></td>
</tr>
</table>
</div>
</form>
</c:otherwise>
</c:choose>
