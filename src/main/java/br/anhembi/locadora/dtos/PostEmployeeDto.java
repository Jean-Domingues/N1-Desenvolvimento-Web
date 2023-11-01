package br.anhembi.locadora.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEmployeeDto {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
