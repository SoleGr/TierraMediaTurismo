<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<jsp:include page="../../partials/noAdmin.jsp"></jsp:include>

<c:if test="${usuario.esAdmin()}">
<head>
<jsp:include page="../../partials/head-admin.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="../../partials/nav-admin.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<nav id="sidebar"
				class="px-3 col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
				<div class="position-sticky pt-md-5">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="/turismo/index-admin.jsp"> <i class="fas fa-home"></i>
								<span class="ml-2">Inicio</span>
						</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/turismo/admin-usuarios.do"> <i
								class="fas fa-users"></i> <span class="ml-2">Usuarios</span>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/turismo/admin-atracciones.do"> <i class="fas fa-mountain"></i>
								<span class="ml-2">Atracciones</span>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/turismo/admin-promociones.do"> <i class="fas fa-route"></i>
								<span class="ml-2">Promociones</span>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/turismo/admin-tipos.do"> <i class="fas fa-shapes"></i> <span
								class="ml-2">Tipos</span>
						</a></li>
					</ul>
				</div>
			</nav>

			<main class="col-md-9 ml-sm-auto col-lg-10 px-md-5 py-4">

				<c:if test="${usuario != null && !usuario.esValido()}">
					<div class="alert alert-danger">
						<p>Se encontraron errores al crear el usuario.</p>
					</div>
				</c:if>




				<form action="/turismo/admin-usuarios/modificar.do" method="post" class="shadow">
					<div class="modal-body">
						<h1 class="text-center">
							Modificar usuario <i class="fas fa-user-edit"></i>
						</h1>
						<div class="row">
							<div class="col-lg-4 mb-3">
								<label for="id" class='col-form-label'>Id:</label> <input
									class="form-control" type="number" id="id"
									value="${modificable.id}" name="id" readonly></input>

							</div>
							<div class="col-lg-4">
								<div class="mb-3">
									<label for="nombre" class="col-form-label">Nombre:</label> <input
										type="text" class="form-control" id="nombre" name="nombre"
										value="${modificable.nombre}" required>
								</div>
							</div>
						</div>
				
					<div class="row">
						<div class="col-lg-4 mb-3">
							<label for="tipos" class="col-form-label">Tipo preferido:</label>
							<select class="form-select" aria-label="Default select example"
								name="tipo" id="tipos" required>
								<option style="font-weight: bold;" value="${modificable.tipo.id}" selected><c:out
											value="${modificable.tipo.nombre}"></c:out></option>
									<c:forEach items="${tipos}" var="tipo">
									<option value="${tipo.id}"><c:out
											value="${tipo.nombre}"></c:out></option>
								</c:forEach>
							</select>
						</div>
						<div class="col-lg-4 mb-3">
								<label for="monedas"
									class='col-form-label ${modificable.errores.get("monedas") != null ? "is-invalid" : "" }'>Monedas:</label>
								<input class="form-control" type="number" id="monedas" min="0"
									value="${modificable.monedas}" name="monedas" required></input>
								<div class="invalid-feedback">
									<c:out value='${modificable.errores.get("monedas")}'></c:out>
								</div>
						</div>
						<div class="col-lg-4 mb-3">
								<label for="tiempo"
									class='col-form-label ${modificable.errores.get("tiempoDisponible") != null ? "is-invalid" : "" }'>Tiempo:</label>
								<input class="form-control" type="number" id="tiempo" min="0"
									value="${modificable.tiempoDisponible}" name="tiempo" required></input>
								<div class="invalid-feedback">
									<c:out value='${modificable.errores.get("tiempoDisponible")}'></c:out>
								</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6 mb-3">
							<span>Imagen cargada:</span> <img
								src="../assets/img/${modificable.imagenPerfil}" height="100">
						</div>

						<div class="col-lg-6">
							<div class="mb-3">
								<label for="imagen"
									class='col-form-label ${modificable.errores.get("imagen") != null ? "is-invalid" : "" }'>Nueva
									imagen de perfil:</label> <input class="form-control" type="file"
									id="imagen" name="imagen" value=""></input>
								<div class="invalid-feedback">
									<c:out value='${modificable.errores.get("imagen")}'></c:out>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="mb-3">
								<label for="contrasenia" class='col-form-label'>Nueva
									Contraseña (Ingresar solo si desea cambiarla):</label> <input
									class="form-control" type="password" id="contrasenia"
									name="contrasenia" value=""></input>
							</div>
						</div>
					</div>

				</div>

				<div class="text-center py-3">
					<button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Guardar</button>
					<a onclick="window.history.back();" class="btn btn-secondary"
						role="button"><i class="fas fa-times"></i> Cancelar</a>
				</div>
			</form>
		</main>
	</div>
	</div>

	<footer class="float-right px-5 py-5">
		<span>Copyright © 2021 <a href="#">FreakisTeam</a></span>
	</footer>
</body>
</c:if>
</html>