
package com.camp_us.dto;

import java.util.Date;

public class ClassFileVO {
    private String cfNo;        // 자료실 게시판 번호 (PK)
    private String lecId;       // 강의 ID
    private String cfName;      // 제목
    private String cfWriterId;  // 작성자 ID
    private Date   cfDate;      // 작성일
    private String cfFileName;  // 첨부파일명
    private String cfFileDetail;// 첨부파일 상세(경로 등)
    private String cfDesc;      // 내용

    // 선택: 화면용
    private Integer rnum;       // 목록 번호
    private Integer viewCnt;    // 조회수(스키마 없으면 사용 X)

    // getter/setter
    public String getCfNo() { return cfNo; }
    public void setCfNo(String cfNo) { this.cfNo = cfNo; }
    public String getLecId() { return lecId; }
    public void setLecId(String lecId) { this.lecId = lecId; }
    public String getCfName() { return cfName; }
    public void setCfName(String cfName) { this.cfName = cfName; }
    public String getCfWriterId() { return cfWriterId; }
    public void setCfWriterId(String cfWriterId) { this.cfWriterId = cfWriterId; }
    public Date getCfDate() { return cfDate; }
    public void setCfDate(Date cfDate) { this.cfDate = cfDate; }
    public String getCfFileName() { return cfFileName; }
    public void setCfFileName(String cfFileName) { this.cfFileName = cfFileName; }
    public String getCfFileDetail() { return cfFileDetail; }
    public void setCfFileDetail(String cfFileDetail) { this.cfFileDetail = cfFileDetail; }
    public String getCfDesc() { return cfDesc; }
    public void setCfDesc(String cfDesc) { this.cfDesc = cfDesc; }
    public Integer getRnum() { return rnum; }
    public void setRnum(Integer rnum) { this.rnum = rnum; }
    public Integer getViewCnt() { return viewCnt; }
    public void setViewCnt(Integer viewCnt) { this.viewCnt = viewCnt; }
}
