# Sistema estudiantil SpringBoot y MySQL

Sistema realizado con el objetivo de tener documentado la conexion de MySQL con Spring Boot.

ACLARACION: No se usa lombok.

Diagrama entidad relacion (DER) que cree de mi sistema:

## Instalar dependencias
* spring-boot-starter-data-jpa 
* spring-boot-starter-web (Spring Web)
* mysql-connector-j (Desde el instalador se puede agregar MySQL Driver)
* spring-boot-starter-test


## Variables de entorno
* DB_URL = url de donde se encuentra la base de datos
* DB_USERNAME = nombre del usuario de la base de datos
* DB_PASSWORD = contrasenia del usuario de la base de datos

## Pasos
1. Agregar las tres variables de base de 
datos en el application.properties.
2. Tener la base de datos creada. 
3. Configurar el RUN para que se ejecuta siempre el archivo MAIN. Esto
se cambia en Edit Configuration -> Aplication -> Main Class -> Poner la ubicacion del archivo main.
4. Crear entidades dentro de carpeta entities con todo lo necesario para crear
las entidaders, relaciones, atributos, etc de la base de datos.
5. 


## Material 
Link al video de youtube donde pude obtener mucho informacion :
https://www.youtube.com/watch?v=9XoaU5IMkRY&t=457s

Documentacion de Jakarta:
https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-intro/persistence-intro.html
que es la que vamos a usar en las entidades.

Tips para agregar anotaciones a las relaciones entre clases :
https://medium.com/devdomain/mastering-spring-database-relationship-annotations-161cb8232619

En la clase Materia de entities dejo informacion al respecto.
