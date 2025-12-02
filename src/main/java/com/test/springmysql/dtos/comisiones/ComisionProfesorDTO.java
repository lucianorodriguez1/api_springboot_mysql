package com.test.springmysql.dtos.comisiones;

public class ComisionProfesorDTO {
    private long id;
    private String nombre;

    public ComisionProfesorDTO() {
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
}
