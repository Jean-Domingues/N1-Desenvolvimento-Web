package br.anhembi.locadora.errors.custom;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import br.anhembi.locadora.errors.ApiError;
import br.anhembi.locadora.errors.ErrorPayload;

public class UnauthorizedError extends ApiError {
	public UnauthorizedError(String message) {
		super(HttpStatus.UNAUTHORIZED, message);
	}

	public ErrorPayload serializeError() {
		return new ErrorPayload(this.getMessage(), ZonedDateTime.now(ZoneId.of("Z")));
	}
}
