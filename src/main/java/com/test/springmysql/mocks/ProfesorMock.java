package com.test.springmysql.mocks;

import com.test.springmysql.entities.Profesor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ProfesorMock {
    public static final String[] NOMBRES = {
            "Nicolas", "Gustavo", "Alejandra", "Leandro", "Federico"
    };

    public static List<Profesor> crearProfesores(int cant){
        List<Profesor> lista = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Profesor p = new Profesor();
            Random random = new Random();
            p.setNombre(NOMBRES[i]);
            p.setAntiguedad_universidad(random.nextInt(10));
            lista.add(p);
        }
        Collections.shuffle(lista);
        return lista;
    }

}
