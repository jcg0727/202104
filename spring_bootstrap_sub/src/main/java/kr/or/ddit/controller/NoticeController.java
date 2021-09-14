package kr.or.ddit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.command.NoticeModifyCommand;
import kr.or.ddit.command.NoticeRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.NoticeVO;
import kr.or.ddit.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/main")
	public void main() {}
	
	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv)throws SQLException{
		String url = "notice/list";
		Map<String, Object> dataMap = noticeService.getNoticeList(cri);
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value="/registForm", method = RequestMethod.GET)
	public String registForm() {
		String url = "notice/regist";
		return url;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(NoticeRegistCommand noticeReq) throws SQLException, IOException{
		String url = "notice/regist_success";
		NoticeVO notice = noticeReq.toNoticeVO();
		noticeService.regist(notice);
		return url;
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(@ModelAttribute("nno") int nno, Model model) throws SQLException{
		String url = "notice/detail";
		NoticeVO notice = noticeService.getNotice(nno);
		model.addAttribute("notice", notice);
		return url;
	}
	
	@RequestMapping(value="/modifyForm", method=RequestMethod.GET)
	public String modifyForm(int nno, Model model)throws SQLException{
		String url = "notice/modify";
		NoticeVO notice = noticeService.getNotice(nno);
		model.addAttribute("notice", notice);
		return url;
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modify(NoticeModifyCommand modifyReq, HttpSession session, RedirectAttributes rttr) throws Exception{
		String url = "";
		NoticeVO notice = modifyReq.toNoticeVO();
		
		noticeService.modify(notice);
		return url;
	}
}
