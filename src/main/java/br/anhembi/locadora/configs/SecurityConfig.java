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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.anhembi.locadora.models.Category;
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
			employeeService.createAdmin("admin@teste.com", "secret");
			categoryService.save(Category.builder().name("Comédia").build());
			categoryService.save(Category.builder().name("Terror").build());
			categoryService.save(Category.builder().name("Drama").build());
			categoryService.save(Category.builder().name("Documentário").build());
			categoryService.save(Category.builder().name("Romance").build());
			categoryService.save(Category.builder().name("Ação").build());
			categoryService.save(Category.builder().name("Suspense").build());
		};
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, EmployeeService userService) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.userDetailsService(userService)
				.authorizeHttpRequests(autorize -> autorize
						.requestMatchers("/employee/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/movies/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/customers/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.build();
	}

	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
