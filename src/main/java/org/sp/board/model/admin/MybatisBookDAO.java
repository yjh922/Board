package org.sp.board.model.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.sp.board.domain.Book;
import org.sp.board.exception.BookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisBookDAO implements BookDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	

	public List selectAll() {
		
		return sqlSessionTemplate.selectList("Book.selectAll");
	}
	
	@Override
	public Book select(int book_idx) {
		return sqlSessionTemplate.selectOne("Book.select", book_idx);
	}
	
	@Override
	public void updateOk(Book book) throws BookException{
		int result=sqlSessionTemplate.update("Book.updateOk", book);
		if(result<1) {
			throw new BookException("글 수정에 실패");
		}
	}
	@Override
	public void updateCancle(Book book) throws BookException{
		int result=sqlSessionTemplate.update("Book.updateCancle", book);
		if(result<1) {
			throw new BookException("글 수정에 실패");
		}
		
	}
}
