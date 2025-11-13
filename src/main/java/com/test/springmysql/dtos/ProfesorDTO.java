package com.test.springmysql.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ProfesorDTO {
    private long id;
    @NotEmpty
    @NotNull
    private String nombre;

    @PositiveOrZero
    private int antiguedad_universidad;


    public ProfesorDTO() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
