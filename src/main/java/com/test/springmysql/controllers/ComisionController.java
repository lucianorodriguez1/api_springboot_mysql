package com.test.springmysql.controllers;

import com.test.springmysql.dtos.ComisionDTO;
import com.test.springmysql.entities.Comision;
import com.test.springmysql.services.ComisionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ComisionDTO>> getAll(){
        return ResponseEntity.ok(comisionService.getComisiones());
    }

    @PostMapping
    public ResponseEntity<ComisionDTO> create(@Valid @RequestBody Comision comision){
        return ResponseEntity.status(HttpStatus.CREATED).body(comisionService.createComision(comision));
    }

    @GetMapping("/{comisionId}")
    public ResponseEntity<Optional<ComisionDTO>> getById(@PathVariable("comisionId") Long id){
        return ResponseEntity.ok(comisionService.getComision(id));
    }

    @DeleteMapping("/{comisionId}")
    public void delete(@PathVariable("comisionId") Long id){
        comisionService.deleteComision(id);
    }
    @PutMapping("/{comisionId}")
    public ResponseEntity<ComisionDTO> update(@PathVariable("comisionId") Long id ,@Valid @RequestBody Comision comision){
        return ResponseEntity.ok(comisionService.updateComision(id, comision));
    }


}
