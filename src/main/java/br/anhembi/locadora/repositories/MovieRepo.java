package br.anhembi.locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.locadora.models.Movie;

public interface MovieRepo extends JpaRepository<Movie, Long> {}
