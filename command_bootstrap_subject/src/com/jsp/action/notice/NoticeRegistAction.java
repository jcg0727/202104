package com.jsp.action.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;
import com.jsp.service.NoticeServiceImpl;

public class NoticeRegistAction implements Action{
	
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = "notice/list";
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		NoticeVO noticeVo = new NoticeVO(); 
		noticeVo.setWriter(writer);
		noticeVo.setTitle(title);
		noticeVo.setContent(content);
		
		noticeService.regist(noticeVo);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.parent.location.reload(true);");
		out.println("window.close()");
		out.println("</script>");
		out.close();
		
		return url;
	}
	
	

}
