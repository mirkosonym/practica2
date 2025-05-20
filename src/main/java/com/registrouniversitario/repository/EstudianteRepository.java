package com.registrouniversitario.repository;

import com.registrouniversitario.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    boolean existsByMatricula(String matricula);
    boolean existsByEmail(String email);
} 