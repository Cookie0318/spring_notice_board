package com.znzldhkvlwk.spring.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.znzldhkvlwk.spring.member.Member;
import com.znzldhkvlwk.spring.member.dao.MemberDao;

@Service("MemberService")
public class ImplMemberService implements MemberService{

	@Autowired
	@Qualifier("MemberDao")
	private MemberDao memberDao;
	
	@Override
	public int join(Member member) {
		return memberDao.join(member);
	}

	@Override
	public Member login(Member member) {
		return memberDao.login(member);
	}

	@Override
	public int modify(Member member) {
		
		return memberDao.modify(member);
	}

}
