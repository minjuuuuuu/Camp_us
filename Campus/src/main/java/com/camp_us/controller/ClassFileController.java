package com.camp_us.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.camp_us.command.PageMakerMJ;
import com.camp_us.dto.ClassFileVO;
import com.camp_us.service.ClassFileService;

@Controller
@RequestMapping("/classfile")
public class ClassFileController {

    private final ClassFileService classFileService;

    @Autowired
    public ClassFileController(ClassFileService classFileService) {
        this.classFileService = classFileService;
    }

    /** /classfile -> /classfile/list 로 포워드 */
    @GetMapping("")
    public String index() {
        return "forward:/classfile/list";
    }

    /** 자료실 목록 (검색/페이징: PageMaker만 사용) */
    @GetMapping("/list")
    public String list(@ModelAttribute PageMakerMJ pageMaker, Model model) throws Exception {
        int totalCount = classFileService.getTotalCount(pageMaker); // DB 기반 총건수
        pageMaker.setTotalCount(totalCount);

        List<ClassFileVO> list = classFileService.getList(pageMaker); // 페이지 목록
        model.addAttribute("list", list);
        model.addAttribute("pageMaker", pageMaker);

        // searchType, keyword 파라미터는 JSP가 그대로 사용
        return "classfile/list";
    }
}
