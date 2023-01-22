package com.example.test.steven.cruz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.test.steven.cruz.dto.FinalResponse;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public FinalResponse globalExceptionHandler(Exception error) {
		return getErrorResponse(error);
	}

	private FinalResponse getErrorResponse(Exception error) {
		return FinalResponse.builder()
				.result(false)
				.errorMessage(error.getMessage())
				.build();
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED)
	public FinalResponse notFoundExceptionHandler(Exception error) {
		return getErrorResponse(error);
	}
	
	@ExceptionHandler(NotDataValidateException.class)
	@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED)
	public FinalResponse notDataValidateExceptionHandler(Exception error) {
		return getErrorResponse(error);
	}
	
	@ExceptionHandler(NotLogicValidateException.class)
	@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED)
	public FinalResponse notLogicValidateExceptionHandler(Exception error) {
		return getErrorResponse(error);
	}
}