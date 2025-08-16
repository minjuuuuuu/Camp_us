package com.camp_us.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.camp_us.command.PageMakerMJ;
import com.camp_us.dto.LecNoticeVO;
import com.camp_us.service.LecNoticeService;

@Controller
@RequestMapping("/lecnotice")
public class LecNoticeController {

    private final LecNoticeService lecNoticeService;

    @Autowired
    public LecNoticeController(LecNoticeService lecNoticeService) {
        this.lecNoticeService = lecNoticeService;
    }

 // LecNoticeController.java

    @GetMapping("/list")
    public String lecNoticeList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "searchType", required = false, defaultValue = "") String searchType,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            Model model) {
        try {
            PageMakerMJ pageMaker = new PageMakerMJ();
            pageMaker.setPage(page);
            pageMaker.setPerPageNum(10);

       
            pageMaker.setSearchType(searchType);
            pageMaker.setKeyword(keyword);

            int totalCount = lecNoticeService.getTotalCount(pageMaker);
            pageMaker.setTotalCount(totalCount);
            int totalPage = (int) Math.ceil((double) totalCount / pageMaker.getPerPageNum());
            model.addAttribute("totalPage", totalPage);
            List<LecNoticeVO> lecNoticeList = lecNoticeService.getLecNoticeList(pageMaker);

            model.addAttribute("lecNoticeList", lecNoticeList);
            model.addAttribute("pageMaker", pageMaker);
            model.addAttribute("page", page);

            // ▼ 선택값 유지
            model.addAttribute("searchType", searchType);
            model.addAttribute("keyword", keyword);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "공지사항 목록을 불러오는 중 오류가 발생했습니다.");
        }
        return "lecnotice/list";
    }

    // 작성 폼
    @GetMapping("/write")
    public String writeForm() { return "lecnotice/write"; }

    @PostMapping("/write")
    public String lecNoticeWrite(@ModelAttribute LecNoticeVO lecNotice,
                                 @RequestParam(value = "files", required = false) List<MultipartFile> files,
                                 HttpServletRequest request,
                                 Model model) {
        try {
            int nextId = lecNoticeService.getMaxLecNoticeId() + 1;
            lecNotice.setLecNoticeId(String.valueOf(nextId));
            lecNotice.setLecId("1");
            lecNotice.setProfesId("prof001");
            lecNotice.setLecNoticeDate(new Timestamp(System.currentTimeMillis()));

            String uploadPath = request.getServletContext().getRealPath("/resources/upload/lecnotice");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            String fileName = "none.pdf";
            String fileDetail = "none.pdf";

            if (files != null) {
                int idx = 0;
                for (MultipartFile mf : files) {
                    if (mf != null && !mf.isEmpty()) {
                        String saved = UUID.randomUUID() + "_" + mf.getOriginalFilename();
                        mf.transferTo(new File(uploadPath, saved));
                        if (idx == 0) fileName = saved;
                        else if (idx == 1) fileDetail = saved;
                        idx++;
                        if (idx >= 2) break; // DB가 2칸뿐
                    }
                }
            }

            lecNotice.setFileName(fileName);
            lecNotice.setFileDetail(fileDetail);

            lecNoticeService.registLecNotice(lecNotice);
            model.addAttribute("success", true);
            return "lecnotice/write";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "등록 실패");
            return "lecnotice/write";
        }
    }


    // 상세(읽기) & 수정 모드 전환
    @GetMapping("/detail")
    public String lecNoticeDetail(@RequestParam("lecNoticeId") String lecNoticeId,
                                  @RequestParam(value = "mode", required = false) String mode,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  Model model) {
        try {
            // 조회수(쿠키로 중복 방지)
            boolean hasViewed = false;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("viewedLecNotice" + lecNoticeId)) {
                        hasViewed = true; break;
                    }
                }
            }
            if (!hasViewed) {
                lecNoticeService.updateViewCount(lecNoticeId);
                Cookie c = new Cookie("viewedLecNotice" + lecNoticeId, "true");
                c.setMaxAge(60 * 60);
                c.setPath("/");
                response.addCookie(c);
            }

            LecNoticeVO lecNotice = lecNoticeService.getLecNoticeById(lecNoticeId);
            model.addAttribute("lecNotice", lecNotice);

      
            boolean editMode = "edit".equalsIgnoreCase(mode);
            model.addAttribute("editMode", editMode);

            return "lecnotice/detail";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "공지사항을 불러오는 중 오류가 발생했습니다.");
            return "error";
        }
    }

    @PostMapping("/update")
    public String lecNoticeUpdate(@ModelAttribute LecNoticeVO form,
                                  @RequestParam(value = "files", required = false) List<MultipartFile> files,
                                  @RequestParam(value = "removeFile1", required = false) String remove1,
                                  @RequestParam(value = "removeFile2", required = false) String remove2,
                                  HttpServletRequest request,
                                  Model model) {
        try {
            LecNoticeVO origin = lecNoticeService.getLecNoticeById(form.getLecNoticeId());

            String uploadPath = request.getServletContext().getRealPath("/resources/upload/lecnotice");
            File dir = new File(uploadPath);
            if (!dir.exists()) dir.mkdirs();

       
            String fileName = origin.getFileName();
            String fileDetail = origin.getFileDetail();
            if ("on".equals(remove1)) fileName = "none.pdf";
            if ("on".equals(remove2)) fileDetail = "none.pdf";

         
            if (files != null) {
                int idx = 0;
                for (MultipartFile mf : files) {
                    if (mf != null && !mf.isEmpty()) {
                        String saved = UUID.randomUUID() + "_" + mf.getOriginalFilename();
                        mf.transferTo(new File(uploadPath, saved));
                        if (idx == 0) fileName = saved;
                        else if (idx == 1) fileDetail = saved;
                        idx++;
                        if (idx >= 2) break;
                    }
                }
            }

            origin.setLecNoticeName(form.getLecNoticeName());
            origin.setLecNoticeDesc(form.getLecNoticeDesc());
            origin.setFileName(fileName);
            origin.setFileDetail(fileDetail);

            lecNoticeService.modifyLecNotice(origin);
            return "redirect:/lecnotice/detail?lecNoticeId=" + origin.getLecNoticeId();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "수정 중 오류가 발생했습니다.");
            return "error";
        }
    }


    // 삭제
    @GetMapping("/delete")
    public String lecNoticeDelete(@RequestParam("lecNoticeId") String lecNoticeId) {
        try {
            lecNoticeService.removeLecNotice(lecNoticeId);
            return "redirect:/lecnotice/list";
        } catch (Exception e) {
            return "error";
        }
    }

    // 파일 다운로드
    @GetMapping("/file")
    public void download(@RequestParam("lecNoticeId") String id,
                         @RequestParam("which") int which,
                         HttpServletRequest req,
                         HttpServletResponse resp) throws Exception {
        LecNoticeVO n = lecNoticeService.getLecNoticeById(id);
        String fname = (which == 2) ? n.getFileDetail() : n.getFileName();
        if (fname == null || "none.pdf".equals(fname)) { resp.setStatus(404); return; }

        File f = new File(req.getServletContext()
                             .getRealPath("/resources/upload/lecnotice/" + fname));
        if (!f.exists()) { resp.setStatus(404); return; }

        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fname + "\"");
        resp.setContentLengthLong(f.length());
        java.nio.file.Files.copy(f.toPath(), resp.getOutputStream());
    }
}
