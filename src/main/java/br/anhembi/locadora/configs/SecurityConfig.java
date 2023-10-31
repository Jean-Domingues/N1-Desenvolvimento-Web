package br.anhembi.locadora.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.anhembi.locadora.security.AppUser.Role;
import br.anhembi.locadora.services.AppUserService;

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
	CommandLineRunner commandLineRunnerUserDetails(AppUserService userService) {
		return args -> userService.createAdmin("user", "secret");
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AppUserService userService) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.userDetailsService(userService)
				.authorizeHttpRequests(autorize -> autorize
						.requestMatchers("/login", "/logout", "/user").permitAll()
						.requestMatchers("/car/delete/**").hasRole(Role.ADMIN.name())
						.requestMatchers("/car/**").authenticated()
						.anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.build();
	}
}
