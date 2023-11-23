package com.sistemadenunciaamb.sda;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sistemadenunciaamb.sda.models.auth.Role;
import com.sistemadenunciaamb.sda.repository.auth.RoleRepository;
import com.sistemadenunciaamb.sda.repository.auth.UserRepository;

@SpringBootApplication
public class SdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdaApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder	passwordEncoder) {
		return args -> {
			
			if(roleRepository.findByAuthority("ROLE_ANALISTA").isPresent()) return;

			roleRepository.save(new Role("DENUNCIANTE"));
			roleRepository.save(new Role("ANALISTA"));
		};
	}

}
