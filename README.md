# 🎓 Sistema estudiantil con Spring Boot y MySQL

Proyecto CRUD que implementa un **sistema estudiantil** usando **Spring Boot**, **Spring Data JPA**, **MySQL** y validaciones con **DTOs**.
Tiene como objetivo documentar buenas prácticas de estructura, manejo de errores, capas y uso de herramientas modernas.

⚠️ **Aclaración**: No se usa **Lombok**.

---

## 📊 Diagrama Entidad Relación (DER)
<img width="734" height="446" alt="Image" src="https://github.com/user-attachments/assets/06420a0a-91bb-40f0-8a7e-ea873b0319a0" /> 

---

## 📦 Dependencias
* **spring-boot-starter-data-jpa** (ORM y repositorios)
* **spring-boot-starter-web** (Spring Web, API REST)
* **mysql-connector-j** (Desde el instalador se puede agregar MySQL Driver, Driver MySQL)
* **spring-boot-starter-test** (Testing (JUnit + Spring Test))
* **spring-boot-starter-validation** (agregar validaciones a los atributos de las clases, Validaciones con Bean Validation)
* **modelmapper** (Conversión entre Entity y DTO)
---

## ⚙️ Variables de entorno
- `DB_URL` → url de la base de datos
- `DB_USERNAME` → usuario de la base de datos
- `DB_PASSWORD` → contraseña de la base de datos

Ejemplo (`application.properties`):

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD} 
spring.application.name=springmysql
#Visualizar las consultas
spring.jpa.show-sql=true
#Crear/actualiza las tablas de la base de datos. Puede ser update o create.
spring.jpa.hibernate.ddl-auto=update
```

## 🚀 Pasos para ejecutar
1. Agregar las variables de la base de datos en `application.properties`.
2. Tener la base creada en MySQL.
3. Configurar el **RUN** para ejecutar siempre el archivo `Main`.
   > IntelliJ IDEA → `Edit Configuration -> Application -> Main Class -> Seleccionar archivo main`.
4. Crear entidades en la carpeta `entities` con atributos, relaciones y anotaciones.
5. Crear las carpetas `dto`, `services` y `controllers`.
6. Crear las excepciones y  un DTO `ApiResponse` para devolver las respuestas en ese formato cuando pasa una excepcion.

---

## 📚 Material de referencia
- 🎥 Video YouTube: [Spring Boot + MySQL + ModelMapper](https://www.youtube.com/watch?v=9XoaU5IMkRY&t=457s)
- 📖 Documentación Jakarta: [Persistencia](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-intro/persistence-intro.html)
- ✍️ Anotaciones de relaciones en Spring: [Mastering Database Relationship Annotations](https://medium.com/devdomain/mastering-spring-database-relationship-annotations-161cb8232619)
- 📑 Documentación de `@Column`: [Jakarta Persistence API](https://jakarta.ee/specifications/persistence/2.2/apidocs/javax/persistence/column)

---
## 🧱 Arquitectura del proyecto

``` 
.
├── controllers/
    ├── MateriaController.java

├── dtos/
    ├── ApiResponse.java
    └── Materia.java
   
├── entities/
    └── Materia.java
├── exceptions/
    └── HandlerException.java
├── repositories/
    └── MateriaRepository.java
└── services/
    ├── MateriaService.java

``` 
---
## 🗄 Entidades — Buenas prácticas

Cada entidad debe incluir:

✔ `@Entity`  
✔ `@Table(name="...")` (opcional pero recomendado)  
✔ ID autogenerado con `@Id` + `@GeneratedValue`  
✔ Constructor vacío  
✔ Getters y setters

Ejemplo recomendado:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
```

---
## 🧩 Buenas prácticas capa Controller-Service-Repository

## ✔ Controller
- Usa **DTOs** para request y response.
- No debe acceder directamente a repositorios.
- No debe devolver entidades del modelo (Entities).
- Valida la entrada con `@Valid`.
- Solo delega la lógica al service.

