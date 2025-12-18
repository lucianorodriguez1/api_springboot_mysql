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
public class EstudianteRepositoryTest {

    @Autowired
    private EstudianteRepository estudianteRepository;
    //private Estudiante e1;

    @BeforeEach
    void setup(){
        estudianteRepository.deleteAll();
    }

    @DisplayName("Test para crear un estudiante")
    @Test
    void testCreateEstudiante(){

        //given --> ESTADO INICIAL Y DATOS DE ENTRADA
        Estudiante e1 = new Estudiante();
        e1.setNombre("luciano");
        e1.setCuil("23434");
        estudianteRepository.save(e1);

        //when --> ya lo cree en el setup.
        Estudiante guardado = estudianteRepository.save(e1);

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

        // GIVEN
        Estudiante e1 = new Estudiante();
        e1.setNombre("laura");
        e1.setCuil("99999");
        estudianteRepository.save(e1);


        Estudiante e2 = new Estudiante();
        e2.setNombre("maria");
        e2.setCuil("8888");
        estudianteRepository.save(e2);

        //WHEN
        List<Estudiante> lista = estudianteRepository.findAll();

        //THEN
        assertThat(lista).isNotNull();
        assertThat(lista).hasSize(2);
        assertThat(lista)
                .extracting(Estudiante::getNombre)
                .containsExactlyInAnyOrder("laura", "maria");
    }



    @DisplayName("Test para encontrar un estudiante por su Id")
    @Test
    void testGetEstudianteById() {

        //GIVEN
        Estudiante e1 = new Estudiante();
        e1.setNombre("luciano");
        e1.setCuil("23434");
        estudianteRepository.save(e1);

        //WHEN
        Estudiante buscado = estudianteRepository.findById(e1.getId())
                .orElseThrow();

        //THEN
        assertThat(buscado).isNotNull();
        assertThat(buscado.getId()).isNotNull();
        assertThat(buscado.getId()).isGreaterThan(0);
        assertThat(buscado.getNombre()).isEqualTo("luciano");
        assertThat(buscado.getCuil()).isEqualTo("23434");
    }

    @DisplayName("Test para actualizar un estudiante por su Id")
    @Test
    void testUpdateEstudianteById(){

        //GIVEN
        Estudiante e1 = new Estudiante();
        e1.setNombre("luciano");
        e1.setCuil("23434");
        estudianteRepository.save(e1);

        //WHEN
        Estudiante buscado = estudianteRepository.findById(e1.getId())
                .orElseThrow();

        buscado.setNombre("update");
        Estudiante actualizado = estudianteRepository.save(buscado);

        //THEN
        assertThat(actualizado.getNombre()).isEqualTo("update");
        assertThat(actualizado.getCuil()).isEqualTo("23434");
    }

    @DisplayName("Test para borrar un estudiante por su Id")
    @Test
    void testDeleteEstudianteById(){
        //GIVEN
        Estudiante e1 = new Estudiante();
        e1.setNombre("luciano");
        e1.setCuil("23434");
        estudianteRepository.save(e1);

        //WHEN
        estudianteRepository.deleteById(e1.getId());

        //THEN
        assertThat(estudianteRepository.findById(e1.getId())).isEmpty();
        assertThat(estudianteRepository.existsById(e1.getId())).isFalse();


    }

}
