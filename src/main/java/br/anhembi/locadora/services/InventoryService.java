package br.anhembi.locadora.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.anhembi.locadora.dtos.PostInventoryDTO;
import br.anhembi.locadora.errors.custom.BadRequestError;
import br.anhembi.locadora.errors.custom.NotFoundError;
import br.anhembi.locadora.mappers.InventoryMapper;
import br.anhembi.locadora.models.Inventory;
import br.anhembi.locadora.repositories.InventoryRepo;
import br.anhembi.locadora.repositories.MovieRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	private final InventoryRepo inventoryRepo;
	private final InventoryMapper inventoryMapper;

	private final MovieRepo movieRepo;

	public Inventory save(long movieId, PostInventoryDTO inventoryDTO) {
		var hasInventory = inventoryRepo.existsById(movieId);
		if (hasInventory)
			throw new BadRequestError("Inventory already exists");

		var movie = movieRepo.findById(movieId).orElseThrow(NotFoundError::new);
		var inventory = inventoryMapper.inventoryDtoToInventory(inventoryDTO);
		inventory.setMovie(movie);

		return inventoryRepo.save(inventory);
	}

	public List<Inventory> findAll() {
		return inventoryRepo.findAll();
	}

	public Inventory update(long movieId, PostInventoryDTO inventoryDTO) {
		var inventory = inventoryRepo.findById(movieId).orElseThrow(NotFoundError::new);

		inventoryMapper.patch(inventoryDTO, inventory);

		inventoryRepo.save(inventory);

		return inventory;
	}

	public Inventory delete(long movieId) {
		var inventory = inventoryRepo.findById(movieId).orElseThrow(NotFoundError::new);

		inventoryRepo.delete(inventory);

		return inventory;
	}
}
