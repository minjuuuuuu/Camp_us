package com.camp_us.dto;

public class BoardVO {
    private int bno;
    private String category;
    private String title;
    private String writer;
    private String regDate;
    private String content; 

    public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BoardVO(int bno, String category, String title, String writer, String regDate) {
        this.bno = bno;
        this.category = category;
        this.title = title;
        this.writer = writer;
        this.regDate = regDate;
    }
    // Getter & Setter
    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}