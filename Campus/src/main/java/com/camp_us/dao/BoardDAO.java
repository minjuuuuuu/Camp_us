package com.camp_us.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.camp_us.dto.BoardVO;

@Repository
public class BoardDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.camp_us.mapper.BoardMapper";

    public List<BoardVO> selectBoardList() {
        return sqlSession.selectList(NAMESPACE + ".selectBoardList");
    }

    public BoardVO selectBoardById(int bno) {
        return sqlSession.selectOne(NAMESPACE + ".selectBoardById", bno);
    }

    

}
