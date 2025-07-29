package com.camp_us.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.camp_us.dto.BoardVO;
import com.camp_us.service.BoardService;

@Controller
@RequestMapping("")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 목록
    @GetMapping("/boardlist")
    public String boardList(@RequestParam(required = false) String category,
                            @RequestParam(required = false) String searchType,
                            @RequestParam(required = false) String keyword,
                            Model model) {

        List<BoardVO> boardList = boardService.selectBoardList(); // 전체 목록 조회

        // Java 11 기준: Collectors.toList() 사용
        List<BoardVO> filteredList = boardList.stream().filter(board -> {
            boolean match = true;

            if (category != null && !category.isEmpty() && !board.getBoardCat().equals(category)) {
                match = false;
            }

            if (keyword != null && !keyword.isEmpty()) {
                if ("title".equals(searchType)) {
                    if (!board.getBoardName().contains(keyword)) {
                        match = false;
                    }
                } else if ("writer".equals(searchType)) {
                    if (!board.getMemId().contains(keyword)) {
                        match = false;
                    }
                } else if (searchType == null || searchType.isEmpty()) {
                    if (!(board.getBoardName().contains(keyword) || board.getMemId().contains(keyword))) {
                        match = false;
                    }
                } else {
                    match = false;
                }
            }

            return match;
        }).collect(Collectors.toList());

        model.addAttribute("boardList", filteredList);
        model.addAttribute("totalPage", 10); // 추후 페이징 적용 가능
        model.addAttribute("category", category);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "board/list";
    }

    // 게시글 작성 화면
    @GetMapping("/board/write")
    public String showWritePage() {
        return "board/write";
    }

    // 게시글 상세 조회
    @GetMapping("/board/detail")
    public String boardDetail(@RequestParam("bno") String bno, Model model) {
        BoardVO board = boardService.selectBoardByNo(bno);
        model.addAttribute("board", board);
        return "board/detail";
    }
}