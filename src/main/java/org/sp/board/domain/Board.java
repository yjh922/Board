package org.sp.board.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Board {
	private int board_idx;
	private String writer;
	private String title;
	private String content;
	private int hit;
	private String regdate;
	
	//바이너리 파일을 받을 수 있는 자료형
	MultipartFile[] photo;
	
	List<BoardFile> boardFileList;
}
