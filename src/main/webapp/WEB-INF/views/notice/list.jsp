<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${cp}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${cp}/resources/css/myStyle.css">
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
	<div id="content-list" class="container" >
		<h2 class="mt-5">게시판</h2>
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
		<c:if test="${not empty sessionScope.mem}">
			<a class="btn btn-primary" href="${cp}/notice/write_form">글쓰기</a>
		</c:if>
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