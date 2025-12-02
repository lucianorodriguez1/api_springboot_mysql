package com.test.springmysql.dtos.estudiantes;

import com.test.springmysql.dtos.comisiones.ComisionResumenDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EstudianteListDTO {

    private long id;

    @NotNull
    @NotEmpty
    private String nombre;

    @NotNull
    @NotEmpty
    private String cuil;

    private List<ComisionResumenDTO> comisiones = new ArrayList<>();

    public EstudianteListDTO() {
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

    public List<ComisionResumenDTO> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<ComisionResumenDTO> comisiones) {
        this.comisiones = comisiones;
    }
}
