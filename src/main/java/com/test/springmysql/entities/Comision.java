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

    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private int alumnosPermitidos;

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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getAlumnosPermitidos() {
        return alumnosPermitidos;
    }

    public void setAlumnosPermitidos(int alumnosPermitidos) {
        this.alumnosPermitidos = alumnosPermitidos;
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
