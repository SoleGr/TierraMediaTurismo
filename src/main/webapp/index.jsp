<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="partials/head.jsp"></jsp:include>
</head>

<body data-spy="scroll" data-target=".fixed-top">

	<!-- NAV -->
	<jsp:include page="partials/nav.jsp"></jsp:include>


	<!-- Hero Image-->
	<div class="hero-image"
		style="background-image: linear-gradient(rgba(128, 121, 196, 0.25), rgba(99, 131, 106, 0.25)), url(assets/img/hero.jpg);">
		<div class="hero-text">
			<h1 id="titulo">¡Bienvenidos a la Tierra Media!</h1>
			<h2 id="subtitulo">Parque de atracciones</h2>
		</div>
	</div>
	<!-- end of Hero img -->


	<!-- Frase -->
	<div class="basic-1 mt-3 py-5">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<p style="font-family: 'Open Sans'; font-size: 20px;">
						<i class="fas fa-xs fa-quote-left text-left"></i>
						<c:out value="${frase}"></c:out>
						<i class="fas fa-xs fa-quote-right"></i>
					</p>
					<p style="text-align: end; font-family: 'Berkshire Swash';">
						<c:out value="${autor}"></c:out>
					</p>
				</div>
				<!-- end of col -->
			</div>
			<!-- end of row -->
		</div>
		<!-- end of container -->
	</div>
	<!-- end of basic-1 -->
	<!-- end of Frase -->


	<!--Carousel Promo-->
	<div class="container text-center">
		<h1>Promociones</h1>
		<div id="carouselExampleCaptions" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<c:forEach items="${promociones}" var="promocion" begin="0" end="2" varStatus="status">
					<div class="carousel-item <c:if test='${status.first}'>active</c:if>">
						<img src="assets/img/${promocion.imagen}" class="d-block w-100"
							alt="Imagen promocion">
						<div class="carousel-caption d-none d-md-block">
							<h1>${promocion.nombre}</h1>
						</div>
					</div>
				</c:forEach>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	</div>
	<!-- end of Carousel -->

	<!-- BotÃ³n Ver MÃ¡s -->
	<div class="text-center pt-2 mt-3">
		<button type="button" class="boton">Ver Más</button>
	</div>
	<!-- end of BotÃ³n -->

	<!-- InformaciÃ³n -->
	<div class="basic-4">
		<div class="container">
			<div class="row">
				<div class="col-lg-5">
					<div class="text-container">
						<h2>Tierra Media es un parque temático basado en los libros
							de J.R.R. Tolkien</h2>
						<p>Compuesto por mas de 10 atracciones meticulosamente creadas
							apartir de el universo Tolkien, ubicado en Nueva Zelanda, lugar
							donde se filmaron las afamadas peliculas .</p>
						<p>Podrás disfrutar de aquellos lugares donde siempre quisiste
							estar mientras leías "El Hobbit" o "El Silmarillon". Vení a
							conocer El Reino de Gondor, Mordor, Erebor, Rohan o su amado
							Abismo de Helm</p>
					</div>
				</div>
				<div class="col-lg-7">
					<div class="image-container">
						<img src="assets/img/map2.jpg" alt="alternative">
					</div>
					<!-- end of image-container -->
				</div>
			</div>
		</div>
	</div>
	<!-- end of Info -->

	<!-- Carousel Atracciones -->
	<div class="container text-center">
		<h1>Atracciones</h1>
		<div id="carouselExampleCaptions2" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<c:forEach items="${atracciones}" var="atraccion" begin="0" end="2" varStatus="status">
					<div class="carousel-item <c:if test='${status.first}'>active</c:if>">
						<img src="assets/img/${atraccion.imagen}" class="d-block w-100"
							alt="Imagen atraccion">
						<div class="carousel-caption d-none d-md-block">
							<h1>${atraccion.nombre}</h1>
						</div>
					</div>
				</c:forEach>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleCaptions2" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleCaptions2" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
	</div>
	<!-- end of Carousel Atracciones -->

	<!-- BotÃ³n Ver MÃ¡s -->
	<div class="text-center pt-2 mt-3 mb-5">
		<button type="button" class="boton">Ver Más</button>
	</div>
	<!-- end of BotÃ³n -->

	<!--Tipos de atracciones-->
	<div class="container marketing text-center pt-5"
		id="#tipos-atracciones">
		<h1>Tipos de Atracciones</h1>
		<div class="row py-5">
			<div class="col-lg-4">
				<img src="assets/img/bilbo1.png" class="img-fluid" width="275px"></img>
				<h2>Aventura</h2>

				<p class="py-3">
					<!--  <a class="boton" href="#">View details &raquo;</a>-->
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<img src="assets/img/gandalf1.png" class="img-fluid" width="275px"></img>
				<h2>Paisaje</h2>

				<p class="py-3">
					<!--  <a class="boton" href="#">View details &raquo;</a>-->
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<img src="assets/img/Sam1.png" class="img-fluid" width="275px"></img>
				<h2>Degustación</h2>

				<p class="py-3">
					<!--  <a class="boton" href="#">View details &raquo;</a>-->
				</p>
			</div>
		</div>
	</div>

	<!-- Testimonials -->
	<div class="slider-1 bg-gray">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h2 class="h2-heading">¿Qué piensan los usuarios de Tierra
						Media Parque Temático?</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="slider-container">
						<div class="swiper-container card-slider">
							<div class="swiper-wrapper">

								<div class="swiper-slide">
									<div class="card">
										<img class="card-image" src="assets/img/testimonial-1.jpg"
											alt="alternative">
										<div class="card-body">

											<p class="testimonial-text">Estaba entre ir a conocer
												Paris o venir aquí. No me arrepiento de haber venido a
												Tierra Media, un sueño cumplido menos!</p>
											<p class="testimonial-author">Sole Grilletta - Australia</p>
										</div>
									</div>
								</div>

								<div class="swiper-slide">
									<div class="card">
										<img class="card-image" src="assets/img/testimonial-3.jpg"
											alt="alternative">
										<div class="card-body">
											<p class="testimonial-text">Cuando leí ESDLA, Me enamoré
												de sus paisajes, imaginaba lo lindo e imponente que debía
												ser. Tierra Media es igual o más imponente que los libros</p>
											<p class="testimonial-author">Nontué Aro - Italia</p>
										</div>
									</div>
								</div>

								<div class="swiper-slide">
									<div class="card">
										<img class="card-image" src="assets/img/testimonial-2.jpg"
											alt="alternative">
										<div class="card-body">
											<p class="testimonial-text">Minas Tirith fue genial! Mi
												atracción preferida de todas, aunque todas me gustaron
												mucho. Visitar Tierra Media fue la mejor decisión que tomé
												en mi vida</p>
											<p class="testimonial-author">Federico Grande - España</p>
										</div>
									</div>
								</div>

								<div class="swiper-slide">
									<div class="card">
										<img class="card-image" src="assets/img/testimonial-4.jpg"
											alt="alternative">
										<div class="card-body">
											<p class="testimonial-text">Como buen fanático de ESDLA
												puedo afirmar que es el mejor lugar para visitar si sos fan
												de Tolkien y su mundo fantástico</p>
											<p class="testimonial-author">Nelson Alderete - Nueva
												Zelanda</p>
										</div>
									</div>
								</div>

								<div class="swiper-slide">
									<div class="card">
										<img class="card-image" src="assets/img/testimonial-5.jpg"
											alt="alternative">
										<div class="card-body">
											<p class="testimonial-text">Minas Tirith fue genial! Mi
												atracción preferida de todas, aunque todas me gustaron
												mucho. Visitar Tierra Media fue la mejor decisión que tomé
												en mi vida</p>
											<p class="testimonial-author">Alan Farias - Argentina</p>
										</div>
									</div>
								</div>
								<div class="swiper-slide">
									<div class="card">
										<img class="card-image" src="assets/img/testimonial-6.jpg"
											alt="alternative">
										<div class="card-body">
											<p class="testimonial-text">Vine buscando cobre y
												encontré oro!. Entre las cosas que mas destaco son las
												buenas promos</p>
											<p class="testimonial-author">Federico Grasior -
												Sudáfrica</p>
										</div>
									</div>
								</div>
							</div>
							<div class="swiper-button-next"></div>
							<div class="swiper-button-prev"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Statistics -->
	<div class="counter">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">

					<!-- Counter -->
					<div id="counter">
						<div class="cell">
							<div class="counter-value number-count" data-count="542">1</div>
							<p class="counter-info">Atracciones Vendidas</p>
						</div>
						<div class="cell">
							<div class="counter-value number-count" data-count="526">1</div>
							<p class="counter-info">Usuarios Satisfechos</p>
						</div>
						<div class="cell">
							<div class="counter-value number-count" data-count="489">1</div>
							<p class="counter-info">Buenas Reseñas</p>
						</div>
						<div class="cell">
							<div class="counter-value number-count" data-count="508">1</div>
							<p class="counter-info">Días sin Accidentes</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Footer -->
	<jsp:include page="/partials/footer.jsp"></jsp:include>

</body>

</html>