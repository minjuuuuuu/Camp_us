package com.camp_us.dto;

public class BoardVO {
    private String boardId;       // 게시물 ID
    private String boardName;     // 게시물 제목
    private String boardCat;      // 카테고리
    private String boardDate;     // 최초 작성일
    private String pfileName;     // 첨부파일명
    private String pfileDetail;   // 첨부파일 상세
    private String boardDesc;     // 게시물 본문
    private String boardMod;      // 수정일
    private String memId;         // 작성자 (회원 ID)

    // 기본 생성자
    public BoardVO() {
    }

    // 전체 생성자 (DB 컬럼 기준)
    public BoardVO(String boardId, String boardName, String boardCat, String boardDate,
                   String pfileName, String pfileDetail, String boardDesc,
                   String boardMod, String memId) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.boardCat = boardCat;
        this.boardDate = boardDate;
        this.pfileName = pfileName;
        this.pfileDetail = pfileDetail;
        this.boardDesc = boardDesc;
        this.boardMod = boardMod;
        this.memId = memId;
    }

    // 간단 생성자 (Controller에서 더미 데이터 테스트용)
    public BoardVO(int bno, String category, String title, String writer, String regDate) {
        this.boardId = String.valueOf(bno); // 정수 → 문자열로 변환
        this.boardCat = category;
        this.boardName = title;
        this.memId = writer;
        this.boardDate = regDate;
    }

    // Getter & Setter
    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getBoardCat() {
        return boardCat;
    }

    public void setBoardCat(String boardCat) {
        this.boardCat = boardCat;
    }

    public String getBoardDate() {
        return boardDate;
    }

    public void setBoardDate(String boardDate) {
        this.boardDate = boardDate;
    }

    public String getPfileName() {
        return pfileName;
    }

    public void setPfileName(String pfileName) {
        this.pfileName = pfileName;
    }

    public String getPfileDetail() {
        return pfileDetail;
    }

    public void setPfileDetail(String pfileDetail) {
        this.pfileDetail = pfileDetail;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc;
    }

    public String getBoardMod() {
        return boardMod;
    }

    public void setBoardMod(String boardMod) {
        this.boardMod = boardMod;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }
}
