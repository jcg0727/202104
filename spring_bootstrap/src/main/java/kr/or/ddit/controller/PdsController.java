package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;

import kr.or.ddit.command.PdsModifyCommand;
import kr.or.ddit.command.PdsRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.dto.PdsVO;
import kr.or.ddit.service.PdsService;
import kr.or.ddit.util.GetAttachesAsMultpartFiles;

@Controller
@RequestMapping("/pds")
public class PdsController {
	
	@Autowired
	private PdsService service;
	
	@Resource(name = "fileUploadPath")
	private String fileUploadPath;
	
	@RequestMapping("/main")
	public void main() throws Exception{}
	
	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws Exception{
		String url = "pds/list";
		
		Map<String, Object> dataMap = service.getList(cri);
		
		mnv.addObject("dataMap" , dataMap);
		mnv.setViewName(url);
		return mnv;
	}
		
	@RequestMapping("/registForm")
	public ModelAndView registForm(ModelAndView mnv) {
		String url = "pds/regist";
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces="text/plain;charset=utf-8")
	public String regist(PdsRegistCommand registReq, HttpServletRequest request, RedirectAttributes rttr) throws Exception{
		
		String url = "redirect:/pds/list.do";
		
		PdsVO pds = registReq.toPdsVO();
		
		List<AttachVO> attachList = GetAttachesAsMultpartFiles.save(registReq.getUploadFile(), fileUploadPath);
		
		//pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		pds.setTitle((String)request.getAttribute("XSStitle"));
		
		pds.setAttachList(attachList);
		
		service.regist(pds);
		
		rttr.addFlashAttribute("from" , "regist");
		
		
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int pno, String from, ModelAndView mnv) throws Exception{
		String url = "pds/detail";
		
		PdsVO pds = null;
		if(from != null && from.equals("list")) {
			pds = service.read(pno);
			url = "redirect:/pds/detail.do?pno=" + pno;
		}else {
			pds = service.getPds(pno);
		}
				
		//파일명 재정의
		if(pds!=null) {
			List<AttachVO> attachList = pds.getAttachList();
			if(attachList != null) {
				for(AttachVO attach : attachList) {
					String fileName = attach.getFileName().split("\\$\\$")[1];
					attach.setFileName(fileName);
				}
			}
		}	
		mnv.addObject("pds" , pds);
		mnv.setViewName(url);
		
		return mnv;
		
	}

	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(int pno, ModelAndView mnv)throws Exception {
		String url = "pds/modify";
		
		PdsVO pds = service.getPds(pno);
		
		//파일명 재정의
				List<AttachVO> attachList = pds.getAttachList();
				if(attachList != null) {
					for(AttachVO attach : attachList) {
						String fileName = attach.getFileName().split("\\$\\$")[1];
						attach.setFileName(fileName);
					}
				}
		
		mnv.addObject("pds", pds);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modify")
	public String modifyPOST(PdsModifyCommand modifyReq, HttpServletRequest request, RedirectAttributes rttr) throws Exception{
		String url = "redirect:/pds/detail.do";
		
		//파일삭제
		if(modifyReq.getDeleteFile() != null && modifyReq.getDeleteFile().length>0) {
			for(String anoStr : modifyReq.getDeleteFile()) {
				int ano = Integer.parseInt(anoStr);
				AttachVO attach = service.getAttachByAno(ano);
				File deleteFile = new File(attach.getUploadPath(), attach.getFileName());
				if(deleteFile.exists()) {
					deleteFile.delete(); //File삭제
				}
				service.removeAttachByAno(ano); //db삭제
			}
		}
		//파일저장
		List<AttachVO> attachList = GetAttachesAsMultpartFiles.save(modifyReq.getUploadFile(), fileUploadPath);
		
		//PdsVO setting
		PdsVO pds = modifyReq.toPdsVO();
		pds.setAttachList(attachList);
		
		//pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		pds.setTitle((String)request.getAttribute("XSStitle"));
		
		//db저장
		service.modify(pds);
		
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("pno", pds.getPno());
		
		return url;
	}
	
	@RequestMapping("/remove")
	public String remove(int pno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";
		
		//첨부파일 삭제
		List<AttachVO> attachList = service.getPds(pno).getAttachList();
		if(attachList != null) {
			for(AttachVO attach : attachList) {
				File target = new File(attach.getUploadPath(), attach.getFileName());
				if(target.exists()) {
					target.delete();
				}
			}
		}
		
		//DB삭제
		service.remove(pno);
		
		rttr.addFlashAttribute("from","remove");
		rttr.addAttribute("pno",pno);
		return url;
	}
	
	@RequestMapping("/getFile")
	public String getFile(int ano, Model model)throws Exception{
		String url = "downloadFile";
		
		AttachVO attach = service.getAttachByAno(ano);
		
		model.addAttribute("savedPath" , attach.getUploadPath());
		model.addAttribute("fileName", attach.getFileName());
		
		return url;
	}
}
