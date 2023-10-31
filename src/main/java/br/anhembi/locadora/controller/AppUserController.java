package br.anhembi.locadora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.locadora.dtos.AppUserDto;
import br.anhembi.locadora.security.AppUser;
import br.anhembi.locadora.services.AppUserService;

@RestController
@RequestMapping("/user")
public class AppUserController {
	@Autowired
	private AppUserService service;

	@PostMapping
	public ResponseEntity<AppUser> create(@RequestBody AppUserDto userDto) {
		var createUser = service.createUser(userDto.getUsername(), userDto.getPassword());

		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}
}
