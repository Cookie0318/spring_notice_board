package com.znzldhkvlwk.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.znzldhkvlwk.spring.member.Member;
import com.znzldhkvlwk.spring.member.service.ImplMemberService;
import com.znzldhkvlwk.spring.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	@Qualifier("MemberService")
	private ImplMemberService service;
	
	
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping("/join_form")
	public String join(Member member) {
		return "member/join_form";
	}
	
	@RequestMapping(value="/joinOk", method=RequestMethod.POST)
	public String joinOk(Member member) {
		int result = service.join(member);
		if(result == 0) System.out.println("회원가입 오류");
		else System.out.println("회원가입 완료");
		return "member/joinOk";
	}
	
	@RequestMapping("/login_form")
	public String login(Member member) {
		return "member/login_form";
	}
	
	@RequestMapping(value = "loginOk" , method = RequestMethod.POST)
	public String loginOk(Member member, HttpSession session) {
		Member mem = service.login(member);
		if(mem == null) {
			return "member/loginFail";
		}
		else {
			session.setAttribute("mem", mem);
		}
		
		return "home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "home";
	}
	
	@RequestMapping("/modify_form")
	public String modify(Member member) {
		return "member/modify_form";
	}
	
	@RequestMapping(value="/modifyOk" , method=RequestMethod.POST)
	public String modifyOk(Member member) {
		service.modify(member);
		
		return "member/modifyOk";
	}
		
}
		
