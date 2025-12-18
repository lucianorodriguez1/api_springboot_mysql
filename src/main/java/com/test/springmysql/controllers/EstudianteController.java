package com.test.springmysql.controllers;

import com.test.springmysql.dtos.estudiantes.EstudianteListDTO;
import com.test.springmysql.services.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Listar estudiantes", description = "Devuelve una lista paginada de estudiantes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de estudiantes obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<List<EstudianteListDTO>> getAll(@Parameter(description = "Número de página", example = "1")
                                                              @RequestParam(required = false, defaultValue = "1") int pageNr,
                                                          @Parameter(description = "Tamaño de página", example = "10")
                                                              @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                          @Parameter(description = "Campo para ordenar resultados", example = "id")
                                                              @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                          @Parameter(description = "Dirección de ordenamiento", example = "ASC")
                                                              @RequestParam(required = false, defaultValue = "ASC") String sortDir,
                                                          @Parameter(description = "Texto de búsqueda opcional")
                                                              @RequestParam(required = false) String search){
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort= Sort.by(sortBy).ascending();
        }else{
            sort= Sort.by(sortBy).descending();

        }
        return ResponseEntity.ok(estudianteService.getEstudiantes(PageRequest.of(pageNr - 1, pageSize,sort),search));
    }

    @Operation(summary = "Crear estudiante", description = "Crea un nuevo estudiante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Estudiante creado correctamente")
    })
    @PostMapping
    public ResponseEntity<EstudianteListDTO> create(@Parameter(description = "Datos del estudiante a crear", required = true)
                                                        @Valid @RequestBody EstudianteListDTO estudiantedto){
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.createEstudiante(estudiantedto));
    }

    @Operation(summary = "Obtener estudiante por ID", description = "Devuelve un estudiante según su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudiante encontrado"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @GetMapping("/{estudianteId}")
    public ResponseEntity<EstudianteListDTO> getById(@Parameter(description = "ID del estudiante", example = "1", required = true)
                                                         @PathVariable("estudianteId") Long estudianteId){
        return ResponseEntity.ok(estudianteService.getEstudiante(estudianteId));
    }

    @Operation(summary = "Eliminar estudiante", description = "Elimina un estudiante por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estudiante eliminado correctamente")
    })
    @DeleteMapping("/{estudianteId}")
    public void delete(@Parameter(description = "ID del estudiante a eliminar", example = "1", required = true)
                           @PathVariable Long estudianteId){
        estudianteService.deleteEstudiante(estudianteId);
    }

    @Operation(summary = "Actualizar estudiante", description = "Actualiza datos de un estudiante existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudiante actualizado correctamente")
    })
    @PutMapping("/{estudianteId}")
    public ResponseEntity<EstudianteListDTO> update(@Parameter(description = "ID del estudiante a actualizar", example = "1", required = true)
                                                        @PathVariable("estudianteId")Long id,
                                                    @Parameter(description = "Datos nuevos del estudiante", required = true)
                                                        @Valid @RequestBody EstudianteListDTO estudiantedto){
        return ResponseEntity.ok(estudianteService.updateEstudiante(id,estudiantedto));
    }

}
