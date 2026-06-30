let emailActivo = localStorage.getItem('wallet_email_usuario') || 'anonimo';
// --- Carga de datos (LocalStorage) ---
let saldo = parseFloat(localStorage.getItem(emailActivo + '_saldo')) || 1000.00; // Sus propios $1000 de regalo
let historial = JSON.parse(localStorage.getItem(emailActivo + '_historial')) || [];
let contactos = JSON.parse(localStorage.getItem('wallet_contactos')) || [
    { nombre: 'Condorito', saldoSimulado: 0 },
    { nombre: 'Pepe Cortizona', saldoSimulado: 0 },
    { nombre: 'Yayita', saldoSimulado: 0 },
    { nombre: 'Huevo Duro', saldoSimulado: 0 }];

//Actualizar pantalla
function actualizarInterfazCompleta() {
    // Actualizar el saldo principal en la pantalla
    $('#txt-saldo').text('$' + saldo.toFixed(0));

    // Limpiar historial, lista de contactos y el menú desplegable de envíos
    $('#lista-historial').empty();
    $('#lista-visual-contactos').empty();
    $('#select-contacto').empty().append('<option value="" selected disabled>Elige un contacto...</option>');

    // Renderizar Historial de transacciones
    if (historial.length === 0) {
        $('#lista-historial').append('<li class="list-group-item text-muted text-center py-3">No hay transacciones registradas</li>');
    } else {
        historial.forEach(function (item) {
            let claseColor = item.tipo === 'Depósito' ? 'text-success' : 'text-danger';
            let signo = item.tipo === 'Depósito' ? '+' : '-';
            let filaHistorial = `
                    <li class="list-group-item d-flex justify-content-between align-items-center py-2">
                        <div>
                            <strong class="${claseColor}">${item.tipo}</strong>
                            <div class="text-muted" style="font-size: 0.8rem;">${item.fecha}</div>
                        </div>
                        <span class="fw-bold ${claseColor}">${signo}$${item.monto.toFixed(0)}</span>
                    </li>
                `;
            $('#lista-historial').prepend(filaHistorial); // Lo más nuevo arriba
        });
    }

    // Renderizar Lista de Contactos y Menú Desplegable
    let miNombreDeUsuario = localStorage.getItem('wallet_nombre_usuario') || "";
    if (contactos.length === 0) {
        $('#lista-visual-contactos').append('<li class="list-group-item text-muted text-center">No tienes contactos</li>');
    } else {
        contactos.forEach(function (contacto, index) {
            if (contacto.nombre.toLowerCase() === miNombreDeUsuario.toLowerCase()) {
                return; // Salta este contacto y continúa con el siguiente del ciclo forEach (evita auto-transferencia)
            }
            // Fila para la lista visual de contactos
            let filaContacto = '<li class="list-group-item d-flex justify-content-between align-items-center px-0">' +
                '<div>' +
                '<span class="fw-bold d-block">' + contacto.nombre + '</span>' +
                '<small class="text-muted">Recibido: $' + contacto.saldoSimulado.toFixed(0) + '</small>' +
                '</div>' +
                '<button class="btn btn-sm btn-outline-danger py-0 px-2 btn-borrar" data-id="' + index + '">&times;</button>' +
                '</li>';
            $('#lista-visual-contactos').append(filaContacto);

            // Opción para el selector de transferencias
            let opcionSelect = '<option value="' + index + '">' + contacto.nombre + '</option>';
            $('#select-contacto').append(opcionSelect);
        });
    }

    // Guardar todos los estados en la memoria del navegador
    localStorage.setItem(emailActivo + '_saldo', saldo);
    localStorage.setItem(emailActivo + '_historial', JSON.stringify(historial));
    localStorage.setItem(emailActivo + '_contactos', JSON.stringify(contactos));
}

// Función auxiliar para registrar movimientos en el historial
function registrarMovimiento(tipo, monto) {
    let fechaActual = new Date().toLocaleString();
    historial.push({
        tipo: tipo,
        monto: monto,
        fecha: fechaActual
    });
    actualizarInterfazCompleta();
}

