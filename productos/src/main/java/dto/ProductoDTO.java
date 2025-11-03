package com.inventario.productos.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
}
