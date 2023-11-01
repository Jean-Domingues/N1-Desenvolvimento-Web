package br.anhembi.locadora.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchCustomerDTO {
	private String firstName;
	private String lastName;
	@Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[\\d])[\\d]{3}\\-[\\d]{4}$")
	private String phone;
	@Email
	private String email;
}
