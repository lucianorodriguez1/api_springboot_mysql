package com.test.springmysql.controllers;

import com.test.springmysql.dtos.ComisionDTO;
import com.test.springmysql.services.ComisionService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<ComisionDTO> create(@Valid @RequestBody ComisionDTO comisiondto){
        return ResponseEntity.status(HttpStatus.CREATED).body(comisionService.createComision(comisiondto));
    }

    @GetMapping("/{comisionId}")
    public ResponseEntity<ComisionDTO> getById(@PathVariable("comisionId") Long id){
        return ResponseEntity.ok(comisionService.getComision(id));
    }

    @DeleteMapping("/{comisionId}")
    public void delete(@PathVariable("comisionId") Long id){
        comisionService.deleteComision(id);
    }

    @PutMapping("/{comisionId}")
    public ResponseEntity<ComisionDTO> update(@PathVariable("comisionId") Long id ,@Valid @RequestBody ComisionDTO comisiondto){
        return ResponseEntity.ok(comisionService.updateComision(id, comisiondto));
    }

    @PostMapping("/{comisionId}/estudiantes/{estudianteId}")
    public ResponseEntity<Void> addStudent(@PathVariable("comisionId") Long cid,@PathVariable("estudianteId") Long eid){
        comisionService.addStudent(cid,eid);
        return ResponseEntity.ok().build();
    }
}
