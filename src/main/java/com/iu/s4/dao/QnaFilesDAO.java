package com.iu.s4.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iu.s4.model.QnaFilesVO;

@Repository
public class QnaFilesDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE = "qnaFilesMapper.";
	
	public int fileWrite(QnaFilesVO qnaFilesVO) throws Exception{
		
		return sqlSession.insert(NAMESPACE+"fileWrite", qnaFilesVO);
	}
	
	
	public List<QnaFilesVO> fileList(int num) throws Exception{
		
		return sqlSession.selectList(NAMESPACE+"fileList", num);
	}
}
