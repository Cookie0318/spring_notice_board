package com.znzldhkvlwk.spring.notice.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.znzldhkvlwk.spring.member.Member;
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
	
	
	@RequestMapping("/list")
	public String list(PagingVo vo, Model model
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage){
		int total = service.getListCount();

		vo = getPagingVo(total, nowPage, cntPerPage);
		model.addAttribute("paging", vo);
		model.addAttribute("list", service.list(vo));
		
		return "notice/list";
	}
	
	@RequestMapping("/write_form")
	public String write_form(Notice notice){
		
		return "notice/write_form";
	}
	
	@RequestMapping(value = "/writeOk", method = RequestMethod.POST)
	public String write_ok(Notice notice) {
		
		int result = service.write(notice);
		if(result == 0) {
			System.out.println("insert failure!");
		} else {
			System.out.println("insert success!");
		}
		
		return "notice/writeOk";
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		service.hitPlus(id);
		Notice notice = service.search(id);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy년MM월dd일 E요일 a hh시mm분");
		model.addAttribute("formattedDate", sf.format(notice.getRegdate()));
		model.addAttribute("n", notice);
		return "notice/detail";
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")String id, Principal principal) {
		//비정상적 접근을 막기위해
		//접근 아이디
		Member mem = (Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loginId = mem.getId();
		Notice notice = service.search(Integer.parseInt(id));
		int result = 0;
		if(notice != null) { //게시글 존재하는경우
			if(loginId.equals(notice.getWriter_Id())) { //현재 로그인한 사용자가 작성자인 경우
				result = service.delete(Integer.parseInt(id));
			}else {
				result = 2; //비정상 접근(작성자가 아니면 버튼이 없는데 접근한 경우임)
			}
		} 
		
		if(result == 1) { //삭제 성공
			System.out.println("Delete Success!");
			return "notice/deleteOk";
		}
		else if(result == 0){ //게시글이 존재하지 않는 경우
			System.out.println("Delete Failure!");
			return "notice/deleteFail";
		} else { //비정상접근인 경우
			System.out.println("비정상 접근");
			return "invalidAccess";
		}
			
	}
	@RequestMapping("/modify_form")
	public String modify(@RequestParam("id")String id, Model model, Notice notice, HttpSession session) {
		Member mem = (Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String loginId = mem.getId();
		//수정을 위해서 현재 보고있는 공지사항 정보 가져옴
		Notice n = service.search(Integer.parseInt(id));
		
		if(n != null && loginId.equals(n.getWriter_Id())) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy년MM월dd일 E요일 a hh시mm분");
			model.addAttribute("formattedDate", sf.format(n.getRegdate()));
			model.addAttribute("n", n);
			return "notice/modify_form";
		}
		
		return "invalidAccess";
		
		
	}
	
	@RequestMapping("/modifyOk")
	public String modifyOk(Notice notice) {
		int result = service.modify(notice);
		
		if(result > 0) System.out.println("modify 성공!");
		else System.out.println("modify failure");
		
		return "notice/modifyOk";
	}
	@RequestMapping("mylist")
	public String mylist(HttpSession session, PagingVo vo, Model model
			, Principal principal
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		Member mem = (Member)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String id = mem.getId();
		
		int total = service.getMyListCount(id);
//		if (nowPage == null && cntPerPage == null) {
//			nowPage = "1";
//			cntPerPage = "5";
//		} else if (nowPage == null) {
//			nowPage = "1";
//		} else if (cntPerPage == null) {
//			cntPerPage = "5";
//		}
//		vo = new PagingVo(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		vo = getPagingVo(total, nowPage, cntPerPage);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("vo", vo);
		model.addAttribute("paging", vo);
		model.addAttribute("list", service.mylist(map));
		
		return "notice/list";
		
	}
	
	public PagingVo getPagingVo(int a_total, String a_nowPage, String a_cntPerPage) {
		String nowPage = a_nowPage;
		String cntPerPage = a_cntPerPage;
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) {
			cntPerPage = "5";
		}
		
		return new PagingVo(a_total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
	}
	
}
