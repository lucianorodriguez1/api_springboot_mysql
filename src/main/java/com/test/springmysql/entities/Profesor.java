package com.test.springmysql.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private int antiguedad_universidad;

    @ManyToMany(mappedBy = "profesores")
    private List<Comision> comisiones;
}
