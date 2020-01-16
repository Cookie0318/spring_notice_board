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
		<form:form id="joinform" action="${cp}/member/joinOk" method="post" commandName="member" autocomplete="off">
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
					<td id="td_password"><form:password path="password" id="password"/></td>
				</tr>
				
				<tr>
					<td>비밀번호 확인: </td>
					<td id="td_password_check"><input type="password" id="password_check"/></td>
				</tr>
				
				<tr>
					<td>성별: </td>
					<td id="gender">
					<form:radiobutton path="gender" value="남성" label="남성" id="man"/>
					<form:radiobutton path="gender" value="여성" label="여성" id="woman" />
					</td>
					
				</tr>
				
				<%-- <tr>
					<td>나이: </td>
					<td><form:input path="age"/>세</td>
				</tr> --%>
				
				<tr>
					<td>생년월일: </td>
					<td style="color:#cccccc;" id="td_birthday"><form:input path="birthday" id="birthday"/>(yyyy-MM-dd)</td>
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
		var idOk = 0; // 아이디가 유효한지 나타내는 변수 0: 유효하지 않음, 1: 유효함
		var passwordOk = 0; // 비밀번호가 유효한지 나타내는 변수 0: 유효하지 않음, 1: 유효함
		var passwordChecked = 0; //0 : 비밀번호 확인 안됨, 1 : 비밀번호 확인 됨
		var birthdayOk = 0;
		
		$("#joinform").submit(function() {
			var getName = RegExp(/^[가-힣]+$/);
			var getId = RegExp(/^[A-Za-z0-9]{4,12}$/);
			var getPhone1 = RegExp(/^[0-9]{3}$/);
			var getPhone2 = RegExp(/^[0-9]{4}$/);

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
			//아이디가 제대로 입력 안됬을 때
			if (idOk == 0){
				alert("아이디를 확인하세요!");
				return false;
			}
			//패스워드 입력 안했을 때
			if (passwordOk == 0) {
				alert("비밀번호를 확인하세요!");
				return false;
			}
			//패스워드가 일치하지 않을 때
			if (passwordChecked == 0) {
				alert("비밀번호가 일치하지 않습니다!");
				return false;
			}
			//성별 입력 안했을 때
			if (!$("#man").prop("checked") && !$("#woman").prop("checked")) {
				alert("성별을 선택 해주세요!");
				$("#gender").focus();
				return false;
			}
			//생년월일 조합이 안맞을때 yyyy-MM-dd
			if (birthdayOk == 0) {
				alert("생일을 정확히 입력하세요. ex)1999-12-25");
				return false;
			}
			//핸드폰번호 정확히 입력 안했을 때
			if(!getPhone1.test($("#phone1").val()) || !getPhone2.test($("#phone2").val()) || !getPhone2.test($("#phone3").val())){
				alert("핸드폰 번호를 확인하세요.");
				return false;
			}
		});
		$("#id").on("propertychange change keyup paste input", function () {
			var getId = RegExp(/^[A-Za-z0-9]{4,12}$/);
			
			idOk = 1;
			if($("#error_id_invalid") != null) {
				$("#error_id_invalid").remove();
			}
			if($("#error_id_null") != null) {
				$("#error_id_null").remove();
			}
			
			if($("#id").val() == "") {
				$("#td_id").append("<p style=\"color:red;\" id=\"error_id_null\">문자열 입력</p>");
				idOk = 0;
			}else if(!getId.test($("#id").val())) {
				$("#td_id").append("<p style=\"color:red;\" id=\"error_id_invalid\">이름 형식에 맞게 입력</p>");
				idOk = 0;
			}
		});
		
		$("#password").on("propertychange change keyup paste input", function() {
			var getPassword = RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{6,12}$/);
			
			passwordOk = 1;
			if($("#error_pw_null") != null){
				$("#error_pw_null").remove();
			}
			if($("#error_pw_invalid") != null){
				$("#error_pw_invalid").remove();
			}
			
			if($("#password").val() == "") {
				$("#td_password").append("<p style=\"color:red;\" id=\"error_pw_null\">패스워드를 입력하세요</p>");
				passwordOk = 0;
			}else if(!getPassword.test($("#password").val())) {
				$("#td_password").append("<p style=\"color:red;\" id=\"error_pw_invalid\">패스워드는 6~12자리 영어,숫자,특수문자 포함입니다.</p>");
				passwordOk = 0;
			}
		});
		
		$("#password_check").on("propertychange change keyup paste input", function() {
			var password = $("#password").val();
			var password_check = $("#password_check").val();
			
			passwordChecked = 1;
			if($("#error_pwchk") != null) {
				$("#error_pwchk").remove();
			}
			
			if(password !== password_check) {
				$("#td_password_check").append("<p style=\"color:red;\" id=\"error_pwchk\">패스워드가 맞지 않습니다.</p>");
				passwordChecked = 0;
			}
		});
		
		$("#birthday").on("propertychange change keyup paste input", function() {
			var getBirthday = RegExp(/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/);
			
			birthdayOk = 1;
			if($("#error_birth") != null){
				$("#error_birth").remove();
			}
			
			if(!getBirthday.test($("#birthday").val())){
				$("#td_birthday").append("<p style=\"color:red;\" id=\"error_birth\">생년월일 형식이 맞지 않습니다.</p>")
				birthdayOk = 0;
			}
		})

	});
</script>
<script src="${cp}/resources/js/bootstrap.js"></script>
</body>
</html>
