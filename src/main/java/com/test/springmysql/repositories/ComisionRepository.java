package com.test.springmysql.repositories;

import com.test.springmysql.entities.Comision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComisionRepository extends JpaRepository<Comision,Long> {
}
