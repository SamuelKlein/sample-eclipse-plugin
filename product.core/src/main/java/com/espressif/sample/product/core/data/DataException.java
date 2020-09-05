package com.espressif.sample.product.core.data;

public class DataException extends RuntimeException {

	private Exception exception;

	public DataException(Exception exception, String msg) {
		super(msg);
		this.exception = exception;
	}

	public Exception getException() {
		return exception;
	}

}
