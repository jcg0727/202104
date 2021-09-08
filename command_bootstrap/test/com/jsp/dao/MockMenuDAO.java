package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dao.MenuDAO;
import com.jsp.dto.MenuVO;

public class MockMenuDAO implements MenuDAO{

	@Override
	public List<MenuVO> selectMainMenu(SqlSession session) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuVO> selectSubMenu(SqlSession session, String mCode) throws SQLException {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public MenuVO selectMenuByMcode(SqlSession session, String mCode) throws SQLException {
		MenuVO menu = null;
		if(mCode.equals("M010000")) {
			menu = new MenuVO();
			menu.setMcode("M010000");
		}
		return menu;
	}

	@Override
	public MenuVO selectMenuByMname(SqlSession session, String mName) throws SQLException {
		MenuVO menu = null;
		if(mName.equals("회원관리")) {
			menu = new MenuVO();
			menu.setMname("회원관리");
		}
		return menu;
	}

}
