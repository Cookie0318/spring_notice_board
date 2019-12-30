package com.znzldhkvlwk.spring.notice.mapper;

import java.util.List;

import com.znzldhkvlwk.spring.notice.Notice;
import com.znzldhkvlwk.spring.utils.PagingVo;

public interface NoticeMapper {
	public String test();
	
	public List<Notice> getList(PagingVo vo);
	
}
