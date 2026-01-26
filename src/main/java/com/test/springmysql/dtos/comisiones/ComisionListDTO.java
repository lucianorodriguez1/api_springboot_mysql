package com.test.springmysql.dtos.comisiones;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ComisionListDTO {

    private long id;
    private Long materiaId;
    private String materiaNombre;
    @PositiveOrZero
    @NotNull
    private int alumnosPermitidos;
    private int cant_estudiantes;
    //private List<Long> estudiantesId = new ArrayList<>();
    private List<Long> profesoresId = new ArrayList<>();
    @NotNull
    private LocalDate fechaInicio;
    @NotNull
    private LocalDate fechaFinal;
    @NotNull
    private LocalTime horaInicio;
    @NotNull
    private LocalTime horaFinal;

    public ComisionListDTO() {
    }

    public int getAlumnosPermitidos() {
        return alumnosPermitidos;
    }
    public void setAlumnosPermitidos(int alumnosPermitidos) {
        this.alumnosPermitidos = alumnosPermitidos;
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
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Long getMateriaId() {
        return materiaId;
    }
    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }
    /*
    public List<Long> getEstudiantesId() {
        return estudiantesId;
    }
    public void setEstudiantesId(List<Long> estudiantesId) {
        this.estudiantesId = estudiantesId;
    }
     */
    public List<Long> getProfesoresId() {
        return profesoresId;
    }
    public void setProfesoresId(List<Long> profesoresId) {
        this.profesoresId = profesoresId;
    }

    public int getCant_estudiantes() {
        return cant_estudiantes;
    }

    public void setCant_estudiantes(int cant_estudiantes) {
        this.cant_estudiantes = cant_estudiantes;
    }

    public String getMateriaNombre() {
        return materiaNombre;
    }

    public void setMateriaNombre(String materiaNombre) {
        this.materiaNombre = materiaNombre;
    }
}
