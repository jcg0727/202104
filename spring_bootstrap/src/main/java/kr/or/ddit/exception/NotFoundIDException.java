package kr.or.ddit.exception;

public class NotFoundIDException extends Exception{

	public NotFoundIDException() {
		super("아이디가 일치하지 않습니다.");
	}
}
