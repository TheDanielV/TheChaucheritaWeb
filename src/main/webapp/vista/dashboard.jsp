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
    <!-- el mostrar cuentas y categorias se podria hacer coon un foreach, cada uno(similar a ver movimientos)-->
    <h1 class="mb-4">Sus Cuentas </h1>

        <form id="formVerCuenta" action="VerMovimientosController?ruta=verPorCuenta" method="post">
            <!-- se enviara el parametro idCuenta del DTO obtenino y se enviara por post -->

            <input type="hidden" name="idCuenta" id="idCuenta" value="1"/><br>

            <button type="submit" id="verCuena" class="btn btn-primary">
                <span class="material-icons"></span> Ver Movimientos
            </button>
        </form>

    <h1 class="mb-4">Categorias </h1>

    <form id="formularioPedido" action="VerMovimientosController?ruta=verPorCategoria" method="post">
        <!-- se enviara el paraametro IdCategoria del DTO obtenino y se enviara por post (---3 es de prueba---)-->
        <input type="hidden" name="idCategoria" id="idCategoria" value="3"/><br>

        <button type="submit" id="verCategoria" class="btn btn-primary">
            <span class="material-icons"></span> Ver Movimientos
        </button>
    </form>

</div>
</body>
</html>
