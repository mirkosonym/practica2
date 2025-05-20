package com.registrouniversitario.controller;

import com.registrouniversitario.model.Materia;
import com.registrouniversitario.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {
    
    @Autowired
    private MateriaService materiaService;
    
    @GetMapping
    public List<Materia> findAll() {
        return materiaService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Materia> findById(@PathVariable Long id) {
        return materiaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Materia create(@RequestBody Materia materia) {
        return materiaService.save(materia);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        materiaService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
