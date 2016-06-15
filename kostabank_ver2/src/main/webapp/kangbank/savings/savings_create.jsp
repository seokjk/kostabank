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
			if(confirm("홈으로 돌아가시겠습니까?")){
				location.href="home.bank";
			}
		}
	});
	$("#conBtn").click(function(){
		if($("#createForm :input[name=savingsPass]").val()==""){
			alert("패스워드를 입력해주세요");
			return false;
		}if($("#createForm :input[name=savingsPass]").val().length!=4){
			alert("패스워드는 4자리여야 합니다");
			$("#createForm :input[name=accountPass]").val("");
			return false;
		}if($("#createForm :input[name=automaticNo]").val()==""){
			alert("자동이체 계좌번호를 선택해주세요");
			return false;
		}if($("#createForm :input[name=savingsTerm]").val()==""){
			alert("계약기간을 선택해주세요");
			return false;
		}if($("#createForm :input[name=savingsRate]").val()==""){
			alert("금리를 선택해주세요");
			return false;
		}if($("#createForm :input[name=monthlyPayment]").val()==""){
			alert("월당 이체금액을 입력해주세요");
			return false;
		}if($("#createForm :input[name=paybackNo]").val()==""){
			alert("환급 계좌를 선택해주세요");
			return false;
		}
		$("#savingsTerm").change(function(){
			$.ajax({
				type:"post",
				url:"rateByTerm.bank",
				data:"savingsTerm="+$("#savingsTerm").val(),
				dataType:"json",
				success:function(rateData){
					$("#savingsRate").html(rateData);
					//$("#createForm :input[name=balance]").val(minMoney);
				}
			});
		});//savingsTerm.change
	});//conBtn.click
	$()
});
</script>
<body>
<br>
<h2>계좌생성</h2>
<br><br>
<c:choose>
<c:when test="${empty loginInfo}">
<script type ="text/javascript">
alert("로그인을 하셔야만 이용가능합니다");
location.href="home.bank";
</script>
</c:when>
</c:choose>
  <form id = "consentForm">
  <textarea id="textarea"cols = "100" rows = "20" readonly="readonly">
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
  <br><br>
  <p id="account_create">
  동의&nbsp;&nbsp;<input type = "radio" name = "consent" value="assent">&nbsp;&nbsp;&nbsp;&nbsp;
  미동의&nbsp;&nbsp;<input type = "radio" name = "consent" value = "unassent"><br><br>
  </p>
  <input type = "button" id = "createBtn" value="이  동">
  </form>
 <form>
 <table>
 <tr>
 <td>패스워드<input type="password" name="savingsPass"></td>
 </tr>
 <tr>
 <td>자동이체 계좌번호<input type="text" name="automaticNo"></td>
 </tr>
 <tr>
 <td>적금이름<input type="text" name="savingsName"></td>
 </tr>
 <tr>
 <td>계약기간<select name="savingsTerm">
 <option value="">기간 선택</option>
 <c:forEach items="${savingsList }" var="c">
 <option value="${c.savingsAccountNo }">${c.savingsAccountNo }</option>
 </c:forEach>
 </select>
 </td></tr>
 <tr>
 <td>금리<span id="savingsRate">%</span></td>
 </tr>
 <tr>
 <td>월당 이체 금액<input type="text" name="monthlyPayment"></td>
 </tr>
 <tr>
 <td>환급 계좌번호<input type="text" name="paybackNo"></td>
</tr>
 </table>
 </form>
</body>
</html>