package com.camp_us.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.camp_us.command.PageMaker;
import com.camp_us.dao.BoardDAO;
import com.camp_us.dto.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO;
	private String summernotePath;
	
	public BoardServiceImpl(BoardDAO boardDAO,String summernotePath) {
		this.summernotePath = summernotePath;
		this.boardDAO = boardDAO;
	}
	@Override
	public int getTotalCount(PageMaker pageMaker) {
	    return boardDAO.getTotalCount(pageMaker);
	}


	@Override
	public List<BoardVO> selectBoardList(PageMaker pageMaker) throws SQLException {
		List<BoardVO> boardList = boardDAO.selectBoardList(pageMaker);
		
		return boardList;
	}
    @Override
    public BoardVO selectBoardByNo(String boardNo) {
        return boardDAO.selectBoardByNo(boardNo);
    }

    @Override
    public int insertBoard(BoardVO board) {
        return boardDAO.insertBoard(board);
    }

    @Override
    public int updateBoard(BoardVO board) {
        return boardDAO.updateBoard(board);
    }

    @Override
    public int deleteBoard(String boardId) {
        return boardDAO.deleteBoard(boardId);
    }
    
}