package kr.or.ddit.command;

import kr.or.ddit.dto.NoticeVO;

public class NoticeModifyCommand {
	private int nno;
	private String title;
	private String content;
	private String writer;
	
	public String getNno() {
		return nno+"";
	}
	public void setNno(int nno) {
		this.nno = nno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public NoticeVO toNoticeVO() {
		NoticeVO notice = new NoticeVO();
		notice.setNno(this.nno);
		notice.setTitle(this.title);
		notice.setContent(this.content);
		notice.setWriter(this.writer);
		return notice;
		
	}
}
