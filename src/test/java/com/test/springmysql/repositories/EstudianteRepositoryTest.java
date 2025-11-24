package com.test.springmysql.repositories;

import com.test.springmysql.entities.Estudiante;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EstudianteRepositoryTest {

    @Autowired
    private EstudianteRepository estudianteRepository;
    private Estudiante e1;

    @BeforeEach
    void setup(){
        estudianteRepository.deleteAll();
        e1 = new Estudiante();
        e1.setNombre("luciano");
        e1.setCuil("23434");
        e1.setComisiones(null);

        estudianteRepository.save(e1);
    }

    @DisplayName("Test para crear un estudiante")
    @Test
    void testCreateEstudiante(){
        //given --> ya tengo el estudiante

        //when --> ya lo cree en el setup

        //then
        assertThat(e1).isNotNull();
        assertThat(e1.getId()).isNotNull();
        assertThat(e1.getId()).isGreaterThan(0);
        assertThat(e1.getNombre()).isEqualTo("luciano");
        assertThat(e1.getCuil()).isEqualTo("23434");
    }

    @DisplayName("Test para listar los estudiantes")
    @Test
    void testGetEstudiantes(){

        // given
        Estudiante e2 = new Estudiante();
        e2.setNombre("maria");
        e2.setCuil("99999");
        e2.setComisiones(null);

        estudianteRepository.save(e2);

        List<Estudiante> lista = estudianteRepository.findAll();
        assertThat(lista).isNotNull();
        assertThat(lista).hasSize(2);  // porque yo guardé 2
        assertThat(lista)
                .extracting(Estudiante::getNombre)
                .containsExactlyInAnyOrder("luciano", "maria");
    }

    @DisplayName("Test para encontrar un estudiante por su Id")
    @Test
    void testGetEstudianteById(){
        Estudiante e = estudianteRepository.findById(e1.getId())
                .orElseThrow();

        assertThat(e).isNotNull();
        assertThat(e.getId()).isNotNull();
        assertThat(e.getId()).isGreaterThan(0);
        assertThat(e.getNombre()).isEqualTo("luciano");
        assertThat(e.getCuil()).isEqualTo("23434");

    }

    @DisplayName("Test para actualizar un estudiante por su Id")
    @Test
    void testUpdateEstudianteById(){
        Estudiante e = estudianteRepository.findById(e1.getId())
                .orElseThrow();

        e.setNombre("update");
        Estudiante actualizado = estudianteRepository.save(e);
        assertThat(actualizado.getNombre()).isEqualTo("update");
        assertThat(actualizado.getCuil()).isEqualTo("23434");

    }

    @DisplayName("Test para borrar un estudiante por su Id")
    @Test
    void testDeleteEstudianteById(){


        estudianteRepository.deleteById(e1.getId());

        assertThat(estudianteRepository.findById(e1.getId())).isEmpty();
        assertThat(estudianteRepository.existsById(e1.getId())).isFalse();


    }



}
