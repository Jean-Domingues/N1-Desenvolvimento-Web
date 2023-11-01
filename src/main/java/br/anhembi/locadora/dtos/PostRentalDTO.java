package br.anhembi.locadora.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRentalDTO {
	@NotNull
	@Positive
	private int quantity;
	@NotNull
	@Positive
	private Long movieId;
	@NotNull
	@Positive
	private Long customerId;
	@Future
	@NotNull
	private LocalDate dueDate;
}
