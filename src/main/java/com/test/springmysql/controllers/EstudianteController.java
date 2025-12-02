package com.test.springmysql.controllers;

import com.test.springmysql.dtos.estudiantes.EstudianteListDTO;
import com.test.springmysql.services.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<List<EstudianteListDTO>> getAll(){
        return ResponseEntity.ok(estudianteService.getEstudiantes());
    }

    @PostMapping
    public ResponseEntity<EstudianteListDTO> create(@Valid @RequestBody EstudianteListDTO estudiantedto){
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.createEstudiante(estudiantedto));
    }

    @GetMapping("/{estudianteId}")
    public ResponseEntity<EstudianteListDTO> getById(@PathVariable("estudianteId") Long estudianteId){
        return ResponseEntity.ok(estudianteService.getEstudiante(estudianteId));
    }

    @DeleteMapping("/{estudianteId}")
    public void delete(@PathVariable Long estudianteId){
        estudianteService.deleteEstudiante(estudianteId);
    }

    @PutMapping("/{estudianteId}")
    public ResponseEntity<EstudianteListDTO> update(@PathVariable("estudianteId")Long id, @Valid @RequestBody EstudianteListDTO estudiantedto){
        return ResponseEntity.ok(estudianteService.updateEstudiante(id,estudiantedto));
    }

}
