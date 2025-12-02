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
    private int alumnos_permitidos;
    private int cant_estudiantes;
    //private List<Long> estudiantesId = new ArrayList<>();
    private List<Long> profesoresId = new ArrayList<>();
    @NotNull
    private LocalDate fecha_inicio;
    @NotNull
    private LocalDate fecha_final;
    @NotNull
    private LocalTime hora_inicio;
    @NotNull
    private LocalTime hora_final;

    public ComisionListDTO() {
    }

    public int getAlumnos_permitidos() {
        return alumnos_permitidos;
    }
    public void setAlumnos_permitidos(int alumnos_permitidos) {
        this.alumnos_permitidos = alumnos_permitidos;
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
