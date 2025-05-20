package com.registrouniversitario.controller;

import com.registrouniversitario.model.Estudiante;
import com.registrouniversitario.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {
    
    @Autowired
    private EstudianteService estudianteService;
    
    @GetMapping
    public List<Estudiante> findAll() {
        return estudianteService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> findById(@PathVariable Long id) {
        return estudianteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Estudiante create(@RequestBody Estudiante estudiante) {
        return estudianteService.save(estudiante);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estudianteService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
