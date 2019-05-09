package br.com.botanica.exception;

public class BotanicaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public BotanicaException(String msg) {
		super(msg);
	}
	
	public BotanicaException(Exception e) {
		super(e);
	}
}
