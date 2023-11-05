package org.sp.board.controller.admin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.sp.board.domain.Admin;
import org.sp.board.exception.AdminException;
import org.sp.board.model.admin.AdminService;
import org.sp.board.model.admin.KakaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private KakaoService kakaoService;
	
	//로그인 폼 요청처ㅣ
	@GetMapping("/loginform")
	public ModelAndView loginform() {
		ModelAndView mav = new ModelAndView("admin/loginform");
		return mav;
	}
	
	//관리자 메인 요청 처리
	@GetMapping("/main")
	public ModelAndView getMain() {
		ModelAndView mav = new ModelAndView("admin/index");
		
		return mav;
	}
	
	//로그인 요청 처리
	@PostMapping("/login")
	public ModelAndView login(Admin admin, HttpServletRequest request) {
		//일치하는 관리자가 있는지 조회
		Admin dto=adminService.login(admin);
		
		//일치하면 세션에 dto를 담아서 서비스해준다.
		HttpSession session=request.getSession();
		session.setAttribute("admin", dto);
		
		ModelAndView mav = new ModelAndView("redirect:/admin/main");
		
		return mav;
	}
	
	//회원가입폼 요청 처리
	@GetMapping("/registform")
	public ModelAndView getRegist() {
		ModelAndView mav = new ModelAndView("admin/regist");
		
		return mav;
	}
	
	//회원가입 요청 처리
	@PostMapping("/regist")
	public String regist(Admin admin, HttpServletRequest request) {
		System.out.println(admin.getId());
		adminService.insert(admin);
		return "redirect:/admin/loginform";
		
	}
	
	@RequestMapping(value="/findidform", method=RequestMethod.GET)
	public ModelAndView findView() {
		ModelAndView mav = new ModelAndView("admin/findidform");
		return mav;
	}
	@PostMapping("/findId")
	public ModelAndView findId(Admin admin, HttpServletRequest request) {
		Admin dto=adminService.findId(admin);
		//System.out.println(dto.getId());
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto", dto);
		mav.setViewName("admin/findidform");
		
		//System.out.println(mav.getModel());
		
		return mav;
	}
	
	@RequestMapping(value="/findpassform", method=RequestMethod.GET)
	public ModelAndView findPassView() {
		ModelAndView mav = new ModelAndView("admin/findpassform");
		return mav;
	}
	@PostMapping("/findPass")
	public ModelAndView findPass(Admin admin, HttpServletRequest request) {
		Admin dto=adminService.findId(admin);
		//System.out.println(dto.getId());
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("dto", dto);
		mav.setViewName("admin/findpassform");
		
		//System.out.println(mav.getModel());
		
		return mav;
	}
	
	@RequestMapping(value="/kakaologin", method=RequestMethod.GET)
	public ModelAndView kakaoLogin(@RequestParam(value="code", required = false)String code) throws Exception{
		System.out.println("#########"+code);
		 String access_Token = kakaoService.getAccessToken(code);
		 HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
	    System.out.println("###access_Token#### : " + access_Token);
	     //System.out.println("###userInfo#### : " + userInfo.get("email"));
	     System.out.println("###nickname#### : " + userInfo.get("nickname"));
	    // System.out.println("###profile_image#### : " + userInfo.get("profile_image"));
		ModelAndView mav = new ModelAndView("redirect:/admin/board/list");
	     return mav;
	}
	

	
	@ExceptionHandler(AdminException.class)
	public ModelAndView handle(AdminException e) {
		ModelAndView mav = new ModelAndView("error/result");
		mav.addObject("e", e);
		return mav;
	}
	
	
	
	
}
