package com.bigroi.shop.exception;

public class EmailVerificationException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailVerificationException() {
		super();		
	}

	public EmailVerificationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);		
	}

	public EmailVerificationException(String message, Throwable cause) {
		super(message, cause);		
	}

	public EmailVerificationException(String message) {
		super(message);		
	}

	public EmailVerificationException(Throwable cause) {
		super(cause);		
	}	
	

}
