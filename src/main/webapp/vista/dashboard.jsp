<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Egreso de dinero</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}css/styles.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<%@include file="templates/banner_inside.html"%>
<%@include file="templates/bannerMes.jsp"%>

<div class="container mt-4 ">
<!-- Se tedran las cuentas existentes por usuario -->
    <div class="row">

        <div class="custom-div col-md-6">
            <h1 class="mb-4">Resumen de sus Cuentas </h1>

            <c:forEach items="${cuentas}" var="cuenta">

                <h1 class="mb-2">Cuenta: ${cuenta.getNombre()} </h1>
                <h1 class="mb-2">Total: ${cuenta.getTotal()}$ </h1>
                <form id="formVerCuenta" action="VerMovimientosController?ruta=verPorCuenta&mesDado=${mesDado}" method="post">
                    <!-- se enviara el parametro idCuenta del DTO obtenino y se enviara por post -->

                    <input type="hidden" name="idCuenta" id="idCuenta" value="${cuenta.getId()}"/><br>

                    <button type="submit" id="verCuena" class="btn btn-primary">
                        <span class="material-icons"></span> Ver Movimientos
                    </button>
                </form>

            </c:forEach>
        </div>


        <div class="custom-div col-md-6">
            <h1 class="mb-4">Resumen por Categorias </h1>
            <c:forEach items="${categorias}" var="categoria">

                <h1 class="mb-2"> ${categoria.getNombre()} </h1>
                <h1 class="mb-2"> ${categoria.getTotal()} </h1>
                <form id="formularioPedido" action="VerMovimientosController?ruta=verPorCategoria&mesDado=${mesDado}" method="post">
                    <!-- se enviara el paraametro IdCategoria del DTO obtenino y se enviara por post (---3 es de prueba---)-->
                    <input type="hidden" name="idCategoria" id="idCategoria" value="${categoria.getId()}"/><br>

                    <button type="submit" id="verCategoria" class="btn btn-primary">
                        <span class="material-icons"></span> Ver Movimientos
                    </button>
                </form>
            </c:forEach>
        </div>
    </div>

</div>
</body>
</html>
