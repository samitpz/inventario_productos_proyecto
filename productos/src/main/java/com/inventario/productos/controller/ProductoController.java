package com.inventario.productos.controller;

import com.inventario.productos.dto.ProductoDTO;
import com.inventario.productos.service.ProductoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoServiceInterface service;

    @GetMapping
    public List<ProductoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ProductoDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public ProductoDTO crear(@RequestBody ProductoDTO productoDTO) {
        return service.guardar(productoDTO);
    }

    @PutMapping("/{id}")
    public ProductoDTO actualizar(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return service.actualizar(id, productoDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
