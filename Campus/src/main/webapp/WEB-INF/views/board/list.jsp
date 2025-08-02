<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
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
        .pagination a.disabled {
            pointer-events: none;
            opacity: 0.5;
        }
    </style>
    <script>
        function submitCategoryForm() {
            document.getElementById('filterForm').submit();
        }
        function openWritePopup() {
            window.open("${pageContext.request.contextPath}/board/write",
                "popupWrite", "width=800,height=600,scrollbars=yes,resizable=yes");
        }
    </script>
</head>
<body>

<div class="wrapper">
    <div class="content-wrapper">
        <h1>게시판</h1>

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
                    <label>
                        <input type="checkbox" name="hideMine" ${hideMine == 'on' ? 'checked' : ''}> 내 글 숨기기
                    </label>
                    <select name="searchType">
                        <option value="">전체</option>
                        <option value="title" ${searchType == 'title' ? 'selected' : ''}>제목</option>
                        <option value="writer" ${searchType == 'writer' ? 'selected' : ''}>작성자</option>
                        <option value="content" ${searchType == 'content' ? 'selected' : ''}>내용</option>
                    </select>
                    <input type="text" name="keyword" placeholder="검색어를 입력해 주세요." value="${keyword}" />
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
                    <tr><td colspan="5">게시글이 없습니다.</td></tr>
                </c:if>
                <c:forEach var="board" items="${boardList}" varStatus="status">
                    <tr>
                        <td>${board.boardId}</td>
                        <td>${board.boardCat}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/board/detail?bno=${board.boardId}">
                                ${board.boardName}
                            </a>
                            <c:if test="${not empty board.pfileName}">
                                <i class="fas fa-paperclip" title="${board.pfileName}"></i>
                            </c:if>
                        </td>
                        <td>${board.memId}</td>
                        <td><fmt:formatDate value="${board.boardDate}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="pagination">
            <c:choose>
                <c:when test="${page == 1}">
                    <a class="disabled" href="#">&lt;</a>
                </c:when>
                <c:otherwise>
                    <a href="?page=${page - 1}">&lt;</a>
                </c:otherwise>
            </c:choose>

            <c:forEach var="i" begin="1" end="${totalPage}">
                <c:choose>
                    <c:when test="${i == page}">
                        <a href="?page=${i}" class="active">${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:choose>
                <c:when test="${page == totalPage}">
                    <a class="disabled" href="#">&gt;</a>
                </c:when>
                <c:otherwise>
                    <a href="?page=${page + 1}">&gt;</a>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="write-btn">
            <button type="button" class="btn btn-primary" onclick="openWritePopup()">작성하기</button>
        </div>
    </div>
</div>

</body>
</html>
