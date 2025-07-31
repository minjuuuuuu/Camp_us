package com.camp_us.controller;

import java.util.List;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.camp_us.command.PageMaker;
import com.camp_us.dto.BoardVO;
import com.camp_us.service.BoardService;

@Controller
@RequestMapping("")
public class BoardController {

private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	
	@GetMapping("/list")
	public String list(@ModelAttribute PageMaker pageMaker, Model model)throws Exception{
		List<BoardVO> boardList = boardService.selectBoardList(pageMaker);
		System.out.println(boardList);
		model.addAttribute("boardList",boardList);	
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