<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${cp}/resources/css/bootstrap.css">
<title>Cookie &amp; Pizza</title>
</head>


<body>
<div class="container">
	<h2 class="mt-5">공지사항 작성</h2>
	<form:form action="${cp}/notice/writeOk" method="post" commandName="notice">
		<table class="table table-stripped">
			<tr>
				<td style="background-color: #cccccc; width:10%">제목</td>
				<td><form:input path="title" /></td>
			</tr>
			<tr>
				<td style="background-color: #cccccc; width:10%">내용</td>
				<td ><form:textarea path="content" /></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="작성" ></td>
			</tr>
		</table>
	</form:form>
	<p class="container">${n.content}</p>
	<div class="text-center">
		<a class="btn btn-primary" href="${cp}/notice/list">목록</a>
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${cp}/resources/js/bootstrap.js"></script>
</body>
</html>