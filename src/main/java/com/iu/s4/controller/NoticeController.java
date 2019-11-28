package com.iu.s4.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s4.model.BoardVO;
import com.iu.s4.model.FilesVO;
import com.iu.s4.service.BoardNoticeService;
import com.iu.s4.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {

	@Inject
	private BoardNoticeService boardNoticeService;
	
	
	@PostMapping("summerDelete")
	public ModelAndView summerDelete(String file, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		boolean check = boardNoticeService.summerDelete(file, session);
		String result = "fail";
		
		if(check) {
			result = "success";
		}
		mv.addObject("result", result);
		mv.setViewName("common/common_ajaxResult");
		return mv;
	}	
	@PostMapping(value = "summerFile")
	public ModelAndView summerFile(MultipartFile file,HttpSession session) throws Exception{
		
		String filename = boardNoticeService.summerFile(file, session);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("result", filename);
		mv.setViewName("common/common_ajaxResult");
		
		return mv;
	}
	@GetMapping("fileDown")
	public ModelAndView fileDown(FilesVO noticeFilesVO) throws Exception{
		noticeFilesVO=boardNoticeService.fileSelect(noticeFilesVO);
		System.out.println("d");
		ModelAndView mv = new ModelAndView();
		mv.addObject("file", noticeFilesVO);
		mv.setViewName("fileDown");
		mv.addObject("board", "notice");
		return mv;
		
	}
	
	@PostMapping("fileDelete")
	public ModelAndView fileDelete(FilesVO noticeFilesVO) throws Exception{
		System.out.println(noticeFilesVO.getFnum());
		int result = boardNoticeService.fileDelete(noticeFilesVO);
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("result", result);
		mv.setViewName("common/common_ajaxResult");
		return mv;
		
	}
	
	@RequestMapping("noticeList")
	public ModelAndView boardList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		List<BoardVO> ar =boardNoticeService.boardList(pager);
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.addObject("board", "notice");
		mv.setViewName("board/boardList");
		
		return mv;
	}
	@RequestMapping("noticeWrite")
	public ModelAndView boardWrite() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("board", "notice");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@RequestMapping(value = "noticeWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(BoardVO boardVO, HttpSession session) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		int result = boardNoticeService.boardWrite(boardVO, session);
		
		if(result > 0) {
			mv.setViewName("redirect:noticeList");
		}else {
			mv.addObject("msg", "글쓰기 실패");
			mv.addObject("path", "noticeList");
			mv.setViewName("common/common_result");
		}
		

		mv.addObject("board", "notice");
		
		return mv;
		
	}
	
	@RequestMapping("noticeSelect")
	public ModelAndView boardSelect(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boardVO = boardNoticeService.boardSelect(boardVO);
		boardVO.setContents(boardVO.getContents().replace("\r\n", "<br>"));
		mv.addObject("dto", boardVO);
		mv.addObject("board", "notice");
		mv.setViewName("board/boardSelect");
		
		return mv;
	}
	
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.GET)
	public ModelAndView boardUpdate(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		
		mv.addObject("dto", boardNoticeService.boardSelect(boardVO));
		mv.addObject("board", "notice");
		mv.setViewName("board/boardUpdate");
		return mv;
	}
	
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.POST)
	public ModelAndView boardUpdate(BoardVO boardVO, ModelAndView mv, HttpSession session) throws Exception{
		
		
		int result = boardNoticeService.boardUpdate(boardVO, session);
		
		if(result > 0) {
			mv.setViewName("redirect:noticeList");
		}else {
			mv.addObject("msg", "수정 실패");
			mv.addObject("path", "noticeList");
			mv.setViewName("common/common_result");
		}
		

		mv.addObject("board", "notice");
		
		return mv;
	}
	
	@RequestMapping("noticeDelete")
	public ModelAndView boardDelete(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = boardNoticeService.boardDelete(boardVO);
		if(result>0) {
			mv.addObject("msg", "삭제 성공");
		
		}else {
			mv.addObject("msg", "삭제 실패");
		}
		
		mv.addObject("path", "noticeList");
		mv.addObject("board", "notice");
		mv.setViewName("common/common_result");
		return mv;
	}
	
	/*
	 * //exception처리메서드
	 * 
	 * @ExceptionHandler(NullPointerException.class) public ModelAndView getNull() {
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * mv.setViewName("common/common_500_error"); return mv; }
	 * 
	 * @ExceptionHandler(NumberFormatException.class) public ModelAndView
	 * getNumberFormat() {
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * mv.setViewName("common/common_500_error"); return mv; }
	 * 
	 * @ExceptionHandler(Exception.class) public ModelAndView getException() {
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * mv.setViewName("common/common_500_error"); return mv; }
	 */
}
