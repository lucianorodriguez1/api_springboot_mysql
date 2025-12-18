## Validaciones
Las alternativas de validación en Spring Boot incluyen el uso de anotaciones de Bean Validation (@NotNull, @Size, etc.) en objetos de transferencia de datos (DTO), la creación de validadores personalizados para lógica de negocio más compleja, y el uso de la interfaz Validator de Spring para validación a nivel de servicio. La forma más eficiente y estándar es usar anotaciones de Bean Validation con @Valid en los controladores, ya que centraliza la lógica y es fácil de usar.


Alternativas de validación
Anotaciones de Bean Validation (JSR 380):
¿Qué es? Es el método estándar y más común. Se añaden anotaciones de validación directamente a los campos de la clase del objeto que se recibe en el controlador.
Anotaciones comunes: @NotNull, @Size, @Min, @Max, @Email, @Pattern, entre otras.
Eficiencia: Muy eficiente para validaciones comunes, ya que la lógica está centralizada en el modelo y la implementación es automática con la dependencia spring-boot-starter-validation.


Validadores personalizados:
¿Qué es? Se utiliza cuando se necesita una lógica de validación compleja que no se puede expresar con las anotaciones estándar. Esto implica crear una nueva anotación personalizada y una clase que implemente ConstraintValidator.
Eficiencia: Es eficiente para validaciones de negocio específicas. Permite reutilizar la lógica de validación en diferentes partes de la aplicación.


Interfaz Validator de Spring:
¿Qué es? Permite validar objetos a un nivel más profundo, a menudo en la capa de servicio, antes de que los datos lleguen a la base de datos. Se utiliza para implementar una validación semántica o lógica de negocio que se ejecuta después de la validación inicial de las anotaciones.
Eficiencia: Es una solución robusta para validar la lógica de negocio en el backend, asegurando que los datos tengan sentido en el contexto de la aplicación.


¿Cuál es la más eficiente?
La combinación de anotaciones de Bean Validation y @Valid en los controladores es la forma más eficiente y recomendada para validaciones comunes.
Ventajas de esta combinación:
Centraliza la lógica: La validación se define en la clase del DTO, manteniendo la lógica de validación separada del controlador.
Simplifica el desarrollo: Es fácil de implementar y mantener.
Mejora la seguridad: Evita que datos inválidos lleguen a las capas de procesamiento de la aplicación.
Para validaciones más complejas, como reglas de negocio específicas, se recomienda crear validadores personalizados o usar la interfaz Validator en la capa de servicio. Esta combinación ofrece la máxima eficiencia, asegurando tanto la validez estructural de los datos como la validez semántica del negocio.

@Valid del controlador
Cuando Spring Boot encuentra un argumento anotado con @Valid , automáticamente inicia la implementación JSR 380 predeterminada (Hibernate Validator) y valida el argumento.
Cuando el argumento de destino no pasa la validación, Spring Boot lanza una excepción MethodArgumentNotValidException .

Link: https://www.baeldung.com/spring-boot-bean-validation
Video (Validar estructuras anidadas): https://www.youtube.com/watch?v=ahaxAoo-NZ4

En Spring Boot y siguiendo las buenas prácticas, es altamente recomendable validar los objetos en los DTO (Data Transfer Objects), específicamente en la capa del controlador, antes de que lleguen a la capa de servicio o a la entidad (modelo de dominio).

### ¿Es recomendable tener validaciones en la capa entidad?

No, no es recomendable tener validaciones de la API (como @NotNull en un campo que el usuario debe completar) directamente en la entidad.
La validación en la entidad, si existe, es diferente y se enfoca en mantener la integridad de los datos a nivel de dominio/negocio, no en manejar los errores de entrada del usuario.

---