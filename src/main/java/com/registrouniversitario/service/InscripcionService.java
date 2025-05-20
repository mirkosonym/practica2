package com.registrouniversitario.service;

import com.registrouniversitario.model.Inscripcion;
import com.registrouniversitario.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {
    
    @Autowired
    private InscripcionRepository inscripcionRepository;
    
    @Transactional(readOnly = true)
    public List<Inscripcion> findAll() {
        return inscripcionRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Inscripcion> findById(Long id) {
        return inscripcionRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<Inscripcion> findByEstudianteId(Long estudianteId) {
        return inscripcionRepository.findByEstudianteId(estudianteId);
    }
    
    @Transactional(readOnly = true)
    public List<Inscripcion> findByMateriaId(Long materiaId) {
        return inscripcionRepository.findByMateriaId(materiaId);
    }
    
    @Transactional
    public Inscripcion save(Inscripcion inscripcion) {
        if (inscripcionRepository.existsByEstudianteIdAndMateriaId(
                inscripcion.getEstudiante().getId(),
                inscripcion.getMateria().getId())) {
            throw new RuntimeException("El estudiante ya está inscrito en esta materia");
        }
        
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setEstado("ACTIVA");
        return inscripcionRepository.save(inscripcion);
    }
    
    @Transactional
    public void deleteById(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
