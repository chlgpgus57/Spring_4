package com.iu.s4.dao;

import java.util.List;

import com.iu.s4.model.BoardVO;
import com.iu.s4.util.Pager;

public interface BoardDAO {
	
	//list
	public List<BoardVO> boardList(Pager pager) throws Exception;
	//select
	public BoardVO boardSelect(BoardVO boardVO) throws Exception;
	//write
	public int boardWrite(BoardVO boardVO) throws Exception;
	//update
	public int boardUpdate(BoardVO boardVO) throws Exception;
	//delete
	public int boardDelete(BoardVO boardVO) throws Exception;
	//count
	public int boardCount(Pager pager) throws Exception;
}
