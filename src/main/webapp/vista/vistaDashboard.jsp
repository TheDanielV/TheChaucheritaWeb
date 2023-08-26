<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestor de Cuentas</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
            crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">Gestor de Cuentas</span>
        <span class="navbar-brand mb-0 h3">Usuario logeado: Daniel</span>
        <span class="navbar-brand mb-0 h3"><a class="text-decoration-none text-white" href="login.html">Salir</a></span>
    </div>
</nav>

<div class="container mt-4">
    <div class="row">
        <div class="col-md-6">
            <h2>Movimientos</h2>
            <ul class="list-group">
                <li class="list-group-item">Pichincha $100</li>
                <li class="list-group-item">Efectivo $10 <a href="#">Ajustar Saldo</a></li>
                <!-- Puedes agregar más elementos aquí -->
            </ul>
        </div>
        <div class="col-md-6">
            <h2>Categorías</h2>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Ingreso</h5>
                    <p class="card-text">Nomina $1000</p>
                </div>
            </div>
            <div class="card mt-3">
                <div class="card-body">
                    <h5 class="card-title">Gasto</h5>
                    <p class="card-text">Comida -$20</p>
                </div>
            </div>
            <!-- Puedes agregar más categorías aquí -->
        </div>
    </div>
</div>

</body>
</html>