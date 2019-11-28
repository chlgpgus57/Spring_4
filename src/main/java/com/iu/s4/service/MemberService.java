package com.iu.s4.service;

import javax.servlet.http.HttpSession;

import com.iu.s4.model.MemberVO;

public interface MemberService {

	//join
	public int memberJoin(MemberVO memberVO, HttpSession session) throws Exception;
	//login
	public MemberVO memberLogin(MemberVO memberVO) throws Exception;
	//update
	public int memberUpdate(MemberVO memberVO) throws Exception;
	//delete
	public int memberDelete(MemberVO memberVO) throws Exception;
	//select
	public MemberVO memberSelect(MemberVO memberVO) throws Exception;
	//point update
	public int memberPointUpdate(MemberVO memberVO) throws Exception;
	//checkId
	public MemberVO memeberIdCheck(MemberVO memberVO) throws Exception;
}
