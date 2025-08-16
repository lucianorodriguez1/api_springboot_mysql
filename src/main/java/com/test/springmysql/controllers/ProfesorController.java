package com.test.springmysql.controllers;

import com.test.springmysql.dtos.ProfesorDTO;
import com.test.springmysql.entities.Profesor;
import com.test.springmysql.services.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> getAll(){
        return ResponseEntity.ok(profesorService.getProfesores());
    }
    @PostMapping
    public ResponseEntity<ProfesorDTO> create(@Valid @RequestBody Profesor profesor){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profesorService.createProfesor(profesor));
    }

    @GetMapping("/{profesorId}")
    public ResponseEntity<?> getById(@PathVariable("profesorId") Long id){
        Optional<ProfesorDTO> pOpt = profesorService.getProfesor(id);
        if(pOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje","Profesor no encontrado"));
        }
        return ResponseEntity.ok(pOpt.get());
    }
    @DeleteMapping("/{profesorId}")
    public ResponseEntity<Void> delete(@PathVariable("profesorId") Long id){
        profesorService.deleteProfesor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{profesorId}")
    public ResponseEntity<?> update(@PathVariable("profesorId") Long id, @Valid @RequestBody Profesor profesor){
        return ResponseEntity.ok(profesorService.updateProfesor(id, profesor));
    }

}
