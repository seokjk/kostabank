<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<script type="text/javascript">
$(document).ready(function() {
	$(":input[value='비밀번호찾기']").click(function(){
		var email = $(":input[name='email']").val();
		var birth = $(":input[name='birth']").val();
		var tel = $(":input[name='tel']").val();
		if(email == ""){
			alert("이메일을 입력하세요");
		} else if(birth == ""){
			alert("생년월일을 입력하세요");
		} else if(tel == ""){
			alert("전화번호를 입력하세요");
		}else {
			$.ajax({
				type : "post",
				url : "findPassword.bank",
				datatype : 'json',
				data : {
					email : email,
					birth : birth,
					tel : tel
				},
				success : function(result) {
					if(result == 0){
						alert("일치하는 정보가 없습니다.");
					} else {
						alert("입력하신 email로 임시비밀번호가 전송되었습니다");
						location.href = "home.bank";
					}
				}
			});
		}
	});
});
</script>

<h3>password 찾기</h3><br>
<form>
email : <input type = "text" name = "email"><br>
전화번호 : <input type = "text" name = "tel"><br>
생년월일 : <input type = "text" name = "birth"><br>
<input type = "button" value = "비밀번호찾기">
</form>
