package br.anhembi.locadora.controller;

import java.util.List;

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

import br.anhembi.locadora.dtos.PostCustomerDTO;
import br.anhembi.locadora.models.Customer;
import br.anhembi.locadora.services.CustomerService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> get() {
		var customers = customerService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(customers);
	}

	@PostMapping
	public ResponseEntity<Customer> create(@Valid @RequestBody PostCustomerDTO customerDto) {
		var customer = customerService.save(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable long id, @Valid @RequestBody PostCustomerDTO customerDTO) {
		var customer = customerService.update(id, customerDTO);
		return ResponseEntity.ok(customer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		customerService.delete(id);
		return ResponseEntity.ok().build();
	}
}
