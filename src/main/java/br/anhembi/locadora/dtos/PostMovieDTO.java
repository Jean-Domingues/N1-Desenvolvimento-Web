package br.anhembi.locadora.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostMovieDTO {
	@NotNull
	@Positive
	private Long categoryId;
	@NotBlank(message = "A placa é obrigatória")
	private String title;
	@NotBlank(message = "A placa é obrigatória")
	private String description;
	@NotBlank(message = "A placa é obrigatória")
	private String genre;
	@NotBlank(message = "A placa é obrigatória")
	private String ageRating;
	@NotBlank(message = "A placa é obrigatória")
	private String director;
}
