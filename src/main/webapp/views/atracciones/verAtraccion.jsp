<!DOCTYPE html>
<html lang="es">

<head>
<jsp:include page="../../partials/head.jsp"></jsp:include>
</head>

<body data-spy="scroll" data-target=".fixed-top">

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<!-- Navigation -->
	<jsp:include page="../../partials/nav.jsp"></jsp:include>

	<div class="container py-5">
		<br> <br> <br>
		<h1>
			<c:out value="${atraccion.nombre}"></c:out>
		</h1>
		<br>
		<div class="card-group">
			<div class="card">
				<img src="../assets/img/${atraccion.imagen}" class="card-img-top"
					alt="...">
				<div class="card-body">
					<p class="card-text">
						<c:out value="${atraccion.descripcion}"></c:out>
					</p>
				</div>
			</div>
		</div>
		<!-- botÃ³n -->
		<div class="d-flex flex-row-reverse bd-highlight">
			<div class="p-2 bd-highlight">
				<div class="d-grid gap-2">
					<a type="button" class="btn btn-success btn-md"
						href="/turismo/atracciones"> Ver todas las atracciones &nbsp;
						<i class="fas fa-arrow-right"></i>
					</a>
				</div>
			</div>
		</div>
		<br>

	</div>
	<br>

	<div class="counter">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div id="counter">
						<div class="cell">
							<h2 class="card-title">Tiempo total</h2>
							<div class="counter-value number-count"
								data-count="${atraccion.tiempo}"></div>
							<p class="counter-info">horas de duración</p>
						</div>
						<div class="cell">
							<h2 class="card-title">Precio total</h2>
							<div class="counter-value number-count"
								data-count="${atraccion.precio}"></div>
							<p class="counter-info">monedas</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="d-grid gap-2 col-12 mx-auto d-flex justify-content-center">
		<c:if test="${usuario != null}">
			<c:choose>
				<c:when test="${usuario.puedeComprar(atraccion)}">
					<a href="/turismo/atracciones/comprar.do?id=${atraccion.id}"
						class="btn btn-success rounded" role="button">Comprar</a>
				</c:when>
				<c:otherwise>
				<div class="alert alert-danger text-center">
				<c:choose>
						<c:when test="${!usuario.noSeVisito(atraccion)}">
							Ya compraste esta atracción.<br>
						</c:when>
						<c:otherwise>
						<c:if test="${usuario.monedas < atraccion.precio}">
							No tenés monedas suficientes para comprar esta atracción.<br>
						</c:if>
						
						<c:if test="${usuario.tiempoDisponible < atraccion.tiempo}">
							No tenés el tiempo suficiente para realizar esta atracción.<br>
						</c:if>
						
						<c:if test="${!(atraccion.cupoDisponible > 0)}">
							No hay cupo disponible para esta atracción.
						</c:if>
						</c:otherwise>	
						</c:choose>
				</div>		
				</c:otherwise>
				
			</c:choose>
		</c:if>
		<c:if test="${usuario == null}">
			<div class="col text-center">
			<div class="alert alert-warning m-5" role="alert">Ingresá para
					poder comprar</div>
				<a class="boton" role="button" href="/turismo/ingresar">Ingresar</a>
				
			</div>
		</c:if>

	</div>
	<!-- Fin de botÃ³n comprar -->
	<br>
	<br>
	<br>
	<br>

	<div>
		<img src="../assets/img/footer-bg-fellowship.svg" id="footerimg" />
	</div>
	<!-- Footer -->
	<div class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h4>Tierra Media es el único parque de atracciones temático de
						El Señor de los Anillos</h4>
					<div class="social-container">
						<span class="fa-stack"> <a href="#your-link"> <i
								class="fas fa-circle fa-stack-2x"></i> <i
								class="fab fa-facebook-f fa-stack-1x"></i>
						</a>
						</span> <span class="fa-stack"> <a href="#your-link"> <i
								class="fas fa-circle fa-stack-2x"></i> <i
								class="fab fa-twitter fa-stack-1x"></i>
						</a>
						</span> <span class="fa-stack"> <a href="#your-link"> <i
								class="fas fa-circle fa-stack-2x"></i> <i
								class="fab fa-pinterest-p fa-stack-1x"></i>
						</a>
						</span> <span class="fa-stack"> <a href="#your-link"> <i
								class="fas fa-circle fa-stack-2x"></i> <i
								class="fab fa-instagram fa-stack-1x"></i>
						</a>
						</span> <span class="fa-stack"> <a href="#your-link"> <i
								class="fas fa-circle fa-stack-2x"></i> <i
								class="fab fa-youtube fa-stack-1x"></i>
						</a>
						</span>
					</div>
					<!-- end of social-container -->
				</div>
				<!-- end of col -->
			</div>
			<!-- end of row -->
		</div>
		<!-- end of container -->
	</div>
	<!-- end of footer -->
	<!-- end of footer -->


	<!-- Copyright -->
	<div class="copyright">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<ul class="list-unstyled li-space-lg p-small">
						<li><a href="article.html">Article Details</a></li>
						<li><a href="terms.html">Terms & Conditions</a></li>
						<li><a href="privacy.html">Privacy Policy</a></li>
					</ul>
				</div>
				<!-- end of col -->
				<div class="col-lg-6">
					<p class="p-small statement"
						style="background-color: rgb(55, 68, 66);">
						Copyright © <a href="#your-link">FreakisTeam</a>
					</p>
				</div>
				<!-- end of col -->
			</div>
			<!-- enf of row -->
		</div>
		<!-- end of container -->
	</div>
	<!-- end of copyright -->

</body>
</html>