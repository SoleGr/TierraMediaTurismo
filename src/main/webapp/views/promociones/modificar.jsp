<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<!-- Si no es Admin muestra esto -->
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
							<li class="nav-item"><a class="nav-link" aria-current="page"
								href="/turismo/admin-usuarios.do"> <i class="fas fa-users"></i>
									<span class="ml-2">Usuarios</span>
							</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/turismo/admin-atracciones.do"> <i
									class="fas fa-mountain"></i> <span class="ml-2">Atracciones</span>
							</a></li>
							<li class="nav-item"><a class="nav-link active"
								href="/turismo/admin-promociones.do"> <i
									class="fas fa-route"></i> <span class="ml-2">Promociones</span>
							</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/turismo/admin-tipos.do"> <i class="fas fa-shapes"></i>
									<span class="ml-2">Tipos</span>
							</a></li>
						</ul>
					</div>
				</nav>

				<main class="col-md-9 ml-sm-auto col-lg-10 px-md-5 py-4">

					<c:if test="${promocion != null && !promocion.esValido()}">
						<div class="alert alert-danger">
							<p>Se encontraron errores al crear la atracción.</p>
						</div>
					</c:if>




					<form
						action="/turismo/admin-promociones/modificar.do?id=${promocion.id}"
						method="post" class="shadow">
						<div class="modal-body">
							<h1 class="text-center">
								Modificar promoción <i class="fas fa-route"></i>
							</h1>
							<div class="row">
								<div class="col-lg-4 mb-3">
									<label for="nombre" class="col-form-label">Nombre:</label> <input
										type="text" class="form-control" id="nombre" name="nombre"
										value="${promocion.nombre}" required>
								</div>
								<div class="row">
									<small>Completá para cambiar el tipo y valor del
										descuento:</small>
									<div class="col-sm-2 mb-3">
										<label for="tipoPromocion" class="col-form-label">Tipo
											promo:</label> <select class="form-select"
											aria-label="Default select example" name="tipoPromocion"
											id="tipoPromocion" required>
											<option value="${promocion.tipoPromocion}" selected><c:out
													value="${promocion.tipoPromocion}"></c:out></option>
											<option value="Absoluta">Absoluta</option>
											<option value="AxB">AxB</option>
											<option value="Porcentual">Porcentual</option>
										</select>
									</div>

									<div class="col-lg-2 mb-3">
										<label for="descuento" class="col-form-label">Descuento:</label>
										<input type="text" class="form-control" id="descuento"
											name="descuento" value="${absoluta}">(monedas)
									</div>

									<div class="col-lg-2 mb-3">
										<label for="porcentaje" class="col-form-label">Porcentaje:</label>
										<input type="text" class="form-control" id="porcentaje"
											name="porcentaje" value="${porcentual}">(%)
									</div>

									<div class="col-sm-4 mb-3">
										<label for="atraccionGratis" class="col-form-label">Atracción
											gratuita:</label> <select class="form-select"
											aria-label="Default select example" name="atraccionGratis"
											id="atraccionGratis">
											<option value="${axb}" selected><c:out
													value="${axb}"></c:out></option>
											<c:forEach items="${atracciones}" var="atraccion">
												<option value="${atraccion.id}"><c:out
														value="${atraccion.nombre}"></c:out>-
													<c:out value="${atraccion.tipo.nombre}"></c:out></option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-4">
										<div class="mb-3">
											<label for="tipoAtraccion" class="col-form-label">Tipo
												de atracción:</label> <select class="form-select"
												aria-label="Default select example" name="tipoAtraccion"
												id="tipoAtraccion" required>
												<option value="${promocion.tipo.id}" selected><c:out
														value="${promocion.tipo.nombre}"></c:out>
												</option>
												<c:forEach items="${tipos}" var="tipo">
													<option value="${tipo.id}"><c:out
															value="${tipo.nombre}"></c:out></option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-lg-4 mb-3">
										<select class="form-select" size="9" multiple
											aria-label="multiple select example" name="atracciones"
											required>
											<option value="0" selected>Seleccioná las nuevas
												atracciones</option>
											<c:forEach items="${atracciones}" var="atraccion">
												<option value="${atraccion.id}"><c:out
														value="${atraccion.nombre}"></c:out>
													<c:out value="${atraccion.tipo.nombre}"></c:out></option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="row">
									<div class="col-lg-12 mb-3">
										<label for="descripcion" class='col-form-label'>Descripción:</label>
										<input class="form-control" type="text" id="descripcion"
											value="${promocion.descripcion}" name="descripcion" required></input>

									</div>
								</div>
								<div class="row">
									<div class="col-lg-6 mb-3">
										<span>Imagen cargada:</span> <img
											src="../assets/img/${promocion.imagen}" height="100">
									</div>

									<div class="col-lg-6">
										<div class="mb-3">
											<label for="imagen" class='col-form-label'>Cambiar
												imagen:</label> <input class="form-control" type="file" id="imagen"
												name="imagen" value="../assets/img/${promocion.imagen}"></input>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="text-center py-3">
							<button type="submit" class="btn btn-primary">
								<i class="fas fa-save"></i> Guardar
							</button>
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