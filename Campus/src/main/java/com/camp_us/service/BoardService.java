package com.camp_us.service;

import java.sql.SQLException;
import java.util.List;

import com.camp_us.command.PageMaker;
import com.camp_us.dto.BoardVO;

//Service Interface
public interface BoardService {
 List<BoardVO> selectBoardList(PageMaker pageMaker)throws SQLException;
 BoardVO selectBoardByNo(String boardNo);
 int insertBoard(BoardVO board);
 int updateBoard(BoardVO board);
 int deleteBoard(String boardNo);
 int getTotalCount(PageMaker pageMaker);
}

