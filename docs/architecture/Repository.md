## 🗄️ Métodos principales de JpaRepository
* `save(S entity)` — guardar o actualizar una entidad.
* `saveAll(Iterable<S> entities)` — guardar varias entidades.
* `findById(ID id)` — buscar por ID (retorna `Optional<T>`).
* `findAll()` — recuperar todos los registros.
* `findAllById(Iterable<ID> ids)` — buscar múltiples IDs.
* `count()` — contar registros.
* `existsById(ID id)` — verificar existencia por ID.
* `deleteById(ID id)`, `delete(T entity)`, `deleteAll(...)`
* `findAll(Pageable pageable)` — paginación.
* `flush()` — sincronizar cambios pendientes con la base.
* `saveAndFlush(S entity)`, `saveAllAndFlush(Iterable<S> entities)` — guardar y vaciar el estado inmediatamente.
* Operaciones por lotes: `deleteInBatch(...)`, `deleteAllInBatch()`.


Además extiende `PagingAndSortingRepository` y `QueryByExampleExecutor`, habilitando paginación, ordenamiento y queries por ejemplo.

