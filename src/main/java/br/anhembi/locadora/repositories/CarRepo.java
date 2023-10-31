package br.anhembi.locadora.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.locadora.models.Car;

public interface CarRepo extends JpaRepository<Car, Long> {
	Optional<Car> findByPlate(String plate);
}
