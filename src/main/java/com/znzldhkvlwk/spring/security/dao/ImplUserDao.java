package com.znzldhkvlwk.spring.security.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.znzldhkvlwk.spring.member.Member;

public class ImplUserDao implements UserDao{
	
	
	private SqlSession sqlSession;
	public ImplUserDao() {
		
	}
	
	public ImplUserDao(SqlSession sqlSession) {
		
		this.sqlSession = sqlSession;
	}
	private final String namespace = "com.znzldhkvlwk.spring.member.MemberMapper";
	
	@Override
	public Member search(String id) {
		Member member = sqlSession.selectOne(namespace+".search", id);
		if(member == null) {
			System.out.println("member null");
			return null;
		}
		return member;
	}
	@Override
	public String getRole(String id) {
		
		return sqlSession.selectOne(namespace+".getRole", id);
	}

}
