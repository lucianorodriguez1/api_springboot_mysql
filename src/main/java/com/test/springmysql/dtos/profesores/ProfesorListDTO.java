package com.test.springmysql.dtos.profesores;

import com.test.springmysql.dtos.comisiones.ComisionResumenDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.ArrayList;
import java.util.List;

public class ProfesorListDTO {

    private long id;
    @NotEmpty
    @NotNull
    private String nombre;
    @PositiveOrZero
    private int antiguedadUniversidad;
    private List<ComisionResumenDTO> comisiones = new ArrayList<>();

    public ProfesorListDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAntiguedadUniversidad() {
        return antiguedadUniversidad;
    }

    public void setAntiguedadUniversidad(int antiguedadUniversidad) {
        this.antiguedadUniversidad = antiguedadUniversidad;
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
