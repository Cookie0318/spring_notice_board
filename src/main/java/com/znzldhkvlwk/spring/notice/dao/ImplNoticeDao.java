package com.znzldhkvlwk.spring.notice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.znzldhkvlwk.spring.notice.Notice;
import com.znzldhkvlwk.spring.utils.PagingVo;

@Repository
public class ImplNoticeDao implements NoticeDao{
	
	private JdbcTemplate template;
	
	@Autowired
	public ImplNoticeDao(BasicDataSource dataSource2) {
		this.template = new JdbcTemplate(dataSource2);
	}
	

//	@Override
//	public List<Notice> getList() {
//		
//		final String sql = "SELECT * FROM NOTICE";
//		return template.query(sql, new RowMapper<Notice>() {
//
//			@Override
//			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Notice notice = new Notice();
//				notice.setId(rs.getInt("ID"));
//				notice.setTitle(rs.getString("TITLE"));
//				notice.setWriter_Id(rs.getString("WRITER_ID"));
//				notice.setContent(rs.getString("CONTENT"));
//				notice.setRegdate(rs.getDate("REGDATE"));
//				notice.setHit(rs.getInt("HIT"));
//				notice.setFiles(rs.getString("FILES"));
//				return notice;
//			}
//			
//		});
//	}
	
	@Override
	public List<Notice> getList(PagingVo vo) {
		
		final String sql = "SELECT * FROM (SELECT ROWNUM RN , A.* FROM (SELECT * FROM NOTICE ORDER BY ID DESC) A ) WHERE RN BETWEEN ? AND ?";
		return template.query(sql, new Object[] {vo.getStart(), vo.getEnd()} ,new RowMapper<Notice>() {

			@Override
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice notice = new Notice();
				notice.setId(rs.getInt("ID"));
				notice.setTitle(rs.getString("TITLE"));
				notice.setWriter_Id(rs.getString("WRITER_ID"));
				notice.setContent(rs.getString("CONTENT"));
				notice.setRegdate(rs.getDate("REGDATE"));
				notice.setHit(rs.getInt("HIT"));
				notice.setFiles(rs.getString("FILES"));
				return notice;
			}
			
		});
	}

	@Override
	public int getCountList() {
		final String sql = "SELECT COUNT(*) FROM NOTICE";
		
		return template.queryForObject(sql, Integer.class);
	}

	@Override
	public int write(Notice notice) {
		final String sql = "INSERT INTO NOTICE(ID, WRITER_ID, TITLE, CONTENT ,REGDATE) VALUES(NUM_NOTICE.NEXTVAL, 'znzldhkvlwk', ?, ?, SYSTIMESTAMP)";
		return template.update(sql, notice.getTitle(), notice.getContent());
		
	}


	@Override
	public Notice search(int id) {
		List<Notice> notices;
		final String sql = "SELECT * FROM NOTICE WHERE ID = ?";
		notices = template.query(sql, new Object[]{id}, new RowMapper<Notice>() {

			@Override
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice n = new Notice();
				n.setId(Integer.parseInt(rs.getString("ID")));
				n.setTitle(rs.getString("TITLE"));
				n.setWriter_Id(rs.getString("WRITER_ID"));
				n.setContent(rs.getString("CONTENT"));
				n.setRegdate(rs.getDate("REGDATE"));
				n.setHit(rs.getInt("HIT"));
				n.setFiles(rs.getString("FILES"));
				
				return n;
			}
		});
		
		if(notices.isEmpty()) return null;
		
		return notices.get(0);
	}


	@Override
	public int delete(int id) {
		final String sql = "DELETE FROM NOTICE WHERE ID = " + id;
		template.execute(sql);
		
		final String sql_count = "SELECT COUNT(*) FROM NOTICE WHERE ID = " + id;
		
		return (template.queryForObject(sql_count, Integer.class) == 0) ? 1 : 0;
	}


	@Override
	public int modify(Notice notice) {
		final String sql = "UPDATE NOTICE SET TITLE = ?, CONTENT = ? WHERE ID = ?";
		return template.update(sql,notice.getTitle(), notice.getContent(), notice.getId());
	}


	@Override
	public String test() {
		// TODO Auto-generated method stub
		return null;
	}



}
