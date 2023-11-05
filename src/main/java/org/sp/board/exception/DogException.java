package org.sp.board.exception;

//나만의 예외 객체 정의하기
public class DogException extends RuntimeException{
	
	//Throwable은 예외의 최상위 객체이다
	public DogException(String msg) {
		super(msg);
	}
	
	public DogException(String msg, Throwable e) {
		super(msg, e);
	}
}
