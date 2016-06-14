<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function(){
		var td = $('table tr td');
		td.each(function(){
			$(this).click(function(){
				location.href="questiontable.bank?section="+$(this).text()+"&page=1";
			});
		});
		
		$("#search").click(function(){
			if($("#questionsearch").val()==""){
				alert("검색하실 단어를 입력해주세요");
				return false;
			}else{
				location.href="questionsearch.bank?questionsearch="+$("#questionsearch").val()+"&page=1";
			}
		});
	}); 
</script>
<br>
<h2>무엇이든 물어보세요</h2><h5>고객님의 궁금증을 해결해드립니다</h5>
<hr>
<br>
<input id="questionsearch" type="text" name="questionsearch" size=50>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="검색" id="search">
<br><br>
<br><br>
<h4>CATEGORY</h4>
<table id="questioncategory">
	<tr>
		<td width="25%" class="td" >계좌이체</td>
		<td  width="25%"class="td" >적금</td>
		<td  width="25%"class="td" >대출</td>
		<td  width="25%"class="td" >보안카드</td>
	</tr>
</table>
<br><br>
<div id="questiondiv"></div>

<%-- <c:if test="${listVO.pagingBean.previousPageGroup}">
		<img src="${initParam.img}left_arrow_btn.gif" onclick="before()"></c:if>
	<c:forEach begin="${listVO.pagingBean.startPageOfPageGroup}" end="${listVO.pagingBean.endPageOfPageGroup}" var="i" >
		<a href="showlist.do?no=${i }">${i }</a> 
	</c:forEach>
	<c:if test="${listVO.pagingBean.nextPageGroup}">
		<img src="${initParam.img}right_arrow_btn.gif" onclick="next()"></c:if> --%>