package com.test.springmysql.controllers;

import com.test.springmysql.entities.Comision;
import com.test.springmysql.services.ComisionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/comisiones")
public class ComisionController {
    private final ComisionService comisionService;

    public ComisionController(ComisionService comisionService) {
        this.comisionService = comisionService;
    }
    @GetMapping
    public List<Comision> getAll(){
        return comisionService.getComisiones();
    }

    @PostMapping
    public void saveUpdate(@RequestBody Comision comision){
        comisionService.saveOrUpdate(comision);
    }

    @GetMapping("/{comisionId}")
    public Optional<Comision> getById(@PathVariable("comisionId") Long id){
        return comisionService.getComision(id);
    }

    @DeleteMapping("/{comisionId}")
    public void delete(@PathVariable("comisionId") Long id){
        comisionService.deleteComision(id);
    }
}
