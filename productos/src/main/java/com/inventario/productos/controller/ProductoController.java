package com.inventario.productos.controller;

import com.inventario.productos.dto.ProductoDTO;
import com.inventario.productos.model.Producto;
import com.inventario.productos.service.ProductoServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoServiceInterface service;

    @Autowired
    public ProductoController(ProductoServiceInterface service) {
        this.service = service;
    }

    // ✅ 1. Listar todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        List<Producto> productos = service.listarProductos();
        return ResponseEntity.ok(productos);
    }

    // ✅ 2. Crear un producto nuevo con validaciones
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        Producto guardado = service.guardarProducto(producto);
        return ResponseEntity.ok(guardado);
    }

    // ✅ 3. Actualizar un producto existente (nuevo método solicitado)
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO dto) {
        Producto producto = service.obtenerProductoPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        Producto actualizado = service.guardarProducto(producto);
        return ResponseEntity.ok(actualizado);
    }

    // ✅ 4. Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        service.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
