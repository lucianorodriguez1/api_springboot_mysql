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

    public Profesor() {
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

    public int getAntiguedad_universidad() {
        return antiguedad_universidad;
    }

    public void setAntiguedad_universidad(int antiguedad_universidad) {
        this.antiguedad_universidad = antiguedad_universidad;
    }

    public List<Comision> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<Comision> comisiones) {
        this.comisiones = comisiones;
    }
}
