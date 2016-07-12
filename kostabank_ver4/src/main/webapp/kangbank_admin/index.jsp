<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${initParam.root}kangbank_admin/kangbank_admin_css/w2ui-1.4.3.min.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="${initParam.root}kangbank_admin/kangbank_admin_js/w2ui-1.4.3.js"></script>
<link rel="stylesheet" type="text/css"
   href="${initParam.root}kangbank/kangcss/home.css">
</head>
<body>
<script>
$(document).ready(function(){
	$('#close').click(function(){
		$('#pop_background').fadeOut();
		$('#pop_box').fadeOut();
		$('#pop_accountList').fadeOut();
		$('#grid').fadeIn();
		return false;
	 });
	var data=[];
	 $.ajax({
        type:"post",
        url:"${initParam.root}searchCustomer.bank",
        dataType:"json",
        success:function(list){
	       	 for(var i=0; i<list.length; i++){
		       		 data.push({
		       			 recid:i+1,
		       			 name:list[i].name,
		       			 birth:list[i].birth,
		       			 address:list[i].address,
		       			 tel:list[i].tel,
		       			 email:list[i].email
		       		})
       	 	}
	       	$('#grid').w2grid({
                name: 'grid',
                header: 'KANGBANK MANAGEMENT SYSTEM',
                show: {
                	header:true,
                	lineNumbers: true,
                	columnHeaders:true,
                	selectColumn:true,
                    footer: true,
                    toolbar: true,
                    footer: true,
                    toolbarAdd:true,
                    toolbarAccount:true,
                    toolbarAdd2:true,
                    toolbarReply:true,
                    toolbarSecure:true,
                },
                columns: [
                    { field: 'name', caption: '회원명', size: '20%' },
                    { field: 'birth', caption: '주민등록번호', size: '30%' },
                    { field: 'address', caption: '주소', size: '30%' },
                    { field: 'tel', caption: '핸드폰번호', size: '30%' },
                    { field: 'email', caption: 'email', size: '30%' }
                ],
        		records:data,
        		onReload:function(event){
                	window.location.reload();
                },
                onAdd:function(target,eventdata){
                	$.ajax({
  				         type : "post",
  				         url : "${initParam.root}adminGoRegister.bank",
  				         datatype : 'json',
  				         data : {
  				         },
  				         success : function(result) {
  				        	$('#accountList').text("");
  				        	$('#accountList').append(result);
  				        	$('#pop_background').fadeIn();
  			        		$('#pop_accountList').fadeIn();
  			        		$('#grid').fadeOut();
  			        		return false;
  				         }
  				      });
        		},
        		onSecure:function(target,eventdata){
        			var email=this.get(this.getSelection()).email;
    				if(confirm("보안카드를 발급하시겠습니까?")){
    					$.ajax({
   				         type : "post",
   				         url : "${initParam.root}adminRegisterSecureCard.bank",
   				         datatype : 'json',
   				         data : {
   				            email : email
   				         },
   				         success : function(result) {
   				        	$('#accountList').text("");
   				        	$('#accountList').append(result);
   				        	$('#pop_background').fadeIn();
   			        		$('#pop_accountList').fadeIn();
   			        		$('#grid').fadeOut();
   			        		return false;
   				         }
   				      });
    				}
        		},
        		onAdd2:function(target,eventdata){
        			var email=this.get(this.getSelection()).email;
    				if(confirm("계좌 내역을 확인하시겠습니까?")){
    					$.ajax({
    				         type : "post",
    				         url : "${initParam.root}adminAccountTotalList.bank",
    				         datatype : 'json',
    				         data : {
    				            email : email
    				         },
    				         success : function(result) {
    				        	$('#accountList').text("");
    				        	$('#accountList').append(result);
    				        	$('#pop_background').fadeIn();
    			        		$('#pop_accountList').fadeIn();
    			        		$('#grid').fadeOut();
    			        		return false;
    				         }
    				      });
    				}
        		},
        		onReply:function(target,eventdata){
        			var email=this.get(this.getSelection()).email;
    				if(confirm("QNA 확인하시겠습니까?")){
    					$.ajax({
   				         type : "post",
   				         url : "${initParam.root}adminQnaListRoad.bank",
   				         datatype : 'json',
   				         data : {
   				            email : email,
   				            page : 1
   				         },
   				         success : function(result) {
   				        	$('#accountList').text("");
   				        	$('#accountList').append(result);
   				        	$('#pop_background').fadeIn();
   			        		$('#pop_accountList').fadeIn();
   			        		$('#grid').fadeOut();
   			        		return false;
   				         }
   				      });
    				}
        		},
        		onAccount:function(target,eventdata){
        			var email=this.get(this.getSelection()).email;
        			if(confirm("계좌를 생성하시겠습니까??")){
    					$.ajax({
    				         type : "post",
    				         url : "${initParam.root}adminCreateAccount.bank",
    				         datatype : 'json',
    				         data : {
    				            email : email
    				         },
    				         success : function(result) {
    				        	$('#accountList').text("");
    				        	$('#accountList').append(result);
    				        	$('#pop_background').fadeIn();
    			        		$('#pop_accountList').fadeIn();
    			        		$('#grid').fadeOut();
    			        		return false;
    				         }
    				      });
    				}
        		},
        	});
        }
		
    });
	   $(":input[name='email']").keyup(function(){
		      var email = $(":input[name='email']").val();
		      $.ajax({
		         type : "post",
		         url : "${initParam.root}checkEmail.bank",
		         datatype : 'json',
		         data : {
		            email : email
		         },
		         success : function(result) {
		            if(result){
		               $("#emailCheck").text("사용 불가능한 email입니다").css("color", "red");
		            } else {
		               $("#emailCheck").text("");
		            }
		         }
		      });
		   });
		   $(":input[value='인증하기']").click(function(){
		      if($("#emailCheck").text() == "사용 불가능한 email입니다"){
		         alert("email을 확인해주세요");
		      } else if($(":input[name='email']").val()==""){
		    	  alert("email을 입력하세요");
		      } else {
		         $.ajax({
		            type : "post",
		            url : "${initParam.root}certificateEmail.bank?email="+$(":input[name='email']").val(),
		            datatype : 'json',
		            success : function(result) {
		               window.open("${initParam.root}kangbank/register/certificate_email.jsp?email="+$(":input[name='email']").val(), "email 인증", "width = 400, height = 200");
		            }
		         });
		      }
		   });
});	
</script>
    <div id="grid" style="overflow:auto; width: 100%; height:920px; font-size:16px;"></div>
    <div id="pop_background"></div>
    <div id="pop_accountList">
    <span id = "close" style="cursor:pointer">&times;</span>
    <span id = "accountList">
	</span>
    </div>
</body>

</html>