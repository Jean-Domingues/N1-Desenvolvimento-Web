package br.anhembi.locadora.errors;

import jakarta.persistence.NoResultException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.anhembi.locadora.errors.custom.BadRequestError;
import br.anhembi.locadora.errors.custom.InternalError;
import br.anhembi.locadora.errors.custom.NotFoundError;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { ApiError.class })
	public ResponseEntity<Object> apiErrorHandler(ApiError error) {
		return ResponseEntity.status(error.getStatusCode()).body(error.serializeError());
	}

	// @ExceptionHandler(value = MethodArgumentNotValidException.class)
	// public ResponseEntity<Object> handlerMethodArgumentNotValid(MethodArgumentNotValidException bre) {
	// var badRequest = new BadRequestError(bre.getFieldError().getDefaultMessage());
	// return ResponseEntity.status(badRequest.getStatusCode()).body(badRequest.serializeError());
	// }

	@ExceptionHandler(value = { EmptyResultDataAccessException.class })
	public ResponseEntity<Object> noResultExceptionHandler(NoResultException error) {
		var notFound = new NotFoundError();
		return ResponseEntity.status(notFound.getStatusCode()).body(notFound.serializeError());
	}

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<Object> exceptionHandler(Exception error) {
		var internalError = new InternalError(error);
		return ResponseEntity.status(internalError.getStatusCode()).body(internalError.serializeError());
	}
}
