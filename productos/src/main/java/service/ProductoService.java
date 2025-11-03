package com.inventario.productos.service;

import com.inventario.productos.model.Producto;
import com.inventario.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements ProductoServiceInterface {

    private final ProductoRepository repository;

    @Autowired
    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Producto> listarProductos() {
        return repository.findAll();
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        repository.deleteById(id);
    }
}
