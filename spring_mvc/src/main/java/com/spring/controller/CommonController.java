package com.spring.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.command.LoginCommand;

@Controller
public class CommonController {
	
	@RequestMapping(value="/common/login",method=RequestMethod.GET)
	public void loginForm()throws Exception{
		
	}
	
	@RequestMapping(value="/common/login",method=RequestMethod.POST)
	public String loginPost(String id, @RequestParam(name="pwd", defaultValue="ttt")String pwddd, HttpSession session, HttpServletResponse response)throws Exception{
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		if(id.equals("mimi") && pwddd.equals("mimi")) {
			session.setAttribute("loginUser", id);
			out.println("<script>alert('로그인성공');</script>");
		}else {
			out.println("<script>alert('로그인실패');</script>");
		}
		return null;
	}
	
}
