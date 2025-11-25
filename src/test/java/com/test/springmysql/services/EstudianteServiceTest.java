package com.test.springmysql.services;

import com.test.springmysql.dtos.EstudianteDTO;
import com.test.springmysql.entities.Estudiante;
import com.test.springmysql.repositories.EstudianteRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EstudianteServiceTest {

    //este es el simulacro
    @Mock
    private EstudianteRepository estudianteRepository;
    @InjectMocks
    private EstudianteService estudianteService;

    @BeforeEach
    void setup() {
    }

    @DisplayName("Test obtener todos los estudiantes")
    @Test
    public void getEstudiantes() {
        //give
        Estudiante e1 = new Estudiante();
        e1.setNombre("luciano");
        e1.setCuil("23434");
        e1.setComisiones(null);

        Estudiante e2 = new Estudiante();
        e2.setNombre("maria");
        e2.setCuil("53433");
        e2.setComisiones(null);

        Estudiante e3 = new Estudiante();
        e3.setNombre("ana");
        e3.setCuil("23232");
        e3.setComisiones(null);

        when(estudianteRepository.findAll()).thenReturn(List.of(e1, e2, e3));

        //when
        List<EstudianteDTO> result = estudianteService.getEstudiantes();
        //then
        assertNotNull(result);
        assertEquals(3, result.size());

        //assertFalse(result.isEmpty());
        assertEquals("luciano", result.getFirst().getNombre());
        assertEquals("23434", result.getFirst().getCuil());
        assertEquals("ana", result.getLast().getNombre());
        //el verify solo utiliza con la dependencia.
        verify(estudianteRepository, times(1)).findAll();


    }

    @DisplayName("Test obtener un estudiante por su id")
    @Test
    public void getEstudianteById() {
        //give
        Estudiante e1 = new Estudiante();
        e1.setId(1L);
        e1.setNombre("luciano");
        e1.setCuil("23434");
        e1.setComisiones(null);

        //👉 when(...) sirve para decirle al mock qué tiene que devolver cuando se llame a un método.
        //✔ 2. thenReturn(...)Describe el resultado que querés devolver.
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(e1));

        //when
        EstudianteDTO result = estudianteService.getEstudiante(e1.getId());
        //then
        assertNotNull(result);
        assertEquals("luciano", result.getNombre());
        assertEquals("23434", result.getCuil());
        verify(estudianteRepository, times(1)).findById(1L);

    }


    //para entender esto se dice `“si te llaman con cualquier Estudiante, devolvé esto”.`
    @DisplayName("Test para crear un estudiante")
    @Test
    public void createEstudiante(){
        //give
        Estudiante e1 = new Estudiante();
        e1.setId(1L);
        e1.setNombre("luciano");
        e1.setCuil("23434");
        e1.setComisiones(null);

        EstudianteDTO edto = new EstudianteDTO();
        edto.setNombre("luciano");
        edto.setCuil("23434");

        //en este caso se usa esto porque
        // ⭐ Porque el service NO usa tu objeto e1, sino que crea un Estudiante nuevo con el ModelMapper.
        //Entonces:
        //El objeto que realmente llega al save() NO es el e1 del test
        //Es otro Estudiante diferente, creado dentro del service
        //
        //Traducido a castellano:
        //
        //“No importa qué Estudiante me mandes al save()…
        //devolvé siempre este Estudiante e1.”

        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(e1);

        //when
        EstudianteDTO result = estudianteService.createEstudiante(edto);

        //then
        assertNotNull(result);
        assertEquals("luciano", result.getNombre());
        assertEquals("23434", result.getCuil());
        verify(estudianteRepository, times(1)).save(any(Estudiante.class));
    }

    @DisplayName("Test para editar un estudiante")
    @Test
    public void updateEstudiante(){
        //give
        Estudiante e1 = new Estudiante();
        e1.setId(1L);
        e1.setNombre("luciano");
        e1.setCuil("23434");
        e1.setComisiones(null);

        EstudianteDTO edto = new EstudianteDTO();
        edto.setNombre("camilo");
        edto.setCuil("23434");

        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(e1));
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(e1);


        //when
        EstudianteDTO result = estudianteService.updateEstudiante(1L,edto);

        //then
        assertNotNull(result);
        assertEquals("camilo", result.getNombre());
        assertEquals("23434", result.getCuil());
        verify(estudianteRepository, times(1)).save(any(Estudiante.class));
        verify(estudianteRepository, times(1)).findById(1L);
    }

    @DisplayName("Test para eliminar un estudiante")
    @Test
    public void deleteEstudiante(){
        //give
        Estudiante e1 = new Estudiante();
        e1.setId(1L);
        e1.setNombre("luciano");
        e1.setCuil("23434");
        e1.setComisiones(null);

        // simulamos que el estudiante existe
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(e1));

        estudianteService.deleteEstudiante(e1.getId());
        verify(estudianteRepository, times(1)).findById(1L);
        verify(estudianteRepository, times(1)).deleteById(1L);

    }


}
