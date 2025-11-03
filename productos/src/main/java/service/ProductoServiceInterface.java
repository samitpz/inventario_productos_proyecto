package com.inventario.productos.service;

import com.inventario.productos.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoServiceInterface {
    List<Producto> listarProductos();
    Optional<Producto> obtenerProductoPorId(Long id);
    Producto guardarProducto(Producto producto);
    void eliminarProducto(Long id);
}
