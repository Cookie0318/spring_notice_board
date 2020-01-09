<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
						<%-- <li class="nav-item active">
							<a class="nav-link" href="#">${sessionScope.mem.id} 님 <span class="sr-only">(current)</span></a>
						</li> --%>
						<li class="nav-item dropdown active">
					        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sessionScope.mem.id} 님</a>
					        <div class="dropdown-menu" aria-labelledby="dropdown03">
					          <a class="dropdown-item" href="${cp}/member/modify_form">정보 수정</a>
					          <a class="dropdown-item" href="${cp}/notice/mylist">내가 쓴글</a>
					        </div>
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
	    	</ul>
		    <c:if test="${not empty sessionScope.mem }">
		    	<a href="${cp}/member/logout" class="btn btn-primary mr-2">Logout</a>
		    </c:if>
	  	</div>
	</nav>
	<div id="login" class="text-center">
	    <form:form class="form-signin" action="${cp}/member/loginOk" method="post" commandName="member">
	      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
	      <label for="inputId" class="sr-only">아이디 입력</label>
	      <form:input id="inputId" name="id" class="form-control" placeholder="아이디 입력" path="id"/>
	      <label for="inputPassword" class="sr-only">Password</label>
	      <form:password id="inputPassword" name="pw" class="form-control" placeholder="Password" path="password"/>
	      <button class="btn btn-lg btn-primary btn-block mt-3"type="submit">Sign in</button>
	      <p class="mt-5 mb-3 text-muted">© Cookie &amp; Pizza</p>
	    </form:form>
	</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${cp}/resources/js/bootstrap.js"></script>
</html>
