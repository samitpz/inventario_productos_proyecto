package com.inventario.cliente.controller;

import com.inventario.cliente.model.Producto;
import com.inventario.cliente.service.ProductoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.List;

public class ProductoController {

    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, Long> colId;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, String> colDescripcion;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;

    private final ProductoService service = new ProductoService();
    private final ObservableList<Producto> lista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        cargarProductos();

        // Evento para llenar los campos al seleccionar una fila
        tablaProductos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtId.setText(String.valueOf(newSel.getId()));
                txtNombre.setText(newSel.getNombre());
                txtDescripcion.setText(newSel.getDescripcion());
                txtPrecio.setText(String.valueOf(newSel.getPrecio()));
                txtStock.setText(String.valueOf(newSel.getStock()));
            }
        });
    }

    @FXML
    public void cargarProductos() {
        try {
            List<Producto> productos = service.listarProductos();
            lista.setAll(productos);
            tablaProductos.setItems(lista);
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudieron cargar los productos: " + e.getMessage());
        }
    }

    @FXML
    public void crearProducto() {
        try {
            if (txtNombre.getText().isBlank() || txtPrecio.getText().isBlank() || txtStock.getText().isBlank()) {
                mostrarAlerta("Campos vacíos", "Completa todos los campos antes de crear un producto.");
                return;
            }

            Producto p = new Producto(
                    null,
                    txtNombre.getText(),
                    txtDescripcion.getText(),
                    Double.parseDouble(txtPrecio.getText()),
                    Integer.parseInt(txtStock.getText())
            );

            service.crearProducto(p);
            mostrarAlerta("Éxito", "Producto creado correctamente.");
            limpiarCampos();
            cargarProductos();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo crear el producto: " + e.getMessage());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Verifica que los campos de precio y stock sean numéricos.");
        }
    }

    @FXML
    public void actualizarProducto() {
        try {
            if (txtId.getText().isBlank()) {
                mostrarAlerta("Error", "Selecciona un producto para actualizar.");
                return;
            }

            Producto p = new Producto(
                    Long.parseLong(txtId.getText()),
                    txtNombre.getText(),
                    txtDescripcion.getText(),
                    Double.parseDouble(txtPrecio.getText()),
                    Integer.parseInt(txtStock.getText())
            );

            service.actualizarProducto(p.getId(), p);
            mostrarAlerta("Éxito", "Producto actualizado correctamente.");
            limpiarCampos();
            cargarProductos();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo actualizar el producto: " + e.getMessage());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Verifica que los campos de precio y stock sean numéricos.");
        }
    }

    @FXML
    public void eliminarProducto() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Error", "Selecciona un producto para eliminar.");
            return;
        }

        try {
            service.eliminarProducto(seleccionado.getId());
            mostrarAlerta("Éxito", "Producto eliminado correctamente.");
            cargarProductos();
            limpiarCampos();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo eliminar el producto: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtId.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
        txtStock.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
