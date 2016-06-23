<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="resources/jquery-1.12.4.min.js"></script>


<script type="text/javascript">

</script>
<br>
<h2>공지사항</h2>
<br><br>
<table id="notice">
      <tr id="tr">
         <td width="8%" align="center">NO</td>
         <td width="60%" align="center">제목</td>
         <td width="10%" align="center">이름</td>
         <td width="14%" align="center">작성일</td>
         <td width="8%" align="center">HIT</td>
         </tr>
         <%-- ${sessionScope.loginInfo.name.equals(${nvo.customerVO.name })}    --%>
      <c:forEach var="nvo" items="${requestScope.nlvo.noticeList}">   
         <tr>
             <td align="center">${nvo.no }</td>            
            <td align="center">
            <c:choose>
            <c:when test="${sessionScope.loginInfo!=null && sessionScope.loginInfo.name==nvo.customerVO.name}">
               <a href="${initParam.root}notice_showContentNoHit.bank?no=${nvo.no }">${nvo.title }</a>
               
            </c:when>
            <c:when test="${sessionScope.loginInfo!=null && sessionScope.loginInfo.name!=nvo.customerVO.name}">
               <a href="${initParam.root}notice_showContent.bank?no=${nvo.no }">${nvo.title }</a>
            </c:when>
            <c:otherwise>
            <a href="${initParam.root}notice_showContent.bank?no=${nvo.no }">${nvo.title }</a>
            <%-- ${nvo.title } --%>
            </c:otherwise>
            </c:choose>
            </td>
            <td align="center">${nvo.customerVO.name }</td>
            <td align="center">${nvo.timePosted }</td>
            <td align="center">${nvo.hits }</td>
         </tr>   
         </c:forEach>
         
   </table><br></br>   
   <c:if test="${sessionScope.loginInfo!=null}">
   <a href="${initParam.root}notice_write.bank"><img  id="img" src="${initParam.root}img/write_btn.jpg" border="0"></a>
   </c:if>
   <br><br>   
<p class="paging">
   <%-- 코드를 줄이기 위해 pb 변수에 pagingBean을 담는다. --%>
   <c:set var="pb" value="${requestScope.nlvo.pagingBean}"></c:set>
   <!-- 
         step2 1) 이전 페이지 그룹이 있으면 이미지 보여준다. (img/left_arrow_btn.gif)
                     페이징빈의 previousPageGroup 이용 
               2)  이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
                      hint)   startPageOfPageGroup-1 하면 됨        
    -->      
   <c:if test="${pb.previousPageGroup}">
   <a href="notice_list.bank?pageNo=${pb.startPageOfPageGroup-1}">
   <!-- <img src="img/left_arrow_btn.gif"> -->
   ◀&nbsp; </a>   
   </c:if>
   <!-- step1. 1)현 페이지 그룹의 startPage부터 endPage까지 forEach 를 이용해 출력한다
               2) 현 페이지가 아니면 링크를 걸어서 서버에 요청할 수 있도록 한다.
                  현 페이지이면 링크를 처리하지 않는다.  
                  PagingBean의 nowPage
                  jstl choose 를 이용  
                  예) <a href="list.do?pageNo=...">               
    -->      
   <c:forEach var="i" begin="${pb.startPageOfPageGroup}" 
   end="${pb.endPageOfPageGroup}">
   <c:choose>
   <c:when test="${pb.nowPage!=i}">
   <a href="notice_list.bank?pageNo=${i}">${i}</a> 
   </c:when>
   <c:otherwise>
   ${i}
   </c:otherwise>
   </c:choose>
   &nbsp;
   </c:forEach>    
   <!-- 
         step3 1) 다음 페이지 그룹이 있으면 이미지(img/right_arrow_btn.gif) 보여준다. 
                     페이징빈의 nextPageGroup 이용 
               2)  이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
                      hint)   endPageOfPageGroup+1 하면 됨        
    -->   
   <c:if test="${pb.nextPageGroup}">
   <a href="list.do?pageNo=${pb.endPageOfPageGroup+1}">
   ▶</a>
   </c:if>
   </p>
