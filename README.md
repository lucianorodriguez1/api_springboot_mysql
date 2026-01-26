# 🎓 Sistema estudiantil con Spring Boot y MySQL

### Descripcion
* Proyecto CRUD (BACK END) que simula un ***sistema estudiantil***. 
* Los componentes son **estudiantes**, **profesores** y **materias** con sus **comisiones**. 
* Se usan las tecnologías **Spring Boot** , **MySQL** y la version de Java es la 21.

### Caracteristicas 
* Realizar métodos CRUD 
* Crear una comision en una materia 
* Agregar un estudiante en una comision. 
* Agregar profesor a una comision
* Realizar paginacion, filtros, busquedas en estudiante 
* Agregar parametros para mostrar los endpoints en swagger de estudiante. 
* Se testea el repositorio, servicio y controlador de estudiante. Testing con JUnit y Spring Test utilizando H2 para aislar la base de datos.
* Tiene:
  - Arquitectura en capas
  - DTOs
  - Entidad -- Repositorio -- Servicio -- Controlador
  - Paginacion -- Filtros -- Busqueda
  - Validaciones
  - Excepciones globales y personalizadas
  - Scripts de inicializacion (Mocks)
  - Swagger
  - Testing (Repositorio, Servicio y Controlador)
  - Docs (Documentacion de todas las funcionalidades del proyecto)
  - Logs
  - Transacciones

### 📊 Diagrama Entidad Relación (DER)
<img width="734" height="446" alt="Image" src="https://github.com/user-attachments/assets/06420a0a-91bb-40f0-8a7e-ea873b0319a0" /> 

### Objetivo
Tiene como objetivo **implementar funciones** para aprender a construir el back end de una aplicacion  con spring boot y **documentar** el proyecto de manera completa. 


⚠️ **Aclaración**: No se usa **Lombok**.



---

## 📦 Dependencias
* **spring-boot-starter-data-jpa** (ORM y repositorios)
* **spring-boot-starter-web** (Spring Web, API REST)
* **mysql-connector-j** (Desde el instalador se puede agregar MySQL Driver, Driver MySQL)
* **spring-boot-starter-test** (Testing (JUnit + Spring Test))
* **spring-boot-starter-validation** (agregar validaciones a los atributos de las clases, Validaciones con Bean Validation)
* **modelmapper** (Conversión entre Entity y DTO)
* **h2** (Base de datos en memoria para test)
* **springdoc-openapi-starter-webmvc-ui** (Swagger)

## Plugin
* **spring-boot-maven-plugin**
---

## ⚙️ Variables de entorno
- `DB_URL` → url de la base de datos
- `DB_USERNAME` → usuario de la base de datos
- `DB_PASSWORD` → contraseña de la base de datos


--- 

## 🚀 Pasos para ejecutar
1. Clonar el repositorio
```cmd
   git clone https://github.com/lucianorodriguez1/sistema-estudiantil.git
```
2. Configurar la base de datos MySQL
   - Tener MySQL en ejecución.
   - Crear la base de datos manualmente:
3. Agregar las variables de la base de datos en `application.properties`.

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

4. Configurar el **RUN** para ejecutar siempre el archivo `Main`.
   > IntelliJ IDEA → `Edit Configuration -> Application -> Main Class -> Seleccionar archivo main`.
5. Al iniciar la app se van a crear los datos en la base de datos por la clase `InicializarDatos.java` de `/config`
6. Acceder a
   - Servidor (La aplicación se ejecuta en `localhost` utilizando el puerto `8080`): 
      * http://localhost:8080
   - Swagger (Documentacion de la API):
      * http://localhost:8080/swagger-ui/index.html
---

## 🧱 Ejemplo de Arquitectura **SIMPLIFICADA** del `src/main/java/com.example.spring/`

``` 
├── config/
    ├── SwaggerConfig
    ├── InicializarDatos 
├── controllers/
    ├── MateriaController.java
├── dtos/
    ├── ApiResponse.java
    └── Materia.java
├── entities/
    └── Materia.java
├── exceptions/
    └── HandlerException.java
├── mocks/
    └── MateriaMock.java
├── repositories/
    └── MateriaRepository.java
└── services/
    ├── MateriaService.java
└── SpringMySQLApplication

```
---

## 📡 Endpoints principales

### Estudiantes
- `GET /api/v1/estudiantes`
- `GET /api/v1/estudiantes/{estudianteId}`
- `POST /api/v1/estudiantes`
- `PUT /api/v1/estudiantes/{estudianteId}`
- `DELETE /api/v1/estudiantes/{estudianteId}`

### Materias
- `GET /api/v1/materias`
- `GET /api/v1/materias/{materiaId}`
- `POST /api/v1/materias`
- `PUT /api/v1/materias/{materiaId}`
- `DELETE /api/v1/materias/{materiaId}`
- `POST /api/v1/materias/{materiaId}/comisiones`

### Comisiones
- `GET /api/v1/comisiones`
- `GET /api/v1/comisiones/{comisionId}`
- `PUT /api/v1/comisiones/{comisionId}`
- `DELETE /api/v1/comisiones/{comisionId}`
- `POST /api/v1/comisiones/{comisionId}/estudiantes/{estudianteId}`
- `POST /api/v1/comisiones/{comisionId}/profesores/{profesorId}`

### Profesores
- `GET /api/v1/profesores`
- `GET /api/v1/profesores/{profesorId}`
- `POST /api/v1/profesores`
- `PUT /api/v1/profesores/{profesorId}`
- `DELETE /api/v1/profesores/{profesorId}`

---

## Documentacion 
En la carpeta `/docs` se encuentran documentadas:
* Buenas practicas de creacion de arquitectura en capas, validaciones y responseEntity.
* Swagger
* Crear datos con mocks
* Excepciones
* Paginacion, filtros y busqueda.
* Testing
* Transacciones y logs

## Posibles mejoras
* Agregar swagger a los demas componentes (solo se encuentra en estudiantes).
* Mejorar la respuesta de la API de Error.
* Lanzar excepcion si se repite la creacion de estudiante con CUIL ya existente.
* Mejorar los testing.
* Mejorar las documentaciones de algunos archivos de `/docs`.