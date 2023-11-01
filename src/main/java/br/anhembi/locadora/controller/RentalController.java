package br.anhembi.locadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.locadora.dtos.PostRentalDTO;
import br.anhembi.locadora.models.Rental;
import br.anhembi.locadora.services.RentalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rentals")
public class RentalController {
	@Autowired
	private RentalService rentalService;

	@GetMapping
	public ResponseEntity<List<Rental>> get() {
		var rentals = rentalService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(rentals);
	}

	@PostMapping
	public ResponseEntity<Rental> create(@Valid @RequestBody PostRentalDTO rentalDto) {
		var rental = rentalService.save(rentalDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(rental);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		rentalService.delete(id);
		return ResponseEntity.ok().build();
	}
}
