package org.sp.board.controller.admin;

import java.util.List;

import org.sp.board.model.board.BoardService;
import org.sp.board.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

//관리자 모드에서 게시판과 관련된 요청을 처리하는 컨트롤러
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private Pager pager;
	
	//게시판 목록 요청 처리
	@GetMapping("/board/list")
	public ModelAndView getList() {
		//모든 레코드 가져오기
		List boardList=boardService.selectAll();
		
		//저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.addObject("pager", pager);
		mav.setViewName("admin/board/list");
		return mav;
	}
}
