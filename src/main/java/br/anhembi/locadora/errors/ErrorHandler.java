package br.anhembi.locadora.errors;

import jakarta.persistence.NoResultException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		var badRequestError = new BadRequestError(
				ex.getFieldErrors().stream().map(e -> e.getField() + ": " + e.getDefaultMessage()).toList().toString());
		return ResponseEntity.status(badRequestError.getStatusCode()).body(badRequestError.serializeError());
	}
}
