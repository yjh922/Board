package org.sp.board.model.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.sp.board.domain.Board;
import org.sp.board.exception.BoardException;
import org.sp.board.mybatis.MybatisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisBoardDAO implements BoardDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public void insert(Board board) throws BoardException{
		
	
		int result=sqlSessionTemplate.insert("Board.insert", board);
		//result=0;//일부러 에러 테스트
		
		if(result==0) {
			//일부러 관련있는 에러를 일으키자
			throw new BoardException("글 등록을 실패했어요");
		}
	}

	public List selectAll() {
		
		List list=sqlSessionTemplate.selectList("Board.selectAll");
		
		return list;
	}

	public Board select(int board_idx) {
		
		Board board=sqlSessionTemplate.selectOne("Board.select", board_idx);
		
		return board;
	}

	public void update(Board board) throws BoardException{
		
		int result=sqlSessionTemplate.update("Board.update", board);
		
		
		if(result<1) {
			throw new BoardException("수정 실패");
		}
	}

	public void delete(int board_idx) throws BoardException{
		
		int result=sqlSessionTemplate.delete("Board.delete", board_idx);
	
		if(result<1) {
			throw new BoardException("삭제실패");
		}
	}
	
	
	public void updateHit(int board_idx) {
		
		int result=sqlSessionTemplate.update("Board.updateHit", board_idx);

		if(result<1) {
			throw new BoardException("수정 실패");
		}
		
	}

}
