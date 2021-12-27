<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">

<head>	
	<jsp:include page="../../partials/head.jsp"></jsp:include>
</head>

<body data-spy="scroll" data-target=".fixed-top">

	<!-- NAV -->
	<jsp:include page="../../partials/nav.jsp"></jsp:include>
	
	<div class="container py-5">
		<br> <br> <br>
		<h1>
			<c:out value="${promocion.nombre}"></c:out>
		</h1>
		<br>
		<div class="card-group">
			<div class="card">
				<img src="../assets/img/${promocion.imagen}" class="card-img-top"
					alt="...">
				<div class="card-body">
					<p class="card-text">
						<c:out value="${promocion.descripcion}"></c:out>
					</p>
				</div>
			</div>
		</div>
		<!-- botÃ³n -->
		<div class="d-flex flex-row-reverse bd-highlight">
			<div class="p-2 bd-highlight">
				<div class="d-grid gap-2">
					<a type="button" class="btn btn-success btn-md"
						href="/turismo/promociones"> Ver todas las promociones &nbsp;
						<i class="fas fa-arrow-right"></i>
					</a>
				</div>
			</div>
		</div>
		<br>
		<!-- fin del botÃ³n -->
	</div>
	<!-- Terminan las cards -->
	<br>

	<!-- Statistics -->
	<div class="counter">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">

					<!-- Counter -->
					<div id="counter">
						<div class="cell">
							<h2 class="card-title">Tiempo total</h2>
							<div class="counter-value number-count"
								data-count="${promocion.tiempo}"></div>
							<p class="counter-info">horas de duración</p>
						</div>
						<div class="cell">
							<h2 class="card-title">Precio total</h2>
							<div class="counter-value number-count"
								data-count="${promocion.precio}"></div>
							<p class="counter-info">monedas</p>
						</div>
					</div>
					<!-- end of counter -->

				</div>
				<!-- end of col -->
			</div>
			<!-- end of row -->
		</div>
		<!-- end of container -->
	</div>
	<!-- end of counter -->
	<!-- end of statistics -->
	<!-- botÃ³n de comprar -->
	<div class="d-grid gap-2 col-12 mx-auto d-flex justify-content-center">
		<c:if test="${usuario != null}">
			<c:choose>
				<c:when test="${usuario.puedeComprar(promocion)}">
					<a href="/turismo/promociones/comprar?id=${promocion.id}"
						class="btn btn-success rounded" role="button">Comprar</a>
				</c:when>
				<c:otherwise>
				<div class="alert alert-danger text-center">
				<c:choose>
						<c:when test="${!usuario.noSeVisito(promocion)}">
							Ya compraste esta promoción o alguna de sus atracciones.<br>
						</c:when>
						<c:otherwise>
						<c:if test="${usuario.monedas < promocion.precio}">
							No tenés monedas suficientes para comprar esta promoción.<br>
						</c:if>
						
						<c:if test="${usuario.tiempoDisponible < promocion.tiempo}">
							No tenés el tiempo suficiente para realizar esta promoción.<br>
						</c:if>
						
						<c:if test="${!promocion.tieneCupo(promocion)}">
							No hay cupo disponible para esta promoción.
						</c:if>
						</c:otherwise>	
				</c:choose>
				</div>	
				</c:otherwise>
			</c:choose>
			<!-- <a class="btn btn-success btn-lg"
				href="/turismo/atracciones/comprar?id=${atraccion.id}" type="button">Comprar
				atracción</a> -->
		</c:if>
		<c:if test="${usuario == null}">
			<div class="col text-center">
			<div class="alert alert-warning m-5" role="alert">Ingresá para
					poder comprar</div>
				<a class="boton" role="button" href="/turismo/ingresar">Ingresar</a>
				
			</div>
		</c:if>

	</div>
	<br>
	<br>
	<br>
	<br>
	
	<!-- Footer -->
	<jsp:include page="../../partials/footer.jsp"></jsp:include>
	<!-- end of footer -->

</body>
</html>