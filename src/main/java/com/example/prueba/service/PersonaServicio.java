package com.example.prueba.service;

import com.example.prueba.common.PersonaException;
import com.example.prueba.common.PersonaRequest;
import com.example.prueba.entity.Persona;
import com.example.prueba.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServicio {

    private final PersonaRepositorio repositorio;

    @Autowired
    public PersonaServicio(PersonaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public void create(PersonaRequest request) throws PersonaException {
        try {
            final Persona persona = new Persona(request.getNombre(), request.getCedula(), request.getFechaNacimiento());
            repositorio.save(persona);
        } catch (Exception e) {
            throw new PersonaException(HttpStatus.BAD_REQUEST.value(), "El usuario no se pudo registrar.");
        }
    }

    public void update(Integer id, PersonaRequest request) throws PersonaException {
        try {
            final Persona persona = repositorio.findById(id).orElseThrow(() ->
                    new PersonaException(HttpStatus.NOT_FOUND.value(), "No se encontro el usuario"));
            persona.setNombre(request.getNombre());
            persona.setCedula(request.getCedula());
            persona.setFechaNacimiento(request.getFechaNacimiento());
            repositorio.save(persona);
        } catch (Exception e) {
            throw new PersonaException(HttpStatus.BAD_REQUEST.value(), "El usuario no se pudo actualizar.");
        }
    }

    public void delete(Integer id) {
        repositorio.deleteById(id);
    }

    public List<Persona> findAll() {
        return repositorio.findAll();
    }

    public Persona findById(Integer id) throws PersonaException {
        try {
            return repositorio.findById(id).orElseThrow(() ->
                    new PersonaException(HttpStatus.NOT_FOUND.value(), "No se encontro el usuario"));
        } catch (Exception e) {
            throw new PersonaException(HttpStatus.BAD_REQUEST.value(), "Se presento un error, intente nuevamente.");
        }
    }
}
