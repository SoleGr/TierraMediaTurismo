
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${!usuario.esAdmin()}">
<head>
<jsp:include page="../partials/head.jsp"></jsp:include>
</head>


<body data-spy="scroll" data-target=".fixed-top">

	<jsp:include page="../partials/nav.jsp"></jsp:include>
	<br><br><br>
	<div class="container pt-5 text-center ">
	<div class="alert alert-danger" role="alert">No tenés permisos para ingresar a esta página. <br>
	<a type="button" class="btn btn-warning" href="/turismo">Volver</a>
	</div>
	</div>
</body>
</c:if>