
# TEST

## JUNIT
- Usar el directorio `src/test/java` para los tests.
- La estructura de paquetes dentro de `test` debería reflejar la de `main`
  (por ejemplo: `com.test.springmysql.service` en `main` → mismo paquete en `test`).
- En un test unitario siempre comparamos:
    - **valor esperado** (lo que debería pasar)
    - **valor actual** (lo que devuelve el método)
- JUnit ejecuta el método anotado con `@Test` y marca el test como:
    - ✅ **OK** si no se lanza ninguna excepción y todas las aserciones pasan
    - ❌ **FAIL** si una aserción falla o se lanza una excepción inesperada
- Estructura recomendada (patrón **Given / When / Then**):
    - **Given** → preparo datos, mocks, estado inicial
    - **When** → ejecuto el método a testear
    - **Then** → verifico el resultado con asserts / verify
- Los métodos de test suelen ser `void` y siempre están anotados con `@Test`.
- Se puede usar Jacoco (plugin) para medir **cobertura de código** en tests.
    - En proyectos profesionales, la cobertura suele apuntar a un 80–90% aprox.
- Para probar casos de error se suele crear **otro método de test**, por ejemplo:
    - `crearEstudiante_ok()`
    - `crearEstudiante_errorYaExiste()`
- En tests de error, muchas veces el **When** es el propio `assertThrows(...)`.
- `@BeforeEach` permite ejecutar código **antes de cada test**
  (por ejemplo, inicializar objetos comunes).
- Para poner una descripción legible al test se usa `@DisplayName` encima de `@Test`.

## ✔ EL ORDEN PROFESIONAL PARA PRACTICAR TESTS
Orden sugerido para aprender de forma progresiva:

1. **Repository** con `@DataJpaTest` (más adelante, usando H2)
2. **Service** (lo más importante) Lógica de negocio y validaciones (por ejemplo: “ya existe estudiante en la comisión”)
3. **Excepciones personalizadas** (validar que se lancen cuando corresponde)
4. **Controller** con `@WebMvcTest` (tests de capa web, más adelante)

## TEST DATAJPATEST
Sirve para probar componentes de la entidad de persistencias. Clases con la anotacion Entity y a los repositorios.

. Instalar la dependencia

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>test</scope>
</dependency>

```
Con eso:

* @DataJpaTest va a levantar una BD H2 en memoria solo para los tests.
* No usa tu MySQL real.
* No necesitas configurar nada más, Spring Boot ya lo sabe hacer.

SI se quiere hacer con la base de datyos origen (emn este caso mysql se copia lo sgte).
```java
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EstudianteRepositoryTest {
    // ...
}
```

Codigo testeando el repositorio de estudiante:
```java
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

```


## TIPOS DE ASSERTIONS
* assertEquals(vEsperado,vActual) : sirve para evaluar un valor esperado con el valor actual.
* assertTrue() : valida que tenga un verdadero
* assertFalse()
* assertNotNull() : valida que el objeto no sea nulo
* assertInstanceOf(vEsperado,vActual) : valida el tipo de objeto que tengo
* assertThrow() : valida excepciones.


## MOCKITO
* Agregamos la dependencia mockito : **mockito-junit-jupiter**
* Mockito es simular objetos y trabaja con las dependencia y no la clase que estoy testeando.
* Arriba de la **clase** a testear ponemos `@ExtendWith(MockitoExtension.class)`,
  `@Mock` en la clase dependencia a simular y `@InjectMocks` en la clase a testear.
* HAY VARIAS FORMAS DE INSERTAR MOCKITO. Anteriormente menciona una de esas formas.
* `verify` se usa con la dependencia.
* `ArgumentCaptur<Dato>` sirve para capturar el objeto y validarlo.

*Repositorio de ejemplo* : http://github.com/hamvocke/spring-testing/blob/main/src/test/java/example/ExampleControllerAPITest.java
*Video mostrando* test de controlador: https://www.youtube.com/watch?v=9-mX5MACs5U
*Repositorio de test de controlador*: http://github.com/sharathbabugv/tutorial-learn/blob/master/src/test/java/com/codestorm/learn/junit_one/TestControllerOneTest.java

```java
package com.test.springmysql.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.springmysql.dto.MateriaDTO;
import com.test.springmysql.exceptions.RecursoNoEncontrado;
import com.test.springmysql.service.MateriaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MateriaController.class)
class MateriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MateriaService materiaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/v1/materias → creado con éxito")
    void createMateria_success() throws Exception {
        MateriaDTO dto = new MateriaDTO();
        dto.setNombre("Matemática");

        when(materiaService.createMateria(ArgumentMatchers.any(MateriaDTO.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/api/v1/materias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Matemática"));
    }

    @Test
    @DisplayName("POST /api/v1/materias → error de validación nombre vacío")
    void createMateria_validationError() throws Exception {
        MateriaDTO dto = new MateriaDTO();
        dto.setNombre("");  // nombre inválido

        mockMvc.perform(post("/api/v1/materias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /api/v1/materias/{id} → éxito")
    void getMateria_success() throws Exception {
        Long id = 1L;
        MateriaDTO dto = new MateriaDTO();
        dto.setNombre("Historia");

        when(materiaService.getMateria(id)).thenReturn(dto);

        mockMvc.perform(get("/api/v1/materias/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Historia"));
    }

    @Test
    @DisplayName("GET /api/v1/materias/{id} → no encontrada")
    void getMateria_notFound() throws Exception {
        Long id = 99L;

        when(materiaService.getMateria(id))
                .thenThrow(new RecursoNoEncontrado("materia", "id", id));

        mockMvc.perform(get("/api/v1/materias/{id}", id)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/v1/materias/{id} → sin contenido")
    void deleteMateria_success() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/v1/materias/{id}", id))
                .andExpect(status().isNoContent());
    }
}

```
---

### Para ver el reporte de los tests :
1. Agregar ``@Disabled`` en la clase de ``SpringmysqlApplicationTests`` (Con eso, Maven ya no intenta levantar el contexto y tus otros tests sí van a correr y generar reporte.)
2. Voy al maven
3. Hago click en la opcion que tiene el nombre del proyecto.
4. Hago click en clean
5. Hago click en test y se genera el reporte de los test
6. Hago click en site
7. Luego voy a la carpeta /target/site/index.html

### Arreglar error de java machine que aprece en consola
**PROXIMAMENTE**


## TEST DE CONTROLLER
No se usa mas **@MockBean** (esta deprecado). Ahora usamos **@MockitoBean** es la ANOTACIÓN NUEVA y OFICIAL que:
* Se integra con el ApplicationContext del test.
* Registra el mock como un bean REAL dentro del contexto.
* Reemplaza beans existentes (igual que hacía @MockBean).
* Evita tener que escribir un archivo @Configuration con mocks.

MockMvc NO acepta objetos Java directamente.
* Por eso usamos la linea **objectMapper.writeValueAsString(request)**