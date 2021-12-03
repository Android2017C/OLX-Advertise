package com.olx.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InvalidUserException.class })
	public ResponseEntity<Object> handleInvalidUserError(RuntimeException exception, WebRequest request) {

		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(value = { InvalidAdvertiseIdException.class })
	public ResponseEntity<Object> handleInvalidStockIdError(RuntimeException exception, WebRequest request) {

		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(value = { InvalidCategoryIdException.class })
	public ResponseEntity<Object> handleInvalidCategoryIdExceptionError(RuntimeException exception,
			WebRequest request) {

		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(value = { InvalidFromDateException.class })
	public ResponseEntity<Object> handleInvalidFromDateExceptionError(RuntimeException exception, WebRequest request) {

		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(value = { InvalidOnDateException.class })
	public ResponseEntity<Object> handleInvalidOnDateExceptionError(RuntimeException exception, WebRequest request) {

		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(value = { InvalidRecordNoException.class })
	public ResponseEntity<Object> handleInvalidRecordNoExceptionError(RuntimeException exception, WebRequest request) {

		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(value = { InvalidStartIndexException.class })
	public ResponseEntity<Object> handleInvalidStartIndexExceptionError(RuntimeException exception,
			WebRequest request) {

		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(value = { InvalidStatusIdException.class })
	public ResponseEntity<Object> handleInvalidStatusIdExceptionError(RuntimeException exception, WebRequest request) {

		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(value = { InvalidDateFormateException.class })
	public ResponseEntity<Object> handleInvalidDateFormateExceptionError(RuntimeException exception,
			WebRequest request) {

		return handleExceptionInternal(exception, exception.toString(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}
}
