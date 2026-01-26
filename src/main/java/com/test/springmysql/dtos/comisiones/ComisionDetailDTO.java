package com.test.springmysql.dtos.comisiones;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ComisionDetailDTO {

    private long id;
    private String materiaNombre;
    @PositiveOrZero
    @NotNull
    private int alumnosPermitidos;
    private List<ComisionEstudianteDTO> estudiantes = new ArrayList<>();
    private List<ComisionProfesorDTO> profesores = new ArrayList<>();
    @NotNull
    private LocalDate fechaInicio;
    @NotNull
    private LocalDate fechaFinal;
    @NotNull
    private LocalTime horaInicio;
    @NotNull
    private LocalTime horaFinal;

    public ComisionDetailDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMateriaNombre() {
        return materiaNombre;
    }

    public void setMateriaNombre(String materiaNombre) {
        this.materiaNombre = materiaNombre;
    }

    public int getAlumnosPermitidos() {
        return alumnosPermitidos;
    }

    public void setAlumnosPermitidos(int alumnosPermitidos) {
        this.alumnosPermitidos = alumnosPermitidos;
    }

    public List<ComisionEstudianteDTO> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<ComisionEstudianteDTO> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public List<ComisionProfesorDTO> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<ComisionProfesorDTO> profesores) {
        this.profesores = profesores;
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
}
