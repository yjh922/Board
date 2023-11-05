package org.sp.board.model.admin;

import java.util.List;

import org.sp.board.domain.Book;
import org.sp.board.exception.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{

		@Autowired
		private BookDAO bookDAO;
		
		public List selectAll() {
			return bookDAO.selectAll();
		}
		
		@Override
		public Book select(int book_idx) {
		
			return bookDAO.select(book_idx);
		}
		@Override
		public void updateOk(Book book) throws BookException{
			bookDAO.updateOk(book);
		}
		@Override
		public void updateCancle(Book book) throws BookException{
			bookDAO.updateCancle(book);
		}
}
