package com.iu.s4.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s4.model.BoardVO;
import com.iu.s4.service.BoardNoticeService;
import com.iu.s4.util.Pager;


@RestController
public class JsonController {
	
	@Inject
	private BoardNoticeService boardNoticeService;
	
	
	
	
	@GetMapping("/getJson3")
	public List<BoardVO> getJson3(Pager pager) throws Exception{
		
		return boardNoticeService.boardList(pager);

	}

	
	@GetMapping("/getJson2")
	public BoardVO getJson2(BoardVO boardVO) throws Exception{
		
		boardVO = boardNoticeService.boardSelect(boardVO);
		/*
		 * String jmsg =
		 * "{\"title\":\""+boardVO.getTitle()+"\",\"writer\":\""+boardVO.getWriter()+
		 * "\",\"contents\":\""+boardVO.getContents()+"\"}";
		 */
		return boardVO;
	}
	
	
	@GetMapping("/getJson1")
	public ModelAndView getJson1() throws Exception{
		
		//name= iu , age=27
		String jmessage = "{\"name\":\"iu\", \"age\":27}";
		//System.out.println(jmessage);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("result",jmessage);
		mv.setViewName("common/common_ajaxResult");
		
		return mv;
	}
}
