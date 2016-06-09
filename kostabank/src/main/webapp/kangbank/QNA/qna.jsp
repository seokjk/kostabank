<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#qnaBtn").click(function(){
		if(confirm("이동하시겠습니까?")){
			location.href="qnaListRoad.bank?page=1";
		}
	});
	$("#sub_Btn").click(function(){		
		if($("#assent").prop("checked")==false){
			alert("동의하셔야만 QNA서비스를 이용할 수 있습니다");
			return false;
		}if($("#qnaForm :input[name=qnaTitle]").val==""){
			alert("제목을 입력하세요");
			return false;
		}if($("select#qnaType").val()==""){
			alert("말머리를 선택하세요");
			return false;
		}
		else{
			alert("문의 완료");
		}
	});
	
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
<c:when test="${empty loginInfo}">
<script type="text/javascript">
alert("로그인 하셔야 사용 할 수 있습니다");
location.href="home.bank";
</script>
</c:when>
</c:choose>
<form action ="QNAPosting.bank" method = "post" enctype="multipart/form-data" id = "qnaForm">
 <div class="container">
<table>
<tr>
<td>
이름</td><td><input type = "text" name ="customerVO.name" value = "${loginInfo.name}" readonly="readonly"></td></tr>
<tr><td>email</td><td><input type = "text" name = "customerVO.email" value = "${loginInfo.email}" readonly="readonly"></td></tr>
<tr><td>연락처</td><td><input type = "text" name = "customerVO.tel" value = "${loginInfo.tel}" readonly="readonly"></td></tr>
<tr>
<td>
질문 내용</td><td><select name = "qnaType" id="qnaType">
<option value = ""></option>
<option value = "계좌이체 오류">계좌이체 오류</option>
<option value = "계좌조회 오류">계좌조회 오류</option>
<option value = "거래내역 오류">거래내역 오류</option>
<option value = "상품문의">상품 문의</option>
</select></td></tr>
<tr>
<td>
제목</td><td><input type = "text" name = "qnaTitle" size="95"></td></tr>
<tr>
<td>
내용</td><td><textarea cols="100" rows="15" name="qnaContent"></textarea></td></tr>
<tr>
<td></td>
<td><input type = "file" name = "uploadFile"></td></tr>
<tr>
<td>
개인정보 수집/<br>
이용에 대한 동의</td><td><input type ="checkbox" id = "assent">동의합니다<br><textarea cols="100" rows="5" readonly="readonly">
* 수집 항목 : 아이디, 이메일, 연락처
* 수집 목적 : 서비스 이용에 따른 고객 문의, 불만처리, 오류해결 등 민원 처리 및 결과 회신
* 보유 및 이용 기간 : 문의처리 후 5년간 보관
※그 밖의 사항은 개인정보취급방침을 준수합니다.
</textarea></td></tr>
<tr><td></td><td align="center"><input type ="submit" id = "sub_Btn" value = "등록">
<input type ="button" id = "qnaBtn" value = "목록">
</td></tr>
</table>
</div>
</form>
</body>
</html>