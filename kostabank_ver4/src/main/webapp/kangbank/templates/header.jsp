<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
   $(document).ready(function(){
      <%HttpSession sessions = request.getSession(false);
         if(sessions.getAttribute("loginInfo")!=null){
      %>
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
      <%}%>
   });
 
</script>
<div class="section">
   <div class="container">
      <div class="row">
         <div class="col-md-12">
            <ul class="lead nav navbar-nav navbar-right">
               <li class="active"><a href="home.bank" id="homeA">KANG BANK</a></li>
                     <li class="계좌"><a href="#"   id="headerA"  class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-expanded="false">계좌
                     <i class="fa fa-caret-down"></i>
               </a>
                  <ul class="dropdown-menu" role="menu">
                      <li><a href="accountTotalList.bank">예금조회</a></li>
                  </ul></li>
               <li><a href="transfer_view.bank"  id="headerA" >계좌이체</a></li>
              <li class="active"><a href="laonNameList.bank"  id="headerA" >대출</a></li>
              <li class="active"><a href="savings.bank?accountType=적금"  id="headerA" >적금</a></li>
              <li class="active"><a href="accountTypeList.bank?page=1"  id="headerA" >상품정보</a></li>
               <li class="보안센터"><a href="#"   id="headerA"  class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-expanded="false">보안센터
                     <i class="fa fa-caret-down"></i>
               </a>
                  <ul class="dropdown-menu" role="menu">
                  <c:choose>
                  <c:when test="${empty sessionScope.loginInfo}">
                     <li><a href="${initParam.root}kangbank/templates/needLogin.jsp">보안카드 폐기</a></li>
                  </c:when>
                  <c:otherwise>
                     <li><a href="secure_delete">보안카드 폐기</a></li>
                  </c:otherwise>
                  </c:choose>
                  </ul></li>
               <li class="게시판"><a href="#"  id="headerA"  class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-expanded="false">게시판
                     <i class="fa fa-caret-down"></i>
               </a>   
                  <ul class="dropdown-menu" role="menu">
                     <li><a href="notice_list.bank">공지사항</a></li>
                     <li><a href="QNA.bank">Q & A</a></li>
                     <li><a href="question_view.bank">자주 묻는 질문</a></li>
                  </ul></li>
            </ul>
         </div>
      </div>
   </div>
</div>
