package br.anhembi.locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.locadora.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {}
