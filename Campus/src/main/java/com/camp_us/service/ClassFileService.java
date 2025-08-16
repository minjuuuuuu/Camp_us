// src/main/java/com/camp_us/service/ClassFileService.java
package com.camp_us.service;

import java.util.List;
import com.camp_us.command.PageMaker;
import com.camp_us.dto.ClassFileVO;

public interface ClassFileService {
    int getTotalCount(PageMaker pageMaker) throws Exception;
    List<ClassFileVO> getList(PageMaker pageMaker) throws Exception;
}