## ✔ Service
- Implementa las reglas de negocio reales.
- Realiza la conversión DTO ↔ Entity.
- Interactúa con repositorios.
- Maneja errores con `orElseThrow()` para Optional.
- No expone entidades directamente fuera del service.
- Debe mantener métodos pequeños y específicos.

## ✔ Repository
- Solo trabaja con entidades.
- No contiene lógica de negocio.
- Puede definir métodos personalizados:
  - Por nombre (`findByEmail`)
  - Via `@Query`
- Devuelve Optional en búsquedas por ID.


--- 

## ⚠️ Notas importantes
* Usar `@JsonIgnore` para evitar ciclos en relaciones bidireccionales.
* En las clases de tipo **entities** :
  *  comienzan con anotaciones `@Entity` y `@Table(name = "nombre_tabla")`
  *  los ids van con `@Id` y `@GeneratedValue(strategy = GenerationType.IDENTITY)`.
  * Se suele utilizar long en IDs y no int porque permite un mayor alcance para valores numericos.
  * sU USA `@Column(name="nombre_columna")` si quiero cambiar el nombre del atributo
  * Incluir siempre getters, setters y constructor vacío en entidades para Hibernate/JPA.
* El literal `.class` se refiere al objeto de clase en Java (metainformación).
* @Autowired nos ayuda con la inyeccion de un servicio a otro. Por ejemplo: `@Autowired` ` private final MateriaRepository materiaRepository;` nos dice que la clase que ya creamos MateriaRepository se inyecta en materiaRepository
* No hace fata try/catch para lanzar excepciones porque si tenés una clase anotada con @RestControllerAdvice, esa clase “escucha” todas las excepciones lanzadas en los controladores. (tiene que estar un controlleradvice con exceptionhandler).
* Acordarse que:
  * El controller deberia usar DTOs (request y response).
  * El service Puede usar DTOs hacia afuera (lo que ve el controller) y Entities hacia adentro (lo que guarda en repos).
  * El repository solo trabaja con Entities.
  * El uso de `ìsPresent` o `get` esta "deprecado". En cambio tendrias que usar el orElseThow. (se muestran ejemplos en los servicios donde los metodos del repositorio devuelven Optional en los servicios).
  *
---

## 🏷️ @Column — Atributos principales
* `columnDefinition` → fragmento SQL usado en el DDL.
* `insertable` → si la columna se incluye en sentencias `INSERT`.
* `length` → longitud de la columna.
* `name` → nombre de la columna en la tabla.
* `nullable` → si admite valores nulos.
* `precision` → precisión para números decimales.
* `scale` → cantidad de decimales para números decimales.
* `table` → tabla a la que pertenece la columna.
* `unique` → si es una clave única.
* `updatable` → si se incluye en sentencias `UPDATE`.

---

## 📬 Métodos de ResponseEntity
* `ok()` → respuesta **200 OK** sin body
* `ok(body)` → respuesta **200 OK** con body
* `status(HttpStatus status)` → definir manualmente el status
* `created(URI location)` → respuesta **201 Created** con header Location
* `accepted()` → respuesta **202 Accepted**
* `noContent()` → respuesta **204 No Content**
* `badRequest()` → respuesta **400 Bad Request**
* `notFound()` → respuesta **404 Not Found**
* `internalServerError()` → respuesta **500 Internal Server Error**

---

