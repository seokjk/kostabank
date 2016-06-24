<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${initParam.root}kangbank/kangcss/notice.css" type="text/css">  
<script src="${initParam.root}resources/jquery-1.12.4.min.js"></script>
<c:if test="${empty sessionScope.loginInfo}">
	<script type="text/javascript">
		location.href = "home.bank";
	</script>
</c:if>
    
<script type="text/javascript">
    $(document).ready(function(){
    	$("#updateForm").submit(function(){ 
    		if($("#title").val()==""){
    			alert("제목을 입력하세요!");
    			return false;
    		}
    		if($("#content").val()==""){
    			alert("본문을 입력하세요!");
    			return false;
    		}
    	});
    	$("#resetBtn").click(function(){    		
    		$("#write_form")[0].reset();
    	});
    });	
</script>    
    
    
    
    
    
    
<div class="noticeUpdate">
<br>
<h2>공지사항</h2>
<br><br>
<form method="post" id="updateForm" action="${initParam.root}notice_updateNotice.bank">
	<table class="content">
				<tr id="contr">
					<td>
						글번호
					</td>
					<td>
						<input type=text name=no value=${nvo.no } size=48 readonly ></input>			
					</td>
					<td>
						타이틀
					</td>
					<td>
						<input type=text id=title name=title value=${nvo.title } size=48></input>	
					</td>
				<tr>
					<td colspan=4>						
						<textarea rows="19" cols="129" id="content" name="content">${nvo.content }</textarea>
					</td>
				</tr>

				<caption align="bottom">
				<input type="submit" value="수정하기" class="action"></input>
				</caption>
			</table>
</form>	    
</div>