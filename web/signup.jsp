<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="description" content="" />
        <meta
            name="author"
            content="Mark Otto, Jacob Thornton, and Bootstrap contributors"
            />
        <meta name="generator" content="Hugo 0.108.0" />
        <title>Signup</title>


        <link
            href="css/bootstrap.min.css"
            rel="stylesheet"
            />

        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet" />
    </head>
    <body class="text-center">
        <main class="form-signin w-100 m-auto">
            <form action="SignUp" method="post" id="signupForm">
                <img
                    class="mb-4"
                    src="https://www.mulesoft.com/sites/default/files/2020-12/Exavalu%20Logo.png"
                    alt=""
                    height="57"
                    />
                <h1 class="h3 mb-3 fw-normal">Create New Account</h1>

                <div class="form-floating">
                    <input
                        type="text"
                        class="form-control rounded-bottom-0"
                        id="floatingFirstName"
                        placeholder="First Name"
                        name="firstName"
                        value="${user.firstName}"
                        required
                        />
                    <label for="floatingFirstName">First Name</label>
                </div>

                <div class="form-floating">
                    <input
                        type="text"
                        class="form-control rounded-0"
                        id="floatingLastName"
                        placeholder="Last Name"
                        name="lastName"
                        value="${user.lastName}"
                        required
                        />
                    <label for="floatingLastName">Last Name</label>
                </div>

                <div class="form-floating">
                    <input
                        type="email"
                        class="form-control rounded-0"
                        id="floatingInput"
                        placeholder="name@example.com"
                        name="email"
                        value="${user.email}"
                        required
                        />
                    <label for="floatingInput">Email address</label>
                </div>

                <div class="form-floating">
                    <input
                        type="password"
                        class="form-control rounded-0 mb-0"
                        id="floatingPassword"
                        placeholder="Password"
                        name="password"
                        value="${user.password}"
                        required
                        />
                    <label for="floatingPassword">Password</label>
                </div>

                <div class="form-floating">
                    <select name="countryCode" class="form-select rounded-0" id="countryCode" onchange="fetchContent('countryCode', 'stateCode')">
                        <option value="0">--Select a Country--</option>

                        <c:forEach var="country" items="${countryList}">
                            <option value="${country.getCountryCode()}" ${country.getCountryCode() == user.getCountryCode() ? "selected" : ""}>
                                ${country.getCountryName()}
                            </option>
                        </c:forEach>
                    </select>
                    <label for="countryCode">Country</label>
                </div>

                <div class="form-floating">
                    <select name="stateCode" class="form-select rounded-0" id="stateCode" onchange="fetchContent('stateCode', 'districtCode')">
                        <option value="0">--Select a State--</option>


                    </select>
                    <label for="stateCode">State</label>
                </div>

                <div class="form-floating">
                    <select name="districtCode" class="form-select rounded-top-0" id="districtCode">
                        <option value="0">--Select a District--</option>


                    </select>
                    <label for="districtCode">District</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary mt-2" type="submit">
                    Sign Up
                </button>
                <p class="mt-5 mb-3 text-muted">&copy; 2017-2022</p>
            </form>

        </main>
    </body>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <script>
                        function fetchContent(selectedId, targetId) {
                            $.ajax({
                                url: 'PreSignUp',
                                data: {
                                    [selectedId]: $("#" + selectedId).val()
                                },
                                success: function (responseText) {
                                    $("#" + targetId).html(responseText);
                                }
                            });
                        }
    </script>
</html>
