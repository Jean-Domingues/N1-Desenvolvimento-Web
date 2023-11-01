package br.anhembi.locadora.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.locadora.models.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmail(String email);
}
