package com.znzldhkvlwk.spring.notice.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.znzldhkvlwk.spring.notice.Notice;
import com.znzldhkvlwk.spring.notice.service.ImplNoticeService;
import com.znzldhkvlwk.spring.utils.PagingVo;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private ImplNoticeService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	
	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}
	
	
	
//	@RequestMapping("/list")
//	public String list(Model model) {
//		
//		model.addAttribute("list", service.list());
//		
//		return "list2";
//	}
	@RequestMapping("/test")
	public String test(Model model) {
		String result = service.test();
		model.addAttribute("test", result);
		
		return "home";
		
	}
	
	
	@RequestMapping("/list")
	public String list(PagingVo vo, Model model
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage){
		int total = service.getListCount();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "5";
		}
		vo = new PagingVo(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", vo);
		model.addAttribute("list", service.list(vo));
		
		return "list";
	}
	
	@RequestMapping("/write_form")
	public String write_form(Notice notice){
		
		return "write_form";
	}
	
	@RequestMapping(value = "/writeOk", method = RequestMethod.POST)
	public String write_ok(Notice notice) {
		
		int result = service.write(notice);
		if(result == 0) {
			System.out.println("insert failure!");
		} else {
			System.out.println("insert success!");
		}
		
		return "writeOk";
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		Notice notice = service.search(id);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy년MM월dd일 E요일 a hh시mm분");
		model.addAttribute("formattedDate", sf.format(notice.getRegdate()));
		model.addAttribute("n", notice);
		return "detail";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")String id) {
		int result = service.delete(Integer.parseInt(id));
		
		if(result == 1) {
			System.out.println("Delete Success!");
			return "deleteOk";
		}
		else {
			System.out.println("Delete Failure!");
			return "deleteFail";
		}
	}
	@RequestMapping("/modify_form")
	public String modify(@RequestParam("id")String id, Model model, Notice notice) {
		//수정을 위해서 현재 보고있는 공지사항 정보 가져옴
		model.addAttribute("n", service.search(Integer.parseInt(id)));
		
		return "modify_form";
	}
	
	@RequestMapping("/modifyOk")
	public String modifyOk(Notice notice) {
		int result = service.modify(notice);
		
		if(result > 0) System.out.println("modify 성공!");
		else System.out.println("modify failure");
		
		return "modifyOk";
	}
	
}
