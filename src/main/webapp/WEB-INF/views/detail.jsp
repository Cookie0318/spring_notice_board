<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${cp}/resources/css/bootstrap.css">
<title>Cookie &amp; Pizza</title>
</head>


<body>
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
			<td>${n.regdate }</td>
			<td style="background-color: #cccccc;width:10%">조회수</td>
			<td>${n.hit }</td>
		</tr>
	</table>
	<p class="container">${n.content}</p>
	<div class="text-center">
		<a class="btn btn-primary" href="${cp}/notice/list">목록</a>
		<a class="btn btn-danger" href="${cp}/notice/delete?id=${n.id}">삭제</a>
		<a class="btn btn-success" href="${cp}/notice/modify_form?id=${n.id}">수정</a>
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${cp}/resources/js/bootstrap.js"></script>
</body>
</html>