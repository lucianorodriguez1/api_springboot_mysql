package com.test.springmysql.controllers;

import com.test.springmysql.dtos.profesores.ProfesorListDTO;
import com.test.springmysql.services.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public ResponseEntity<List<ProfesorListDTO>> getAll(){
        return ResponseEntity.ok(profesorService.getProfesores());
    }

    @PostMapping
    public ResponseEntity<ProfesorListDTO> create(@Valid @RequestBody ProfesorListDTO profesordto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profesorService.createProfesor(profesordto));
    }

    @GetMapping("/{profesorId}")
    public ResponseEntity<?> getById(@PathVariable("profesorId") Long id){
       ProfesorListDTO p = profesorService.getProfesor(id);
        return ResponseEntity.ok(p);
    }
    @DeleteMapping("/{profesorId}")
    public ResponseEntity<Void> delete(@PathVariable("profesorId") Long id){
        profesorService.deleteProfesor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{profesorId}")
    public ResponseEntity<?> update(@PathVariable("profesorId") Long id, @Valid @RequestBody ProfesorListDTO profesordto){
        return ResponseEntity.ok(profesorService.updateProfesor(id, profesordto));
    }

}
