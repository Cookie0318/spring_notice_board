<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
	      		<sec:authorize access="isAuthenticated()">
					<%-- <li class="nav-item active">
						<a class="nav-link" href="#">${sessionScope.mem.id} 님 <span class="sr-only">(current)</span></a>
					</li> --%>
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
	<div id="join" class="container text-center">
		<h1>회원가입 하기</h1>
		<form:form id="joinform" action="${cp}/member/joinOk" method="post" commandName="member">
			<table class="table">
				<tr>
					<td>이름: </td>
					<td><form:input path="name" size="10" id="name"/></td>
				</tr>
				<tr>
					<td>아이디: </td>
					<td id="td_id">
						<form:input path="id" id="id"/>
						<a href="#" class="btn btn-primary">중복 확인</a>
					</td>
				</tr>
				
				<tr>
					<td>비밀번호: </td>
					<td><form:password path="password" id="password"/></td>
				</tr>
				
				<tr>
					<td>성별: </td>
					<td id="gender">
					<form:radiobutton path="gender" value="남성" label="남성" id="man"/>
					<form:radiobutton path="gender" value="여성" label="여성" id="woman" />
					</td>
					
				</tr>
				
				<tr>
					<td>나이: </td>
					<td><form:input path="age"/>세</td>
				</tr>
				
				<tr>
					<td>생년월일: </td>
					<td style="color:#cccccc;"><form:input path="birthday" id="birthday"/>(yyyy-MM-dd)</td>
				</tr>
				
				<tr>
					<td>핸드폰 번호</td>
					<td><form:input path="phone1" id="phone1"/>-<form:input path="phone2" id="phone2"/>-<form:input path="phone3" id="phone3"/></td>
				</tr>
			</table>
			
			<input type="submit" value="Submit" class="btn btn-primary"/>
		</form:form>
		
	</div>
</div>
<script src="${cp}/resources/js/jquery-3.4.1.min.js"></script>
<script>
	
	$(document).ready(function() {
		$("#joinform").submit(function() {
			var getName = RegExp(/^[가-힣]+$/);
			var getId = RegExp(/^[A-Za-z0-9]{4,12}$/);
			var getBirthday = RegExp(/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/);

			//이름 칸 비어있을때
			if ($("#name").val() == "") {
				alert("이름 입력");
				$("#name").focus();
				return false;
			}
			//이름이 한글이 아닐때
			if (!getName.test($("#name").val())) {
				alert("이름 형식에 맞게 입력");
				$("#name").val("");
				$("#name").focus();
				return false;
			}
			//아이디칸 비어있을때
			if ($("#id").val() == "") {
				alert("아이디를 입력하세요!");
				$("#id").focus();
				return false;
			}
			//아이디 조합이 이상할때
			if (!getId.test($("#id").val())) {
				alert("아이디는 영어, 숫자 조합으로 4~12자까지 가능합니다.");
				$("#id").focus();
				return false;
			}
			//패스워드 입력 안했을때
			if ($("#password").val() == "") {
				alert("비밀 번호를 입력 해주세요!");
				$("#pasword").focus();
				return false;
			}
			//성별 입력 안했을 때
			if (!$("#man").prop("checked") && !$("#woman").prop("checked")) {
				alert("성별을 선택 해주세요!");
				$("#gender").focus();
				return false;
			}
			//생년월일 조합이 안맞을때 yyyy-MM-dd
			if (!getBirthday.test($("#birthday").val())) {
				alert("생일을 정확히 입력하세요. ex)1999-12-25");
				$("#birthday").val("");
				$("#birthday").focus();
				return false;
			}
		});
		$("#id").change(function () {
			var getId = RegExp(/^[A-Za-z0-9]{4,12}$/);
			if($("#error_invalid") != null) {
				$("#error_invalid").remove();
			}
			if($("#error_null") != null) {
				$("#error_null").remove();
			}
			
			if($("#id").val() == "") {
				$("#td_id").append("<td style=\"color:red;\" id=\"error_null\">문자열 입력</td>");
			}else if(!getId.test($("#id").val())) {
				$("#td_id").append("<td style=\"color:red;\" id=\"error_invalid\">이름 형식에 맞게 입력</td>");
			}
		})

	});
</script>
<script src="${cp}/resources/js/bootstrap.js"></script>
</body>
</html>
