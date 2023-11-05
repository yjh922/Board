package org.sp.board.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sp.board.domain.Book;
import org.sp.board.domain.Dog;
import org.sp.board.model.board.DogService;
import org.sp.board.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DogController {
	
	@Autowired
	private DogService dogService;
	
	@Autowired
	private Pager pager;
	
	@GetMapping("/book/home")
	public ModelAndView getHome() {
		ModelAndView mav = new ModelAndView("book/home");
		
		return mav;
	}
	@GetMapping("/book/booking")
	public ModelAndView getForm() {
		ModelAndView mav = new ModelAndView("book/booking");
		
		return mav;
	}
	
	@PostMapping("/book/regist")
	public String regist(Dog dog, HttpServletRequest request) {
		dogService.insert(dog);
		return "redirect:/book/home";
	}
	
	
	//예약 목록 보기
	@GetMapping("/book/list")
	public ModelAndView getList(HttpServletRequest request) {
		List dogList=dogService.selectAll();
		pager.init(dogList, request);
		
		ModelAndView mav = new ModelAndView("admin/book/list");
		mav.addObject("dogList", dogList);
		mav.addObject("pager", pager);
		return mav;
	}
	
	//예약 한건 보기
	@GetMapping("/book/content")
	public ModelAndView getContent(int dog_idx) {
		
		Dog dog=dogService.select(dog_idx);
		
		ModelAndView mav = new ModelAndView("admin/book/content");
		mav.addObject("dog", dog);
		
		return mav;
	}
	//예약 확정
	@PostMapping("/book/ok")
	public ModelAndView updateOk(Dog dog) {
		dogService.updateOk(dog);
		
		ModelAndView mav = new ModelAndView("redirect:/admin/book/list");
		
		return mav;
	}
	//예약 취소
	@PostMapping("/book/cancle")
	public ModelAndView updateCancle(Dog dog) {
		dogService.updateCancle(dog);
		
		ModelAndView mav = new ModelAndView("redirect:/admin/book/list");
		
		return mav;
	}

}
