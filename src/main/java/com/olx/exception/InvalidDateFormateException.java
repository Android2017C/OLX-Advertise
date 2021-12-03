package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDateFormateException extends RuntimeException {

	private String message;

	public InvalidDateFormateException() {
		this.message = "";
	}

	public InvalidDateFormateException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Invalid Date Formate " + this.message;
	}
}
