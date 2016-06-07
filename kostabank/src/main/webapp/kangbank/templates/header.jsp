<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
   $(document).ready(function(){
      $("a[href$='secure_register']").click(function(){
         var a = ${sessionScope.loginInfo.security_card};
         if(a !=0){
            alert("이미 보안카드가 존재합니다");
            return false;
         }else{
            var c = confirm("보안카드를 신청하시겠습니까?");
            if(c==false){
               return false;
            }else {
               $.ajax({
                  type : "post",
                  url : "certificateEmail.bank?email=${sessionScope.loginInfo.email}",
                  datatype : 'json',
                  success : function(result) {
                     window.open("kangbank/secure/certificate_email.jsp?email=${sessionScope.loginInfo.email}&flag=register", "email 인증", "width = 400, height = 200");
                  }
               });
               return false;
            }
         }
         
      });
      $("a[href$='secure_reissue']").click(function(){      
         var a = ${sessionScope.loginInfo.security_card};
         if(a ==0){
            alert("소지하고 계신 보안카드가 없습니다 보안카드를 생성해주세요");
            return false;
         }else{
            var c = confirm("보안카드를 재발급 받으시겠습니까?");
            if(c==false){
               return false;
            }else {
               $.ajax({
                  type : "post",
                  url : "certificateEmail.bank?email=${sessionScope.loginInfo.email}",
                  datatype : 'json',
                  success : function(result) {
                     window.open("kangbank/secure/certificate_email.jsp?email=${sessionScope.loginInfo.email}&flag=reissue", "email 인증", "width = 400, height = 200");
                  }
               });
               return false;
            }
         }
      });
      $("a[href$='secure_delete']").click(function(){
         var a = ${sessionScope.loginInfo.security_card};
         if(a ==0){
            alert("소지하고 계신 보안카드가 없습니다 보안카드를 생성해주세요");
            return false;
         }else{
            var c = confirm("보안카드를 해지 하시겠습니까?");
            if(c==false){
               return false;
            }else {
               $.ajax({
                  type : "post",
                  url : "certificateEmail.bank?email=${sessionScope.loginInfo.email}",
                  datatype : 'json',
                  success : function(result) {
                     window.open("kangbank/secure/certificate_email.jsp?email=${sessionScope.loginInfo.email}&flag=deletecard", "email 인증", "width = 400, height = 200");
                  }
               });
               return false;
            }      
         }
      });
   });
</script>
<div class="section">
   <div class="container">
      <div class="row">
         <div class="col-md-12">
            <ul class="lead nav navbar-nav navbar-right">
               <li class="active"><a href="#">KANG BANK</a></li>
               <li><a href = "ccountTypeList.bank?page=1">상품 정보</a></li>
                     <li class="계좌"><a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-expanded="false">계좌정보
                     <i class="fa fa-caret-down"></i>
               </a>
                  <ul class="dropdown-menu" role="menu">
                     <li><a href="passwordCheck.bank">계좌 생성</a></li>
                      <li><a href="accountTotalList.bank">예금조회</a></li>
                  </ul></li>
               <li><a href="${initParam.root}transfer_view.bank">계좌이체</a></li>
               <li class="보안센터"><a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-expanded="false">보안센터
                     <i class="fa fa-caret-down"></i>
               </a>
                  <ul class="dropdown-menu" role="menu">
                     <li><a href="secure_register"">보안카드 발급</a></li>
                     <li><a href="secure_reissue">보안카드 재발급</a></li>
                     <li><a href="secure_delete">보안카드 폐기</a></li>
                  </ul></li>
               <li class="게시판"><a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-expanded="false">게시판
                     <i class="fa fa-caret-down"></i>
               </a>
                  <ul class="dropdown-menu" role="menu">
                     <li><a href="#">공지사항</a></li>
                     <li><a href="#">Q & A</a></li>
                     <li><a href="#">자주 묻는 질문</a></li>
                  </ul></li>
            </ul>
         </div>
      </div>
   </div>
</div>