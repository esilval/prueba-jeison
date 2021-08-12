package com.example.prueba.controller;

import com.example.prueba.common.PersonaException;
import com.example.prueba.common.PersonaRequest;
import com.example.prueba.service.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personas")
public class PersonaControlador {

    private final PersonaServicio servicio;

    @Autowired
    public PersonaControlador(PersonaServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping()
    public ResponseEntity<?> crearPersona(@RequestBody PersonaRequest request) {
        try {
            servicio.create(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PersonaException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}/persona")
    public ResponseEntity<?> actualizarPersona(@PathVariable(value = "id") Integer id, @RequestBody PersonaRequest request) {
        try {
            servicio.update(id, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PersonaException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}/persona")
    public ResponseEntity<?> eliminarPersona(@PathVariable(value = "id") Integer id) {
        servicio.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtenerPersonas() {
        return ResponseEntity.ok(servicio.findAll());
    }

    @GetMapping(value = "/{id}/persona", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtenerPorId(@PathVariable(value = "id") Integer id) {
        try {
            return ResponseEntity.ok(servicio.findById(id));
        } catch (PersonaException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }
}
