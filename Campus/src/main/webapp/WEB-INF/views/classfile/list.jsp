<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>자료실</title>
<style>
  body { font-family:'맑은 고딕',sans-serif; padding:30px; }
  .page-title { font-size:28px; font-weight:800; margin-bottom:18px; }
  .searchbar { display:flex; gap:8px; justify-content:flex-end; margin-bottom:12px; }
  select, input[type="text"] {
    border:1px solid #ccc; border-radius:6px; padding:8px 10px; font-size:14px; outline:none;
  }
  .search-btn {
    border:1px solid #19a; background:#19a; color:#fff; border-radius:6px;
    padding:8px 12px; cursor:pointer;
  }
  table { width:100%; border-collapse:collapse; border-top:3px solid #63b3ad; }
  th, td { padding:12px 10px; border-bottom:1px solid #ddd; text-align:center; }
  th { background:#f7f7f7; font-weight:700; }
  td.title { text-align:left; }
  .clip { cursor:pointer; }
  .pagination { display:flex; justify-content:center; gap:6px; margin-top:18px; }
  .pagination a, .pagination span {
    min-width:34px; padding:8px 10px; border:1px solid #ddd; border-radius:6px;
    text-decoration:none; color:#333; font-size:14px;
  }
  .pagination .active { background:#2bb; color:#fff; border-color:#2bb; }
</style>
</head>
<body>

  <div class="page-title">자료실</div>

  <!-- 검색 -->
  <form class="searchbar" method="get" action="${pageContext.request.contextPath}/classfile/list">
    <select name="searchType">
      <option value="">전체</option>
      <option value="t">제목</option>
      <option value="w">작성자</option>
      <option value="c">내용</option>
    </select>
    <input type="text" name="keyword" value="${param.keyword}" placeholder="검색어를 입력해 주세요.">
    <button type="submit" class="search-btn">검색</button>
  </form>

  <!-- 목록 -->
  <table>
    <colgroup>
      <col style="width:8%">
      <col>
      <col style="width:14%">
      <col style="width:16%">
      <col style="width:10%">
      <col style="width:10%">
    </colgroup>
    <thead>
      <tr>
        <th>No</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
        <th>첨부파일</th>
        <th>조회</th>
      </tr>
    </thead>
    <tbody>
      <c:if test="${empty list}">
        <tr><td colspan="6" style="text-align:center;color:#777;padding:40px 0;">등록된 자료가 없습니다.</td></tr>
      </c:if>

      <c:forEach var="row" items="${list}">
        <tr>
          <td>${row.cfNo}</td>
          <td class="title">
            <a href="${pageContext.request.contextPath}/classfile/detail?cfNo=${row.cfNo}">
              ${row.cfName}
            </a>
          </td>
          <td>${row.cfWriterId}</td>
          <td><fmt:formatDate value="${row.cfDate}" pattern="yyyy-MM-dd"/></td>
          <td>
            <c:if test="${not empty row.cfFileName}">
              <a class="clip" href="${pageContext.request.contextPath}/classfile/download?cfNo=${row.cfNo}" title="다운로드">
                📎
              </a>
            </c:if>
          </td>
          <td>${row.viewCnt}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

  <!-- 페이지네이션 -->
  <c:if test="${pageMaker.totalCount > 0}">
    <div class="pagination">
      <c:set var="base" value="${pageContext.request.contextPath}/classfile/list?searchType=${param.searchType}&keyword=${param.keyword}"/>

      <a href="${base}&page=1">&laquo;</a>
      <a href="${base}&page=${pageMaker.startPage - 1}">&lt;</a>

      <c:forEach var="p" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
        <c:choose>
          <c:when test="${p == pageMaker.page}">
            <span class="active">${p}</span>
          </c:when>
          <c:otherwise>
            <a href="${base}&page=${p}">${p}</a>
          </c:otherwise>
        </c:choose>
      </c:forEach>

      <a href="${base}&page=${pageMaker.endPage + 1}">&gt;</a>
      <a href="${base}&page=${pageMaker.maxPage}">&raquo;</a>
    </div>
  </c:if>

</body>
</html>
