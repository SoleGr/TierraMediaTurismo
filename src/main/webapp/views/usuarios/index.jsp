<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

	<jsp:include page="../../partials/noAdmin.jsp"></jsp:include>
	
<head>
<jsp:include page="../../partials/head-admin.jsp"></jsp:include>
</head>

<body>
	<jsp:include page="../../partials/nav-admin.jsp"></jsp:include>

	<div class="container-fluid">
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
				<!-- Si hay errores los muestra aca -->
				<c:if test="${flash != null}">
					<div class="alert alert-danger">
						<p>
							<c:out value="${flash}" />
							<c:if test="${errores != null}">
								<ul>
									<c:forEach items="${errores}" var="entry">
										<li><c:out value="${entry.getValue()}"></c:out></li>
									</c:forEach>
								</ul>
							</c:if>
						</p>
					</div>
				</c:if>

				<h1 class="h2 text-center">
					<i class="fas fa-users"></i> Usuarios
				</h1>

				<p class="text-center">Altas, bajas, modificaciones y consultas</p>
				<br>
				<div class="mb-3 text-right">
					<a href="/turismo/admin-usuarios/crear.do" class="btn btn-primary"
						role="button"> <i class="fas fa-user-plus"></i> Nuevo Usuario
					</a>
				</div>

				<table class="table table-striped table-hover shadow display nowrap pt-2" id="tabla">
					<thead class="table-dark">
						<tr>
							<th scope="col">Nombre</th>
							<th scope="col">Preferencia</th>
							<th scope="col">Monedas</th>
							<th scope="col">Tiempo</th>
							<th scope="col">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${usuarios}" var="usuario">
							<tr>
								<th scope="col"><c:out value="${usuario.nombre}"></c:out></th>
								<td scope="col"><c:out value="${usuario.tipo.nombre}"></c:out></td>
								<td scope="col"><c:out value="${usuario.monedas}"></c:out></td>
								<td scope="col"><c:out value="${usuario.tiempoDisponible}"></c:out></td>

								<td style="width: 11%">
									<a href="/turismo/admin-usuarios/modificar.do?id=${usuario.id}"
									class="btn btn-warning rounded" role="button"><i class="fas fa-pen"></i></a>
									<a class="btn btn-danger rounded" href="/turismo/admin-usuarios/eliminar.do?id=${usuario.id}"
									type="button" name="button" role="button"> <i class="fas fa-trash-alt"></i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>




			</main>
		</div>
	</div>

	<footer class="float-right px-5 py-5">
		<span>Copyright © 2021 <a href="#">FreakisTeam</a></span>
	</footer>
	<!-- ================= MODAL ELIMINACION ================== -->


	<!-- Modal -->
	<div class="modal fade" id="ExampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<input id="userId" value="">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Advertencia</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>
						Se va a eliminar el usuario
						<c:out value="${usuario.nombre}"></c:out>
						.
					</p>
					<p>¿Está seguro que desea continuar?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cerrar</button>
					<button type="button" name="button" class="btn btn-danger"
						onclick="eliminarUsuario()">Eliminar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- ================= FIN MODAL ELIMINACION ================== -->

</body>



</html>