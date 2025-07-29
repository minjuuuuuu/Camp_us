package com.camp_us.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.camp_us.dao.BoardDAO;
import com.camp_us.dto.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	private final BoardDAO boardDAO;

    @Autowired
    public BoardServiceImpl(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    @Override
    public List<BoardVO> selectBoardList() {
        return boardDAO.selectBoardList();
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
    public int deleteBoard(String boardNo) {
        return boardDAO.deleteBoard(boardNo);
    }
}