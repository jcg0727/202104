package com.jsp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jsp.dto.NoticeVO;
import com.jsp.request.Criteria;
import com.jsp.request.SearchCriteria;

public interface NoticeService {
	
	//회원정보조회
	NoticeVO getNotice(int nno) throws SQLException;
	
	//공지리스트조회
	List<NoticeVO> getSearchNoticeList() throws SQLException;
	List<NoticeVO> getSearchNoticeList(Criteria cri) throws SQLException;
	Map<String,Object> getSearchNoticeList(SearchCriteria cri) throws SQLException;
	
	//공지등록
	public void regist(NoticeVO notice) throws SQLException;
	
	//공지수정
	void modify(NoticeVO notice) throws SQLException;
	
	//공지삭제
	void remove(int nno) throws SQLException;
}
