<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#button").click(function(){
			history.back();
		});
	});
</script>
<div class="questionShow">
	<br><br>
	<h2>무엇이든 물어보세요</h2>
	<h5>고객님의 궁금증을 해결해드립니다</h5>
	<br><br>
	
	<p id="h2">Q</p>
	<p id="p">${requestScope.answerVO.title}</p><br>
	<p id="h2">A</p>
	<p id="p">${answerVO.contents}</p>
	
	<br><br><br>
	
	<input id="button" type="button" value="자주묻는질문으로 가기"> 
</div>