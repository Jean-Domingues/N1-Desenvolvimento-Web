package br.anhembi.locadora.dtos;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PatchMovieDTO {
	@Positive
	private Long categoryId;
	private String title;
	private String description;
	private String genre;
	private String ageRating;
	private String director;
}
