<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="../../partials/noAdmin.jsp"></jsp:include>

<c:if test="${usuario.esAdmin()}">
<head>
	<jsp:include page="../../partials/head-admin.jsp"></jsp:include>
</head>

<body>
     <jsp:include page="../../partials/nav-admin.jsp"></jsp:include>

        <div class="container-fluid">
        <div class="row">
            <nav id="sidebar" class="px-3 col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                <div class="position-sticky pt-md-5">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="/turismo/index-admin.jsp">
                                <i class="fas fa-home"></i>
                                <span class="ml-2">Inicio</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="/turismo/admin-usuarios.do">
                                <i class="fas fa-users"></i>
                                <span class="ml-2">Usuarios</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/turismo/admin-atracciones.do">
                                <i class="fas fa-mountain"></i>
                                <span class="ml-2">Atracciones</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/turismo/admin-promociones.do">
                                <i class="fas fa-route"></i>
                                <span class="ml-2">Promociones</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/turismo/admin-tipos.do">
                                <i class="fas fa-shapes"></i>
                                <span class="ml-2">Tipos</span>
                            </a>
                        </li>
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
                <h1 class="h2 text-center"><i class="fas fa-route"></i> Promociones</h1>

                <p class="text-center">Altas, bajas, modificaciones y consultas</p>
                <br>
                <div class="mb-3 text-right">
                    <a href="/turismo/admin-promociones/crear.do" class="btn btn-primary" role="button"> <i
                            class="fas fa-plus"></i> Nueva Promoción
                    </a>
                </div>
                <table class="table table-striped table-hover shadow display pt-2" id="tabla">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">Nombre</th>
                            <th scope="col">Descripción</th>
                            <th scope="col">Tipo Atraccion</th>
                            <th scope="col">Tipo Descuento</th>
                            <th scope="col">Descuento</th>
                            <th scope="col">Atracciones</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${promociones}" var="promocion">
                        <tr>
                            <th scope="col"><c:out value="${promocion.nombre}"></c:out></th>
                            <td scope="col"><c:out value="${promocion.descripcion}"></c:out></td>
                            <td scope="col"><c:out value="${promocion.tipo.nombre}"></c:out></td>
                            <td scope="col"><c:out value="${promocion.tipoPromocion}"></c:out></td>
                            <td scope="col"><c:out value="${promocion.descuento}"></c:out> monedas</td>
                            <td scope="col"><c:out value="${promocion.listado}"></c:out></td>
                            <!--cambiar el href-->
                            <td style="width: 11%">
									<a href="/turismo/admin-promociones/modificar.do?id=${promocion.id}"
									class="btn btn-warning rounded" role="button"><i class="fas fa-pen"></i></a>
									<a class="btn btn-danger rounded" href="/turismo/admin-promociones/eliminar.do?id=${promocion.id}"
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

</body>
</c:if>
</html>