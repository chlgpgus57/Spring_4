package com.iu.s4;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.iu.s4.model.BoardVO;
import com.iu.s4.service.BoardQnaService;

public class BoardServiceTest extends TestAbstractCase{
	
	
	@Inject
	private BoardQnaService boardQnaService;
	@Test
	public void test() throws Exception{
		
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(392);
		boardVO.setTitle("test");
		boardVO.setWriter("test");
		boardVO.setContents("test");
		int result = boardQnaService.boardReply(boardVO);
		
		assertEquals(1, result);
	}

}
