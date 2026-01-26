Link a reddit sobre buenas practicas de loggs:
https://www.reddit.com/r/devsarg/comments/1gamqxi/buenas_pr%C3%A1cticas_para_logs/
Link a blog: https://newrelic.com/es/resources


Niveles de registro

Error
Se utiliza para errores no recuperables.

Warning
Se utiliza para errores recuperables.

Info
Se utiliza con fines de auditoría.

Debug
Se utiliza para investigación.

Trace
Se utiliza para investigación detallada.


```text
Java proporciona varios marcos de registro, algunos de los cuales son:

Registro de configuración de Logback
Registro de configuración de Log4j2
Registro con Lombok
@Slf4j y @CommonsLog
```

```java
// Rest Controller to print various log level messages
package com.log.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

    // creating a logger
    Logger logger
        = LoggerFactory.getLogger(LogController.class);

    @RequestMapping("/log") public String log()
    {
        // Logging various log level messages
        logger.trace("Log level: TRACE");
        logger.info("Log level: INFO");
        logger.debug("Log level: DEBUG");
        logger.error("Log level: ERROR");
        logger.warn("Log level: WARN");

        return "Hey! You can check the output in the logs";
    }
}

```

## LOGCKBAG
Spring Boot utiliza Logback como registro predeterminado. 
LINK : https://www.geeksforgeeks.org/springboot/spring-boot-logging/

Cuando Spring Boot encuentra un archivo con cualquiera de los siguientes nombres, **ANULA** automáticamente la configuración predeterminada.
* logback-spring.groovy
* logback.groovy
* logback-spring.xml
* logback.xml


EXAMPLE

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!-- Setting up log path and log file name -->
    <property name="LOG_PATH" value="./logs" />
    <property name="LOG_FILE_NAME" value="application_logback" />

    <!-- Setting up logging pattern for console logging -->
    <appender name="ConsoleOutput"
        class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <Pattern>
                %white(%d{ISO8601}) %highlight(%-5level) [%yellow(%t)] : %msg%n%throwable
            </Pattern>
        </layout>
    </appender>

    <!-- Setting the filename and logging pattern for log file -->
    <appender name="LogFile"
        class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_PATH}/${LOG_FILE_NAME}.log</file>
        <encoder
            class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <Pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level : %msg%n
            </Pattern>
        </encoder>
      
        <!-- Setting up a rolling policy with rolling done
              daily and when file size is 10MB-->
        <rollingPolicy
            class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_PATH}/archived/${LOG_FILE_NAME}-%d{yyyy-MM-dd}.%i.log
            </fileNamePattern>
            <timeBasedFileNamingAndTriggeringPolicy
                class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
    </appender>

    <!-- Logging at INFO level -->
    <root level="info">
        <appender-ref ref="LogFile" />
        <appender-ref ref="ConsoleOutput" />
    </root>
    
    <!-- Logging at TRACE level -->
     <logger name="com.log" level="trace" additivity="false">
        <appender-ref ref="LogFile" />
        <appender-ref ref="ConsoleOutput" />
    </logger>
    
</configuration>
```


### Log4j2

Instalar:

```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

uando Spring Boot encuentra un archivo con alguno de los siguientes nombres, lo sobrescribe automáticamente sobre la configuración predeterminada.

log4j2-spring.xml
log4j2.xml
Ahora vamos a crear un archivo log4j2-spring.xml simple como el siguiente:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<Configuration>

    <!-- Setting up log path and log file name -->
    <Properties> 
    <property name="LOG_PATH" value="./logs" />
    <property name="LOG_FILE_NAME" value="application-log4j2" />
    </Properties>
    
    <!-- Setting up logging pattern for console logging -->
    <Appenders>
        <Console name="ConsoleOutput" target="SYSTEM_OUT">
            <PatternLayout
                pattern="%style{%d{ISO8601}}{white} %highlight{%-5level} [%style{%t}{bright,yellow}] : %msg%n%throwable"
                disableAnsi="false" />
        </Console>

        <!-- Setting the filename and logging pattern for log file. Also setting 
             up a rolling policy with rolling done daily and when file size is 10MB -->
        <RollingFile name="LogFile"
            fileName="${LOG_PATH}/${LOG_FILE_NAME}.log"
            filePattern="${LOG_PATH}/$${date:yyyy-MM}/application-log4j2-%d{dd-MMMM-yyyy}-%i.log.gz">
            <PatternLayout>
                <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level : %msg%n
                </pattern>
            </PatternLayout>
            <Policies>
                <OnStartupTriggeringPolicy />
                <SizeBasedTriggeringPolicy size="10 MB" />
                <TimeBasedTriggeringPolicy />
            </Policies>
        </RollingFile>
    </Appenders>

    <Loggers>
      
        <!-- Logging at INFO level -->
        <Root level="info">
            <AppenderRef ref="ConsoleOutput" />
            <AppenderRef ref="LogFile" />
        </Root>

        <!-- Logging at TRACE level -->
        <logger name="com.log" level="trace" additivity="false">
            <appender-ref ref="LogFile" />
            <appender-ref ref="ConsoleOutput" />
        </logger>

    </Loggers>

</Configuration>

```

### @Slf4j y @CommonsLog  

Para cambiar el marco de registro sin afectar el código, podemos usar las anotaciones de la API de registro @Slf4j y @CommonsLog. 
Estas anotaciones añaden las instancias del registrador a la ruta de clase.

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
  
  public static void main(String args[]) {
    log.info("Info level");
    log.error("Error level");
  }
}
```

En el código anterior, añadimos la anotación @Slf4j. 
Esta anotación añadirá automáticamente el campo log.

Link a la diferencia entre 3 formas de escribir log: https://stackify.com/compare-java-logging-frameworks/

El equipo de desarrollo anunció el fin de la vida útil de Log4j en 2015. Si bien muchos proyectos heredados aún lo utilizan, 
debería preferir uno de los otros marcos analizados en este artículo si comienza un nuevo proyecto.
Matt ya explicó Log4j con gran detalle en un artículo anterior , y puedes usar la SLF4JAPI que te mostré antes para escribir mensajes de registro con Log4j. 
Así que, repasemos rápidamente las dependencias y la configuración necesarias antes de hablar de Logback y Log4j2.


https://sematext.com/blog/java-logging-frameworks/


Para proyectos grandes en Java / Spring Boot, la elección estándar y profesional es:
SLF4J + Logback

Y en casos más específicos:
SLF4J + Log4j2



**ERROR** se loguea una sola vez, en un punto central.
No se repite en cada capa.

Ejemplo :

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex,
                                                        HttpServletRequest request) {

        log.error("Error inesperado en {} {}",
                request.getMethod(),
                request.getRequestURI(),
                ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Error interno"));
    }
}

```


### Regla de oro para recordar

ERROR = el sistema falló
WARN = el negocio dijo no
INFO = pasó algo normal
DEBUG = detalle técnico