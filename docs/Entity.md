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