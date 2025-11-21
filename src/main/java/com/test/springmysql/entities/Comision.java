package com.test.springmysql.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

@Entity
public class Comision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate fecha_inicio;
    private LocalDate fecha_final;
    private LocalTime hora_inicio;
    private LocalTime hora_final;
    private int alumnos_permitidos;

    @ManyToOne
    @JoinColumn(name="materia_id", referencedColumnName = "id")
    private Materia materia;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="comision_estudiantes",
            joinColumns = @JoinColumn(name = "comision_id"),
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
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(LocalDate fecha_final) {
        this.fecha_final = fecha_final;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_final() {
        return hora_final;
    }

    public void setHora_final(LocalTime hora_final) {
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

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }
}
