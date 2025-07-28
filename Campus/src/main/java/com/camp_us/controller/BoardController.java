package com.camp_us.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.camp_us.dto.BoardVO;

@Controller
@RequestMapping("")
public class BoardController {

    @GetMapping("/boardlist")
    public String boardList(@RequestParam(required = false) String category,
                            @RequestParam(required = false) String searchType,
                            @RequestParam(required = false) String keyword,
                            Model model) {

        List<BoardVO> boardList = new ArrayList<>();
        boardList.add(new BoardVO(1, "공지", "2차 현장학습 준비물 안내", "정다운", "2025-07-15"));
        boardList.add(new BoardVO(2, "시험", "과제 주제 관련 질문있습니다.", "김원희", "2025-07-08"));
        boardList.add(new BoardVO(3, "토론", "지역 활성화 방안에 대한 의견 공유", "권오규", "2025-06-24"));
        boardList.add(new BoardVO(4, "자유", "중간평가 시험변경 안내", "김민주", "2025-06-15"));
        boardList.add(new BoardVO(5, "자유", "팀방 소감 공유해요.", "윤용선", "2025-05-01"));
        boardList.add(new BoardVO(6, "공지", "현장체험 출발 시간 변경 공지", "박홍원", "2025-04-16"));
        boardList.add(new BoardVO(7, "토론", "수업 방식에 대한 의견 모음", "채윤성", "2025-04-07"));
        boardList.add(new BoardVO(8, "시험", "중간평가 시험변경 안내", "정다운", "2025-04-01"));
        boardList.add(new BoardVO(9, "자유", "자유 업로드 위치 문의드립니다.", "김원희", "2025-04-01"));
        boardList.add(new BoardVO(10, "토론", "지역 활성화 방안에 대한 의견 공유", "권오규", "2025-03-05"));

        List<BoardVO> filteredList = new ArrayList<>();

        for (BoardVO board : boardList) {
            boolean match = true;

            if (category != null && !category.isEmpty() && !board.getCategory().equals(category)) {
                match = false;
            }

            if (keyword != null && !keyword.isEmpty()) {
                if ("title".equals(searchType)) {
                    if (!board.getTitle().contains(keyword)) {
                        match = false;
                    }
                } else if ("writer".equals(searchType)) {
                    if (!board.getWriter().contains(keyword)) {
                        match = false;
                    }
                } else if (searchType == null || searchType.isEmpty()) {
                    if (!(board.getTitle().contains(keyword) || board.getWriter().contains(keyword))) {
                        match = false;
                    }
                } else {
                    match = false;
                }
            }

            if (match) filteredList.add(board);
        }

        model.addAttribute("boardList", filteredList);
        model.addAttribute("totalPage", 10);
        model.addAttribute("category", category);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        return "board/list";
    }

    @GetMapping("/board/write")
    public String showWritePage() {
        return "board/write"; 
    }
    @GetMapping("/board/detail")
    public String boardDetail(@RequestParam("bno") int bno, Model model) {
        // 더미 데이터
        BoardVO board = new BoardVO(bno, "공지", "2차 현장학습 준비물 안내", "정다운", "2025-07-15");
        model.addAttribute("board", board);
        return "board/detail"; 
    }
}