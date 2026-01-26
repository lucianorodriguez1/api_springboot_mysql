# 🎓 Sistema estudiantil con Spring Boot y MySQL

### Descripcion
* Proyecto CRUD BACK END que simula un ***sistema estudiantil***. 
* Los componentes son **estudiantes**, **profesores** y **materias** con sus **comisiones**. 
* Además de los métodos CRUD basicos tambien se puede crear una comision en una materia y agregar un estudiante en una comision. 
* En el componente estudiante se pueden encontrar paginacion, filtros, busquedas y parametros para mostrar los endpoints en swagger. 
* Se testea el repositorio, servicio y controlador de estudiante.
* Se usan las tecnologías **Spring Boot** y **MySQL**.

### 📊 Diagrama Entidad Relación (DER)
<img width="898" height="518" alt="Image" src="https://github.com/user-attachments/assets/a0b3b488-f5ff-4cc0-9d7b-5fe7cd02d0c7" />

### Objetivo
Tiene como objetivo **implementar funciones** para aprender a construir el back end de una aplicacion de manera completa con spring boot y **documentar** un proyecto back end de manera completa. 

### Caracteristicas
* Arquitectura en capas
* Entidad -- Repositorio -- Servicio -- Controlador
* DTOs
* Paginacion -- Filtros -- Busqueda
* Validaciones
* Excepciones
* Scripts de inicializacion (Mocks)
* Swagger
* Testing (Repositorio, Servicio y Controlador)
* Docs (Documentacion de todas las funcionalidades del proyecto)

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
6. Acceder a la API y SWAGGER:
   * http://localhost:8080
   * http://localhost:8080/swagger-ui/index.html

---
## 🧱 Arquitectura del proyecto del `src/main/java/com.example.spring/`

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


