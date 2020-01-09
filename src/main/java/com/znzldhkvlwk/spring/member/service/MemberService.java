package com.znzldhkvlwk.spring.member.service;

import com.znzldhkvlwk.spring.member.Member;

public interface MemberService {
	public int join(Member member);
	public Member login(Member member);
	public int modify(Member member);
	public Member search(String id);
	public String getRole(String id);
}
