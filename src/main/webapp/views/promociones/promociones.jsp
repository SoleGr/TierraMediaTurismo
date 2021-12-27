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


	<!-- Comienzan las cards y sus botones -->
	<div class="container pt-5">
		<br>
		<h1 class="text-center">Promociones</h1>
		<br>
		<div class="row">
			<c:if test="${promociones.size() == 0}">
				<div class="alert alert-warning text-center" role="alert">No
					hay ninguna promoción disponible :(</div>
			</c:if>
			<c:forEach items="${promociones}" var="promocion">
					<div class="col-md-4  p-3">
						<div class="card shadow">
							<img src="assets/img/${promocion.imagen}" class="card-img-top"
								alt="Imagen Producto" style="max-height: 170px;">
							<div class="card-body">
								<h5 class="card-title">
									<c:out value="${promocion.nombre}"></c:out>
								</h5>
								<p class="card-text" style="max-height: 20 rem">
									<c:out value="${promocion.descripcion}"></c:out>
								</p>
							</div>
							<div class="text-center m-3">
								<a href="/turismo/productos/promocion?id=${promocion.id}"
									class="boton" aria-current="page" role="button">Más info</a>
							</div>
						</div>
					</div>
			</c:forEach>
		</div>

	</div>

	<jsp:include page="../../partials/footer.jsp"></jsp:include>

</body>
</html>