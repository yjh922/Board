package org.sp.board.model.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.sp.board.domain.Dog;
import org.sp.board.exception.DogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisDogDAO implements DogDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(Dog dog) throws DogException{
		int result=sqlSessionTemplate.insert("Dog.insert", dog);
		
		if(result==0) {
			//일부러 관련있는 에러를 일으키자
			throw new DogException("글 등록을 실패했어요");
		}
	}

	@Override
	public List selectAll() {
		
		return sqlSessionTemplate.selectList("Dog.selectAll");
	}

	@Override
	public Dog select(int dog_idx) {
		return sqlSessionTemplate.selectOne("Dog.select", dog_idx);
	}

	@Override
	public void updateOk(Dog dog) throws DogException{
		int result=sqlSessionTemplate.update("Dog.updateOk", dog);
		if(result<1) {
			throw new DogException("글 수정에 실패");
		}
	}

	@Override
	public void updateCancle(Dog dog) throws DogException{
		int result=sqlSessionTemplate.update("Dog.updateCancle", dog);
		if(result<1) {
			throw new DogException("글 수정에 실패");
		}
	}

}
