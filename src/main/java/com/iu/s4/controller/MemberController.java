package com.iu.s4.controller;

import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s4.model.MemberVO;
import com.iu.s4.service.MemberServiceImpl;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Inject
	private MemberServiceImpl memberService;
	
	@GetMapping("memberDelete")
	public void memberDelete(HttpSession session) throws Exception{
		
	}
	
	@GetMapping("memberIdCheck")
	public void memberIdcheck(MemberVO memberVO, Model model) throws Exception{
		
		
		memberVO = memberService.memeberIdCheck(memberVO);
		
		int result = 0;
		if(memberVO == null) {
			result = 1;
			
		}else {
			
		}
		
		model.addAttribute("dto", memberVO);
		model.addAttribute("result", result);
	}
	
	@GetMapping("memberJoin")
	public void memberJoin() throws Exception{
		
	}
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(MemberVO memberVO, HttpSession session) throws Exception{
		session.getServletContext().getRealPath("resources/upload");
		
		int result = memberService.memberJoin(memberVO, session);
		
		ModelAndView mv = new ModelAndView();
		String msg = "Join Fail";
		if(result > 0)
			msg = "Join Success";
		
		mv.addObject("msg", msg);
		mv.addObject("path", "../");
		mv.setViewName("common/common_result");
		
		return mv;
	}
	
	@GetMapping("memberLogin")
	public void memberLogin() throws Exception{
		
		
	}
	
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
	
		memberVO = memberService.memberLogin(memberVO);
		String msg = "로그인 실패";
		if(memberVO != null) {
			msg = "로그인 성공";
			session.setAttribute("member", memberVO);
		}
		mv.addObject("msg", msg);
		mv.addObject("path", "../");
		mv.setViewName("common/common_result");
		
		return mv;
	}
	@RequestMapping("memberLoginout")
	public ModelAndView memberLoginout(HttpSession session, ModelAndView mv) throws Exception{
		
		session.invalidate();
		mv.setViewName("./index");
		
		return mv;
	}
	@RequestMapping("memberMypage")
	public void memberMypage(HttpSession session) throws Exception{
		
		
		 MemberVO memberVO = (MemberVO)session.getAttribute("member");
		 
		 String date = memberVO.getBirth();
		 
		 SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd"); 
		 java.util.Date to = sf.parse(date);
		 
		 memberVO.setBirth(sf.format(to));
		 
		 session.setAttribute("member", memberVO);
		 
	}
	
	@GetMapping("memberUpdate")
	public void memberUpdate() throws Exception{
		
	}
	
//	@PostMapping("memberUpdate")
//	public String memberUpdate(MemberVO memberVO, HttpSession session) throws Exception{
//		System.out.println(memberVO.getId());
//		int result = memberService.memberUpdate(memberVO);
//		
//		session.setAttribute("member", memberVO);
//		
//		return "memberMypage";
//	}
	@PostMapping("memberUpdate")
	public ModelAndView memberUpdate(MemberVO memberVO, HttpSession session) throws Exception{
		int result = memberService.memberUpdate(memberVO);
		ModelAndView mv = new ModelAndView();
		if(result>0) {
			mv.addObject("msg", "Success");
			mv.addObject("path", "./memberMypage");
			mv.setViewName("common/common_result");
			mv.addObject("member", memberVO);
			session.setAttribute("member", memberVO);
		}else {
			mv.addObject("msg", "Fail");	
			mv.addObject("path", "./memberMypage");
			mv.setViewName("common/common_result");
		}
		return mv;
	}
}
