package br.anhembi.locadora.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.anhembi.locadora.repositories.AppUserRepo;
import br.anhembi.locadora.security.AppUser;
import br.anhembi.locadora.security.AppUser.Role;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
	private final AppUserRepo userRepo;
	private final PasswordEncoder passwordEncoder;

	public Optional<AppUser> getByUsername(String username) {
		return userRepo.findByEmail(username);
	}

	public AppUser createUser(String username, String password) {
		var user = AppUser.builder()
				.email(username)
				.role(Role.ROLE_USER)
				.password(passwordEncoder.encode(password))
				.build();
		return userRepo.save(user);
	}

	public AppUser createAdmin(String username, String password) {
		var user = AppUser.builder()
				.email(username)
				.role(Role.ADMIN)
				.password(passwordEncoder.encode(password))
				.build();
		return userRepo.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
	}
}
