package com.znzldhkvlwk.spring.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znzldhkvlwk.spring.notice.Notice;
import com.znzldhkvlwk.spring.notice.dao.ImplNoticeDao;
import com.znzldhkvlwk.spring.notice.mapper.NoticeMapper;
import com.znzldhkvlwk.spring.utils.PagingVo;


@Service
public class ImplNoticeService implements NoticeService{
	
	@Autowired
	private ImplNoticeDao noticeDao;
	
	@Autowired
	private NoticeMapper mapper;
	
//	@Override
//	public List<Notice> list() {
//		
//		return noticeDao.getList();
//	}
	
	
	@Override
	public List<Notice> list(PagingVo vo) {
		
		return noticeDao.getList(vo);
	}
	@Override
	public int getListCount() {
		
		return noticeDao.getCountList();
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
		
		return mapper.test();
	}

	
	

}
