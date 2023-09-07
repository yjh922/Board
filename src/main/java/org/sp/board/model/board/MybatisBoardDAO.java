package org.sp.board.model.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.sp.board.domain.Board;
import org.sp.board.exception.BoardException;
import org.sp.board.mybatis.MybatisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisBoardDAO implements BoardDAO{
	
	@Autowired
	private MybatisConfig mybatisConfig;

	public void insert(Board board) throws BoardException{
		SqlSession sqlSession=mybatisConfig.getSqlSession();
		
		int result=sqlSession.insert("Board.insert", board);
		sqlSession.commit();//DML인경우
		mybatisConfig.release(sqlSession);
		
		//result=0;//일부러 에러 테스트
		
		if(result==0) {
			//개발자가 일부러 관련있는 에러를 일으키자
			
			throw new BoardException("글 등록을 실패했어요");
		}
	}

	public List selectAll() {
		SqlSession sqlSession=mybatisConfig.getSqlSession();
		List list=sqlSession.selectList("Board.selectAll");
		mybatisConfig.release(sqlSession);
		return list;
	}

	public Board select(int board_idx) {
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		Board board=sqlSession.selectOne("Board.select", board_idx);
		mybatisConfig.release(sqlSession);
		return board;
	}

	public void update(Board board) {
		
	}

	public void delete(int board_idx) throws BoardException{
		SqlSession sqlSession = mybatisConfig.getSqlSession();
		int result=sqlSession.delete("Board.delete", board_idx);
		sqlSession.commit();
		mybatisConfig.release(sqlSession);
		if(result<1) {
			throw new BoardException("삭제실패");
		}
	}

}
