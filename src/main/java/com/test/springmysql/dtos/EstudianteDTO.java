package com.test.springmysql.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EstudianteDTO {

    @NotNull
    @NotEmpty
    private String nombre;

    @NotNull
    @NotEmpty
    private String cuil;


    public EstudianteDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getCuil() {
        return cuil;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }
}
