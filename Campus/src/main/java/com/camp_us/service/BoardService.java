package com.camp_us.service;

import java.util.List;
import com.camp_us.dto.BoardVO;

//Service Interface
public interface BoardService {
 List<BoardVO> selectBoardList();
 BoardVO selectBoardByNo(String boardNo);
 int insertBoard(BoardVO board);
 int updateBoard(BoardVO board);
 int deleteBoard(String boardNo);
}

