package br.anhembi.locadora.dtos;

import br.anhembi.locadora.models.Car;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarDTO {
	@NotBlank(message = "A placa é obrigatória")
	private String plate;

	private int year;
	private String color;

	public CarDTO(Car car) {
		plate = car.getPlate();
		year = car.getYear();
		color = car.getColor();
	}

	public Car toCar() {
		return new Car(null, plate, year, color, null);
	}
}
