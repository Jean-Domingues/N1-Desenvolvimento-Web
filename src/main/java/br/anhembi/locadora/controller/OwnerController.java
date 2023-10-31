package br.anhembi.locadora.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.locadora.models.Car;
import br.anhembi.locadora.models.Owner;
import br.anhembi.locadora.services.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;

	@PostMapping
	public ResponseEntity<Owner> create(@RequestBody Owner owner) {
		return ResponseEntity.ok(ownerService.save(owner));
	}

	@GetMapping("/{id}/cars")
	public ResponseEntity<List<Car>> getCars(@PathVariable long id) {
		List<Car> list = ownerService.getCars(id);

		if (list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(list);
	}
}
