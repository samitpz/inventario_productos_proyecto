package com.inventario.productos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;


    private String categoria;
    private Long proveedor_id;

    public Producto() {}

    // Constructor con campos nuevos
    public Producto(String nombre, String descripcion, Double precio, Integer stock, String categoria, Long proveedor_id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria; // Nuevo
        this.proveedor_id = proveedor_id; // Nuevo
    }

    // Getters y Setters EXISTENTES
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }


    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Long getProveedor_id() { return proveedor_id; }
    public void setProveedor_id(Long proveedor_id) { this.proveedor_id = proveedor_id; }
}