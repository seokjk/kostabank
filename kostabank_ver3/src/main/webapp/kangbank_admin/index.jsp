<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${initParam.root}kangbank_admin/kangbank_admin_css/w2ui-1.4.3.min.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="${initParam.root}kangbank_admin/kangbank_admin_js/w2ui-1.4.3.js"></script>
</head>
<body>
<script>
$(document).ready(function(){
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
                   /*  toolbarEdit:true, */
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
        		onSelect: function(event){
        			event.onComplete = function(){
        				var email=this.get(this.getSelection()).email;
        				var c = confirm("계좌 내역을 확인하시겠습니까?");
        				if(c){
        					window.open("${initParam.root}adminAccountTotalList.bank?email="+email,'KANGBANK',"width=1100,height=700,location=no,resizable=yes,left=200,top=200");
        				}else{
        					return false;
        				}
        			}
        		},
        		onAdd:function(target,eventdata){
        			var c = confirm("회원 생성을 확인하시겠습니까?");
    				if(c){
    					window.open("${initParam.root}kangbank_admin/popup/register.jsp",'KANGBANK',"width=700,height=700,location=no,resizable=yes,left=200,top=200");
    				}else{
    					return false;
    				}
        		},
        	
        		/* onEdit:function(target,event){
        			var c = confirm("수정하시겠습니까?");
        			var str="";
        			if(c){
        				str+="<br><br>";
        				str+="<form id='modifyForm'>"
        				str+="<table border='1' cellpadding='5'><tr><td>NAME</td>"
        				str+="<td><input type='text' value="+this.get(this.getSelection()).name+" readonly></td></tr>";
        				str+="<tr><td>EMAIL</td><td><input type='text' value="+this.get(this.getSelection()).email+" readonly></td></tr>";
        				str+="<tr><td>ADDRESS</td><td><input type='text' value="+this.get(this.getSelection()).address+"></td></tr>";
        				str+="<tr><td>TEL</td><td><input type='text' value="+this.get(this.getSelection()).tel+"></td></tr>";
        				str+="</table></form>";
        				str+="<br><input type='button' id='modify' value='수정하기'>";
        				$("#result").html(str);
        				$("#modify").click(function(){
        					 $.ajax({
        					        type:"post",
        					        url:"${initParam.root}modifyCustomerInfo.bank",
        					        data:$("#modifyForm").serialize(),
        					        dataType:"json",
        					        success:function(list){
        					        	
        					        }
        					 });
        				});
        			}else{
        				return false;
        			}
        		}
        		 */
               
        	});
        }
		
    });
});	
	
	
	
	


</script>
    <div id="grid" style="overflow:auto; width: 100%; height:920px; font-size:16px;"></div>
    <div id="result"></div>
</body>

</html>