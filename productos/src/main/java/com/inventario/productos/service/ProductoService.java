package com.inventario.productos.service;

import com.inventario.productos.dto.ProductoDTO;
import com.inventario.productos.model.Producto;
import com.inventario.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// 🟢 IMPORTACIÓN NECESARIA PARA LA GESTIÓN DE TRANSACCIONES
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements ProductoServiceInterface {

    @Autowired
    private ProductoRepository repository;

    private ProductoDTO convertir(Producto producto) {
        return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(),
                producto.getPrecio(), producto.getStock());
    }

    private Producto convertirDTO(ProductoDTO dto) {
        return new Producto(dto.getNombre(), dto.getDescripcion(), dto.getPrecio(), dto.getStock());
    }


    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> listar() {
        return repository.findAll().stream().map(this::convertir).collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public ProductoDTO obtenerPorId(Long id) {
        return repository.findById(id).map(this::convertir).orElse(null);
    }


    @Override
    @Transactional
    public ProductoDTO guardar(ProductoDTO productoDTO) {
        Producto p = convertirDTO(productoDTO);
        return convertir(repository.save(p));
    }


    @Override
    @Transactional
    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto producto = repository.findById(id).orElseThrow();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        return convertir(repository.save(producto));
    }


    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}