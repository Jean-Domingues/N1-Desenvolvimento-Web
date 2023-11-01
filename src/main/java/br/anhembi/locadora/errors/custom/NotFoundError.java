package br.anhembi.locadora.errors.custom;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import br.anhembi.locadora.errors.ApiError;
import br.anhembi.locadora.errors.ErrorPayload;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundError extends ApiError {
	public NotFoundError(String message) {
		super(HttpStatus.NOT_FOUND, message);
		log.debug("Não encontrado, mensagem: {}", this.getMessage());
	}

	public NotFoundError() {
		super(HttpStatus.NOT_FOUND, "Not Found");
		log.debug("Não encontrado, mensagem: {}", this.getMessage());
	}

	public ErrorPayload serializeError() {
		return new ErrorPayload(this.getMessage(), ZonedDateTime.now(ZoneId.of("Z")));
	}
}
