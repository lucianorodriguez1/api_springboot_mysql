package com.test.springmysql.controllers;

import com.test.springmysql.dtos.estudiantes.EstudianteListDTO;
import com.test.springmysql.services.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<List<EstudianteListDTO>> getAll(@RequestParam(required = false,defaultValue = "1") int pageNr,
                                                          @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                          @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                          @RequestParam(required = false, defaultValue = "ASC") String sortDir,
                                                          @RequestParam(required = false) String search){
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort= Sort.by(sortBy).ascending();
        }else{
            sort= Sort.by(sortBy).descending();

        }
        return ResponseEntity.ok(estudianteService.getEstudiantes(PageRequest.of(pageNr - 1, pageSize,sort),search));
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
