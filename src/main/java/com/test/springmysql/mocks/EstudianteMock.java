package com.test.springmysql.mocks;

import com.test.springmysql.entities.Estudiante;

import java.util.*;

public class EstudianteMock {

    private static final String[] NOMBRES = {
            "Carlos", "Lucía", "Mariano", "Ana", "Tomás", "Julieta", "Eduardo",
            "Sofía", "Pablo", "Valentina", "Nicolás", "Micaela"
    };

    public static List<Estudiante> crearEstudiantes(int cant){
        List<Estudiante> lista = new ArrayList<>();
        for(int i = 1; i <= cant;i++){
            Random random = new Random();
            Estudiante e = new Estudiante();
            int indiceAleatorio = random.nextInt(NOMBRES.length);
            e.setNombre(NOMBRES[indiceAleatorio]);
            e.setCuil(generarCuilAleatorio());
            lista.add(e);
        }
        Collections.shuffle(lista);
        return lista;
    }
    public static String generarCuilAleatorio() {
        Random random = new Random();

        // 1. Generar los 8 números aleatorios
        // Generamos un número entre 0 y 99,999,999 (10^8 - 1)
        int numAleatorio = random.nextInt(100000000);

        // 2. Formatear el número a 8 dígitos con ceros a la izquierda (ej: 00123456)
        // String.format("%08d", numAleatorio) asegura que siempre sean 8 dígitos
        String numeroStr = String.format("%08d", numAleatorio);

        return "20-" + numeroStr + "-";
    }
}
