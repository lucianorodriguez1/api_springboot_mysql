## 📬 Métodos de ResponseEntity
* `ok()` → respuesta **200 OK** sin body
* `ok(body)` → respuesta **200 OK** con body
* `status(HttpStatus status)` → definir manualmente el status
* `created(URI location)` → respuesta **201 Created** con header Location
* `accepted()` → respuesta **202 Accepted**
* `noContent()` → respuesta **204 No Content**
* `badRequest()` → respuesta **400 Bad Request**
* `notFound()` → respuesta **404 Not Found**
* `internalServerError()` → respuesta **500 Internal Server Error**

---

* El controller deberia usar DTOs (request y response).

