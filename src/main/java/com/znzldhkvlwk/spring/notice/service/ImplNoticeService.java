package com.znzldhkvlwk.spring.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.znzldhkvlwk.spring.notice.Notice;
import com.znzldhkvlwk.spring.notice.dao.NoticeDao;
import com.znzldhkvlwk.spring.utils.PagingVo;


@Service
public class ImplNoticeService implements NoticeService{
	
	
	@Qualifier("MybatisDAO")
	@Autowired
	private NoticeDao noticeDao;
	
	
	@Override
	public List<Notice> list(PagingVo vo) {
		
		return noticeDao.getList(vo);
	}
	@Override
	public List<Notice> mylist(Map<String, Object> map) {
		
		return noticeDao.getMyList(map);
	}
	@Override
	public int getListCount() {
		
		return noticeDao.getCountList();
	}
	
	@Override
	public int getMyListCount(String id) {
		
		return noticeDao.getCountMyList(id);
	}

	@Override
	public int write(Notice notice) {
		
		return noticeDao.write(notice);
	}

	@Override
	public Notice search(int id) {
		
		return noticeDao.search(id);
		
	}
	@Override
	public int delete(int id) {
		
		return noticeDao.delete(id);
	}
	@Override
	public int modify(Notice notice) {
		
		return noticeDao.modify(notice);
	}
	@Override
	public String test() {
		return noticeDao.test();
	}
	@Override
	public int hitPlus(int id) {
		return noticeDao.hitPlus(id);
	}
	
	

	
	

}
