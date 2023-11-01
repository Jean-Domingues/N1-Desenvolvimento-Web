package br.anhembi.locadora.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.anhembi.locadora.dtos.PostRentalDTO;
import br.anhembi.locadora.errors.custom.BadRequestError;
import br.anhembi.locadora.errors.custom.NotFoundError;
import br.anhembi.locadora.mappers.RentalMapper;
import br.anhembi.locadora.models.Rental;
import br.anhembi.locadora.repositories.CustomerRepo;
import br.anhembi.locadora.repositories.InventoryRepo;
import br.anhembi.locadora.repositories.MovieRepo;
import br.anhembi.locadora.repositories.RentalRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {
	private final RentalRepo rentalRepo;
	private final RentalMapper rentalMapper;

	private final InventoryRepo inventoryRepo;
	private final CustomerRepo customerRepo;
	private final MovieRepo movieRepo;

	@Transactional
	public Rental save(PostRentalDTO rentalDTO) {
		var inventory = inventoryRepo.findById(rentalDTO.getMovieId()).orElseThrow(NotFoundError::new);
		var customer = customerRepo.findById(rentalDTO.getCustomerId()).orElseThrow(NotFoundError::new);
		var movie = movieRepo.findById(rentalDTO.getMovieId()).orElseThrow(NotFoundError::new);

		var newQuantity = inventory.getQuantity() - rentalDTO.getQuantity();
		if (newQuantity < 0)
			throw new BadRequestError("Not enough  itens in inventory");
		inventory.setQuantity(newQuantity);
		inventoryRepo.save(inventory);

		var rental = rentalMapper.rentalDtoToRental(rentalDTO);
		rental.setCustomer(customer);
		rental.setMovie(movie);

		return rentalRepo.save(rental);
	}

	public List<Rental> findAll() {
		return rentalRepo.findAll();
	}

	@Transactional
	public Rental delete(long id) {
		var rental = rentalRepo.findById(id).orElseThrow(NotFoundError::new);

		var inventory = inventoryRepo.findById(rental.getMovie().getId()).orElseThrow(NotFoundError::new);
		var newQuantity = inventory.getQuantity() + rental.getQuantity();
		inventory.setQuantity(newQuantity);
		inventoryRepo.save(inventory);

		rentalRepo.delete(rental);

		return rental;
	}
}
