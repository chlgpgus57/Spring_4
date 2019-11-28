package com.iu.s4.service;


import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.iu.s4.dao.MemberDAOImpl;
import com.iu.s4.model.MemberVO;
import com.iu.s4.util.FileSaver;
@Service
public class MemberServiceImpl implements MemberService {
	
	@Override
	public MemberVO memeberIdCheck(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.memberIdCheck(memberVO);
	}

	@Inject
	private MemberDAOImpl memberDAO;
	
	@Override
	public int memberJoin(MemberVO memberVO, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		//Server에 HDD파일 저장
		//1. 파일을 저장할 실제 경로
		String realPath = session.getServletContext().getRealPath("resources/upload/member");
		
		String fileName = null;
		FileSaver fs = new FileSaver();
		//fileName = fs.save(realPath, memberVO.getFile());
		fileName = fs.save2(realPath, memberVO.getFile());
		
		
		System.out.println(realPath);
		memberVO.setFilename(fileName);
		memberVO.setOriginalname(memberVO.getFile().getOriginalFilename());
		return memberDAO.memberJoin(memberVO);
		
	}

	@Override
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.memberLogin(memberVO);
	}

	@Override
	public int memberUpdate(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return memberDAO.memberUpdate(memberVO);
	}

	@Override
	public int memberDelete(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		
		
		return 0;
	}

	@Override
	public MemberVO memberSelect(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int memberPointUpdate(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
