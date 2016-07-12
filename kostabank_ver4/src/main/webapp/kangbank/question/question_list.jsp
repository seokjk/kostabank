<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		
		$("#next").click(function(){
			location.href="questiontable.bank?section=${listVO.list[0].section}&page=${listVO.pagingBean.endPageOfPageGroup+1}";
		});
		
		$("#before").click(function(){
			location.href="questiontable.bank?section=${listVO.list[0].section}&page=${listVO.pagingBean.endPageOfPageGroup-1}";

		});
	}); 
</script>
<div class="question_list">
	<h2>무엇이든 물어보세요</h2>
	<h5>고객님의 궁금증을 해결해드립니다</h5>
	<br>
	<input id="questionsearch" type="text" name="questionsearch" size=50>&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="검색" id="search">
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
	<h2>${listVO.list[0].section}</h2><br>
	<div>
		<table>
			<tr>
				<th>번호</th>
				<th id="question_list_th">제목</th>
				<th>조회수</th>
			</tr>
			<%int num=1; %>
			<c:forEach items="${listVO.list}" var="list">
				<tr>
					<td><%=num%></td>
					<td id="question_list_td"><a  id="a"href="questionshowcontents.bank?no=${list.no}">${list.title }</a></td>
					<td>${list.hits }</td>
				</tr>
				<%num++; %>
			</c:forEach>
		</table>
	</div>
	<br><br>
	<div class="question_paging">
		<c:if test="${listVO.pagingBean.previousPageGroup}">
			<img src="${initParam.root}kangbank/img/left_arrow_btn.gif" id="before">
		</c:if>
		<c:forEach begin="${listVO.pagingBean.startPageOfPageGroup}" end="${listVO.pagingBean.endPageOfPageGroup}" var="i" >
			<a id="a" href="questiontable.bank?section=${listVO.list[0].section}&page=${i }">${i }</a> 
		</c:forEach>
		<c:if test="${listVO.pagingBean.nextPageGroup}">
			<img src="${initParam.root}kangbank/img/right_arrow_btn.gif" id="next">
		</c:if>
	</div>
</div>	
