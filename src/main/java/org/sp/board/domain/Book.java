package org.sp.board.domain;

import lombok.Data;

@Data
public class Book {
	private int book_idx;
	private String name;
	private String title;
	private String regdate;
	private String regtime;
	private String content;
	private String confirmed;
}
