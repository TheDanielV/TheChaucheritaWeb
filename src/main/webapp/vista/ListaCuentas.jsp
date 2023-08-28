<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />
</head>
<body>
<%@include file="templates/banner_inside.html"%>
	
	<h1>Listado de Cuentas</h1>

    <div>
        <a href="CuentaController?ruta=mostrarFormularioCuentas">Agregar cuenta</a>
    </div>

    <table class="dark">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${cuentas}" var="cuenta">
                <tr>
                    <td>${cuenta.getId()}</td>
                    <td>${cuenta.getNombre()}</td>
                    <td><a href="CuentaController?action=eliminar&idCuenta=${cuenta.getId()}">Eliminar</a></td>
                </tr>
        </c:forEach>
        </tbody>
    </table>

</body>
</html>