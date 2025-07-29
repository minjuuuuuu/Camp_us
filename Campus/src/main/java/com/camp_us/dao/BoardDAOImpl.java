package com.camp_us.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.camp_us.dto.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

    private static final String NAMESPACE = "com.camp_us.mapper.BoardMapper";

    private final SqlSession sqlSession;

    public BoardDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<BoardVO> selectBoardList() {
        return sqlSession.selectList(NAMESPACE + ".selectBoardList");
    }

    @Override
    public BoardVO selectBoardByNo(String boardNo) {
        return sqlSession.selectOne(NAMESPACE + ".selectBoardByNo", boardNo);
    }

    @Override
    public int insertBoard(BoardVO board) {
        return sqlSession.insert(NAMESPACE + ".insertBoard", board);
    }

    @Override
    public int updateBoard(BoardVO board) {
        return sqlSession.update(NAMESPACE + ".updateBoard", board);
    }

    @Override
    public int deleteBoard(String boardNo) {
        return sqlSession.delete(NAMESPACE + ".deleteBoard", boardNo);
    }
}
