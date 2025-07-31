package com.camp_us.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.camp_us.command.PageMaker;
import com.camp_us.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

private SqlSession session;
	
	public BoardDAOImpl(SqlSession session) {
		this.session = session;
	}

	@Override
	public List<BoardVO> selectBoardList(PageMaker pageMaker) throws SQLException {
		int offset = pageMaker.getStartRow()-1;
		int limit = pageMaker.getPerPageNum();
		RowBounds bounds = new RowBounds(offset,limit);
		
		List<BoardVO> boardList = session.selectList("Board-Mapper.selectBoardList",pageMaker,bounds);
		
		return boardList;
	}


	@Override
	public BoardVO selectBoardByNo(String boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(BoardVO board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(String boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}


    
}
