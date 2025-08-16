// src/main/java/com/camp_us/service/ClassFileServiceImpl.java
package com.camp_us.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.camp_us.command.PageMaker;
import com.camp_us.dao.ClassFileDAO;
import com.camp_us.dto.ClassFileVO;

@Service
public class ClassFileServiceImpl implements ClassFileService {

    private final ClassFileDAO classFileDAO;

    @Autowired
    public ClassFileServiceImpl(ClassFileDAO classFileDAO) {
        this.classFileDAO = classFileDAO;
    }

    @Override
    public int getTotalCount(PageMaker pageMaker) throws Exception {
        return classFileDAO.selectTotalCount(pageMaker);
    }

    @Override
    public List<ClassFileVO> getList(PageMaker pageMaker) throws Exception {
        return classFileDAO.selectList(pageMaker);
    }
}
