package br.anhembi.locadora.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.anhembi.locadora.models.Employee;
import br.anhembi.locadora.models.Employee.Role;
import br.anhembi.locadora.repositories.EmployeeRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {
	private final EmployeeRepo userRepo;
	private final PasswordEncoder passwordEncoder;

	public Employee createUser(String username, String password) {
		var user = Employee.builder()
				.email(username)
				.role(Role.ROLE_USER)
				.password(passwordEncoder.encode(password))
				.build();
		return userRepo.save(user);
	}

	public Employee createAdmin(String username, String password) {
		var user = Employee.builder()
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
