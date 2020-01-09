package com.znzldhkvlwk.spring.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.znzldhkvlwk.spring.member.Member;

@Repository("MemberDao")
public class ImplMemberDao implements MemberDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String namespace = "com.znzldhkvlwk.spring.member.MemberMapper";
	@Override
	public int join(Member member) {
		return sqlSession.insert(namespace+".join", member);
	}
	@Override
	public Member login(Member member) {
		
		return sqlSession.selectOne(namespace+".login", member);
	}
	@Override
	public int modify(Member member) {
		
		return sqlSession.update(namespace+".modify", member);
	}
	@Override
	public Member search(String id) {
		return sqlSession.selectOne(namespace+".search", id);
	}
	@Override
	public String getRole(String id) {
		
		return sqlSession.selectOne(namespace+".getRole", id);
	}
	
}
