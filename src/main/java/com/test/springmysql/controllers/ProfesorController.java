package com.test.springmysql.controllers;

import com.test.springmysql.entities.Profesor;
import com.test.springmysql.services.ProfesorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1/profesores")
public class ProfesorController {
    private final ProfesorService profesorService;

    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @GetMapping
    public List<Profesor> getAll(){
        return profesorService.getProfesores();
    }
    @PostMapping
    public void saveUpdate(@RequestBody Profesor profesor){
        profesorService.saveOrUpdate(profesor);
    }
    @GetMapping("/{profesorId}")
    public Optional<Profesor> getById(@PathVariable("profesorId") Long id){
        return profesorService.getProfesor(id);
    }
    @DeleteMapping("/{profesorId}")
    public void delete(@PathVariable("profesorId") Long id){
        profesorService.deleteProfesor(id);
    }
}
