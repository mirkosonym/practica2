package com.registrouniversitario.service;

import com.registrouniversitario.model.Estudiante;
import com.registrouniversitario.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    
    @Autowired
    private EstudianteRepository estudianteRepository;
    
    @Transactional(readOnly = true)
    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Estudiante> findById(Long id) {
        return estudianteRepository.findById(id);
    }
    
    @Transactional
    public Estudiante save(Estudiante estudiante) {
        if (estudianteRepository.existsByMatricula(estudiante.getMatricula())) {
            throw new RuntimeException("Ya existe un estudiante con esa matrícula");
        }
        if (estudianteRepository.existsByEmail(estudiante.getEmail())) {
            throw new RuntimeException("Ya existe un estudiante con ese email");
        }
        return estudianteRepository.save(estudiante);
    }
    
    @Transactional
    public void deleteById(Long id) {
        estudianteRepository.deleteById(id);
    }
} 