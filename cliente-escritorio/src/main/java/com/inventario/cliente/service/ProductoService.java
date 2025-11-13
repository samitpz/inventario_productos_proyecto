package com.inventario.cliente.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventario.cliente.model.Producto;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ProductoService {

    private static final String BASE_URL = "http://localhost:8080/api/productos";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 🔹 LISTAR PRODUCTOS (GET)
    public List<Producto> listarProductos() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            try (InputStream input = conn.getInputStream()) {
                return objectMapper.readValue(input, new TypeReference<List<Producto>>() {});
            }
        } else {
            throw new IOException("Error al listar productos: " + conn.getResponseCode());
        }
    }

    // 🔹 CREAR PRODUCTO (POST)
    public Producto crearProducto(Producto producto) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            objectMapper.writeValue(os, producto);
        }

        if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201) {
            try (InputStream input = conn.getInputStream()) {
                return objectMapper.readValue(input, Producto.class);
            }
        } else {
            throw new IOException("Error al crear producto: " + conn.getResponseCode());
        }
    }

    // 🔹 ACTUALIZAR PRODUCTO (PUT)
    public Producto actualizarProducto(Long id, Producto producto) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            objectMapper.writeValue(os, producto);
        }

        if (conn.getResponseCode() == 200) {
            try (InputStream input = conn.getInputStream()) {
                return objectMapper.readValue(input, Producto.class);
            }
        } else {
            throw new IOException("Error al actualizar producto: " + conn.getResponseCode());
        }
    }

    // 🔹 ELIMINAR PRODUCTO (DELETE)
    public void eliminarProducto(Long id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        if (conn.getResponseCode() != 204 && conn.getResponseCode() != 200) {
            throw new IOException("Error al eliminar producto: " + conn.getResponseCode());
        }
    }
}
