package org.sp.board.model.board;

import java.util.List;

import org.sp.board.domain.Board;
import org.sp.board.domain.BoardFile;

//컨트롤러가 이 서비스 객체를 보유할 때 결합도를 낮추기 위해
public interface BoardService {
	public void regist(Board board);
	public List selectAll();
	public Board select(int board_idx);
	public void update(Board board);
	public void delete(int board_idx);
	public void updateHit(int board_idx);
}
