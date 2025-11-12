package com.inventario.productos.service;

import com.inventario.productos.dto.ProductoDTO;
import java.util.List;

public interface ProductoServiceInterface {
    List<ProductoDTO> listar();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO guardar(ProductoDTO productoDTO);
    ProductoDTO actualizar(Long id, ProductoDTO productoDTO);
    void eliminar(Long id);
}
