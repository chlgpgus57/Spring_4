package com.iu.s4.dao;

import com.iu.s4.model.MemberVO;

public interface MemberDAO {
	
	//join
	public int memberJoin(MemberVO memberVO) throws Exception;
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
	//idcheck
	public MemberVO memberIdCheck(MemberVO memberVO) throws Exception;
}
