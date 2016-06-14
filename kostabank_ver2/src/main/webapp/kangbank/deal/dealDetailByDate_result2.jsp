<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	$("#chekcBtn1").submit(function(){
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
		/*$.ajax({
			type:"POST",
			url:"dealDetailByDate_result.bank",
			data:  {
				startDay : startDay,
				endDay : endDay,
				accountNo : accountNo,
				dealType : dealType
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
					html+="<tr><td>"+ deal.dealDetailList.DealDetailVO.dealNo +"</td>";				
					html+="<td>"+ deal.otherAccountNo +"</td>";	
					
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
		});//ajax */
		//location.herf="deal_dealDetailByDate_result.bank?startDay=startDay&endDay=endDay&dealType=dealType&accountNo=accountNo";
	});//form click1
	
	$("#dateForm :input[name=termInfo]").click(function(){
		var accountNo = "<%=request.getParameter("accountNo")%>";
		//var gapChecked= $("#dateForm :input[name=termInfo]:checked").val();
		var dealType = $("#dateForm :input[name=dealType]:checked").val();
/* 		$.ajax({
			type:"POST",
			url:"dealDetailByDate_result2.bank",
			data:  {
				gapChecked : gapChecked,
				accountNo : accountNo,
				dealType : $("#dateForm :input[name=transferType]").val()
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
		});//ajax*/
		location.href="dealDetailByDate_result2.bank?gapChecked="+$("#dateForm :input[name=termInfo]:checked").val()+"&dealType="+$("#dateForm :input[name=dealType]").val()+"&accountNo="+"<%=request.getParameter("accountNo")%>"+"&page=1"
	});//form click2
});//document
</script>
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
<hr><br>
<table id="transferlist">
<tr id="tr"><td align="center">No</td>
<td align="center">이체 계좌명</td>
<td align="center">입금</td>
<td align="center">출금</td>
<td align="center">거래일</td></tr>
<c:forEach items="${requestScope.dvo.dealList}" var="list">
<tr><td align="center">${list.dealNo}</td>
<td align="center">${list.name}</td>
<c:choose>
<c:when test="${list.dealType=='deposit'}">
<td align="center">${list.amountOfMoney}</td>
<td></td>
</c:when>
<c:otherwise>
<td></td>
<td align="center">${list.amountOfMoney}</td>
</c:otherwise>
</c:choose>
<td align="center">${list.dealDate}</td></tr>
</c:forEach>
</table>
<br><br>
<%-- 코드를 줄이기 위해 pb 변수에 pagingBean을 담는다. --%>
   <c:set var="pb" value="${requestScope.dvo.pagingBean}"></c:set>
   <!-- 
         step2 1) 이전 페이지 그룹이 있으면 이미지 보여준다. (img/left_arrow_btn.gif)
                     페이징빈의 previousPageGroup 이용 
               2)  이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
                      hint)   startPageOfPageGroup-1 하면 됨        
    -->      
   <c:if test="${pb.previousPageGroup}">
   <a href="dealDetailByDate_result2.bank?page=${pb.startPageOfPageGroup-1}&accountNo=<%=request.getParameter("accountNo")%>&dealType=<%=request.getParameter("dealType")%>&gapChecked=<%=request.getParameter("gapChecked")%>">
   <!-- <img src="img/left_arrow_btn.gif"> -->
   ◀&nbsp; </a>   
   </c:if>
   <!-- step1. 1)현 페이지 그룹의 startPage부터 endPage까지 forEach 를 이용해 출력한다
               2) 현 페이지가 아니면 링크를 걸어서 서버에 요청할 수 있도록 한다.
                  현 페이지이면 링크를 처리하지 않는다.  
                  PagingBean의 nowPage
                  jstl choose 를 이용  
                  예) <a href="list.do?pageNo=...">               
    -->      
   <c:forEach var="i" begin="${pb.startPageOfPageGroup}" 
   end="${pb.endPageOfPageGroup}">
   <c:choose>
   <c:when test="${pb.nowPage!=i}">
   <a href="dealDetailByDate_result2.bank?page=${i}&accountNo=<%=request.getParameter("accountNo")%>&dealType=<%=request.getParameter("dealType")%>&gapChecked=<%=request.getParameter("gapChecked")%>">
   ${i}
   </a>
   </c:when>
   <c:otherwise>
   ${i}
   </c:otherwise>
   </c:choose>
   &nbsp;
   </c:forEach>    
   <!-- 
         step3 1) 다음 페이지 그룹이 있으면 이미지(img/right_arrow_btn.gif) 보여준다. 
                     페이징빈의 nextPageGroup 이용 
               2)  이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
                      hint)   endPageOfPageGroup+1 하면 됨        
    -->   
   <c:if test="${pb.nextPageGroup}">
   <a href="dealDetailByDate_result2.bank?page=${pb.endPageOfPageGroup+1}&accountNo=<%=request.getParameter("accountNo")%>&dealType=<%=request.getParameter("dealType")%>&gapChecked=<%=request.getParameter("gapChecked")%>">
   ▶<!-- <img src="img/right_arrow_btn.gif"> --></a>
   </c:if>   