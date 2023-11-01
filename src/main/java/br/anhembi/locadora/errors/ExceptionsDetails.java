package br.anhembi.locadora.errors;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionsDetails {
	private String titulo;
	private int codigo;
	private String detail;
	private LocalDateTime timeStamp;
}