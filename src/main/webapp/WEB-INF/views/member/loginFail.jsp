<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${cp}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${cp}/resources/css/myStyle.css">
<title>Cookie &amp; Pizza</title>
</head>
<body>
<div>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	  <a class="navbar-brand" href="${cp}/">Cookie &amp; Pizza</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarsExample03">
	    <ul class="navbar-nav mr-auto">
	      <c:choose>
				<c:when test="${not empty sessionScope.mem}">
					<li class="nav-item active">
						<a class="nav-link" href="#">${sessionScope.mem.id} 님 <span class="sr-only">(current)</span></a>
					</li>
				</c:when>
				
				<c:otherwise>
					<li class="nav-item active">
						<a class="nav-link" href="${cp}/member/login_form">로그인<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
	        			<a class="nav-link" href="${cp}/member/join_form">회원가입</a>
	      			</li>
				</c:otherwise>
	      </c:choose>
	      
	      <li class="nav-item">
	      	<a class="nav-link" href="${cp}/notice/list">공지사항</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">마이페이지</a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">바로 가기</a>
	        <div class="dropdown-menu" aria-labelledby="dropdown03">
	          <a class="dropdown-item" href="#">Blog</a>
	          <a class="dropdown-item" href="#">Git Hub</a>
	        </div>
	      </li>
	    </ul>
	    <c:if test="${not empty sessionScope.mem }">
	    	<a href="${cp}/member/logout" class="btn btn-primary mr-2">Logout</a>
	    </c:if>
	    <form class="form-inline my-2 my-md-0">
	      <input class="form-control" type="text" placeholder="Search">
	    </form>
	  </div>
	</nav>
	<div id="content">
		<h1>로그인 실패</h1>
		<p class="lead">
		"아이디와 비밀번호를 확인하세요"
		</p>
		
	</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${cp}/resources/js/bootstrap.js"></script>
</html>
