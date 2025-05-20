package com.registrouniversitario.controller;

import com.registrouniversitario.model.Inscripcion;
import com.registrouniversitario.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {
    
    @Autowired
    private InscripcionService inscripcionService;
    
    @GetMapping
    public List<Inscripcion> findAll() {
        return inscripcionService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> findById(@PathVariable Long id) {
        return inscripcionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/estudiante/{estudianteId}")
    public List<Inscripcion> findByEstudianteId(@PathVariable Long estudianteId) {
        return inscripcionService.findByEstudianteId(estudianteId);
    }
    
    @GetMapping("/materia/{materiaId}")
    public List<Inscripcion> findByMateriaId(@PathVariable Long materiaId) {
        return inscripcionService.findByMateriaId(materiaId);
    }
    
    @PostMapping
    public Inscripcion create(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.save(inscripcion);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inscripcionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
