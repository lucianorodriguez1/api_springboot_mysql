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
*  los ids van con `@Id` y `@GeneratedValue(strategy = GenerationType.IDENTITY)`.
* Se suele utilizar long en IDs y no int porque permite un mayor alcance para valores numericos.
* Se usa `@Column(name="nombre_columna")` si quiero cambiar el nombre del atributo
* Incluir siempre getters, setters y constructor vacío en entidades para Hibernate/JPA.