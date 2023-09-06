package org.sp.board.exception;

//나만의 예외 객체 정의하기
public class BoardFileException extends RuntimeException{
	
	//Throwable은 예외의 최상위 객체이다
	public BoardFileException(String msg) {
		super(msg);
	}
	
	public BoardFileException(String msg, Throwable e) {
		super(msg, e);
	}
}
