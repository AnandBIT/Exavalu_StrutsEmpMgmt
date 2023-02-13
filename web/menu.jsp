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
                    <button class="mx-2 btn btn-primary d-flex align-items-center" onclick="sendDataToDB()" style="gap: 0.6rem">
                        Click here to fetch JSON and store into DB
                        <div class="spinner-border spinner-border-sm d-none text-light " role="status" style="width: 1.2rem; height: 1.2rem;" id="loader">
                        </div>
                    </button>
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
    <div class="alert alert-success d-flex d-none align-items-center justify-content-between mt-2 mx-4" role="alert" id="successAlert">
        <p class="m-0"></p>
        <button type="button" class="btn-close" onclick="closeAlert(event)"></button>
    </div>
    <div class="alert alert-danger d-flex d-none align-items-center justify-content-between mt-2 mx-4" role="alert" id="errorAlert">
        <p class="m-0"></p>
        <button type="button" class="btn-close" onclick="closeAlert(event)"></button>
    </div>

    <script>
        function closeAlert(event) {
            event.currentTarget.parentNode.classList.add("d-none");
        }

        async function sendDataToDB() {
            var jsonData = [];
            loader.classList.remove('d-none');
            await fetch("https://jsonplaceholder.typicode.com/posts").then(res => res.json()).then(data => {
                jsonData = data;
            }).catch((err) => {
                console.error(err);
            });

            jsonData.splice(10, 90);
            console.log(jsonData);

            await fetch('SendDataToDB', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({jsonData})
            }).then((response) => {
                return response.json();
            }).then(data => {
//                console.log(data);
                if (data.done) {
                    successAlert.classList.remove("d-none");
                    successAlert.firstElementChild.innerText = "Yayy! Data Successfully inserted into DB";
                } else if (data.error) {
                    errorAlert.classList.remove("d-none");
                    errorAlert.firstElementChild.innerText = "Oops! Something went wrong. Please try again";
                }
            }).catch((err) => {
                console.error(err);
            }).finally(() => {
                loader.classList.add('d-none');
            });
        }
    </script>