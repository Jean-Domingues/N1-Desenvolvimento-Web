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

import br.anhembi.locadora.dtos.PostInventoryDTO;
import br.anhembi.locadora.models.Inventory;
import br.anhembi.locadora.services.InventoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/movies/{movieId}/inventories")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;

	@GetMapping
	public ResponseEntity<List<Inventory>> get() {
		var inventorys = inventoryService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(inventorys);
	}

	@PostMapping
	public ResponseEntity<Inventory> create(@PathVariable long movieId, @Valid @RequestBody PostInventoryDTO inventoryDto) {
		var inventory = inventoryService.save(movieId, inventoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(inventory);
	}

	@PatchMapping
	public ResponseEntity<Inventory> update(@PathVariable long movieId, @Valid @RequestBody PostInventoryDTO inventoryDTO) {
		var inventory = inventoryService.update(movieId, inventoryDTO);
		return ResponseEntity.ok(inventory);
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(@PathVariable long movieId) {
		inventoryService.delete(movieId);
		return ResponseEntity.ok().build();
	}
}
