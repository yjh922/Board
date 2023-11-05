package org.sp.board.model.board;

import java.util.List;

import org.sp.board.domain.Dog;

public interface DogDAO {
	public void insert(Dog dog);
	public List selectAll();
	public Dog select(int dog_idx);
	public void updateOk(Dog dog);
	public void updateCancle(Dog dog);
}
