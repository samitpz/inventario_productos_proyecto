package com.inventario.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 👇 Importa las anotaciones de Swagger/OpenAPI
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
        info = @Info(
                title = "Sistema de Inventario de Productos",
                version = "1.0",
                description = "API REST para la gestión de productos. Permite crear, listar, actualizar y eliminar productos del inventario."
        )
)
@SpringBootApplication
public class ProductosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductosApplication.class, args);
    }

}
