package com.iu.s4.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.iu.s4.dao.BoardQnaDAO;
import com.iu.s4.dao.QnaFilesDAO;
import com.iu.s4.model.BoardVO;
import com.iu.s4.model.QnaFilesVO;
import com.iu.s4.util.FileSaver;
import com.iu.s4.util.Pager;
@Service
public class BoardQnaService implements BoardService {
	
	@Inject
	private BoardQnaDAO boardQnaDAO;
	@Inject
	private FileSaver fs;
	@Inject
	private QnaFilesDAO qnaFilesDAO;
	
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		
		pager.makeRow();
		pager.makePage(boardQnaDAO.boardCount(pager));
		return boardQnaDAO.boardList(pager);
	}

	@Override
	public BoardVO boardSelect(BoardVO boardVO) throws Exception {
		//boardVO = boardQnaDAO.boardSelect(boardVO);
		//BoardQnaVO boardQnaVO = (BoardQnaVO)boardVO;
		
		//boardQnaVO.setFiles(qnaFilesDAO.fileList(boardVO.getNum()));
		//return boardQnaVO;
		
		return boardQnaDAO.boardSelect(boardVO);
	}

	@Override
	public int boardWrite(BoardVO boardVO, HttpSession session) throws Exception {
		
		String realpath = session.getServletContext().getRealPath("resources/upload/qna");
		int result = boardQnaDAO.boardWrite(boardVO);
		
		if(boardVO.getFile() != null) {
			
		for (int i = 0; i < boardVO.getFile().length; i++) {
			QnaFilesVO qnaFilesVO = new QnaFilesVO();
			String filename = fs.save2(realpath, boardVO.getFile()[i]);
			
			qnaFilesVO.setNum(boardVO.getNum());
			qnaFilesVO.setFname(filename);
			qnaFilesVO.setOname(boardVO.getFile()[i].getOriginalFilename());
			
			qnaFilesDAO.fileWrite(qnaFilesVO);
			
		}
		}
		return result;
	}

	@Override
	public int boardUpdate(BoardVO boardVO, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		return boardQnaDAO.boardUpdate(boardVO);
	}

	@Override
	public int boardDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return boardQnaDAO.boardDelete(boardVO);
	}
	public int boardReply(BoardVO boardVO) throws Exception{	
		
		boardQnaDAO.boardReplyUpdate(boardVO);
		int result2 = boardQnaDAO.boardReply(boardVO);
		
		return result2;
	}
}
