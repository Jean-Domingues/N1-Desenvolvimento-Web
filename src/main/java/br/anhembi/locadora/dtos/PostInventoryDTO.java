package br.anhembi.locadora.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostInventoryDTO {
	@NotNull
	@Positive
	private Integer quantity;
}
