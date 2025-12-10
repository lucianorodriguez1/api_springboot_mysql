# 🎓 Sistema estudiantil con Spring Boot y MySQL

Proyecto CRUD que implementa un **sistema estudiantil** usando **Spring Boot**, **Spring Data JPA**, **MySQL** y validaciones con **DTOs**.
Tiene como objetivo documentar buenas prácticas de estructura, manejo de errores, capas y uso de herramientas modernas.

⚠️ **Aclaración**: No se usa **Lombok**.

---

## 📊 Diagrama Entidad Relación (DER)
<img width="734" height="446" alt="Image" src="https://github.com/user-attachments/assets/06420a0a-91bb-40f0-8a7e-ea873b0319a0" /> 

---

## 📦 Dependencias
* **spring-boot-starter-data-jpa** (ORM y repositorios)
* **spring-boot-starter-web** (Spring Web, API REST)
* **mysql-connector-j** (Desde el instalador se puede agregar MySQL Driver, Driver MySQL)
* **spring-boot-starter-test** (Testing (JUnit + Spring Test))
* **spring-boot-starter-validation** (agregar validaciones a los atributos de las clases, Validaciones con Bean Validation)
* **modelmapper** (Conversión entre Entity y DTO)
---

## ⚙️ Variables de entorno
- `DB_URL` → url de la base de datos
- `DB_USERNAME` → usuario de la base de datos
- `DB_PASSWORD` → contraseña de la base de datos

Ejemplo (`application.properties`):

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

## 🚀 Pasos para ejecutar
1. Agregar las variables de la base de datos en `application.properties`.
2. Tener la base creada en MySQL.
3. Configurar el **RUN** para ejecutar siempre el archivo `Main`.
   > IntelliJ IDEA → `Edit Configuration -> Application -> Main Class -> Seleccionar archivo main`.
4. Crear entidades en la carpeta `entities` con atributos, relaciones y anotaciones.
5. Crear las carpetas `dto`, `services` y `controllers`.
6. Crear las excepciones y  un DTO `ApiResponse` para devolver las respuestas en ese formato cuando pasa una excepcion.

---

## 📚 Material de referencia
- 🎥 Video YouTube: [Spring Boot + MySQL + ModelMapper](https://www.youtube.com/watch?v=9XoaU5IMkRY&t=457s)
- 📖 Documentación Jakarta: [Persistencia](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-intro/persistence-intro.html)
- ✍️ Anotaciones de relaciones en Spring: [Mastering Database Relationship Annotations](https://medium.com/devdomain/mastering-spring-database-relationship-annotations-161cb8232619)
- 📑 Documentación de `@Column`: [Jakarta Persistence API](https://jakarta.ee/specifications/persistence/2.2/apidocs/javax/persistence/column)

---
## 🧱 Arquitectura del proyecto

``` 
.
├── controllers/
    ├── MateriaController.java

├── dtos/
    ├── ApiResponse.java
    └── Materia.java
   
├── entities/
    └── Materia.java
├── exceptions/
    └── HandlerException.java
├── repositories/
    └── MateriaRepository.java
└── services/
    ├── MateriaService.java

``` 
---

