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
<style>
	h2 {
		text-align: center;
	}
	table {
		width: 100%;
	}
	#outter {
		display: block;
		width: 60%;
		margin: auto;
	}
	a {
		text-decoration: none;
	}
</style>
<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="${cp}/notice/list?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>
<body>
<h2 class="mt-5">공지사항</h2>

<div class="container">
	<div style="float: right;">
		<select id="cntPerPage" name="sel" onchange="selChange()">
			<option value="5"
				<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄 보기</option>
			<option value="10"
				<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄 보기</option>
			<option value="15"
				<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄 보기</option>
			<option value="20"
				<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄 보기</option>
		</select>
	</div> <!-- 옵션선택 끝 -->
	<table class="table table-striped">
		<tr>
			<td>No.</td>
			<td width="50%">제목</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>		
		</tr>
		<c:forEach items="${list }" var="list">
			<tr>
				<td>${list.id }</td>
				<td><a href="${cp}/notice/detail?id=${list.id }">${list.title }</a></td>
				<td>${list.writer_Id }</td>
				<td><fmt:formatDate value="${list.regdate }" pattern="yyyy.MM.dd"/> </td>
				<td>${list.hit }</td>
			</tr>
		</c:forEach>
	</table>
	<div style="text-align:right">
	<a class="btn btn-primary" href="${cp}/notice/write_form">글쓰기</a>
	<a class="btn btn-primary" href="${cp}/notice/list">목록</a>
	</div>
	
	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="${cp }/notice/list?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">
				<span aria-hidden="true">&laquo;</span>
       			<span class="sr-only">Previous</span>
			</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="${cp }/notice/list?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="${cp }/notice/list?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">
				<span aria-hidden="true">&raquo;</span>
       			<span class="sr-only">Previous</span>
			</a>
		</c:if>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="${cp}/resources/js/bootstrap.js"></script>
</body>
</html>