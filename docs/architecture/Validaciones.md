# Validaciones

Las alternativas de validación en Spring Boot incluyen el uso de anotaciones de Bean Validation (`@NotNull`, `@Size`, etc.) en objetos de transferencia de datos (DTO), la creación de validadores personalizados para lógica de negocio más compleja y el uso de la interfaz `Validator` de Spring para validación a nivel de servicio.  
La forma más eficiente y estándar es usar anotaciones de Bean Validation junto con `@Valid` en los controladores, ya que centraliza la lógica y es fácil de usar.

---

## Alternativas de validación

### Anotaciones de Bean Validation (JSR 380)
**¿Qué es?**  
Es el método estándar y más común. Se añaden anotaciones de validación directamente a los campos de la clase del objeto que se recibe en el controlador.

**Anotaciones comunes:**  
`@NotNull`, `@Size`, `@Min`, `@Max`, `@Email`, `@Pattern`, entre otras.

**Eficiencia:**  
Muy eficiente para validaciones comunes, ya que la lógica está centralizada en el modelo y la implementación es automática con la dependencia `spring-boot-starter-validation`.

---

### Validadores personalizados
**¿Qué es?**  
Se utiliza cuando se necesita una lógica de validación compleja que no se puede expresar con las anotaciones estándar. Implica crear una anotación personalizada y una clase que implemente `ConstraintValidator`.

**Eficiencia:**  
Eficiente para validaciones de negocio específicas. Permite reutilizar la lógica de validación en diferentes partes de la aplicación.

---

### Interfaz `Validator` de Spring
**¿Qué es?**  
Permite validar objetos a un nivel más profundo, generalmente en la capa de servicio, antes de que los datos lleguen a la base de datos. Se usa para validación semántica o lógica de negocio posterior a la validación estructural.

**Eficiencia:**  
Es una solución robusta para validar la lógica de negocio en el backend, asegurando que los datos tengan sentido en el contexto de la aplicación.

---

## ¿Cuál es la más eficiente?

La combinación de anotaciones de Bean Validation y `@Valid` en los controladores es la forma más eficiente y recomendada para validaciones comunes.

### Ventajas de esta combinación
- **Centraliza la lógica:** la validación se define en la clase del DTO, separándola del controlador.
- **Simplifica el desarrollo:** fácil de implementar y mantener.
- **Mejora la seguridad:** evita que datos inválidos lleguen a las capas internas de la aplicación.

Para validaciones más complejas (reglas de negocio), se recomienda crear validadores personalizados o usar la interfaz `Validator` en la capa de servicio. Esta combinación asegura tanto la validez estructural como la validez semántica del negocio.

---

## `@Valid` en el controlador

Cuando Spring Boot encuentra un argumento anotado con `@Valid`, inicia automáticamente la implementación JSR 380 predeterminada (Hibernate Validator) y valida el argumento.  
Si el argumento no pasa la validación, Spring Boot lanza una excepción `MethodArgumentNotValidException`.

---

## Recursos
- Artículo: https://www.baeldung.com/spring-boot-bean-validation
- Video (validar estructuras anidadas): https://www.youtube.com/watch?v=ahaxAoo-NZ4

---

En Spring Boot y siguiendo buenas prácticas, es altamente recomendable validar los objetos en los DTO (Data Transfer Objects), específicamente en la capa del controlador, antes de que lleguen a la capa de servicio o a la entidad (modelo de dominio).

### ¿Es recomendable tener validaciones en la capa entidad?

No.  
No es recomendable tener validaciones de la API (por ejemplo, `@NotNull` en un campo que completa el usuario) directamente en la entidad.

Si existen validaciones en la entidad, deben enfocarse en **mantener la integridad del dominio y las reglas de negocio**, no en manejar errores de entrada del usuario.
