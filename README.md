# 🎓 Sistema estudiantil con Spring Boot y MySQL

Sistema realizado con el objetivo de tener documentado la conexion de MySQL con Spring Boot.

⚠️ **Aclaración**: No se usa **Lombok**.

---

## 📊 Diagrama Entidad Relación (DER)
<img width="734" height="446" alt="Image" src="https://github.com/user-attachments/assets/06420a0a-91bb-40f0-8a7e-ea873b0319a0" /> 

---

## 📦 Dependencias
* spring-boot-starter-data-jpa 
* spring-boot-starter-web (Spring Web)
* mysql-connector-j (Desde el instalador se puede agregar MySQL Driver)
* spring-boot-starter-test
* spring-boot-starter-validation (agregar validaciones a los atributos de las clases)
* modelmapper
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
* `MethodArgumentNotValidException` → fallan validaciones de @Valid en body (DTO).  
* `BindException` → fallan binds de formularios/params.  
* `ConstraintViolationException` → validación de @Validated en path/query params.    
* `MethodArgumentTypeMismatchException` → tipo incompatible en path/query (ej: id no numérico).    
* `MissingServletRequestParameterException` → falta un query param requerido.    
* `HttpMessageNotReadableException` → JSON mal formado o tipos inválidos.    
* `HttpRequestMethodNotSupportedException` → método no permitido (POST vs GET).    
* `HttpMediaTypeNotSupportedException` / HttpMediaTypeNotAcceptableException → Content-Type o Accept inválidos.     
* `NoHandlerFoundException` → 404 por ruta inexistente (solo si lo activás, ver abajo).        
* `DataAccessException` (raíz, unchecked)    
* `DataIntegrityViolationException` → violación de integridad (p. ej., UNIQUE/NOT NULL/FK).    
* `DuplicateKeyException` (en algunos drivers) → clave duplicada.        
* `AccessDeniedException` → 403.    
* `ResponseStatusException` → lanzar un error HTTP sin crear exception custom.    

En mi clase `HandlerException` comente algunas funciones para que se sepa de que trata cada una.

---

# TEST

## JUNIT
* Usar el directorio `test`.
* Tener el mismo direvtorio que main.
* En los test unitarios siempre voy a trabajar con valor esperado y 
valor actual (valor que el metodo me devolvio).
* Lo que hace Junit es comprar el valor esperado con el valor actual.
* Por defecto, los test unitarios pasan.
* La estructura que se suele usar en test unitario es **given** (se suele tener las variables listas) **when** (se ejecuta el metodo) 
y **then** (evaluamos el resultado del metodo).
* Los metodos son de tipo void y cada uno tiene una anotacion @Test
* Opcionalmente puedo instalar un plugin llamado jacoco para ver la cobertura de mis test
* La calidad del test suele estar entre 85 - 95.
* Cuando queremos probar una funcion de error creamos otra metodo con una palabra 
Error agregado al final del nombre del metodo. Y dentro del metodo
no suele ir el **when** si no que se coloca directamente el assertThrow
* Podemos configurar un metodo para que se ejecute antes de todos los tests 
con `@BeforeEach `
* @Para poner nombre al test es con **@DisplayName** arriba de **@Test**


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