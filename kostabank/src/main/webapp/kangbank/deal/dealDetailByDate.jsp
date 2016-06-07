<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
					alert("시작일은 마감일보다 늦을수 없습니다");
					  $("#bd").val("");
		        	  $("#ed").val("");
					return false;
	          }
	});//ed change
	$("#chekcBtn1").click(function(){
		var startDay = $("#sd").val();
		var endDay = $("#ed").val();
		//var DayGap=endDay-startDay;
		var accountNo = <%=request.getParameter("accountNo")%>;
	
		alert(accountNo);
		$.ajax({
			type:"POST",
			url:"dealDetailByDate_result.bank",
			data:  {
				startDay : startDay,
				endDay : endDay,
				accountNo : accountNo
			  },
			dataType:"json",   
			success:function(data){
					var html="<table border='1'>";
					html+="<tr><td>"+"No"+"</td>";
					html+="<td>"+"이체 계좌번호"+"</td>";
					html+="<td>"+"입금"+"</td>";
					html+="<td>"+"출금"+"</td>";
					html+="<td>"+"거래일"+"</td></tr>";
				$.each(data, function(index, deal){
					
					html+="<tr><td>"+ deal.dealNo +"</td>";				
					html+="<td>"+ deal.otherAccountNo +"</td>";	
					//html+="<td>"+ deal.accountNo +"</td>";			
					/* if($("#dateForm :input[name=transferType]").val()=="deposit"&&
							deal.dealType=="deposit"){
						html+="<td>입금</td>";	
						html+="<td>"+""+"</td>";	
					} else if($("#dateForm :input[name=transferType]").val()=="withdraw"&&
							deal.dealType=="withdraw") {
						html+="<td>"+ " "+"</td>";	
						html+="<td>출금</td>";	
					} */
					if(deal.dealType=="deposit"){
						html+="<td>입금</td>";	
						html+="<td>"+""+"</td>";
					}else if(deal.dealType=="withdraw"){
						html+="<td>"+ " "+"</td>";	
						html+="<td>출금</td>";
					}
					html+="<td>"+ deal.dealDate +"</td></tr>";					
				})
				html+="</table>";
				$("#viewDetail").html(html);
			}//success function
		});//ajax
	});//form click1
	$("#dateForm :input[name=termInfo]").click(function(){
		var gapChecked= $("#dateForm :input[name=termInfo]:checked").val();
		//alert(gapChecked);
		var accountNo = <%=request.getParameter("accountNo")%>;
		$.ajax({
			type:"POST",
			url:"dealDetailByDate_result2.bank",
			data:  {
				gapChecked : gapChecked,
				accountNo : accountNo
			  },
			dataType:"json",   
			success:function(data){
				var html="<table border='1'>";
				html+="<tr><td>"+"No"+"</td>";
				html+="<td>"+"이체 계좌번호"+"</td>";
				html+="<td>"+"입금"+"</td>";
				html+="<td>"+"출금"+"</td>";
				html+="<td>"+"거래일"+"</td></tr>";
			$.each(data, function(index, deal){
				
				html+="<tr><td>"+ deal.dealNo +"</td>";				
				html+="<td>"+ deal.otherAccountNo +"</td>";	
				//html+="<td>"+ deal.accountNo +"</td>";			
				/* if($("#dateForm :input[name=transferType]").val()=="deposit"&&
						deal.dealType=="deposit"){
					html+="<td>입금</td>";	
					html+="<td>"+""+"</td>";	
				} else if($("#dateForm :input[name=transferType]").val()=="withdraw"&&
						deal.dealType=="withdraw") {
					html+="<td>"+ " "+"</td>";	
					html+="<td>출금</td>";	
				}*/
				if(deal.dealType=="deposit"){
					html+="<td>입금</td>";	
					html+="<td>"+""+"</td>";
				}else if(deal.dealType=="withdraw"){
					html+="<td>"+ " "+"</td>";	
					html+="<td>출금</td>";
				}
				html+="<td>"+ deal.dealDate +"</td></tr>";					
			})
			html+="</table>";
			$("#viewDetail").html(html);
		}//success function
		});//ajax
	});//form click2
});//document
</script>
</head>
<body>
==${param.accountNo} 거래 내역==

<br><br>
조회설정<br>
<form action="checking_dealDetailByDate.bank" id="dateForm">
<input type="date" name="startDate" id="sd"> ~  <input type="date" name="endDate" id="ed">
<select name="transferType">
   <option value="deposit" selected="selected">입/출금</option>
    <option value="deposit">입금</option>
    <option value="withdraw">출금</option>
</select>
<input type="button" value="조회" id = "chekcBtn1">
<br>
<input type="radio" name="termInfo" value="today">당일
<input type="radio" name="termInfo" value="oneMonth">1개월
<input type="radio" name="termInfo" value="threeMonth">3개월
<input type="radio" name="termInfo" value="sixMonth">6개월
<input type="radio" name="termInfo" value="oneYear">1년

<br><br>
<span id="viewDetail"></span>
</form>
</body>
</html>