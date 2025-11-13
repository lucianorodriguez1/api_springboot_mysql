package com.test.springmysql.controllers;

import com.test.springmysql.dtos.MateriaDTO;
import com.test.springmysql.services.MateriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path = "api/v1/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> getAll() {
        return ResponseEntity.ok(materiaService.getMaterias());
    }

    @GetMapping("/{materiaId}")
    public ResponseEntity<?> getById(@PathVariable("materiaId") Long materiaId) {
        MateriaDTO materia = materiaService.getMateria(materiaId);
        return  ResponseEntity.ok(materia);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody MateriaDTO materiadto) {
        MateriaDTO savedMateria = materiaService.createMateria(materiadto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMateria);
    }

    @DeleteMapping("/{materiaId}")
    public ResponseEntity<Void> delete(@PathVariable("materiaId") Long materiaId) {
        materiaService.deleteMateria(materiaId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{materiaId}")
    public ResponseEntity<?> update(@PathVariable("materiaId") Long id, @Valid @RequestBody MateriaDTO materiadto){
        MateriaDTO upd = materiaService.updateMateria(id,materiadto);
        return ResponseEntity.ok(upd);
    }
}
