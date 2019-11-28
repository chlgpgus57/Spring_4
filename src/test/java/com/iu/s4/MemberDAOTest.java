package com.iu.s4;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.iu.s4.dao.MemberDAOImpl;
import com.iu.s4.model.MemberVO;

public class MemberDAOTest extends TestAbstractCase{
	
	@Inject
	private MemberDAOImpl memberDAO;
	
	//@Test
	public void Join() throws Exception{
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId("test");
		memberVO.setPw("test");
		memberVO.setBirth("1999-12-12");
		memberVO.setEmail("123@123");
		memberVO.setGender("F");
		memberVO.setName("test");
		
		int result = memberDAO.memberJoin(memberVO);
		
		assertEquals(1, result);
	}
	
	@Test
	public void UpdateTest() throws Exception{
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setName("plz");
		memberVO.setEmail("plz@plz");
		memberVO.setBirth("1999-10-10");
		memberVO.setGender("M");
		memberVO.setId("123");
		
		int num = memberDAO.memberUpdate(memberVO);
		
		assertEquals(1, num);
	}

}
