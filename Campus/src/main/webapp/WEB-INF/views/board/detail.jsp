<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>게시판 - 상세보기</title>
<style>
body {
	font-family: '맑은 고딕', sans-serif;
	padding: 30px;
}

h1 {
	font-size: 24px;
	margin-bottom: 20px;
}

.title {
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 10px;
}

.meta {
	color: gray;
	margin-bottom: 20px;
}

.content {
	border-top: 1px solid #ddd;
	padding: 20px 0;
	margin-bottom: 20px;
}

.attachment {
	margin-bottom: 30px;
	color: #444;
}

.btn-box {
	text-align: right;
	margin-bottom: 30px;
}

.btn {
	padding: 6px 14px;
	border: none;
	border-radius: 4px;
	margin-left: 8px;
	cursor: pointer;
}

.btn-edit {
	background-color: #20c997;
	color: white;
}

.btn-delete {
	background-color: #adb5bd;
	color: white;
}

.comment-section {
	margin-top: 40px;
}

.comment {
	border: 1px solid #ccc;
	padding: 12px;
	border-radius: 4px;
	margin-bottom: 10px;
	position: relative;
}

.comment-meta {
	font-weight: bold;
	margin-bottom: 6px;
}

.comment-btns {
	position: absolute;
	top: 12px;
	right: 12px;
}

.comment-btns button {
	margin-left: 5px;
	padding: 4px 8px;
	border: none;
	border-radius: 4px;
	background-color: #aaa;
	color: white;
	cursor: pointer;
}

.comment-form textarea {
	width: 100%;
	height: 80px;
	padding: 10px;
	box-sizing: border-box;
	margin-bottom: 10px;
}

.comment-form .btn {
	background-color: #20c997;
	color: white;
}

.footer-btns {
	text-align: right;
	margin-top: 30px;
}

.btn-back {
	background-color: #20c997;
	color: white;
}

.report {
	text-align: right;
	margin-bottom: 10px;
}

.report a {
	cursor: pointer;
	margin-left: 10px;
}

.report a:hover {
	color: #20c997;
}

/* 신고 팝업 */
.report-popup {
	display: none;
	position: fixed;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	background: white;
	border: 1px solid #ccc;
	padding: 30px;
	width: 500px;
	z-index: 9999;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}

.report-popup h3 {
	font-size: 20px;
	margin-bottom: 20px;
}

.report-popup select, .report-popup textarea {
	width: 100%;
	padding: 12px;
	font-size: 14px;
	border: 1px solid #ddd;
	border-radius: 4px;
	margin-top: 10px;
}

.report-popup .desc {
	font-size: 13px;
	color: #666;
	margin-top: 8px;
}

.popup-buttons {
	margin-top: 20px;
	text-align: right;
}

.popup-buttons button {
	padding: 6px 14px;
	border: none;
	border-radius: 4px;
	margin-left: 10px;
	cursor: pointer;
}

.popup-buttons button:first-child {
	background-color: #ccc;
	color: black;
}

.popup-buttons button:last-child {
	background-color: #20c997;
	color: white;
}
</style>
<script>
	// 신고 팝업 열고 닫기
	function openReportPopup() {
		document.getElementById("reportPopup").style.display = "block";
	}
	function closeReportPopup() {
		document.getElementById("reportPopup").style.display = "none";
	}
	function toggleTextarea() {
		const select = document.getElementById("reasonSelect");
		const textareaBox = document.getElementById("customReasonBox");
		textareaBox.style.display = select.value.includes("기타") ? "block"
				: "none";
	}
	function submitReport() {
		const reason = document.getElementById("reasonSelect").value;
		const custom = document.getElementById("customReasonText").value.trim();
		if (!reason || (reason.includes("기타") && !custom)) {
			alert("신고 사유를 확인해주세요.");
			return;
		}
		alert("신고가 정상적으로 접수되었습니다.");
		closeReportPopup();
	}

	// 댓글 수정 기능
	function enableEdit(btn) {
		const comment = btn.closest('.comment');
		const textDiv = comment.querySelector('.comment-text');
		const originalText = textDiv.innerText.trim();

		const textarea = document.createElement('textarea');
		textarea.value = originalText;
		textarea.style.width = '100%';
		textarea.style.height = '80px';
		textarea.classList.add('edit-area');

		const saveBtn = document.createElement('button');
		saveBtn.innerText = '저장';
		saveBtn.style.marginTop = '10px';
		saveBtn.onclick = function() {
			textDiv.innerText = textarea.value;
			textDiv.style.display = 'block';
			textarea.remove();
			saveBtn.remove();
		};

		textDiv.style.display = 'none';
		comment.insertBefore(textarea, textDiv.nextSibling);
		comment.insertBefore(saveBtn, textarea.nextSibling);
	}
</script>
</head>
<body>

	<h1>게시판</h1>

	<!-- 글 수정/삭제 -->
	<div class="btn-box">
		<button class="btn btn-edit">수정</button>
		<button class="btn btn-delete">삭제</button>
	</div>

	<div class="title">${board.boardName}</div>
<div class="meta">작성자: ${board.memId} &nbsp; | &nbsp; ${board.boardDate}</div>


	<div class="content">
		<p>
			안녕하세요. 정다운 교수입니다.<br>
			<br> 2차 현장학습(7월 18일 금요일)을 앞두고, 사전에 준비해야 할 물품을 아래와 같이 안내드립니다.<br>
			모든 학생은 반드시 확인 후 준비해주시기 바랍니다.
		</p>
	</div>

	<div class="attachment">📎 [2차 현장학습] 준비물 지침서.pdf</div>

	<div class="report">
	 <a onclick="openReportPopup()">[신고]</a>
	</div>

	<!-- 댓글 영역 -->
	<div class="comment-section">
		<div class="comment">
			<div class="comment-meta">
				김민주&nbsp; 2025-07-15 11:24
				<div class="comment-btns">
					<button onclick="enableEdit(this)">수정</button>
					<button>삭제</button>
				</div>
			</div>
			<div class="comment-text">준비물 중에 개인 노트북도 가져가야 하나요? 테블릿만 가져가도 되는
				일정인지 궁금합니다.</div>
		</div>

		<div class="comment-form">
			<div class="comment-meta">김민주&nbsp; 2025-07-15 11:24</div>
			<textarea placeholder="댓글을 입력해주세요."></textarea>
			<button class="btn">등록</button>
		</div>
	</div>

<div class="footer-btns">
    <button class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/boardlist'">목록</button>
</div>


	<!-- 신고 팝업 -->
	<div id="reportPopup" class="report-popup">
		<h3>신고 글 작성</h3>
		<select id="reasonSelect" onchange="toggleTextarea()">
			<option selected disabled>삭제 사유를 선택하세요</option>
			<option>1. 욕설/비방</option>
			<option>2. 음란물/선정적인 내용</option>
			<option>3. 정치적 선동/특정 단체 홍보</option>
			<option>4. 개인정보 노출</option>
			<option>5. 타인 사칭/허위정보</option>
			<option>6. 다른 이용자 신고 누적</option>
			<option>7. 기타 (직접 입력)</option>
		</select>

		<div id="customReasonBox" style="display: none;">
			<textarea id="customReasonText" placeholder="[직접 입력란]"></textarea>
		</div>

		<div class="desc">* 선택된 사유는 관리자에게 전달됩니다.</div>
		<div class="popup-buttons">
			<button onclick="closeReportPopup()">취소</button>
			<button onclick="submitReport()">보내기</button>
		</div>
	</div>

</body>
</html>
