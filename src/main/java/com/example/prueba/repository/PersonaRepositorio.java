package com.example.prueba.repository;

import com.example.prueba.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {

}
