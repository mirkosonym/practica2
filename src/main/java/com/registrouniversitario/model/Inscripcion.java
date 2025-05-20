package com.registrouniversitario.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "inscripciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;
    
    @ManyToOne
    @JoinColumn(name = "materia_id", nullable = false)
    private Materia materia;
    
    @Column(nullable = false)
    private LocalDateTime fechaInscripcion;
    
    @Column(nullable = false)
    private String estado; // ACTIVA, COMPLETADA, CANCELADA
} 