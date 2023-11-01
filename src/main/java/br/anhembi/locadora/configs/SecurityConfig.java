package br.anhembi.locadora.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.anhembi.locadora.models.Category;
import br.anhembi.locadora.models.Employee.Role;
import br.anhembi.locadora.services.CategoryService;
import br.anhembi.locadora.services.EmployeeService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	@ConditionalOnMissingBean(AuthenticationEventPublisher.class)
	DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher(ApplicationEventPublisher delegate) {
		return new DefaultAuthenticationEventPublisher(delegate);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner commandLineRunnerUserDetails(EmployeeService employeeService, CategoryService categoryService) {
		return args -> {
			employeeService.createAdmin("user", "secret");
			categoryService.save(Category.builder().name("Categoria 1").build());
			categoryService.save(Category.builder().name("Categoria 2").build());
			categoryService.save(Category.builder().name("Categoria 3").build());
			categoryService.save(Category.builder().name("Categoria 4").build());
		};
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, EmployeeService userService) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.userDetailsService(userService)
				.authorizeHttpRequests(autorize -> autorize
						.requestMatchers("/employee/**").hasRole(Role.ADMIN.name())
						.requestMatchers(HttpMethod.DELETE, "/movies/**").hasRole(Role.ADMIN.name())
						.requestMatchers(HttpMethod.DELETE, "/customers/**").hasRole(Role.ADMIN.name())
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.build();
	}
}
