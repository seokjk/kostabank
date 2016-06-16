<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#sd").change(function(){
		var startDay = $("#sd").val();
		  var now = new Date();
	      var nowYear  = now.getFullYear();
	      var nowMonth =(now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);            
	      var nowDay   = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
	      var today=nowYear+"-"+nowMonth+"-"+nowDay;  
	          if(startDay>today){
	        	  $("#sd").val("");
	        	  alert("현재보다 큰 시간은 선택할 수 없습니다");
	        	  return false;
	          }
	});//sd change
	$("#ed").change(function(){
		var endDay = $("#ed").val();
		var startDay = $("#sd").val();
		  var now = new Date();
	      var nowYear  = now.getFullYear();
	      var nowMonth =(now.getMonth()+1)>9 ? ''+(now.getMonth()+1) : '0'+(now.getMonth()+1);            
	      var nowDay   = now.getDate()>9 ? ''+now.getDate() : '0'+now.getDate();
	      var today=nowYear+"-"+nowMonth+"-"+nowDay;  
	          if(startDay>today||today<endDay){
	        	  alert("현재보다 큰 시간은 선택할 수 없습니다");
	        	  $("#bd").val("");
	        	  $("#ed").val("");
	        	  return false;
	          }
	          if(startDay>endDay){
					alert("계좌조회 시작일은 마감일보다 늦을수 없습니다");
					  $("#bd").val("");
		        	  $("#ed").val("");
					return false;
	          }
	});//ed change 
	
	var accountNo = "<%=request.getParameter("accountNo")%>";
	$("#dateForm").submit(function(){
		 var startDay = $("#sd").val();
		var endDay = $("#ed").val();
		var dealType = $("#dateForm :input[name=dealType]").val();
		var accountNo = "<%=request.getParameter("accountNo")%>";
		if(startDay.length==0){
			alert("시작일을 선택해주세요");
			return false;
		}if(endDay.length==0){
			alert("마감일을 선택해주세요");
			return false;
		}if(startDay>=endDay){
			alert("계좌조회 시작일은 마감일보다 늦을수 없습니다");
			return false;
		}
	});//form click1
	
	$("#dateForm :input[name=termInfo]").click(function(){
		var accountNo = "<%=request.getParameter("accountNo")%>";
		var dealType = $("#dateForm :input[name=dealType]:checked").val();
		location.href="dealDetailByDate_result2.bank?gapChecked="+$("#dateForm :input[name=termInfo]:checked").val()+"&dealType="+$("#dateForm :input[name=dealType]").val()+"&accountNo="+"<%=request.getParameter("accountNo")%>"+"&page=1"
	});//form click2
});//document
</script>
</head>
<body>
<br>
<h2>거래 내역</h2>
<br>
<p id="transferselect">
${param.accountNo} 거래 내역
<hr>
</p>

<h4>조회설정</h4><br>
<form action="dealDetailByDate_result.bank" id="dateForm">
<input type="hidden" name="accountNo" value="${param.accountNo}">
<select id="select" name="dealType">
   <option value="both" selected="selected">입/출금</option>
    <option value="deposit">입금</option>
    <option value="withdraw">출금</option>
</select><hr>
<input type="date" name="startDay" id="sd"> ~  <input type="date" name="endDay" id="ed">&nbsp;&nbsp;
<input type="submit" value="조회" id = "chekcBtn1"><br><hr>
<input type="hidden" name="page" value="1">
  <p id="account_create">
<input type="radio" name="termInfo" value="today">당일&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" name="termInfo" value="oneMonth">1개월&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" name="termInfo" value="threeMonth">3개월&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" name="termInfo" value="sixMonth">6개월&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio" name="termInfo" value="oneYear">1년
</p>
<span id="viewDetail"></span>
</form>
<hr>
</body>
</html>