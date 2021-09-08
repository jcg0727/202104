package com.jsp.action.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.XSSResolver;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

public class BoardRegistAction implements Action {


	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="board/regist_success";
		
		XSSResolver.parseXSS(request);
		
		String title = (String)request.getAttribute("XSStitle");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		
		boardService.regist(board);
		
		return url;
	}

}
