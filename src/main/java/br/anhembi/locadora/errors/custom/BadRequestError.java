package br.anhembi.locadora.errors.custom;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import br.anhembi.locadora.errors.ApiError;
import br.anhembi.locadora.errors.ErrorPayload;

public class BadRequestError extends ApiError {
	public BadRequestError(String message) {
		super(HttpStatus.BAD_REQUEST, message);
	}

	public BadRequestError() {
		super(HttpStatus.BAD_REQUEST, "Bad Request");
	}

	public ErrorPayload serializeError() {
		return new ErrorPayload(this.getMessage(), ZonedDateTime.now(ZoneId.of("Z")));
	}
}
