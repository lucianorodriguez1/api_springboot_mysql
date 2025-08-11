package com.test.springmysql.controllers;

import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.services.EstudianteService;
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
    public List<Estudiante> getAll(){
        return estudianteService.getEstudiantes();
    }
    @PostMapping
    public void saveUpdate(@RequestBody Estudiante estudiante){
        estudianteService.saveOrUpdate(estudiante);
    }
    @GetMapping("/{estudianteId}")
    public Optional<Estudiante> getById(@PathVariable("estudianteId") Long estudianteId){
        return estudianteService.getEstudiante(estudianteId);
    }
    @DeleteMapping("/{estudianteId}")
    public void delete(@PathVariable Long estudianteId){
        estudianteService.deleteEstudiante(estudianteId);
    }

}
