package com.test.springmysql.controllers;

import com.test.springmysql.entities.Materia;
import com.test.springmysql.services.MateriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public ResponseEntity<List<Materia>> getAll() {
        return ResponseEntity.ok(materiaService.getMaterias());
    }

    @PostMapping
    public ResponseEntity<?> saveUpdate(@Valid @RequestBody Materia materia) {
        Materia savedMateria = materiaService.saveOrUpdate(materia);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMateria);
    }

    @GetMapping("/{materiaId}")
    public ResponseEntity<?> getById(@PathVariable("materiaId") Long materiaId) {
        Optional<Materia> materia = materiaService.getMateria(materiaId);
        return materia.isPresent() ? ResponseEntity.ok(materia.get()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "message", "Materia no encontrada",
                "id", materiaId
        ));
    }

    @DeleteMapping("/{materiaId}")
    public ResponseEntity<Void> delete(@PathVariable("materiaId") Long materiaId) {
        materiaService.deleteMateria(materiaId);
        return ResponseEntity.noContent().build();
    }

}
