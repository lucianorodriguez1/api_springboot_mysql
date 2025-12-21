package com.test.springmysql.repositories;

import com.test.springmysql.entities.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {

    // sirve para usar con la clase TestDataLoader de estudiante.
    List<Estudiante> findByCuilStartingWith(String prefix);

    //este metodo sirve para paginacion
    Page<Estudiante> findByNombre(String name, Pageable pageable);

}
