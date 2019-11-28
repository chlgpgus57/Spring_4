package com.iu.s4.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iu.s4.model.FilesVO;

@Repository
public class NoticeFilesDAO {

	@Inject
	private SqlSession sqlsession;
	private static final String NAMESPACE="noticeFilesMapper.";
	
	public int noticefilesWrite (FilesVO noticeFilesVO) throws Exception{
		
		return sqlsession.insert(NAMESPACE+"fileWrite",noticeFilesVO);
		
	}
	
	public List<FilesVO> fileList (int num) throws Exception{
		
		return sqlsession.selectList(NAMESPACE+"fileList", num);
	}
	
	public int fileDelete(FilesVO noticeFilesVO) throws Exception{
		
		return sqlsession.delete(NAMESPACE+"fileDelete", noticeFilesVO);
		
	}
	
	public FilesVO fileSelect(FilesVO noticeFilesVO) throws Exception{
		noticeFilesVO = sqlsession.selectOne(NAMESPACE+"fileSelect", noticeFilesVO);
		return noticeFilesVO;
	}
}
