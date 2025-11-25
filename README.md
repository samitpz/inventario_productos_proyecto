#  Sistema de Inventario de Productos
**Versión:** 1.1  
**Autor:** Samuel Patiño Toro  
**Tecnologías:** Java 21, Spring Boot 3.4.11, Maven, MySQL 8.0, Swagger UI

---

##  Descripción del Proyecto
El **Sistema de Inventario de Productos** es una **API REST** desarrollada con **Spring Boot** que permite administrar productos dentro de una base de datos **MySQL**.  
Implementa las operaciones básicas de un CRUD con arquitectura por capas (**Controller**, **Service**, **Repository**, **DTO**).

**Operaciones disponibles:**
- **GET:** Listar todos los productos o buscar por ID.
- **POST:** Crear un nuevo producto.
- **PUT:** Actualizar un producto existente.
- **DELETE:** Eliminar un producto por su ID.

La aplicación utiliza **Spring Data JPA** para la persistencia y **Swagger UI** para la documentación interactiva de los endpoints.

---

## ⚙️ Requisitos Previos
- Java 21 o superior
- MySQL 8.0 o superior
- Maven 3.9+
- IntelliJ IDEA / Eclipse / VS Code

---

## 🛠️ Configuración y Ejecución

1. **Clona este repositorio en tu máquina local:**
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd productos
   ```

2. **Abre el proyecto en tu IDE favorito.**

3. **Configura la base de datos** en el archivo `src/main/resources/application.properties` con los siguientes valores:

```properties
# ============================================================
# ================ CONFIGURACIÓN DE BASE DE DATOS ============
# ============================================================

spring.datasource.url=jdbc:mysql://localhost:3306/inventariodb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=Da*t4g5?T4rn5n-
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# ============================================================
# ==================== CONFIGURACIÓN JPA =====================
# ============================================================

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# ============================================================
# ==================== CONFIGURACIÓN SERVIDOR ================
# ============================================================

server.port=8080

# ============================================================
# =================== CONFIGURACIÓN SWAGGER ==================
# ============================================================

springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.doc-expansion=none
```

4. **Ejecuta la clase principal del proyecto:**  
   `ProductosApplication.java`

5. **Abre tu navegador y accede a Swagger UI:**  
   👉 [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

---

##  Estructura del Proyecto

La estructura actual del proyecto (según IntelliJ IDEA) es la siguiente:

```bash
inventario productos proyecto/
 └── productos/
     └── src/
         └── main/
             ├── java/
             │   └── com/inventario/productos/
             │       ├── controller/
             │       │   └── ProductoController.java
             │       ├── dto/
             │       │   └── ProductoDTO.java
             │       ├── model/
             │       │   └── Producto.java
             │       ├── repository/
             │       │   └── ProductoRepository.java
             │       ├── service/
             │       │   ├── ProductoService.java
             │       │   └── ProductoServiceInterface.java
             │       └── ProductosApplication.java
             └── resources/
                 ├── static/
                 ├── templates/
                 └── application.properties
```

---

## Pruebas en Swagger

Puedes probar los endpoints directamente desde **Swagger UI**.

---

### 🔹 Crear Producto (POST)
**Endpoint:** `/api/productos`

**Ejemplo JSON:**
```json
{
  "nombre": "Teclado Mecánico",
  "descripcion": "Teclado retroiluminado RGB",
  "precio": 250.0,
  "stock": 15
}
```

---

### 🔹 Actualizar Producto (PUT)
**Endpoint:** `/api/productos/{id}`

**Ejemplo JSON:**
```json
{
  "nombre": "Teclado Mecánico Premium",
  "descripcion": "Versión mejorada RGB",
  "precio": 300.0,
  "stock": 10
}
```

---

### 🔹 Eliminar Producto (DELETE)
**Endpoint:** `/api/productos/{id}`

En el campo **id**, escribe el identificador del producto a eliminar.  
**Ejemplo:**
```
id = 3
```

---

##  Validaciones y Manejo de Errores

- Validaciones con `javax.validation` (`@NotBlank`, `@NotNull`, `@PositiveOrZero`, `@Min`).
- Manejo centralizado de excepciones con `@ControllerAdvice`:
    - `MethodArgumentNotValidException` → 400 Bad Request
    - `EntityNotFoundException` → 404 Not Found
    - `DataIntegrityViolationException` → 409 Conflict

**Ejemplo de respuesta de error:**
```json
{
  "timestamp": "2025-11-12T09:12:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": [
    {"field": "nombre", "message": "must not be blank"}
  ]
}
```

---

##  Swagger / Documentación OpenAPI

La API está documentada con **springdoc-openapi**.
- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
- **OpenAPI JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

##  Pruebas (Tests)

- **Unitarias:** JUnit 5 + Mockito
- **Integración:** `@SpringBootTest` + `@AutoConfigureMockMvc`
- **Repositorios:** `@DataJpaTest` + H2 o Testcontainers

**Ejecutar tests:**
```bash
mvn test
```

##  Changelog

**v1.1**
- Documentación ampliada.
- DTOs y validaciones añadidas.
- Manejo de errores centralizado.
- Integración con Swagger.

**v1.0**
- CRUD básico.
- Configuración inicial de JPA.

---

## 👨‍💻 Autor
Proyecto desarrollado por **Samuel Patiño Toro**  
📍 **Colombia — 2025**
