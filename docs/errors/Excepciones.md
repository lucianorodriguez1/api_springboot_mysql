
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