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
            integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
            crossorigin="anonymous"
            />

        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet" />
    </head>
    <body class="text-center">
        <main class="form-signin w-100 m-auto">
            <form action="SignUp" method="post">
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
                        required
                        />
                    <label for="floatingInput">Email address</label>
                </div>

                <div class="form-floating">
                    <input
                        type="password"
                        class="form-control"
                        id="floatingPassword"
                        placeholder="Password"
                        name="password"
                        required
                        />
                    <label for="floatingPassword">Password</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary mt-2" type="submit">
                    Sign Up
                </button>
                <p class="mt-5 mb-3 text-muted">&copy; 2017-2022</p>
            </form>
        </main>
    </body>
</html>
