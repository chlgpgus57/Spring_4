package com.iu.s4.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s4.dao.BoardNoticeDAO;
import com.iu.s4.dao.NoticeFilesDAO;
import com.iu.s4.model.BoardVO;
import com.iu.s4.model.FilesVO;
import com.iu.s4.util.FileSaver;
import com.iu.s4.util.Pager;

@Service
@Transactional
public class BoardNoticeService implements BoardService {
	
	@Inject
	private BoardNoticeDAO boardNoticeDAO;
	@Inject
	private FileSaver fs;
	@Inject
	private NoticeFilesDAO noticeFilesDAO;
	
	public boolean summerDelete(String file, HttpSession session) throws Exception{
		String realpath = session.getServletContext().getRealPath("resources/upload/summerFile");
		return fs.fileDelete(realpath, file);
	}
	
	public String summerFile(MultipartFile file, HttpSession session) throws Exception{
		String realpath = session.getServletContext().getRealPath("resources/upload/summerFile");
		String filename =fs.save2(realpath, file);
		System.out.println(realpath);
		return filename;
	}
	
	public FilesVO fileSelect(FilesVO noticeFilesVO) throws Exception{
		return noticeFilesDAO.fileSelect(noticeFilesVO);
	}
	
	
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		pager.makeRow();
		pager.makePage(boardNoticeDAO.boardCount(pager));
		
		
		return boardNoticeDAO.boardList(pager);
	}
	
	public int fileDelete(FilesVO noticeFilesVO) throws Exception{
		
		return noticeFilesDAO.fileDelete(noticeFilesVO);
	}
	
	@Override
	public BoardVO boardSelect(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		//boardVO = boardNoticeDAO.boardSelect(boardVO);
		
		//BoardNoticeVO boardNoticeVO = (BoardNoticeVO)boardVO;
		
		//List<NoticeFilesVO> ar =noticeFilesDAO.fileList(boardVO.getNum());
		
		//boardNoticeVO.setFiles(ar);
		
		//return boardNoticeVO;
		
		return boardNoticeDAO.boardSelect(boardVO);
	}
	
	
	@Override
	public int boardWrite(BoardVO boardVO, HttpSession session) throws Exception {
		
		String realpath = session.getServletContext().getRealPath("resources/upload/notice");
		

		int result = boardNoticeDAO.boardWrite(boardVO);
		
		if(boardVO.getFile() !=null){
			for (int i = 0; i < boardVO.getFile().length; i++) {
				if(boardVO.getFile()[i].getSize()>0) {
					
					FilesVO noticeFilesVO = new FilesVO();
					String filename = fs.save2(realpath, boardVO.getFile()[i]);
			
					noticeFilesVO.setNum(boardVO.getNum());
					noticeFilesVO.setFname(filename);
					noticeFilesVO.setOname(boardVO.getFile()[i].getOriginalFilename());
			
					int result2 = noticeFilesDAO.noticefilesWrite(noticeFilesVO);
					
					if(result2 <1) {
						throw new SQLException();
					}
					
				}
			}
		}
		return result;
	}
	
	
	@Override
	public int boardUpdate(BoardVO boardVO, HttpSession session) throws Exception {
		
		String realpath = session.getServletContext().getRealPath("resources/upload/notice");
		
		int result =boardNoticeDAO.boardUpdate(boardVO);
		
		if(boardVO.getFile() != null) {
			
			for (MultipartFile file : boardVO.getFile()) {
				if (file.getSize() >0) {
					
				FilesVO noticeFilesVO = new FilesVO();
				String filename = fs.save2(realpath, file);
				
				noticeFilesVO.setNum(boardVO.getNum());
				noticeFilesVO.setOname(file.getOriginalFilename());
				noticeFilesVO.setFname(filename);
				
				noticeFilesDAO.noticefilesWrite(noticeFilesVO);
				}
			}
		}
		
		
		return result;
	}

	@Override
	public int boardDelete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return boardNoticeDAO.boardDelete(boardVO);
	}

}
