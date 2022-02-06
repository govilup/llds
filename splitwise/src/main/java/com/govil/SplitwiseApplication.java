package com.govil;

import com.govil.services.command.RegisterUserCommand;
import com.govil.services.command.UpdateProfileCommand;
import com.govil.services.command.registry.CommandRegistry;
import com.govil.services.command.registry.CommandRegistryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {

	@Autowired
	RegisterUserCommand registerUserCommand;

	@Autowired
	UpdateProfileCommand updateProfileCommand;

	@Autowired
	CommandRegistry commandRegistry;

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		commandRegistry.registerCommand(registerUserCommand);
		commandRegistry.registerCommand(updateProfileCommand);

		String input = "u1 UpdateProfile robinchwan";
		commandRegistry.executeCommand(input);
	}
}
