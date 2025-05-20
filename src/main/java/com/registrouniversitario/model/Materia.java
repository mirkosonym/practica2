package com.registrouniversitario.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "materias")
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    private String codigo;
    
    @Column(nullable = false)
    private Integer creditos;
    
    @Column(nullable = false)
    private String profesor;
} 