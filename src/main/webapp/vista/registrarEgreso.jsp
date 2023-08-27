<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Egreso de dinero</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<%@include file="templates/banner_inside.html"%>

<div class="container mt-5">
    <h1 class="mb-4">Seleccione la cuenta </h1>

    <form id="formularioPedido" action="MovimientoController?ruta=confirmarEgreso" method="post">
        <div class="form-group">
            <label for="cuenta">Cuentas:</label>
            <select class="form-control" name="cuenta" id="cuenta">
                <c:forEach items="${cuentas}" var="cuenta">
                    <option value="${cuenta.id}">${cuenta.nombre}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Campo de selección de  Categoria-->
        <div class="form-group">
            <label for="categoriaID">Categorias:</label>
            <select class="form-control" name="categoriaID" id="categoriaID">
                <c:forEach items="${categorias}" var="categoria">
                    <option value="${categoria.id}">${categoria.nombre}</option>
                </c:forEach>
            </select>
        </div>
        <label for="monto">Monto</label> <br>
        <input type="text" name="monto" id="monto" class="form-control" oninput="validarNumero(this)">
        <p id="mensajeError"></p>
        <label for="desc">Descripcion</label> <br>
        <input type="text" name="desc" id="desc" class="form-control"/><br>

        <button type="submit" id="confirmarIngreso" class="btn btn-primary">
            <span class="material-icons"></span> Registrar Egreso
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
