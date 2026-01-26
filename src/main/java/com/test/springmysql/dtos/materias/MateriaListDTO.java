package com.test.springmysql.dtos.materias;

import java.util.ArrayList;
import java.util.List;

public class MateriaListDTO {

    private long id;
    private String nombre;
    private int cantComisiones;

    public MateriaListDTO() {
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

    public int getCantComisiones() {
        return cantComisiones;
    }

    public void setCantComisiones(int cantComisiones) {
        this.cantComisiones = cantComisiones;
    }
}
