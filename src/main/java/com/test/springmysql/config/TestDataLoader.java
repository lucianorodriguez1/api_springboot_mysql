package com.test.springmysql.config;

import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.repositories.EstudianteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//@Profile("test") // Solo se ejecuta en el perfil "test"
@Configuration
public class TestDataLoader {

    private static final String[] NOMBRES = {
            "Carlos", "Lucía", "Mariano", "Ana", "Tomás", "Julieta", "Eduardo",
            "Sofía", "Pablo", "Valentina", "Nicolás", "Micaela"
    };

    private static final String[] APELLIDOS = {
            "Gómez", "Pérez", "Rodríguez", "Fernández", "López",
            "Martínez", "García", "Díaz", "Álvarez", "Suárez", "Romero"
    };

    @Bean
    CommandLineRunner loadTestData(EstudianteRepository repo) {
        return args -> {

            // borrar solo estudiantes de test
            List<Estudiante> testExisting = repo.findByCuilStartingWith("TEST-");
            if (!testExisting.isEmpty()) {
                repo.deleteAll(testExisting);
            }

            Random random = new Random();

            List<Estudiante> nuevos = new ArrayList<>();

            for (int i = 0; i < 200; i++) {
                Estudiante e = new Estudiante();
                e.setNombre(NOMBRES[random.nextInt(NOMBRES.length)]);
                e.setCuil("TEST-" + (100000 + random.nextInt(900000)));

                nuevos.add(e);
            }

            // DESORDENAR ANTES DE INSERTAR
            Collections.shuffle(nuevos);

            repo.saveAll(nuevos);

            System.out.println(">>> Cargados 200 estudiantes de prueba desordenados.");
        };
    }
}
