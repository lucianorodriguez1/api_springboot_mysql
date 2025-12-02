package com.test.springmysql.dtos.materias;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MateriaComisionDTO {
    private long id;
    private int alumnos_permitidos;
    private List<Long> profesoresId = new ArrayList<>();
    @NotNull
    private LocalDate fecha_inicio;
    @NotNull
    private LocalDate fecha_final;
    @NotNull
    private LocalTime hora_inicio;
    @NotNull
    private LocalTime hora_final;

    public MateriaComisionDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAlumnos_permitidos() {
        return alumnos_permitidos;
    }

    public void setAlumnos_permitidos(int alumnos_permitidos) {
        this.alumnos_permitidos = alumnos_permitidos;
    }

    public List<Long> getProfesoresId() {
        return profesoresId;
    }

    public void setProfesoresId(List<Long> profesoresId) {
        this.profesoresId = profesoresId;
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
