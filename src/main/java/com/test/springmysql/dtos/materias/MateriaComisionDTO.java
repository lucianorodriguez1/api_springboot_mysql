package com.test.springmysql.dtos.materias;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MateriaComisionDTO {
    private long id;
    private int alumnosPermitidos;
    private List<Long> profesoresId = new ArrayList<>();
    @NotNull
    private LocalDate fechaInicio;
    @NotNull
    private LocalDate fechaFinal;
    @NotNull
    private LocalTime horaInicio;
    @NotNull
    private LocalTime horaFinal;

    public MateriaComisionDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAlumnosPermitidos() {
        return alumnosPermitidos;
    }

    public void setAlumnosPermitidos(int alumnosPermitidos) {
        this.alumnosPermitidos = alumnosPermitidos;
    }

    public List<Long> getProfesoresId() {
        return profesoresId;
    }

    public void setProfesoresId(List<Long> profesoresId) {
        this.profesoresId = profesoresId;
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
