package org.sp.board.model.board;

import java.util.List;

import org.sp.board.domain.Board;

public interface BoardDAO {
	public void insert(Board board);
	public List selectAll();
	public Board select(int board_idx);
	public void update(Board board);
	public void delete(int board_idx);
	public void updateHit(int board_idx);
}
