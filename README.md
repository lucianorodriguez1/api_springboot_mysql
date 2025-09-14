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

🚀 Pasos para ejecutar

1. Agregar las variables de la base de datos en application.properties.
2. Tener la base creada en MySQL.
3. Configurar el RUN para ejecutar siempre el archivo Main.
    En IntelliJ: Edit Configuration -> Application -> Main Class -> Seleccionar archivo main.
4. Crear entidades en la carpeta entities con atributos, relaciones y anotaciones.
5. Crear las carpetas dto, services y controllers.
6. Crear excepciones con un DTO ApiResponse.


## 🚀 Pasos para ejecutar
1. Agregar las variables de la base de datos en `application.properties`.
2. Tener la base creada en MySQL.
3. Configurar el **RUN** para ejecutar siempre el archivo `Main`.
   > IntelliJ IDEA → `Edit Configuration -> Application -> Main Class -> Seleccionar archivo main`.
4. Crear entidades en la carpeta `entities` con atributos, relaciones y anotaciones.
5. Crear las carpetas `dto`, `services` y `controllers`.
6. Crear las excepciones con un DTO `ApiResponse`.

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
* Incluir siempre getters, setters y constructor vacío en entidades para Hibernate/JPA.
* El literal `.class` se refiere al objeto de clase en Java (metainformación).

---

## 🛑 Manejo de Excepciones
1. Crear paquete `exceptions`.
2. Agregar clase **global** de manejo de excepciones (`@RestControllerAdvice`).
3. Las excepciones personalizadas extienden de `RuntimeException`.
4. Métodos con `@ExceptionHandler` capturan excepciones y retornan un `ResponseEntity`.
5. Crear un DTO `ApiResponse` para estandarizar las respuestas de error.

Ejemplo de configuración en `application.properties` para rutas inexistentes:
```properties
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false

// Si queremos poner una excepcion por si una ruta es inexiste podemos poner esto en aplication.properties:
// Si se debe lanzar una "NoHandlerFoundException" si no se encontró ningún controlador para procesar una solicitud.
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false


// y luego ponemos en el controlador global de excepciones la excepcion que trae spring por defecto.
