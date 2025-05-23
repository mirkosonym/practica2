package com.registrouniversitario.controller;

import com.registrouniversitario.model.User;
import com.registrouniversitario.repository.UserRepository;
import com.registrouniversitario.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil; // Necesitaremos crear esta clase

    @Autowired
    private UserRepository userRepository; // Para el registro (opcional, pero útil)

    @Autowired
    private PasswordEncoder passwordEncoder; // Para cifrar contraseñas

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }

    // Clase interna para la solicitud de login
    static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // Opcional: Endpoint para registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User registrationUser) {
        if (userRepository.findByUsername(registrationUser.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        registrationUser.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
        userRepository.save(registrationUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
} 