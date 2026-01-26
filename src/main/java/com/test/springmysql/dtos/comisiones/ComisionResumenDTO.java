package com.test.springmysql.dtos.comisiones;

public class ComisionResumenDTO {
    private long id;
    private String nombreMateria;

    public ComisionResumenDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }
}
