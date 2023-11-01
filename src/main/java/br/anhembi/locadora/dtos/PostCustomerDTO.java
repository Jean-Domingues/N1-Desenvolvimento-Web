package br.anhembi.locadora.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCustomerDTO {
	@NotBlank(message = "A placa é obrigatória")
	private String firstName;
	@NotBlank(message = "A placa é obrigatória")
	private String lastName;
	@Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[0-9])[0-9]{3}\\-[0-9]{4}$")
	private String phone;
	@Email
	private String email;
}
