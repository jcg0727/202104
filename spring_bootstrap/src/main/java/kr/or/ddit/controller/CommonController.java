package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.dto.MenuVO;
import kr.or.ddit.exception.InvalidPasswordException;
import kr.or.ddit.exception.NotFoundIDException;
import kr.or.ddit.service.MemberService;
import kr.or.ddit.service.MenuService;
import kr.or.ddit.util.ExceptionLoggerHelper;

@Controller
public class CommonController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping("/index")
	public String index(@RequestParam(defaultValue="M000000")String mCode, Model model) throws Exception {	
		String url="common/indexPage";
		
		try {
			List<MenuVO> menuList = menuService.getMainMenuList();			
			MenuVO menu = menuService.getMenuByMcode(mCode);
			
			model.addAttribute("menuList",menuList);
			model.addAttribute("menu",menu);
		} catch (SQLException e) {			
			e.printStackTrace();
			url=null;
			//throw e;
		}
		return url;
	}
	
	@RequestMapping(value="/common/loginForm" , method=RequestMethod.GET)
	public void loginForm() {}		
	
	
	@RequestMapping(value="/common/login", method=RequestMethod.POST)
	public String login(String id, String pwd, HttpServletRequest request, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		String url = "redirect:/index.do";
		
		try {
			memberService.login(id, pwd);
			session.setAttribute("loginUser", memberService.getMember(id));
		}catch(InvalidPasswordException | NotFoundIDException e) {
			
			rttr.addFlashAttribute("message", e.getMessage());
			url="redirect:/common/loginForm.do";
			
		}catch(SQLException e) {
			
			ExceptionLoggerHelper.write(request, e, memberService);
			e.printStackTrace();
			throw e;
		}
		return url;
	}
	
	@RequestMapping("/subMenu")
	@ResponseBody
	public ResponseEntity<List<MenuVO>> subMenuToJson(String mCode) throws Exception {
		ResponseEntity<List<MenuVO>> entity = null;

		List<MenuVO> subMenu = null;

		try {
			subMenu = menuService.getSubMenuList(mCode);			
				
			entity = new ResponseEntity<List<MenuVO>>(subMenu, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<MenuVO>>(HttpStatus.INTERNAL_SERVER_ERROR);

			e.printStackTrace();
		}

		return entity;
	}
	@RequestMapping(value="/common/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		String url = "redirect:/";
		session.invalidate();
		return url;
	}
	@RequestMapping("/main")
	public String main() {
		String url = "common/main";
		return url;
	}
	
	
}
