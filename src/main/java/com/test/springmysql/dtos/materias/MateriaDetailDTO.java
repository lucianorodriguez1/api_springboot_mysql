package com.test.springmysql.dtos.materias;

import java.util.List;

public class MateriaDetailDTO {

    private long id;
    private String nombre;
    private List<MateriaComisionDTO> comisiones;

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


    public List<MateriaComisionDTO> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<MateriaComisionDTO> comisiones) {
        this.comisiones = comisiones;
    }
}
