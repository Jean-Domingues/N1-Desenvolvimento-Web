package br.anhembi.locadora.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.locadora.dtos.CarDTO;
import br.anhembi.locadora.models.Car;
import br.anhembi.locadora.services.CarService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;

	@PostMapping("/{id}")
	public ResponseEntity<Car> create(@Valid @RequestBody CarDTO cardDto, @PathVariable long id) {
		Car carSaved = carService.saveCar(cardDto.toCar(), id);
		return ResponseEntity.status(HttpStatus.CREATED).body(carSaved);
	}

	@GetMapping("/{plate}")
	public ResponseEntity<Car> findByPlate(@PathVariable String plate) {
		Optional<Car> car = carService.findByPlate(plate);

		if (car.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(car.get());
	}

	@PatchMapping
	public ResponseEntity<CarDTO> update(@RequestBody CarDTO carDTO) {
		Optional<CarDTO> carOptional = carService.update(carDTO);
		if (carOptional.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(carOptional.get());
	}

	@DeleteMapping("/delete/{plate}")
	public ResponseEntity<Void> delete(@PathVariable String plate) {
		boolean response = carService.delete(plate);
		if (response) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
