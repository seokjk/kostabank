<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#createForm").hide();
	$("#createBtn").click(function(){
		if($("#consentForm :radio[name=consent]:checked").val()=="assent"){
		if(confirm("계좌를 생성 하시겠습니까?")){
						$("#consentForm").hide();
						$("#createForm").show();
		}
			
			
		}if($("#consentForm :radio[name=consent]:checked").val()=="unassent"){
			alert("동의 하지 않으면 계좌를 생성 할 수 없습니다.");
		}
	});
	$("#conBtn").click(function(){
		if($("#createForm :input[name=accountPass]").val()==""){
			alert("패스워드를 입력해주세요");
			return false;
		}if($("#createForm :input[name=accountPass]").val().length!=4){
			alert("패스워드는 4자리여야 합니다");
			$("#createForm :input[name=accountPass]").val("");
			return false;
		}if(parseInt($("#createView").html())>$("#createForm :input[name=balance]").val()){
			alert("최소금액보다 작습니다.");
			$("#createForm :input[name=balance]").val("");
			return false;
		}if(isNaN($("#createForm :input[name=accountPass]").val())){
			alert("비밀번호는 숫자 4자리로만 입력 할 수 있습니다.");
			$("#createForm :input[name=accountPass]").val("");
			return false;
		}if(isNaN($("#createForm :input[name=balance]").val())){
			alert("숫자로 입력하십시오.");
			$("#createForm :input[name=balance]").val("");
			return false;
		}if($("#createForm :input[name=balance]").val()==""){
			alert("기본금을 입력해주십시오.(0원 일시 0원으로 입력하세요)");
			return false;
		}
		
	});
	$("#accountName").change(function(){
		$.ajax({
			type:"post",
			url:"minMoneyShow.bank",
			data:"accountName="+$("#accountName").val(),
			dataType:"json",
			success:function(minMoney){
				$("#createView").html(minMoney);
				$("#createForm :input[name=balance]").val(minMoney);
			
			}
		});
	});
});
</script>


<body>
  <form id = "consentForm">
  <textarea cols = "100" rows = "20" readonly="readonly">
◎ 개인 정보 수집 동의
 코스타뱅크에서는 고객 관리, 계약서 작성 등 서비스 제공을 위해
아래와 같은 최소한의 개인정보를 수집하고 있습니다.

1. 수집하는 개인정보의 항목
    이름, 주소, 전화번호, 휴대폰번호
2. 개인정보 수집 방법
    코스타 뱅크에서는 다음과 같은 방법으로 개인정보를 수집합니다.
   - 코스타 뱅크 관리자의 고객 확인
3. 개인 정보의 수집 및 이용 목적
    개인정보의 수집은 아래와 같은 목적을 위하여 수집하며 이외의 목적으로는 사용되지 않습니다.
   - 고객 관리를 위한 정보 활용
   - 계좌 유지 보수를 위한 정보 활용
4. 개인정보의 보유 및 이용기간
  저장된 개인정보는 수집 및 이용 목적이 달성되면 파기합니다.

◎ 개인 정보 3자 제공 안내
 코스타뱅크에서는 수집된 정보를 제 3자에게 제공하지 않습니다.

* 동의를 거부할 수 있으며, 동의 거부시 제공되는 서비스가 일부 제한 될 수 있습니다.

개인정보 수집자 코스타뱅크 회장 서트진스칸
  </textarea>
  <br>
  동의<input type = "radio" name = "consent" value="assent">
  미동의<input type = "radio" name = "consent" value = "unassent"><br>
  <input type = "button" id = "createBtn" value="이동">
  </form>
 <!-- method = "post"  -->
<form method = "post" action = "createAccount.bank" id = "createForm">
 <table border="1">
 <tr>
<td>E-Mail</td><td><input type = "email" name="customerVO.email" id="customerVO.email" value = "${cvo.email}" readonly="readonly"></td></tr>
<tr>
<td>
계좌 비밀번호</td><td><input type ="password" name="accountPass"></td></tr>
<tr>
<td>
계좌 종류</td><td><select name="accountName" id="accountName">
<option value=""></option>
<c:forEach items="${tlist}" var="c">
<option value="${c.accountName}">${c.accountName}</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>기본금</td><td><input type = "text" name = "balance"></td>
</tr>
<tr>
<td>
<input type ="submit" id = "conBtn" value="가입">
</td>
<td>
최소금액:
<span id = "createView"></span>
</td>
</table>
</form>
</body>
</html>