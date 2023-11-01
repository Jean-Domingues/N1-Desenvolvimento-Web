package br.anhembi.locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.locadora.models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {}
