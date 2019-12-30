package com.znzldhkvlwk.spring.notice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.znzldhkvlwk.spring.notice.Notice;
import com.znzldhkvlwk.spring.utils.PagingVo;

@Repository("MybatisDAO")
public class ImplNoticeMybatisDAO implements NoticeDao{

	@Autowired
	private SqlSession sqlSession;
	
	private final String namespace = "com.znzldhkvlwk.spring.notice.mapper.NoticeMapper";
	
	@Override
	public List<Notice> getList(PagingVo vo) {
		
		return sqlSession.selectList(namespace+".getList", vo);
	}

	@Override
	public int getCountList() {
		
		return sqlSession.selectOne(namespace+".getCountList");
	}

	@Override
	public int write(Notice notice) {
		return sqlSession.insert(namespace+".write", notice);
	}

	@Override
	public Notice search(int id) {
		Notice notice = sqlSession.selectOne(namespace+".search", id);
		
		if(notice == null) return null;
		
		return notice;
	}

	@Override
	public int delete(int id) {
		sqlSession.delete(namespace+".delete", id);
		
		int isExist = sqlSession.selectOne(namespace+".idCount", id);
		
		return (isExist == 0) ? 1 : 0;
	}

	@Override
	public int modify(Notice notice) {
		return sqlSession.update(namespace+".modify", notice);
	}

	@Override
	public String test() {
		return sqlSession.selectOne(namespace+".test");
	}

}
