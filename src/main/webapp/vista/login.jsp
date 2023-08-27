<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<span class="navbar-brand mb-0 h1 ">Chaucherita</span>
				</div>
			</nav>


			<div class="wrapper">
				<div id="formContent">

					<!-- Login Form -->
					<form action="../LoginController?ruta=login" method="POST">
						<fieldset>
							<legend>Login</legend>

							<p>Nombre</p>
							<input type="text" id="nombre" name="nombre" />

							<br>
							<p>Clave</p>
							<input type="password" id="clave" name="clave" />

							<br><br><br>
							<input type="submit" value="Login" />
							<br><br><br>
						</fieldset>
					</form>

					<div class="text-center">
						<p><c:out value="${mensajeError}"/><p>
					</div>

					<form action="../LoginController?ruta=registrar" method="GET">
						<fieldset>
							<input type="submit" value="¿No está registrado? Hágalo aquí." />
						</fieldset>
					</form>

				</div>
			</div>

			<!-- JavaScript Bundle with Popper -->
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
				integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
				crossorigin="anonymous"></script>
		</body>

		</html>