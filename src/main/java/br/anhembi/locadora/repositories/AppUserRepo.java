package br.anhembi.locadora.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.anhembi.locadora.security.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findByEmail(String email);
}
