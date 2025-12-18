package com.test.springmysql.config;

import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.mocks.EstudianteMock;
import com.test.springmysql.repositories.EstudianteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//@Profile("test") // Solo se ejecuta en el perfil "test"
//El tag @Configuration ot sirve para indicar que una clase Java esuna fuente de definiciones de beans,
//  permitiendo definir y configurar objetos personalizados que Spring debe gestionar en su contenedor,
//  como servicios, repositorios o conexiones a bases de datos
@Configuration
public class InicializarDatos {


    private static final String[] APELLIDOS = {
            "Gómez", "Pérez", "Rodríguez", "Fernández", "López",
            "Martínez", "García", "Díaz", "Álvarez", "Suárez", "Romero"
    };

    @Bean
    CommandLineRunner loadTestData(EstudianteRepository estRepo) {
        return args -> {
            if(estRepo.count() > 0 ) return;
            List<Estudiante> e = EstudianteMock.crearEstudiantes(50);
            estRepo.saveAll(e);
        };
    }
}