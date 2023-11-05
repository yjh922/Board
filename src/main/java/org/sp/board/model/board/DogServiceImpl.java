package org.sp.board.model.board;

import java.util.List;

import org.sp.board.domain.Dog;
import org.sp.board.exception.DogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogServiceImpl implements DogService{
	
	@Autowired
	private DogDAO dogDAO;

	@Override
	public void insert(Dog dog) throws DogException{
		dogDAO.insert(dog);
	}

	@Override
	public List selectAll() {
		return dogDAO.selectAll();
	}

	@Override
	public Dog select(int dog_idx) {
		return dogDAO.select(dog_idx);
	}

	@Override
	public void updateOk(Dog dog) throws DogException{
		dogDAO.updateOk(dog);
	}

	@Override
	public void updateCancle(Dog dog) throws DogException{
		dogDAO.updateCancle(dog);
	}

}
