package com.znzldhkvlwk.spring.member.dao;

import com.znzldhkvlwk.spring.member.Member;

public interface MemberDao {
	public int join(Member member);
	public Member login(Member member);
	public int modify(Member member);
}
