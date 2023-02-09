<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
    <head>
        <title>Employee Management</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <meta name="theme-color" content="#712cf9">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Custom styles for this template -->
        <link href="css/header.css" rel="stylesheet">
        <!--<link href="css/carousel.css" rel="stylesheet">-->
    </head>
    <header class="p-3 text-bg-dark">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none" style="margin-right: 1rem">
                    <img src="https://www.exavalu.com/wp-content/themes/quincy/sds/assets/img/logo.png" width="150px" alt="Exavalu"></img>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>
                    <li><a href="CreateEmployee" class="nav-link px-2 text-white">Create Employee</a></li>
                    <li><a href="#" class="nav-link px-2 text-white">Show Employee</a></li>
                    <li><a href="SearchEmployee" class="nav-link px-2 text-white">Search Employee</a></li>
                </ul>

                <div class="text-end">
                    <c:choose>
                        <c:when test="${loggedIn==null}">
                            <a href="login.jsp" class="text-decoration-none">
                                <button type="button" class="btn btn-outline-light me-2" >Login</button>
                            </a>
                            <a href="PreSignUp" class="text-decoration-none">
                                <button type="button" class="btn btn-warning">Sign-up</button>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="Logout" class="text-decoration-none">
                                <button type="button" class="btn btn-outline-light me-2" >Log Out</button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </header>