// Depósitos y envíos
$('#btn-deposito').click(function () {
    let monto = parseFloat($('#monto').val());
    if (isNaN(monto) || monto <= 0) {
        alert("Por favor, ingresa un monto válido mayor a cero.");
        return;
    }
    saldo += monto;
    $('#monto').val('');
    registrarMovimiento('Depósito', monto);
});

$('#btn-retiro').click(function () {
    let monto = parseFloat($('#monto').val());
    if (isNaN(monto) || monto <= 0) {
        alert("Por favor, ingresa un monto válido mayor a cero.");
        return;
    }
    if (monto > saldo) {
        alert("¡Fondos insuficientes! No puedes retirar más de tu saldo disponible.");
        return;
    }
    saldo -= monto;
    $('#monto').val('');
    registrarMovimiento('Retiro', monto);
});

// Eventos gestión contactos
$('#btn-agregar').click(function () {
    let nombreIngresado = $('#input-nombre').val().trim();
    if (nombreIngresado === "") {
        alert("Por favor, escribe un nombre.");
        return;
    }
    let yaExiste = contactos.some(function (c) {
        return c.nombre.toLowerCase() === nombreIngresado.toLowerCase();
    });
    if (yaExiste) {
        alert("Este contacto ya existe en tu lista.");
        return;
    }

    contactos.push({
        nombre: nombreIngresado,
        saldoSimulado: 0
    });
    $('#input-nombre').val('');
    actualizarInterfazCompleta();
});

$('#lista-visual-contactos').on('click', '.btn-borrar', function () {
    let posicion = $(this).data('id');
    contactos.splice(posicion, 1);
    actualizarInterfazCompleta();
});

// Evento tranferencias
$('#btn-transferir').click(function () {
    let emailActivo = localStorage.getItem('wallet_email_usuario') || 'anonimo';
    let posicionContacto = $('#select-contacto').val();
    let montoAEnviar = parseFloat($('#input-monto').val());

    if (posicionContacto === null) {
        alert("Por favor, seleccione un destinatario de la lista.");
        return;
    }
    if (isNaN(montoAEnviar) || montoAEnviar <= 0) {
        alert("Por favor, ingrese un monto válido mayor a cero.");
        return;
    }
    if (montoAEnviar > saldo) {
        alert("¡Fondos insuficientes! Su saldo es de $" + saldo.toFixed(0) + " e intentas enviar $" + montoAEnviar.toFixed(0));
        return;
    }
    let nombreDestinatario = contactos[posicionContacto].nombre;
    let listaUsuariosGlobales = JSON.parse(localStorage.getItem('wallet_usuarios')) || [];
    let cuentaDestinatarioReal = listaUsuariosGlobales.find(u => u.nombre.toLowerCase() === nombreDestinatario.toLowerCase());

    // Ejecutar transferencia matemática
    saldo = saldo - montoAEnviar;
    localStorage.setItem(emailActivo + '_saldo', saldo);

    contactos[posicionContacto].saldoSimulado = contactos[posicionContacto].saldoSimulado + montoAEnviar;
    localStorage.setItem(emailActivo + '_contactos', JSON.stringify(contactos));

    if (cuentaDestinatarioReal) {
        let emailDestinatario = cuentaDestinatarioReal.email;
        let saldoActualDestinatario = parseFloat(localStorage.getItem(emailDestinatario + '_saldo')) || 1000;
        let nuevoSaldoDestinatario = saldoActualDestinatario + montoAEnviar;
        localStorage.setItem(emailDestinatario + '_saldo', nuevoSaldoDestinatario);
        let historialDestinatario = JSON.parse(localStorage.getItem(emailDestinatario + '_historial')) || [];
        historialDestinatario.push({
            tipo: 'Depósito',
            monto: montoAEnviar,
            fecha: new Date().toLocaleString()
        });
        localStorage.setItem(emailDestinatario + '_historial', JSON.stringify(historialDestinatario));
    }
    //let nombreDestinatario = contactos[posicionContacto].nombre;
    $('#input-monto').val('');
    $('#select-contacto').val('');
    
    registrarMovimiento('Envío a ' + nombreDestinatario, montoAEnviar);

    alert("¡Envío exitoso!\nHas transferido $" + montoAEnviar.toFixed(0) + " a " + nombreDestinatario);
});

// Ejecución inicial automática de la interfaz completa
actualizarInterfazCompleta();
