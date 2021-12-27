<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<!DOCTYPE html>
<html>

<head>
	<title>Login</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/stylesheets/login.css">
	<link href="<%=request.getContextPath()%>/assets/css/fontawesome-all.css" rel="stylesheet">
	<script defer src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script defer src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script defer src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body style="background-image: linear-gradient(rgba(128, 121, 196, 0.25), rgba(99, 131, 106, 0.25)), url(assets/img/hero.jpg);">
	<!-- Si hay algun error lo muestra aca -->
	<c:if test="${flash != null}">
		<div class="alert alert-danger text-center">
			<p>
				<c:out value="${flash}" />
			</p>
		</div>
	</c:if>
	
	<div class="container h-100">
		<div class="d-flex justify-content-center h-100">
			<div class="user_card">
				<div class="d-flex justify-content-center">
					<div class="brand_logo_container">
						<a href="/turismo"> <img
							src="<%=request.getContextPath()%>/assets/img/logo.png"
							class="brand_logo" alt="Logo"></a>
					</div>
				</div>
				<div class="d-flex justify-content-center form_container">
					<form action="ingresar" method="post">
						<div class="input-group mb-3">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" name="usuario" class="form-control input_user"
								value="" placeholder="Nombre de Usuario" required>
						</div>
						<div class="input-group mb-2">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" name="contrasenia"
								class="form-control input_pass" value=""
								placeholder="Contraseña" required>
						</div>
						<div class="form-group">
							<div class="custom-control custom-checkbox">
								<input type="checkbox" class="custom-control-input"
									id="customControlInline"> <label
									class="custom-control-label" for="customControlInline">Recordarme</label>
							</div>
						</div>
						<div class="d-flex justify-content-center mt-3 login_container">
							<button type="submit" name="button" class="btn login_btn">Ingresar</button>
						</div>
					</form>
					<!-- Fin del form -->

				</div>
				<div class="mt-4">
					<div class="d-flex justify-content-center links">
						¿Aún no estás registrado? <a href="#" class="ml-2">Registrate</a>
					</div>
					<div class="d-flex justify-content-center links">
						<a href="#">¿Olvidaste tu contraseña?</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
