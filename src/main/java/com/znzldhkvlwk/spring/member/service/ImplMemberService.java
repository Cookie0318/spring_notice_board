package com.znzldhkvlwk.spring.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.znzldhkvlwk.spring.member.Member;
import com.znzldhkvlwk.spring.member.dao.ImplMemberDao;

@Service("MemberService")
public class ImplMemberService implements MemberService{

	@Autowired
	@Qualifier("MemberDao")
	private ImplMemberDao memberDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public int join(Member member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
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

	@Override
	public Member search(String id) {
		return memberDao.search(id);
	}

	@Override
	public String getRole(String id) {
		return memberDao.getRole(id);
	}
	
	

}
