<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-grey ">
    <div class="container-fluid row">
        <div class="col-md-6">
           <h4>Viendo Por mes de: ${mesDado}</h4>
        </div>
        <div class="col-md-6">
            <form id="formMes" action="VerMovimientosController?ruta=dashboard" method="post" class="row">
                <select class="form-control col-md-6" name="mesDado" id="mesDado">
                <c:forEach items="${meses}" var="mes">
                    <option value="${mes}">${mes}</option>
                </c:forEach>
                </select>
                <button type="submit" id="mes" class="btn btn-primary col-md-6">
                    <span class="material-icons"></span> Confirmar Mes
                </button>
                </form>
        </div>
    </div>
</nav>
</body>
</html>