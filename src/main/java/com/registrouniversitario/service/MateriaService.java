package com.registrouniversitario.service;

import com.registrouniversitario.model.Materia;
import com.registrouniversitario.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    
    @Autowired
    private MateriaRepository materiaRepository;
    
    @Transactional(readOnly = true)
    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Materia> findById(Long id) {
        return materiaRepository.findById(id);
    }
    
    @Transactional
    public Materia save(Materia materia) {
        if (materiaRepository.existsByCodigo(materia.getCodigo())) {
            throw new RuntimeException("Ya existe una materia con ese código");
        }
        return materiaRepository.save(materia);
    }
    
    @Transactional
    public void deleteById(Long id) {
        materiaRepository.deleteById(id);
    }
}
