package br.anhembi.locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.locadora.models.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {}
