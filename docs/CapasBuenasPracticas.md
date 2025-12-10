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
    * Se usa `@Column(name="nombre_columna")` si quiero cambiar el nombre del atributo
    * Incluir siempre getters, setters y constructor vacío en entidades para Hibernate/JPA.
* El literal `.class` se refiere al objeto de clase en Java (metainformación).
* @Autowired nos ayuda con la inyeccion de un servicio a otro. Por ejemplo: `@Autowired` ` private final MateriaRepository materiaRepository;` nos dice que la clase que ya creamos MateriaRepository se inyecta en materiaRepository
* No hace fata try/catch para lanzar excepciones porque si tenés una clase anotada con @RestControllerAdvice, esa clase “escucha” todas las excepciones lanzadas en los controladores. (tiene que estar un controlleradvice con exceptionhandler).
* Acordarse que:
    * El controller deberia usar DTOs (request y response).
    * El service Puede usar DTOs hacia afuera (lo que ve el controller) y Entities hacia adentro (lo que guarda en repos).
    * El repository solo trabaja con Entities.
    * El uso de `ìsPresent` o `get` esta "deprecado". En cambio tendrias que usar el orElseThow. (se muestran ejemplos en los servicios donde los metodos del repositorio devuelven Optional en los servicios).
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
