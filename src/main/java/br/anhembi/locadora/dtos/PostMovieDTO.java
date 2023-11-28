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
	@NotBlank
	private String title;
	@NotBlank
	private String description;
	private String ageRating;
	private String director;
	@NotBlank
	private String imageLink;
}
