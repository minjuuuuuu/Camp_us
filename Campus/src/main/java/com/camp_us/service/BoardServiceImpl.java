package com.camp_us.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp_us.dao.BoardDAO;
import com.camp_us.dto.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;

    @Override
    public List<BoardVO> getBoardList() {
        return boardDAO.selectBoardList();
    }
}
