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
* El literal `.class` se refiere al objeto de clase en Java (metainformación).
* @Autowired nos ayuda con la inyeccion de un servicio a otro. Por ejemplo: `@Autowired` ` private final MateriaRepository materiaRepository;` nos dice que la clase que ya creamos MateriaRepository se inyecta en materiaRepository
Se recomienda inyección por constructor para favorecer testeo e inmutabilidad.
* No hace fata try/catch para lanzar excepciones porque si tenés una clase anotada con @RestControllerAdvice, esa clase “escucha” todas las excepciones lanzadas en los controladores. (tiene que estar un controlleradvice con exceptionhandler).
* No es recomendable usar isPresent() + get() en código productivo; se prefiere orElseThrow().--- 

---
## 📚 Material de referencia
- 🎥 Video YouTube: [Spring Boot + MySQL + ModelMapper](https://www.youtube.com/watch?v=9XoaU5IMkRY&t=457s)
---
