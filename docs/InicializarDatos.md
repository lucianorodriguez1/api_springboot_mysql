# Inicializar datos
Hay 4 formas

1. data.sql / schema.sql (SQL puro)
   Cómo funciona

Spring ejecuta automáticamente estos archivos al arrancar:

2. CommandLineRunner / ApplicationRunner (Java)
3. @Sql en tests (solo testing)
4. Migraciones (Flyway / Liquibase)

### Borrar filas de tablas con relaciones entre sí
Si quiero borrar datos de tablas relacionados entre si: 
```sql
SET FOREIGN_KEY_CHECKS = 0;

-- Repite esto para cada tabla
TRUNCATE TABLE nombre_tabla1;
TRUNCATE TABLE nombre_tabla2;

SET FOREIGN_KEY_CHECKS = 1;
```

### Consultar cuantas filas hay en mi bdd
```sql
SELECT TABLE_NAME, TABLE_ROWS 
FROM INFORMATION_SCHEMA.TABLES 
WHERE TABLE_SCHEMA = 'NOMBRE_BASE_DE_DATOS' 
AND TABLE_TYPE = 'BASE TABLE'
ORDER BY TABLE_ROWS DESC;
```