package com.test.springmysql.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;

    //CascadeType sirve para decidir que se hace con las clases hijas si la clase padre persiste, se borran, etc.
    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    private List<Comision> comisiones;

    //ES OBLIGATORIO USAR GET, SET Y CONSTRUCTOR VACIO porque JPA los necesita. Spring Boot usa Hibernate para la persistencia de datos.


    public Materia() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Comision> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<Comision> comisiones) {
        this.comisiones = comisiones;
    }
}