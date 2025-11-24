package com.test.springmysql.exceptions;

public class EstudianteYaExisteEnComision extends RuntimeException {
    private long idEstudiante;
    private long idComision;

    public EstudianteYaExisteEnComision(long idEstudiante, long idComision) {
        super(String.format("estudiante con id %d ya existe en la comision con id = '%d' ", idEstudiante, idComision));
        this.idEstudiante = idEstudiante;
        this.idComision = idComision;
    }

}
