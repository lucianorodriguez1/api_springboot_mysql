# Paginacion
La paginacion la use en **Estudiante** (Metodo GET ALL).

A continuación se mostrará un ejemplo completo:

### Ejemplo del repositorio
```java
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {

    // sirve para usar con la clase TestDataLoader de estudiante.
    List<Estudiante> findByCuilStartingWith(String prefix);
    
    //este metodo sirve para paginacion
    Page<Estudiante> findByNombre(String name, Pageable pageable);

}

```

### Ejemplo del servicio
```java
public List<EstudianteListDTO> getEstudiantes(Pageable pageable, String search) {
    if(search != null){
        List<Estudiante> lista =  estudianteRepository.findByNombre(search, pageable).getContent();
        return lista.stream().map(estudiante -> {
            EstudianteListDTO e = new EstudianteListDTO();
            e.setId(estudiante.getId());
            e.setNombre(estudiante.getNombre());
            e.setCuil(estudiante.getCuil());
            e.setComisiones(estudiante.getComisiones().stream().map(comision -> {
                ComisionResumenDTO ec = new ComisionResumenDTO();
                ec.setId(comision.getId());
                ec.setNombre_materia(comision.getMateria().getNombre());
                return ec;
            }).toList());

            return e;
        }).toList();

    }else {
        return estudianteRepository.findAll(pageable).getContent()
                .stream()
                .map(estudiante -> {
                    EstudianteListDTO e = new EstudianteListDTO();
                    e.setId(estudiante.getId());
                    e.setNombre(estudiante.getNombre());
                    e.setCuil(estudiante.getCuil());
                    e.setComisiones(estudiante.getComisiones().stream().map(comision -> {
                        ComisionResumenDTO ec = new ComisionResumenDTO();
                        ec.setId(comision.getId());
                        ec.setNombre_materia(comision.getMateria().getNombre());
                        return ec;
                    }).toList());

                    return e;
                }).toList();
    }
}
```

### Ejemplo del controlador

```java
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

```

