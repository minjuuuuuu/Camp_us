<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/module/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="wrapper">
	<div class="content-wrapper">
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1 class="m-0">게시판</h1>
					</div>
				</div>
			</div>
		</div>

		<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	border-bottom: 1px solid #ddd;
	text-align: center;
}

.top-bar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

.top-bar form {
	display: flex;
	gap: 10px;
	align-items: center;
	width: 100%;
	justify-content: space-between;
}

.btn {
	padding: 6px 12px;
	background-color: #2ac1bc;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.btn:hover {
	background-color: #1aa6a1;
}

.write-btn {
	text-align: right;
	margin-top: 20px;
}

.pagination {
	margin-top: 30px;
	display: flex;
	justify-content: center;
	gap: 8px;
}

.pagination a {
	padding: 6px 12px;
	text-decoration: none;
	border: 1px solid #ddd;
	border-radius: 4px;
	color: #333;
}

.pagination a.active {
	background-color: #2ac1bc;
	color: white;
	font-weight: bold;
}

.pagination a:hover {
	background-color: #e6f7f6;
}
</style>

		<script>
			function submitCategoryForm() {
				document.getElementById('filterForm').submit();
			}
		</script>

		<form id="filterForm" method="get" action="${pageContext.request.contextPath}/campus/boardlist">

			<div class="top-bar">
				<div class="top-left">
					<select name="category" onchange="submitCategoryForm()">
						<option value="">전체</option>
						<option value="공지" ${category == '공지' ? 'selected' : ''}>공지</option>
						<option value="자유" ${category == '자유' ? 'selected' : ''}>자유</option>
						<option value="토론" ${category == '토론' ? 'selected' : ''}>토론</option>
						<option value="시험" ${category == '시험' ? 'selected' : ''}>시험</option>
					</select>
				</div>
				<div class="top-right">
					<label><input type="checkbox" name="hideMine"
						${hideMine == 'on' ? 'checked' : ''}> 내 글 숨기기</label> <select
						name="searchType">
						<option value="">전체</option>
						<option value="title" ${searchType == 'title' ? 'selected' : ''}>제목</option>
						<option value="writer" ${searchType == 'writer' ? 'selected' : ''}>작성자</option>
						<option value="content"
							${searchType == 'content' ? 'selected' : ''}>내용</option>
					</select> <input type="text" name="keyword" placeholder="검색어를 입력해 주세요."
						value="${keyword}" />
					<button type="submit" class="btn">검색</button>
				</div>
			</div>
		</form>

		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>말머리</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty boardList}">
					<tr>
						<td colspan="5">게시글이 없습니다.</td>
					</tr>
				</c:if>

				<%-- ✅ 게시글 번호 계산 --%>
				<c:forEach var="board" items="${boardList}" varStatus="status">
					<tr>
						<td>${board.boardId}</td>
						<!-- 이 줄로 변경 -->

						<td>${board.boardCat}</td>
						<td><a
							href="${pageContext.request.contextPath}/board/detail?bno=${board.boardId}">
								${board.boardName} </a> <c:if test="${not empty board.pfileName}">
								<i class="fas fa-paperclip" title="${board.pfileName}"></i>
							</c:if></td>
						<td>${board.memId}</td>
						<td><fmt:formatDate value="${board.boardDate}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="pagination">
			<a href="?page=1">&laquo;</a> <a href="?page=${page-1}"
				${page == 1 ? 'class="disabled"' : ''}>&lt;</a>
			<c:forEach var="i" begin="1" end="${totalPage}">
				<a href="?page=${i}" class="${i == page ? 'active' : ''}">${i}</a>
			</c:forEach>
			<a href="?page=${page+1}"
				${page == totalPage ? 'class="disabled"' : ''}>&gt;</a> <a
				href="?page=${totalPage}">&raquo;</a>
		</div>

		<div class="write-btn">
			<button type="button" class="btn btn-primary"
				onclick="openWritePopup()"
				style="padding: 6px 12px; font-size: 14px; border-radius: 4px; background-color: #2ac1bc; color: white; border: none; height: 36px;">
				작성하기</button>
		</div>

		<script>
			function openWritePopup() {
				window.open("${pageContext.request.contextPath}/board/write",
						"popupWrite",
						"width=800,height=600,scrollbars=yes,resizable=yes");
			}
		</script>
	</div>
</div>

</body>
</html>
