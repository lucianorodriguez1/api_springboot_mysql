package com.test.springmysql.repositories;

import com.test.springmysql.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
//Parametros:
//  Materia: la clase entidad a la que vamos acceder.
//  Long: El tipo de data de la clase principal.
//
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    // No es necesario escribir métodos si uso los que ya vienen

}
