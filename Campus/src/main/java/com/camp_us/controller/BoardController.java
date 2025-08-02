package com.camp_us.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.camp_us.command.PageMaker;
import com.camp_us.dto.BoardVO;
import com.camp_us.service.BoardService;

@Controller
@RequestMapping("")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시글 목록 조회
    @GetMapping("/boardlist")
    public String list(@ModelAttribute PageMaker pageMaker, Model model) throws Exception {
        // 1. 전체 게시글 수 조회
        int totalCount = boardService.getTotalCount(pageMaker);
        pageMaker.setTotalCount(totalCount);

        // 2. 게시글 목록 조회 (페이징 적용)
        List<BoardVO> boardList = boardService.selectBoardList(pageMaker);

        // 3. 전체 페이지 수 계산
        int totalPage = (int) Math.ceil((double) totalCount / pageMaker.getPerPageNum());

        // 4. JSP에 전달할 데이터 세팅
        model.addAttribute("boardList", boardList);
        model.addAttribute("pageMaker", pageMaker);
        model.addAttribute("page", pageMaker.getPage());
        model.addAttribute("totalPage", totalPage);

        return "board/list";
    }

    // 게시글 작성 화면
    @GetMapping("/board/write")
    public String showWritePage() {
        return "board/write";
    }
    @PostMapping("/board/write")
    public String writeBoard(@ModelAttribute BoardVO board,
                             @RequestParam(value = "file1", required = false) MultipartFile file1,
                             @RequestParam(value = "file2", required = false) MultipartFile file2,
                             Model model) {

        board.setBoardDesc(board.getBoardContent());
        board.setMemId("stu001");

        if (file1 != null && !file1.isEmpty()) {
            board.setPfileName(file1.getOriginalFilename());
            board.setPfileDetail("/upload/" + file1.getOriginalFilename());
        }

        boardService.insertBoard(board);

        // 등록 성공 여부를 모델로 전달
        model.addAttribute("success", true);

        // 여전히 write.jsp로 이동
        return "board/write";
    }



    // 게시글 상세 조회
    @GetMapping("/board/detail")
    public String boardDetail(@RequestParam("bno") String bno, Model model) {
        BoardVO board = boardService.selectBoardByNo(bno);
        model.addAttribute("board", board);
        return "board/detail";
    }
 // 게시글 수정 처리 (AJAX 요청)
    @PostMapping("/board/update")
    public String updateBoard(@RequestBody BoardVO board) {
        boardService.updateBoard(board);  // DB에 반영
        return "redirect:/list"; // 수정 후 목록으로 이동
    }
    @PostMapping("/board/delete")
    public String deleteBoard(@RequestParam("boardId") String boardId) {
        boardService.deleteBoard(boardId);
        return "redirect:/list"; // 삭제 후 목록으로 이동
    }


}
