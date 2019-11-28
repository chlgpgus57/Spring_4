package com.iu.s4.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s4.model.BoardVO;
import com.iu.s4.model.QnaFilesVO;
import com.iu.s4.service.BoardQnaService;
import com.iu.s4.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {

	@Inject
	private BoardQnaService boardQnaService;
	
	@Value("${qna}")
	private String board;
	
	@ModelAttribute("board")
	private String getBoard() {
		return board;
	}
	
	
	@GetMapping("fileDown")
	public ModelAndView fileDown(QnaFilesVO qnaFilesVO) throws Exception{
		 
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("file", qnaFilesVO);
		mv.setViewName("fileDown");
		return mv;
		
	}
	
	@RequestMapping("qnaReply")
	public ModelAndView boardReply(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("dto", boardVO);
		mv.setViewName("board/boardReply");
		return mv;
	}
	
	@RequestMapping(value =  "qnaReply", method = RequestMethod.POST)
	public ModelAndView boardReply(BoardVO boardVO, ModelAndView mv) throws Exception{
		
		int result = boardQnaService.boardReply(boardVO);
		
		if(result > 0) {
			mv.setViewName("redirect:qnaList");
		}else {
			mv.addObject("msg", "글쓰기 실패");
			mv.addObject("path", "qnaList");
			mv.setViewName("common/common_result");
		}
		
		return mv;
	}
	
	@RequestMapping("qnaList")
	public ModelAndView boardList(Pager pager) throws Exception{
		List<BoardVO> ar = boardQnaService.boardList(pager);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");
		
		return mv;
	}
	@RequestMapping("qnaWrite")
	public ModelAndView boardWrite() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@RequestMapping(value = "qnaWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(BoardVO boardVO, HttpSession session) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		int result = boardQnaService.boardWrite(boardVO,session);
		
		
		if(result > 0) {
			mv.setViewName("redirect:qnaList");
		}else {
			mv.addObject("msg", "글쓰기 실패");
			mv.addObject("path", "qnaList");
			mv.setViewName("common/common_result");
		}
		

		
		return mv;
	}
	
	@RequestMapping("qnaSelect")
	public ModelAndView boardSelect(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("dto", boardQnaService.boardSelect(boardVO));
		mv.setViewName("board/boardSelect");
		
		return mv;
	}
	
	@RequestMapping("qnaUpdate")
	public ModelAndView boardUpdate(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("dto", boardQnaService.boardSelect(boardVO));
		
		mv.setViewName("board/boardUpdate");
		return mv;
	}
	
	@RequestMapping(value = "qnaUpdate", method = RequestMethod.POST)
	public ModelAndView boardUpdate(BoardVO boardVO, ModelAndView mv, HttpSession session) throws Exception{
		int result = boardQnaService.boardUpdate(boardVO, session);
		if(result > 0) {
			mv.setViewName("redirect:qnaList");
		}else {
			mv.addObject("msg", "수정 실패");
			mv.addObject("path", "qnaList");
			mv.setViewName("common/common_result");
		}
		return mv;
	}
	
	@RequestMapping("qnaDelete")
	public ModelAndView boardDelete(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = boardQnaService.boardDelete(boardVO);
		
		if(result>0) {
			mv.addObject("msg", "삭제 성공");
		
		}else {
			mv.addObject("msg", "삭제 실패");
		}
		
		mv.addObject("path", "qnaList");
		mv.setViewName("common/common_result");
		return mv;
	}
}
