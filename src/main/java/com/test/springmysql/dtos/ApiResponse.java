package com.test.springmysql.dtos;

import java.util.Date;
import java.util.Objects;

public class ApiResponse {
    private Date fecha = new Date();
    private String mensaje;
    private String url;

    public ApiResponse(String mensaje, String url) {
        this.mensaje = mensaje;
        this.url = url.replace("uri=","");
    }

    public ApiResponse() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "fecha=" + fecha +
                ", mensaje='" + mensaje + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ApiResponse that = (ApiResponse) o;
        return Objects.equals(fecha, that.fecha) && Objects.equals(mensaje, that.mensaje) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, mensaje, url);
    }
}
