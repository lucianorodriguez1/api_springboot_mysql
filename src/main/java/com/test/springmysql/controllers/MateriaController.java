package com.test.springmysql.controllers;

import com.test.springmysql.entities.Materia;
import com.test.springmysql.services.MateriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public List<Materia> getAll(){
        return materiaService.getMaterias();
    }
    @PostMapping
    public void saveUpdate(@RequestBody Materia materia){
        materiaService.saveOrUpdate(materia);
    }
    @GetMapping("/{materiaId}")
    public Optional<Materia> getById(@PathVariable("materiaId") Long materiaId){
        return materiaService.getMateria(materiaId);
    }
    @DeleteMapping("/{materiaId}")
    public void delete(@PathVariable("materiaId") Long materiaId){
        materiaService.deleteMateria(materiaId);
    }

}
