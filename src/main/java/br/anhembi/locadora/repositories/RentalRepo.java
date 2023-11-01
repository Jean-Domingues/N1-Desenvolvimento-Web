package br.anhembi.locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.locadora.models.Rental;

public interface RentalRepo extends JpaRepository<Rental, Long> {}
