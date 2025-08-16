// src/main/java/com/camp_us/dao/ClassFileDAO.java
package com.camp_us.dao;

import java.util.List;
import com.camp_us.command.PageMaker;
import com.camp_us.dto.ClassFileVO;

public interface ClassFileDAO {
    int selectTotalCount(PageMaker pageMaker) throws Exception;
    List<ClassFileVO> selectList(PageMaker pageMaker) throws Exception;
}
