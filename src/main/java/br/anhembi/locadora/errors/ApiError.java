package br.anhembi.locadora.errors;

import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ApiError extends RuntimeException {
	protected final HttpStatus statusCode;

	protected ApiError(HttpStatus statusCode, String message) {
		super(message);
		log.debug("ApiError mensagem {}", message);
		this.statusCode = statusCode;
	}

	protected ApiError(HttpStatus statusCode, Throwable throwable) {
		super(throwable);
		log.debug("ApiError mensagem {}", throwable.getMessage());
		this.statusCode = statusCode;
		throwable.printStackTrace();
	}

	public abstract ErrorPayload serializeError();

	public HttpStatus getStatusCode() {
		return this.statusCode;
	}
}
