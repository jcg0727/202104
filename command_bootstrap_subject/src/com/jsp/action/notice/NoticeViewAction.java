package com.jsp.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

import sun.print.resources.serviceui;

public class NoticeViewAction implements Action {
	
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "notice/view";
		int nno = Integer.parseInt(request.getParameter("nno"));
				
		NoticeVO noticeVo = noticeService.getNotice(nno);
		request.setAttribute("noticeVo", noticeVo);
		return url;
	}

}
