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
