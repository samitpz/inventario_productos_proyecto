# 🧾 Sistema de Inventario de Productos

**Versión:** 1.0
**Autor:** Samuel patiño toro 
**Tecnologías:** Java 21, Spring Boot 3.4.11, Maven, MySQL, Swagger UI

---

## 📖 Descripción del Proyecto

Este proyecto es una **API REST** desarrollada en **Spring Boot** para la **gestión de productos de inventario**.
Permite realizar las operaciones básicas de un CRUD:

* **GET:** Listar todos los productos.
* **POST:** Crear un nuevo producto.
* **PUT:** Actualizar un producto existente.
* **DELETE:** Eliminar un producto por su ID.

La aplicación utiliza **Spring Data JPA** para la conexión con MySQL y **Swagger UI** para la documentación interactiva de la API.

---

## ⚙️ Requisitos Previos

* **Java 21** o superior
* **MySQL 8.0** o superior
* **IntelliJ IDEA / VS Code / Eclipse**
* **Maven**

---

## 🛠️ Configuración y Ejecución

1. Clona o descarga este repositorio.

2. Abre el proyecto en tu IDE favorito.

3. Configura la base de datos en `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/inventario_db
   spring.datasource.username=root
   spring.datasource.password=TuContraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

4. Ejecuta la clase principal:

   ```
   ProductosApplication.java
   ```

5. Abre tu navegador y accede a Swagger en:
   👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

---

## 📸 Captura de Swagger

A continuación se muestra la interfaz del sistema funcionando con los 4 endpoints del CRUD:



---

## 🧩 Estructura del Proyecto

```
src/
 ├── main/
 │   ├── java/com/inventario/productos/
 │   │   ├── controller/ProductoController.java
 │   │   ├── dto/ProductoDTO.java
 │   │   ├── model/Producto.java
 │   │   ├── repository/ProductoRepository.java
 │   │   ├── service/ProductoService.java
 │   │   ├── service/ProductoServiceInterface.java
 │   │   └── ProductosApplication.java
 │   └── resources/application.properties
 └── test/
```

---

## 🧪 Pruebas en Swagger

Puedes probar los endpoints directamente desde Swagger:

### 🔹 Crear Producto (POST)

```json
{
  "nombre": "Teclado Mecánico",
  "descripcion": "Teclado retroiluminado RGB",
  "precio": 250.0,
  "stock": 15
}
```

### 🔹 Actualizar Producto (PUT)

```json
{
  "nombre": "Teclado Mecánico Premium",
  "descripcion": "Versión mejorada RGB",
  "precio": 300.0,
  "stock": 10
}
```

---

## 👨‍💻 Autor

Proyecto desarrollado por **Samuel patiño toro**
