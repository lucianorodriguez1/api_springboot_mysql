package com.test.springmysql.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontrado extends RuntimeException{
    private String nombreRecurso;
    private String nombreCampo;
    private Object valorCampo;

    public RecursoNoEncontrado(String nombreRecurso, String nombreCampo, Object valorCampo) {
        super(String.format("%s no fue encontrado con %s = '%s' ",nombreRecurso,nombreCampo,valorCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }
}
