package kr.or.ddit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService; 
	
	@RequestMapping("/member/list")
	public void list(SearchCriteria cri, Model model) throws Exception {

		Map<String, Object> dataMap = memberService.getMemberList(cri);
		
		model.addAttribute("memberList", dataMap.get("memberList"));
		model.addAttribute("pageMaker", dataMap.get("pageMaker"));

		
	}
}
