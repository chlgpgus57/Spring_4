package com.iu.s4;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.iu.s4.dao.BoardNoticeDAO;
import com.iu.s4.dao.BoardQnaDAO;
import com.iu.s4.model.BoardVO;

public class BoardDAOTest extends TestAbstractCase{
	
	@Inject
	BoardNoticeDAO notice;
	
	@Inject
	BoardQnaDAO qna;
	
	@Test
	public void test() throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(376);

		int result = qna.boardDelete(boardVO);
		
		assertEquals(1, result);
	
		
	}

}
