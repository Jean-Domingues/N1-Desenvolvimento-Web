package br.anhembi.locadora.errors.custom;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import br.anhembi.locadora.errors.ApiError;
import br.anhembi.locadora.errors.ErrorPayload;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalError extends ApiError {
	public InternalError(String message) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, message);
		log.error("Erro Interno, mensagem: {}", this.getMessage());
	}

	public InternalError(Throwable throwable) {
		super(HttpStatus.INTERNAL_SERVER_ERROR, throwable);
		log.error("Erro Interno, mensagem: {}", this.getMessage());
	}

	public ErrorPayload serializeError() {
		return new ErrorPayload("Algo deu errado!", ZonedDateTime.now(ZoneId.of("Z")));
	}

	@Override
	public HttpStatus getStatusCode() {
		return this.statusCode;
	}
}
