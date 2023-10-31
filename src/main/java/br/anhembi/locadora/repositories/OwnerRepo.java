package br.anhembi.locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.locadora.models.Owner;

public interface OwnerRepo extends JpaRepository<Owner, Long> {

}
