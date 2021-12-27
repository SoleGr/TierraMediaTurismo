<!DOCTYPE html>
<html lang="es">

<head>
	<jsp:include page="../../partials/head.jsp"></jsp:include>
</head>

<body data-spy="scroll" data-target=".fixed-top">

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<!-- NAV -->
	<jsp:include page="../../partials/nav.jsp"></jsp:include>

	<div class="container py-5">
		<br>
		<br> <br>
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
					<a type="button" class="btn btn-success btn-md" href="/turismo/promociones">
						Ver todas las Promociones &nbsp; <i class="fas fa-arrow-right"></i>
					</a>
				</div>
			</div>
		</div>
		<br>
	</div>
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
				</div>
			</div>
		</div>
	</div>

	<div class="d-grid gap-2 col-12 mx-auto d-flex justify-content-center">
		<a class="btn btn-success btn-lg"
			href="/turismo/productos/comprar?id=${promocion.id}" type="button">Comprar
			atracción</a>
	</div>

	<jsp:include page="../../partials/footer.jsp"></jsp:include>

</body>
</html>