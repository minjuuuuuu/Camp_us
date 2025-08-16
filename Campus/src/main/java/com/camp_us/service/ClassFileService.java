// src/main/java/com/camp_us/service/ClassFileService.java
package com.camp_us.service;

import java.util.List;
import com.camp_us.command.PageMakerMJ;
import com.camp_us.dto.ClassFileVO;

public interface ClassFileService {
    int getTotalCount(PageMakerMJ pageMaker) throws Exception;
    List<ClassFileVO> getList(PageMakerMJ pageMaker) throws Exception;
}
