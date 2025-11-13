package com.test.springmysql.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Estudiante")

public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="nombre")
    private String nombre;

    private String cuil;

    @ManyToMany(mappedBy = "estudiantes")
    private List<Comision> comisiones;

    public Estudiante() {
    }

    public String getNombre() {
        return nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public List<Comision> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<Comision> comisiones) {
        this.comisiones = comisiones;
    }
}
