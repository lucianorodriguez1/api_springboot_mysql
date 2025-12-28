package com.test.springmysql.mocks;

import com.test.springmysql.entities.Materia;

import java.util.ArrayList;
import java.util.List;

public class MateriaMock {
    public static final String[] NOMBRES = {
            "Matematica", "Objetos", "Programacion", "Sistemas Operativos",
            "Ingenieria software", "Base de datos", "Redes"
    };
    public static List<Materia> crearMaterias(){
        List<Materia> lista = new ArrayList<>();

        for(int i = 0; i < NOMBRES.length; i++){
            Materia m = new Materia();
            m.setNombre(NOMBRES[i]);
            lista.add(m);
        }
        return lista;
    }

}
