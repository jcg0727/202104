package com.jsp.action.notice;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.NoticeService;

public class NoticeDeleteAction implements Action {

	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String url = "notice/list";
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		noticeService.remove(nno);
		
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.parent.location.reload(true);");
		out.println("window.close()");
		out.println("</script>");
		out.close();
		return url;
	}

}
