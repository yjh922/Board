package org.sp.board.domain;

import lombok.Data;

@Data
public class BoardFile {
	private int board_file_idx;
	private Board board;
	private String filename;
}
