const API_URL = 'http://localhost:8080/api/productos';
const form = document.getElementById('productForm');

// 🔹 LISTAR PRODUCTOS (GET)
function cargarProductos() {
    fetch(API_URL)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error al conectar con la API: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            const tableBody = document.getElementById('productTableBody');
            tableBody.innerHTML = '';

            if (Array.isArray(data)) {
                data.forEach(producto => {
                    const row = tableBody.insertRow();
                    row.insertCell().textContent = producto.id;
                    row.insertCell().textContent = producto.nombre;
                    row.insertCell().textContent = producto.descripcion;
                    row.insertCell().textContent = producto.precio;
                    row.insertCell().textContent = producto.stock;
                    row.insertCell().textContent = producto.categoria;
                    // Asegurar que muestra 'null' o '0' si no hay proveedor_id
                    row.insertCell().textContent = producto.proveedor_id || 'N/A';

                    row.onclick = () => seleccionarProducto(producto);
                    row.classList.add('table-light', 'cursor-pointer');
                });
            }
        })
        .catch(error => {
            console.error('Error al cargar productos:', error.message);
            alert('Fallo al cargar productos. ¿El Backend está corriendo en http://localhost:8080?');
        });
    limpiarCampos();
}

// Rellena el formulario al hacer clic en una fila
function seleccionarProducto(producto) {
    document.getElementById('txtId').value = producto.id;
    document.getElementById('txtNombre').value = producto.nombre;
    document.getElementById('txtDescripcion').value = producto.descripcion;
    document.getElementById('txtPrecio').value = producto.precio;
    document.getElementById('txtStock').value = producto.stock;
    // ⬅️ NUEVOS CAMPOS AÑADIDOS AL FORMULARIO
    document.getElementById('txtCategoria').value = producto.categoria || '';
    document.getElementById('txtProveedorId').value = producto.proveedor_id || '';
}

// 🔹 CREAR PRODUCTO (POST)
function crearProducto() {
    const data = obtenerDatosFormulario();
    if (!data) return;

    fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    })
    .then(response => {
        if (!response.ok) throw new Error(`Error HTTP: ${response.status} ${response.statusText}`);
        return response.json();
    })
    .then(() => {
        alert('Producto creado y guardado en MySQL con éxito!');
        cargarProductos();
    })
    .catch(error => alert('Fallo al crear producto. Revisa la consola: ' + error.message));
}

// 🔹 ACTUALIZAR PRODUCTO (PUT)
function actualizarProducto() {
    const id = document.getElementById('txtId').value;
    if (!id) { alert('Selecciona un producto de la tabla para actualizar.'); return; }
    const data = obtenerDatosFormulario();
    if (!data) return;

    fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    })
    .then(response => {
        if (!response.ok) throw new Error(`Error HTTP: ${response.status} ${response.statusText}`);
        return response.text().then(text => text ? JSON.parse(text) : {});
    })
    .then(() => {
        alert('Producto actualizado con éxito!');
        cargarProductos();
    })
    .catch(error => alert('Fallo al actualizar producto. Revisa la consola: ' + error.message));
}

// 🔹 ELIMINAR PRODUCTO (DELETE)
function eliminarProducto() {
    const id = document.getElementById('txtId').value;
    if (!id) { alert('Selecciona un producto de la tabla para eliminar.'); return; }

    if (!confirm(`¿Estás seguro de que quieres eliminar el producto con ID ${id}?`)) {
        return;
    }

    fetch(`${API_URL}/${id}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (!response.ok && response.status !== 204) throw new Error(`Error HTTP: ${response.status} ${response.statusText}`);
        alert('Producto eliminado con éxito!');
        cargarProductos();
    })
    .catch(error => alert('Fallo al eliminar producto. Revisa la consola: ' + error.message));
}

// --- UTILIDADES ---

function obtenerDatosFormulario() {
    const nombre = document.getElementById('txtNombre').value.trim();
    const descripcion = document.getElementById('txtDescripcion').value.trim();
    const precio = document.getElementById('txtPrecio').value;
    const stock = document.getElementById('txtStock').value;
    const categoria = document.getElementById('txtCategoria').value.trim();
    const proveedorId = document.getElementById('txtProveedorId').value;


    if (!nombre || !precio || !stock) {
        alert('Completa los campos obligatorios: Nombre, Precio y Stock.');
        return null;
    }

    return {
        nombre: nombre,
        descripcion: descripcion,
        precio: parseFloat(precio),
        stock: parseInt(stock),
        categoria: categoria,
        proveedor_id: proveedorId ? parseInt(proveedorId) : null
    };
}

function limpiarCampos() {
    form.reset();
    document.getElementById('txtId').value = '';
}

window.onload = cargarProductos;