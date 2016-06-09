<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${initParam.root}kangbank/notice/notice.css" type="text/css">    
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
    
    
    
    
    
    

<form method="post" id="updateForm" action="${initParam.root}notice_updateNotice.bank">
	<table class="content">
	<tr>
		<td>
			<table>
				<tr>
					<td>
					글번호: <input type=text name=no value=${nvo.no } readonly></input>
					| 타이틀:<input type=text id=title name=title value=${nvo.title }></input>					
					</td>
				<tr>
					<td>						
	<textarea rows="15" cols="75" id="content" name="content">${nvo.content }</textarea>
					</td>
				</tr>
				<tr>
				<td valign="middle">						
					<input type="submit" value="수정하기" class="action"></input>			
					</td>				
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>	    