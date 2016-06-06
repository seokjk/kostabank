<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <ul class="lead nav navbar-nav navbar-right">
              <li class="active">
                <a href="#">KANG BANK</a>
              </li>
              <li>
                <a href="#">예금조회</a>
              </li>
              <li>
                <a href="${initParam.root}transfer_view.bank">계좌이체</a>
              </li>
              <li class="보안센터">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                aria-expanded="false">보안센터 <i class="fa fa-caret-down"></i></a>
                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="#">보안카드 발급</a>
                  </li>
                  <li>
                    <a href="#">보안카드 재발급</a>
                  </li>
                  <li>
                    <a href="#">보안카드 폐기</a>
                  </li>
                </ul>
              </li>
              <li class="게시판">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                aria-expanded="false">게시판 <i class="fa fa-caret-down"></i></a>
                <ul class="dropdown-menu" role="menu">
                  <li>
                    <a href="#">공지사항</a>
                  </li>
                  <li>
                    <a href="#">Q & A</a>
                  </li>
                  <li>
                    <a href="#">자주 묻는 질문</a>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
