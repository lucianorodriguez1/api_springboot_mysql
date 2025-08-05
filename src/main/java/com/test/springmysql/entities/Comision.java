package com.test.springmysql.entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
public class Comision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private Date fecha_inicio;
    private Date fecha_final;
    private Time hora_inicio;
    private Time hora_final;
    private int alumnos_permitidos;

    @ManyToOne
    @JoinColumn(name="materia_id", referencedColumnName = "id")
    private Materia materia;

    @ManyToMany
    @JoinTable(
            name="comision_estudiantes",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name="estudiante_id")
    )

    private List<Estudiante> estudiantes;

    @ManyToMany
    @JoinTable(
            name="comision_profesores",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name="profesor_id")
    )
    private List<Profesor> profesores;


    public Comision() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_final() {
        return hora_final;
    }

    public void setHora_final(Time hora_final) {
        this.hora_final = hora_final;
    }

    public int getAlumnos_permitidos() {
        return alumnos_permitidos;
    }

    public void setAlumnos_permitidos(int alumnos_permitidos) {
        this.alumnos_permitidos = alumnos_permitidos;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
