package com.test.springmysql.dtos;

import java.util.List;

public class MateriaDetailDTO {

    private long id;
    private String nombre;
    private List<ComisionDTO> comisiones;

    public MateriaDetailDTO() {
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


    public List<ComisionDTO> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<ComisionDTO> comisiones) {
        this.comisiones = comisiones;
    }
}
