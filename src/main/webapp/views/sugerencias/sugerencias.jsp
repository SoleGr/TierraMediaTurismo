<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../../partials/head.jsp"></jsp:include>
</head>

<body data-spy="scroll" data-target=".fixed-top">

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<jsp:include page="../../partials/nav.jsp"></jsp:include>
	<br><br><br>


	<!-- Comienzan las cards y sus botones -->
	<div class="container pt-5">
		<br>
		<h1 class="text-center">Nuestros sugerencia para vos</h1>
		<br>

		
			<c:if test="${usuario.esAdmin()}">
				<div class="alert alert-danger text-center" role="alert">Página no
					accesible para usuarios administradores</div>
			</c:if>
			<c:if test="${!usuario.esAdmin()}">
				<div class="row">
					<c:if test="${productos.size() == 0}">
						<div class="alert alert-warning text-center" role="alert">
							No podés comprar ningún producto :(</div>
					</c:if>
					<c:forEach items="${productos}" var="producto">
						<c:if test="${usuario.puedeComprar(producto)}">
							<div class="col-md-4  p-3">
								<div class="card shadow">
									<img src="assets/img/${producto.imagen}" class="card-img-top"
										alt="Imagen Producto" style="max-height: 170px;">
									<div class="card-body">
										<h5 class="card-title">
											<c:out value="${producto.nombre}"></c:out>
										</h5>
										<p class="card-text" style="max-height: 20 rem">
											<c:out value="${producto.descripcion}"></c:out>
										</p>
									</div>
									<div class="text-center m-3">
										<!-- /atraccion o /promocion
							<a href="#" class="btn btn-secondary" role="button">Ver más</a> -->
										<!-- cuando es atraccion -->
										<c:if test="${producto.esAtraccion()}">
											<a href="/turismo/productos/atraccion?id=${producto.id}"
												class="boton" aria-current="page" role="button">Más info</a>
										</c:if>

										<c:if test="${producto.esPromocion()}">
											<a href="/turismo/productos/promocion?id=${producto.id}"
												class="boton" aria-current="page" role="button">Más info</a>
										</c:if>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
				</c:if>
	</div>

	<div>
		<img src="assets/img/footer-bg-fellowship.svg" id="footerimg" />
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
						Copyright © <a href="#your-link">Your name</a>
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