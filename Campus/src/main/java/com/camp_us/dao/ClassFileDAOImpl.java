// src/main/java/com/camp_us/dao/ClassFileDAOImpl.java
package com.camp_us.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.camp_us.command.PageMakerMJ;
import com.camp_us.dto.ClassFileVO;

@Repository
public class ClassFileDAOImpl implements ClassFileDAO {

    private static final String NAMESPACE = "ClassFile-Mapper";

    @Autowired
    private SqlSession session;

    @Override
    public int selectTotalCount(PageMakerMJ pageMaker) throws Exception {
        return session.selectOne(NAMESPACE + ".getTotalCount", pageMaker);
    }

    @Override
    public List<ClassFileVO> selectList(PageMakerMJ pageMaker) throws Exception {
        return session.selectList(NAMESPACE + ".getList", pageMaker);
    }
}
