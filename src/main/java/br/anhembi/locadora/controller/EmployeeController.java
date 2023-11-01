package br.anhembi.locadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.locadora.dtos.LoginDto;
import br.anhembi.locadora.models.Employee;
import br.anhembi.locadora.services.EmployeeService;

@RestController
@RequestMapping("/user")
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@PostMapping
	public ResponseEntity<Employee> create(@RequestBody LoginDto userDto) {
		var createUser = service.createUser(userDto.getUsername(), userDto.getPassword());

		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}
}
