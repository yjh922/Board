package org.sp.board.model.admin;

import java.util.List;

import org.sp.board.domain.Book;

public interface BookService {
	public List selectAll();
	public Book select(int book_idx);
	public void updateOk(Book book);
	public void updateCancle(Book book);
}
