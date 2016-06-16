<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(document).ready(function() {
	$(":input[value='아이디찾기']").click(function(){
		var name = $(":input[name='name']").val();
		var birth = $(":input[name='birth']").val();
		var tel = $(":input[name='tel']").val();
		if(name == ""){
			alert("이름을 입력하세요");
		} else if(birth == ""){
			alert("생년월일을 입력하세요");
		} else if(tel == ""){
			alert("전화번호를 입력하세요");
		} else {
			$.ajax({
				type : "post",
				url : "findId.bank",
				datatype : 'json',
				data : {
					name : name,
					birth : birth,
					tel : tel
				},
				success : function(result) {
					if(result[0]){
						window.open("kangbank/register/findId_result.jsp?email="+result[1], "email 찾기", "width = 400, height = 200");
						location.href = "customerLogout.bank";
					} else {
						alert("일치하는 정보가 없습니다.");
						$(":input[name='name']").val("");
						$(":input[name='tel']").val("");
						$(":input[name='birth']").val("");
					}
				}
			});
		}
	});
});
</script>
<h3>email 찾기</h3><br>
<form>
이름 : <input type = "text" name = "name"><br>
전화번호 : <input type = "text" name = "tel"><br>
생년월일 : <input type = "text" name = "birth"><br>
<input type = "button" value = "아이디찾기">
</form>