# Swagger

El swagger esta implementado en los archivos:
* `config/SwaggerConfig` 
* `controllers/EstudianteControllers`
* `dto/EstudianteListDTO`


## Pasos a ejecutar
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

3. Configuramos el swagger con un archivo de config. Ejemplo basico con `@Info`
```java
package com.test.springmysql.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "API ESTUDIANTIL",
                description = "Una api donde se puede consultar materias con sus comision, estudiantes y profesores.",
                version = "1.0.0"
        )
)
public class SwaggerConfig {

}
```
4. Configurar
   - La clase controladora. Poner :`@Tag(name="nombre del controlador" description= "breve descripcion del mismo")` arriba de las clases de controladores.
   - El metodo del controlador: `@Operation(
                                              summary= "nombre",
                                              description=  "descripcion",
                                              tags={palabras claves estudiantes},
                                              requestBody=, 
                                              response=)`


### Descripcion de anotaciones

* `@Operation` define resumen y descripción del endpoint.
* `@ApiResponses` documenta los códigos de respuesta.
* `@Parameter` describe cada parámetro de entrada.
* `@Schema` describe campos de DTO.


## Material 
Link a las anotaciones: https://www.bobsantosjr.com/openapi3-springboot-springdocopenapi
Breve documentacion de como usar swagger en una app sin tener spring security.
Link de la docu de swagger : https://springdoc.org/
Link al video que habla sobre configuracion de Info y Seguridad de Swagger : https://www.youtube.com/watch?v=SVZZ3B5gwuM
Link al docs de swagger:
https://www.kranio.io/blog/integra-swagger-con-spring-boot-documenta-tus-apis-restful
