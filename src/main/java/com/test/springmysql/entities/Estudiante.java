package com.test.springmysql.entities;

import jakarta.persistence.*;

import java.util.List;


@Entity
//Esta anotacion permite crear la tabla con el nombre que deseemos. No es obligatoria
@Table(name = "Estudiante")


public class Estudiante {

    //Permite crear un id y que se genere de forma automatica
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Se suele utilizar long en IDs y no int porque permite un mayor alcance para valores numericos.
    private long id;

    //Se usa por si quiero cambiar el nombre del atributo, no es obligatorio.
    @Column(name="nombre")
    private String nombre;

    private String cuil;

    @ManyToMany(mappedBy = "estudiantes")
    private List<Comision> comisiones;

    public Estudiante() {
    }

    public String getNombre() {
        return nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public List<Comision> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<Comision> comisiones) {
        this.comisiones = comisiones;
    }
}
