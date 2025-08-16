package com.test.springmysql.controllers;

import com.test.springmysql.dtos.EstudianteDTO;
import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.services.EstudianteService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getAll(){
        return ResponseEntity.ok(estudianteService.getEstudiantes());
    }
    @PostMapping
    public ResponseEntity<EstudianteDTO> create(@Valid @RequestBody Estudiante estudiante){
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.createEstudiante(estudiante));
    }
    @GetMapping("/{estudianteId}")
    public ResponseEntity<Optional<EstudianteDTO>> getById(@PathVariable("estudianteId") Long estudianteId){
        return ResponseEntity.ok(estudianteService.getEstudiante(estudianteId));
    }
    @DeleteMapping("/{estudianteId}")
    public void delete(@PathVariable Long estudianteId){
        estudianteService.deleteEstudiante(estudianteId);
    }
    @PutMapping("/{estudianteId}")
    public ResponseEntity<EstudianteDTO> update(@PathVariable("estudianteId")Long id,@Valid @RequestBody Estudiante estudiante){
        return ResponseEntity.ok(estudianteService.updateEstudiante(id,estudiante));
    }

}
