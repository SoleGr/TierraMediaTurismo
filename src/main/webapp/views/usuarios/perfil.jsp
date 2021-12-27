<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../partials/head.jsp"></jsp:include>
</head>

<body data-spy="scroll" data-target=".fixed-top">

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<jsp:include page="../../partials/nav.jsp"></jsp:include>

	<br>
	<br>
	<br>


	<div class="container">
		<br> <br>
		<h1 class="text-center">Mi perfil</h1>
		<br>

		<div class="class container">
			<div class="class row pb-5">
				<div class="class col-sm-6 text-center">
					<img
						src="<%=request.getContextPath()%>/assets/img/${usuario.imagenPerfil}"
						style="max-height: 200px;">
				</div>
				<div class="class col-sm-6">
					<h4 class="pt-4">
						Nombre:
						<c:out value="${usuario.nombre}"></c:out>
					</h4>
					<h6>
						Tipo preferido:
						<c:out value="${usuario.tipo.nombre}"></c:out>
					</h6>
					<h6>
						Dinero:
						<c:out value="${usuario.monedas}"></c:out>
						monedas
					</h6>
					<h6>
						Tiempo:
						<c:out value="${usuario.tiempoDisponible}"></c:out>
						horas
					</h6>
				</div>
			</div>

			<c:if test="${ok != null}">
				<div class="alert alert-success text-center" role="alert">
						<c:out value="${ok}" /></div>
			</c:if>

			<c:if test='${usuario.errores.get("comprar") != null}'>
				<div class="alert alert-danger text-center">
						<c:out value='${usuario.errores.get("comprar")}' />
				</div>
			</c:if>

			<c:choose>
				<c:when test="${usuario.sinCompras()}">
					<div class="alert alert-warning text-center" role="alert">
						Todavía no realizaste ninguna compra.</div>
				</c:when>
				<c:otherwise>
					<h3>Mi itinerario</h3>

					<table class="table py-5">
						<thead>
							<tr>
								<th scope="col">Tipo</th>
								<th scope="col">Nombre</th>
								<th scope="col">Tiempo</th>
								<th scope="col">Precio</th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${usuario.productosComprados}" var="producto">
								<tr>
									<th scope="row"><c:out value="${producto.tipo.nombre}"></c:out></th>
									<td><c:out value="${producto.nombre}"></c:out></td>
									<td><c:out value="${producto.tiempo}"></c:out></td>
									<td><c:out value="${producto.precio}"></c:out></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!-- Footer -->
	<jsp:include page="../../partials/footer.jsp"></jsp:include>

</body>
</html>