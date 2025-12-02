package com.test.springmysql.dtos.comisiones;

public class ComisionResumenDTO {
    private long id;
    private String nombre_materia;

    public ComisionResumenDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }
}
