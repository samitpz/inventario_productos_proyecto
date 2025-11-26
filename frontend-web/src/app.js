const API_URL = 'http://localhost:8080/api/productos';
const form = document.getElementById('productForm');

// 🔹 LISTAR PRODUCTOS (GET)
function cargarProductos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById('productTableBody');
            tableBody.innerHTML = '';

            // Revisa si la respuesta es una lista (debería serlo)
            if (Array.isArray(data)) {
                data.forEach(producto => {
                    const row = tableBody.insertRow();
                    row.insertCell().textContent = producto.id;
                    row.insertCell().textContent = producto.nombre;
                    row.insertCell().textContent = producto.descripcion;
                    row.insertCell().textContent = producto.precio;
                    row.insertCell().textContent = producto.stock;

                    // Permite seleccionar la fila para editar/eliminar
                    row.onclick = () => seleccionarProducto(producto);
                });
            }
        })
        .catch(error => console.error('Error al cargar productos. ¿El Backend está corriendo en 8080?'));
    limpiarCampos();
}

// Rellena el formulario al hacer clic en una fila
function seleccionarProducto(producto) {
    document.getElementById('txtId').value = producto.id;
    document.getElementById('txtNombre').value = producto.nombre;
    document.getElementById('txtDescripcion').value = producto.descripcion;
    document.getElementById('txtPrecio').value = producto.precio;
    document.getElementById('txtStock').value = producto.stock;
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
        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
        return response.json();
    })
    .then(() => {
        alert('Producto creado y guardado en MySQL con éxito!');
        cargarProductos(); // Refresca la tabla
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
        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
        return response.json();
    })
    .then(() => {
        alert('Producto actualizado con éxito!');
        cargarProductos();
    })
    .catch(error => alert('Fallo al actualizar producto.'));
}

// 🔹 ELIMINAR PRODUCTO (DELETE)
function eliminarProducto() {
    const id = document.getElementById('txtId').value;
    if (!id) { alert('Selecciona un producto de la tabla para eliminar.'); return; }

    fetch(`${API_URL}/${id}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (!response.ok && response.status !== 204) throw new Error(`HTTP error! status: ${response.status}`);
        alert('Producto eliminado con éxito!');
        cargarProductos();
    })
    .catch(error => alert('Fallo al eliminar producto.'));
}

// --- UTILIDADES ---

function obtenerDatosFormulario() {
    const nombre = document.getElementById('txtNombre').value.trim();
    const descripcion = document.getElementById('txtDescripcion').value.trim();
    const precio = document.getElementById('txtPrecio').value;
    const stock = document.getElementById('txtStock').value;

    if (!nombre || !precio || !stock) {
        alert('Completa los campos obligatorios: Nombre, Precio y Stock.');
        return null;
    }

    return {
        nombre: nombre,
        descripcion: descripcion,
        precio: parseFloat(precio),
        stock: parseInt(stock)
    };
}

function limpiarCampos() {
    form.reset();
    document.getElementById('txtId').value = '';
}

// Cargar productos al iniciar la página
window.onload = cargarProductos;