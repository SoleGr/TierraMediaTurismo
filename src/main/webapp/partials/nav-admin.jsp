   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    <nav class="navbar navbar-light bg-light p-3 px-5">
        <div class="d-flex col-12 col-md-3 col-lg-2 mb-2 mb-lg-0 flex-wrap flex-md-nowrap justify-content-between">
            <a class="navbar-brand" href="#">
                <img src="<%=request.getContextPath()%>/assets/img/logo.png" alt="logo" height="50">
                Admin
            </a>


            <button class="navbar-toggler d-md-none collapsed mb-3" type="button" data-toggle="collapse"
                data-target="#sidebar" aria-controls="sidebar" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>

        <div class="col-12 col-md-5 col-lg-8 d-flex align-items-center justify-content-md-end mt-3 mt-md-0">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                    data-toggle="dropdown" aria-expanded="false">
                    Hola, <c:out value="${usuario.nombre}"></c:out>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <li><a class="dropdown-item" href="/turismo/salir">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>