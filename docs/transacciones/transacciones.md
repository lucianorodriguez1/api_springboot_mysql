Link al video que hablka sobre transacciones:
https://www.youtube.com/watch?v=-fYjhcbPIro 

Las transacciones se definen en la capa SERVICE, no en controllers ni repositories. 

**ACLARACION**: En este proyecto se usa en MateriaService

## Cuándo NO usar @Transactional

❌ Controllers
❌ DTOs
❌ Entidades
❌ Mappers
❌ Repositories (salvo casos muy puntuales)

Spring Data ya maneja transacciones simples a nivel repository.

## Transacciones de solo lectura

Para consultas (muy recomendable):

```java
@Transactional(readOnly = true)
public MateriaDetailDTO getMateria(Long id) {
    ...
}
```