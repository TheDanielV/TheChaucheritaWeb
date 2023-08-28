<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Información del Cliente</title>
    <!-- Agrega los enlaces a Bootstrap y otros recursos aquí -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<jsp:include page="/vista/templates/banner_inside.html"/>

<div class="container mt-5">
    <h1>Movimientos por cuenta:
    </h1>

    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>Cuenta</th>
            <th>monto</th>
            <th>Descripcion</th>
            <th>Categoria</th>
            <th>Fecha</th>
            <th>Tipo</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="movimientos" value="${movimientos}" />

        <c:forEach var="indice" begin="0" end="${movimientos.size()-1}" step="1">
            <c:set var="movimiento" value="${movimientos[indice]}" />


            <fmt:formatDate value="${movimiento.getFecha()}" pattern="dd/MM/yyyy" var="fechaFormateada" />

                    <tr>
                    <td>${movimiento.getCuenta().getNombre()}</td>
                    <td>${movimiento.getMonto()}</td>
                    <td>${movimiento.getDescripcion()}</td>
                    <td>${movimiento.getCategoria().getNombre()}</td>
                    <td>${fechaFormateada}</td>
                    <td>${movimiento.getMovimiento().getNombreTipo()}</td>
                    </tr>

                    </c:forEach>

        </tbody>
    </table>

</div>
<!-- Agrega los enlaces a los scripts de Bootstrap y otros recursos aquí -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
