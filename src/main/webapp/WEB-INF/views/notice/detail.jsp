<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${cp}/resources/css/bootstrap.css">
<title>Cookie &amp; Pizza</title>
</head>


<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<a class="navbar-brand" href="${cp}/">Cookie &amp; Pizza</a>
	  	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
	    	<span class="navbar-toggler-icon"></span>
	  	</button>
	
	  	<div class="collapse navbar-collapse" id="navbarsExample03">
	    	<ul class="navbar-nav mr-auto">
	      		<sec:authorize access="isAuthenticated()">
					<sec:authentication var="principal" property="principal" />
					<li class="nav-item dropdown active">
				        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${principal.username} 님</a>
				        <div class="dropdown-menu" aria-labelledby="dropdown03">
				          <a class="dropdown-item" href="${cp}/member/modify_form">정보 수정</a>
				          <a class="dropdown-item" href="${cp}/notice/mylist">내가 쓴글</a>
				          <sec:authorize access="hasRole('ROLE_ADMIN')">
				          	<a class="dropdown-item" href="${cp}/admin/page">관리자 페이지</a>
				          </sec:authorize>
				        </div>
	     	 		</li>
     	 		</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<li class="nav-item active">
						<a class="nav-link" href="${cp}/sec/login_form">로그인<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
	        			<a class="nav-link" href="${cp}/member/join_form">회원가입</a>
	      			</li>
      			</sec:authorize>
				
	      
	      		<li class="nav-item">
	      			<a class="nav-link" href="${cp}/notice/list">공지사항</a>
	      		</li>
	    	</ul>
		    <sec:authorize access="isAuthenticated()" >
		    	<form action="${cp}/logout" method="post">
		    		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		    		<button type="submit" class="btn btn-primary mr-2">Logout</button>
		    	</form>
		    </sec:authorize>
	  	</div>
	</nav>
	<div class="container">
		<h2 class="mt-5">공지사항</h2>
		<table class="table table-stripped">
			<tr>
				<td style="background-color: #cccccc; width:10%">제목</td>
				<td colspan="3">${n.title }</td>
			</tr>
			
			<tr>
				<td style="background-color: #cccccc;width:10%">작성자</td>
				<td colspan="3">${n.writer_Id }</td>
			</tr>
			
			<tr>
				<td style="background-color: #cccccc;width:10%">등록일</td>
				<td>${formattedDate}</td>
				<td style="background-color: #cccccc;width:10%">조회수</td>
				<td>${n.hit }</td>
			</tr>
		</table>
		<p class="container">${n.content}</p>
		<div class="text-center">
			<a class="btn btn-primary" href="${cp}/notice/list">목록</a>
			<sec:authentication var="principal" property="principal" />
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<c:if test="${n.writer_Id == principal.username}">	
					<a class="btn btn-success" href="${cp}/notice/modify_form?id=${n.id}">수정</a>
				</c:if>
				<a class="btn btn-danger" href="${cp}/notice/delete?id=${n.id}">삭제</a>
			</sec:authorize>
			
			<sec:authorize access="hasRole('ROLE_USER')">
				<c:if test="${n.writer_Id == principal.username}">
					<a class="btn btn-danger" href="${cp}/notice/delete?id=${n.id}">삭제</a>
					<a class="btn btn-success" href="${cp}/notice/modify_form?id=${n.id}">수정</a>
				</c:if>
			</sec:authorize>
		</div>
	</div>


<script src="${cp}/resources/js/jquery-3.4.1.min.js"></script>
<script src="${cp}/resources/js/bootstrap.js"></script>
</body>
</html>