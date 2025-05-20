package com.registrouniversitario;

import com.registrouniversitario.model.User;
import com.registrouniversitario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RegistroUniversitarioApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(RegistroUniversitarioApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Crear un usuario de prueba si no existe
            if (userRepository.findByUsername("testuser").isEmpty()) {
                User testUser = new User();
                testUser.setUsername("testuser");
                testUser.setPassword(passwordEncoder.encode("password"));
                userRepository.save(testUser);
                System.out.println("Usuario de prueba 'testuser' creado con contraseña 'password'");
            }
        };
    }
}
