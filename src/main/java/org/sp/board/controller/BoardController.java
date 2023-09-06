package org.sp.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.sp.board.domain.Board;
import org.sp.board.domain.BoardFile;
import org.sp.board.exception.BoardException;
import org.sp.board.exception.BoardFileException;
import org.sp.board.exception.FileException;
import org.sp.board.model.board.BoardService;
import org.sp.board.util.FileManager;
import org.sp.board.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	//컨트롤러가 직접 DAO를 다루게 되면 트랜잭션 처리까지 부담한다거나
	//모델 part의 업무를 너무 전문적으로 처리하게 된다.
	//컨트롤러와 모델의 업무 경계가 모호해 지므로 즉 코드의 분리가 안되므로 추후 비슷한 업무시
	//코드를 분리해 놓지 않았기 때문에 코드의 재사용성이 떨어진다.
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private Pager pager;
	
	//게시판 목록
	@RequestMapping(value="/board/list", method=RequestMethod.GET)
	public ModelAndView getList(HttpServletRequest request) {
		List list = new ArrayList();
		for(int i=0; i<926;i++) {
			list.add("");
		}
		
		pager.init(list, request);
		ModelAndView mav = new ModelAndView("board/list");
		mav.addObject("pager", pager);//저장했다는 것은 포워딩이 필요하다는 것임
		
	
		return mav;
	}
	
	//글 쓰기 폼
	@RequestMapping(value="/board/registform", method=RequestMethod.GET)
	public String getform() {
		return "board/regist";
	}
	
	//글쓰기 등록
	@RequestMapping(value="/board/regist", method=RequestMethod.POST)
	public String regist(Board board, HttpServletRequest request) {
		//글 등록, 파일업로드
		
		MultipartFile[] photo=board.getPhoto();
		System.out.println("넘겨받은 파일의 수는 "+photo.length);
		
		//jsp의 application 내장객체는 서블릿 api에서 ServletContext 이다
		//따라서 이 객체를 얻기 위해 HttpSession을 얻어야 한다.
		ServletContext context=request.getSession().getServletContext();
		String path=context.getRealPath("/resources/data/");
		System.out.println("파일이 저장될 풀 경로는"+path);
		
		List<BoardFile> fileList=new ArrayList<BoardFile>();//새롭게 생성한 파일정보이 누적될 곳
		
		for(int i=0; i<photo.length;i++) {
			String filename=photo[i].getOriginalFilename();
			
			String name=fileManager.save(path, filename, photo[i]);
			
			BoardFile boardFile = new BoardFile();//empty
			boardFile.setBoard(board);// 이 시점의 board dto에는 아직 board_idx는 0인상태
			boardFile.setFilename(filename);
			
			fileList.add(boardFile);
			
			
		}
		
		//Board DTO에 boardFile 들을 생성하여 List로 넣어두기
		board.setBoardFileList(fileList);
		
		//서비스에서 예외가 발생했을 땐 스프링의 컨트롤러는 예외를 감지하는 이벤트가 발생함
		//이때 이 이벤트를 처리할 수 있는 메서드를 정의해 놓고 개발자가 알맞는 에러 페이지 및 메시지 구성
		boardService.regist(board);//글 등록 요청

		return "redirect:/board/list";// 형님인 DispatcherServlet이 viewResolver를 이용하여 해석
	}
	
	@ExceptionHandler(FileException.class)
	public ModelAndView handle(FileException e) {
		//jsp에서 에러 메시지를 보여줘야 하므로 요청은 유지가 되어야 한다
		//즉 저장할 것이 있고 가져갈 것이 있다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("e", e);//에러 객체 저장
		mav.setViewName("error/result");
		return mav;
	}
	
	//어떠한 예외가 발생했을떄 어떤 처리를 할지 아래의 메서드에서 로직 작성
	@ExceptionHandler(BoardException.class)
	public ModelAndView handle(BoardException e) {
		//jsp에서 에러 메시지를 보여줘야 하므로 요청은 유지가 되어야 한다
		//즉 저장할 것이 있고 가져갈 것이 있다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("e", e);//에러 객체 저장
		mav.setViewName("error/result");
		return mav;
	}
	
	@ExceptionHandler(BoardFileException.class)
	public ModelAndView handle(BoardFileException e) {
		//jsp에서 에러 메시지를 보여줘야 하므로 요청은 유지가 되어야 한다
		//즉 저장할 것이 있고 가져갈 것이 있다.
		ModelAndView mav = new ModelAndView();
		mav.addObject("e", e);//에러 객체 저장
		mav.setViewName("error/result");
		return mav;
	}
}












