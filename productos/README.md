# 🧾 Sistema de Inventario de Productos
**Versión:** 1.1  
**Autor:** Samuel Patiño Toro  
**Tecnologías:** Java 21, Spring Boot 3.4.11, Maven, MySQL 8.0, Swagger UI

---

## 📖 Descripción del Proyecto
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

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE favorito.
3. Configura la base de datos en el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventariodb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=Da*t4g5?T4rn5n-
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080

springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.doc-expansion=none