## 🗄️ Métodos principales de JpaRepository
* `save(S entity)` — guardar o actualizar una entidad.
* `saveAll(Iterable<S> entities)` — guardar varias entidades.
* `findById(ID id)` — buscar por ID (retorna `Optional<T>`).
* `findAll()` — recuperar todos los registros.
* `findAllById(Iterable<ID> ids)` — buscar múltiples IDs.
* `count()` — contar registros.
* `existsById(ID id)` — verificar existencia por ID.
* `deleteById(ID id)`, `delete(T entity)`, `deleteAll(...)`
* `findAll(Pageable pageable)` — paginación.
* `flush()` — sincronizar cambios pendientes con la base.
* `saveAndFlush(S entity)`, `saveAllAndFlush(Iterable<S> entities)` — guardar y vaciar el estado inmediatamente.
* Operaciones por lotes: `deleteInBatch(...)`, `deleteAllInBatch()`.

Además extiende `PagingAndSortingRepository` y `QueryByExampleExecutor`, habilitando paginación, ordenamiento y queries por ejemplo.

---



## 🛑 Manejo de Excepciones
1. Crear paquete `exceptions`.
2. Agregar clase **global** de manejo de excepciones con nombre **HandlerException** con `@RestControllerAdvice`.
3. Las excepciones personalizadas extienden de `RuntimeException`.
4. Métodos con `@ExceptionHandler` capturan excepciones y retornan un `ResponseEntity`.
5. Crear un DTO `ApiResponse` para estandarizar las respuestas de error.

Ejemplo de `handlerException`

```java
@RestControllerAdvice
public class HandlerException {

   
    //controla los errores de varios tipos y globalizrlo con un error 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception exception, WebRequest webRequest){
        //en el paraemtro del getDescription va false
        //para que no devuelva informacion del cliente y solo la uri
        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
```
Ejemplo de `Excepcion personalizada`

``` java
package com.test.springmysql.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontrado extends RuntimeException{
    private String nombreRecurso;
    private String nombreCampo;
    private Object valorCampo;

    public RecursoNoEncontrado(String nombreRecurso, String nombreCampo, Object valorCampo) {
        super(String.format("%s no fue encontrado con %s = '%s' ",nombreRecurso,nombreCampo,valorCampo));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorCampo = valorCampo;
    }
}

```



Ejemplo de configuración en `application.properties` para rutas inexistentes:
```properties
// Si queremos poner una excepcion por si una ruta es inexiste podemos poner esto en aplication.properties:
// Si se debe lanzar una "NoHandlerFoundException" si no se encontró ningún controlador para procesar una solicitud.
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false


// y luego ponemos en el controlador global de excepciones la excepcion que trae spring por defecto.
```

### EXCEPCIONES DE SPRING POR DEFECTO
`MethodArgumentNotValidException` → fallan validaciones de @Valid en body (DTO).  
`BindException` → fallan binds de formularios/params.  
`ConstraintViolationException` → validación de @Validated en path/query params.    
`MethodArgumentTypeMismatchException` → tipo incompatible en path/query (ej: id no numérico).    
`MissingServletRequestParameterException` → falta un query param requerido.    
`HttpMessageNotReadableException` → JSON mal formado o tipos inválidos.    
`HttpRequestMethodNotSupportedException` → método no permitido (POST vs GET).    
`HttpMediaTypeNotSupportedException` / HttpMediaTypeNotAcceptableException → Content-Type o Accept inválidos.     
`NoHandlerFoundException` → 404 por ruta inexistente (solo si lo activás, ver abajo).        
`DataAccessException` (raíz, unchecked)    
`DataIntegrityViolationException` → violación de integridad (p. ej., UNIQUE/NOT NULL/FK).    
`DuplicateKeyException` (en algunos drivers) → clave duplicada.        
`AccessDeniedException` → 403.    
`AuthenticationException` → 401.     
`ResponseStatusException` → lanzar un error HTTP sin crear exception custom.    

En mi clase `HandlerException` comente algunas funciones para que se sepa de que trata cada una.

## Swagger

Link de la docu de swagger : https://springdoc.org/ 
1. Instalar dependencia: 
```xml
  <dependency>
    <groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
	<version>2.8.14</version>
  </dependency>
```
2. Entrar a la url:
```
http://localhost:8080/swagger-ui.html
```

---

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