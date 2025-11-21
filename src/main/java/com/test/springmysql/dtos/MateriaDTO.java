package com.test.springmysql.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class MateriaDTO {

    private long id;
    @NotNull
    @NotEmpty
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String nombre;
    private List<Long> comisionesId = new ArrayList<>();

    public MateriaDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
