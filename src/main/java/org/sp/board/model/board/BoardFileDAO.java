package org.sp.board.model.board;

import java.util.List;

import org.sp.board.domain.BoardFile;

public interface BoardFileDAO {
	public void insert(BoardFile boardFile);
	public List selectAll();
	public BoardFile select(int board_file_idx);
	public void update(BoardFile boardFile);
	public void delete(int board_file_idx);
}
