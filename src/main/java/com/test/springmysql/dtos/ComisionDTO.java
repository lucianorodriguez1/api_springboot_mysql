package com.test.springmysql.dtos;

import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.entities.Materia;
import com.test.springmysql.entities.Profesor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ComisionDTO {
    @NotNull
    @NotEmpty
    private Materia materia;

    @PositiveOrZero
    private int alumnos_permitidos;

    private List<Estudiante> estudiantes;
    private List<Profesor> profesores;
    private LocalDate fecha_inicio;
    private LocalDate fecha_final;
    private LocalTime hora_inicio;
    private LocalTime hora_final;

    public ComisionDTO() {
    }

    public Materia getMateria() {
        return materia;
    }

    public int getAlumnos_permitidos() {
        return alumnos_permitidos;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public void setAlumnos_permitidos(int alumnos_permitidos) {
        this.alumnos_permitidos = alumnos_permitidos;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
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
}
