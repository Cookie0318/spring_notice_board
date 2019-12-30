package com.znzldhkvlwk.spring.notice;

import java.util.Date;

public class Notice {
	private int 	id;
	private String 	title;
	private String 	writer_Id;
	private String 	content;
	private Date 	regdate;
	private int		hit;
	private String	files;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter_Id() {
		return writer_Id;
	}
	public void setWriter_Id(String writer_Id) {
		this.writer_Id = writer_Id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	
	
	
}
