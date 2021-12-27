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
	<!-- NAV s -->
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
						<li class="nav-item"><a class="nav-link"
							aria-current="page" href="/turismo/admin-usuarios"> <i
								class="fas fa-users"></i> <span class="ml-2">Usuarios</span>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/turismo/admin-atracciones"> <i class="fas fa-mountain"></i>
								<span class="ml-2">Atracciones</span>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/turismo/admin-promociones"> <i class="fas fa-route"></i>
								<span class="ml-2">Promociones</span>
						</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="/turismo/admin-tipos"> <i class="fas fa-shapes"></i> <span
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

				<form action="/turismo/admin-tipos/crear" method="post" class="shadow">
					<div class="modal-body">
						<h1 class="text-center">Nuevo Tipo <i class="fas fa-shapes"></i>
						</h1>
						<div class="row">
							<div class="col-lg-6">
								<div class="mb-3">
									<label for="nombre" class="col-form-label">Nombre:</label>
									<input type="text" class="form-control" id="nombre" name="nombre" required>
								</div>
							</div>
							
							<div class="col-lg-6">
								<div class="mb-3">
									<label for="imagen"
										class='col-form-label ${usuario.errores.get("imagen") != null ? "is-invalid" : "" }'>Imagen
										de perfil:</label> <input class="form-control" type="file" id="imagen"
										name="imagen" accept="image/*" required multiple></input>
									<div class="invalid-feedback">
										<c:out value='${usuario.errores.get("imagen")}'></c:out>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="mb-3">
										<label for="descripcion" class='col-form-label'>Descripción:</label>
										<input class="form-control" type="text" id="descripcion"
											name="descripcion" required></input>
									</div>
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