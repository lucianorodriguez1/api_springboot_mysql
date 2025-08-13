package com.test.springmysql.dtos;

import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.entities.Materia;
import com.test.springmysql.entities.Profesor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public class ComisionDTO {
    @NotNull
    @NotEmpty
    private Materia materia;

    @PositiveOrZero
    private int alumnos_permitidos;

    private List<Estudiante> estudiantes;
    private List<Profesor> profesores;

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
}
