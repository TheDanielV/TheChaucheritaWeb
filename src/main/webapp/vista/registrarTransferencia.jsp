<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Transferencia</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>


<div class="container mt-5">
    <h1 class="mb-4">Seleccione las Cuentas </h1>

    <form id="formularioPedido" action="MovimientoController?ruta=confirmarTransferencia" method="post">
        <div class="form-group">
            <label for="cuentaOrigen">Cuentas:</label>
            <select class="form-control" name="cuentaOrigen" id="cuentaOrigen">
                <c:forEach items="${cuentas}" var="cuenta">
                    <option value="${cuenta.id}">${cuenta.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="cuentaDestino">Cuentas:</label>
            <select class="form-control" name="cuentaDestino" id="cuentaDestino">
                <c:forEach items="${cuentas}" var="cuenta">
                    <option value="${cuenta.id}">${cuenta.nombre}</option>
                </c:forEach>
            </select>
        </div>

        <label for="monto">Monto</label> <br>
        <input type="text" name="monto" id="monto" class="form-control" oninput="validarNumero(this)">
        <p id="mensajeError"></p>

        <input type="hidden" name="desc" id="desc" value="Transferencia entre cuentas" class="form-control"/><br>

        <button type="submit" id="confirmarIngreso" class="btn btn-primary">
            <span class="material-icons"></span> Registrar Transferencia
        </button>
    </form>

</div>


<script>
    function validarNumero(input) {
        // Remover cualquier carácter no numérico o punto decimal
        input.value = input.value.replace(/[^0-9.]/g, '');

        // Verificar si hay más de un punto decimal y evitarlo
        if (input.value.split('.').length > 2) {
            input.value = input.value.slice(0, -1);
        }

        // Verificar si el número es válido (puede ser analizado como un número)
        if (isNaN(input.value)) {
            document.getElementById("mensajeError").textContent = "Ingresa un número válido.";
        } else {
            document.getElementById("mensajeError").textContent = "";
        }
    }
</script>

</body>
</html>
