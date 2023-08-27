<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>ProyectoWebGrupo6</title>
			<!-- CSS only -->
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
				integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
				crossorigin="anonymous">
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" />

		</head>

		<body>


			<nav class="navbar navbar-dark bg-dark">
				<div class="container-fluid">
					<span class="navbar-brand mb-0 h1 ">Registro Usuarios</span>
				</div>
			</nav>


			<div class="wrapper">
				<div id="formContent">

					<!-- Login Form -->
					<form action="RegistrarUsuarioController?=registrarUsuario" method="POST">
						<fieldset>
							<legend>Registro</legend>

							<p>Nombre</p>
							<input type="text" name="nombre" />

							<br>
							<p>Clave</p>
							<input type="password" name="clave" />

							<br><br><br>
							<input type="submit" value="Registrar" />
							<br><br><br>
						</fieldset>
					</form>

					<div class="text-center">
						<p>
							<c:out value="${mensajeError}" />
						<p>
					</div>

					<div>
						<a href="LoginController?ruta=inicio">Regresar a Login</a>
					</div>

				</div>
			</div>

			<!-- JavaScript Bundle with Popper -->
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
				crossorigin="anonymous"></script>
		</body>

		</html>