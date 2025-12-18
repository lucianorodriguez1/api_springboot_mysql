package com.test.springmysql.dtos.estudiantes;

import com.test.springmysql.dtos.comisiones.ComisionResumenDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class EstudianteListDTO {

    @Schema(description = "Identificador único del estudiante", example = "1")
    private long id;

    @NotNull
    @NotEmpty
    @Schema(description = "Nombre completo del estudiante", example = "Juan Pérez", required = true)
    private String nombre;

    @NotNull
    @NotEmpty
    @Schema(description = "CUIL del estudiante", example = "20-12345678-9", required = true)
    private String cuil;

    @Schema(description = "Comisiones asignadas al estudiante")
    private List<ComisionResumenDTO> comisiones = new ArrayList<>();


    public EstudianteListDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getCuil() {
        return cuil;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ComisionResumenDTO> getComisiones() {
        return comisiones;
    }

    public void setComisiones(List<ComisionResumenDTO> comisiones) {
        this.comisiones = comisiones;
    }
}
