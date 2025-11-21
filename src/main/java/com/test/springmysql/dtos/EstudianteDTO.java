package com.test.springmysql.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EstudianteDTO {

    private long id;

    @NotNull
    @NotEmpty
    private String nombre;

    @NotNull
    @NotEmpty
    private String cuil;

    private List<Long> comisionesId = new ArrayList<>();

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getComisionesId() {
        return comisionesId;
    }

    public void setComisionesId(List<Long> comisionesId) {
        this.comisionesId = comisionesId;
    }
}
