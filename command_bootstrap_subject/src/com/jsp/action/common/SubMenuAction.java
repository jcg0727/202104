package com.jsp.action.common;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.controller.JSONResolver;
import com.jsp.dto.MenuVO;
import com.jsp.service.MenuService;

public class SubMenuAction implements Action {
   
   private MenuService menuService;
   public void setMenuService(MenuService menuService) {
      this.menuService = menuService;
   }
   
   
   @Override
   public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String url = null; //화면은 없다라고 명시적으로 적어준 것 ( 너 얼마 있니? null 없어~)
      
      String mCode = request.getParameter("mCode");
      List<MenuVO> subMenu = null;
      
      try {
         subMenu = menuService.getSubMenuList(mCode);
         
          JSONResolver.view(response, subMenu);
          
      } catch (Exception e) {
         response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
         e.printStackTrace();
      }
      
      return url;
   }

}