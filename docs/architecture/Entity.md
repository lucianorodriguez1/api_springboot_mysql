## 🗄 Entidades — Buenas prácticas

Cada entidad debe incluir:

✔ `@Entity`  
✔ `@Table(name="...")` (opcional pero recomendado)  
✔ ID autogenerado con `@Id` + `@GeneratedValue`  
✔ Constructor vacío  
✔ Getters y setters

Ejemplo recomendado:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
```

En las clases de tipo **entities** :
*  comienzan con anotaciones `@Entity` y `@Table(name = "nombre_tabla")`
*  los ids van con `@Id` y `@GeneratedValue(strategy = GenerationType.IDENTITY)`. (EN MYSQL, en otras db pueden usar otro codigo.)
* Se suele utilizar long en IDs y no int porque permite un mayor alcance para valores numericos.
* Se usa `@Column(name="nombre_columna")` si quiero cambiar el nombre del atributo
* Incluir siempre getters, setters y constructor vacío en entidades para Hibernate/JPA.

---

## 🏷️ @Column — Atributos principales
* `columnDefinition` → fragmento SQL usado en el DDL.
* `insertable` → si la columna se incluye en sentencias `INSERT`.
* `length` → longitud de la columna.
* `name` → nombre de la columna en la tabla.
* `nullable` → si admite valores nulos.
* `precision` → precisión para números decimales.
* `scale` → cantidad de decimales para números decimales.
* `table` → tabla a la que pertenece la columna.
* `unique` → si es una clave única.
* `updatable` → si se incluye en sentencias `UPDATE`.

Link: 
- 📖 Documentación Jakarta: [Persistencia](https://jakarta.ee/learn/docs/jakartaee-tutorial/current/persist/persistence-intro/persistence-intro.html)
- ✍️ Anotaciones de relaciones en Spring: [Mastering Database Relationship Annotations](https://medium.com/devdomain/mastering-spring-database-relationship-annotations-161cb8232619)
- 📑 Documentación de `@Column`: [Jakarta Persistence API](https://jakarta.ee/specifications/persistence/2.2/apidocs/javax/persistence/column)
