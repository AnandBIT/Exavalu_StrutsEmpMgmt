<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${loggedIn == null}">
    <c:redirect url="login.jsp" />
</c:if>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.108.0">
        <title>Edit Employee</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/sign-in/">

        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/signin.css" rel="stylesheet">
    </head>
    <body class="text-center">

        <main class="form-signin w-100 m-auto">

            <form action='EditEmployee' method="post">
                <img class="mb-4" src="https://www.mulesoft.com/sites/default/files/2020-12/Exavalu%20Logo.png" alt="" height="57">
                <h1 class="h3 mb-3 fw-normal">Edit Employee Data</h1>

                <div class="form-floating">
                    <input type="text" class="form-control rounded-bottom-0" id="floatingEmployeeId" placeholder="Employee ID" name="employeeId" required value="${emp.getEmployeeId()}" readonly="true">
                    <label for="floatingEmployeeId">Employee ID</label>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control rounded-0" id="floatingFirstName" placeholder="First Name" name="firstName" required value="${emp.getFirstName()}">
                    <label for="floatingFirstName">First Name</label>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control rounded-0" id="floatingLastName" placeholder="Last Name" name="lastName" required value="${emp.getLastName()}">
                    <label for="floatingLastName">Last Name</label>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control rounded-0" id="floatingPhone" placeholder="Phone" name="phone" required value="${emp.getPhone()}">
                    <label for="floatingPhone">Phone</label>
                </div>

                <div class="form-floating">
                    <input type="text" class="form-control rounded-0" id="floatingAddress" placeholder="Address" name="address" required value="${emp.getAddress()}">
                    <label for="floatingAddress">Address</label>
                </div>

                <div class="form-floating">
                    <select name="gender" class="form-select rounded-0" id="floatingGender">
                        <option value="0" disabled>Select a Gender</option>
                        <option value="M" ${emp.getGender() == "M" ? "selected" : ""}>Male (M)</option>
                        <option value="F" ${emp.getGender() == "F" ? "selected" : ""}>Female (F)</option>
                    </select>
                    <label for="floatingGender">Gender</label>
                </div>

                <div class="form-floating">
                    <input type="number" class="form-control rounded-0" id="floatingAge" placeholder="Age" name="age" required value="${emp.getAge()}">
                    <label for="floatingAge">Age</label>
                </div>

                <div class="form-floating">
                    <select name="departmentId" class="form-select rounded-0" id="departmentId">
                        <option value="0" disabled>Select a Department</option>

                        <c:forEach var="dept" items="${deptList}">
                            <option ${emp.getDepartmentId() == dept.getDepartmentId() ? "selected" : ""}
                                value="${dept.getDepartmentId()}">
                                ${dept.getDepartmentName()}
                            </option>
                        </c:forEach>
                    </select>
                    <label for="departmentId">Department</label>
                </div>



                <div class="form-floating">
                    <select name="roleId" class="form-select rounded-0" id="roleId">
                        <option value="0" disabled>Select a Role</option>
                        <c:forEach var="role" items="${roleList}">
                            <option ${emp.getRoleId() == role.getRoleId() ? "selected" : ""}
                                value="${role.getRoleId()}">
                                ${role.getRoleName()}
                            </option>
                        </c:forEach>
                    </select>
                    <label for="roleId">Role</label>
                </div>

                <div class="form-floating">
                    <input type="number" class="form-control rounded-0" id="floatingBasicSalary" placeholder="Basic Salary" name="basicSalary" required value="${emp.getBasicSalary()}">
                    <label for="floatingBasicSalary">Basic Salary</label>
                </div>

                <div class="form-floating">
                    <input type="number" class="form-control rounded-top-0" id="floatingCarAllowance" placeholder="Car Allowance" name="carAllowance" required value="${emp.getCarAllowance()}">
                    <label for="floatingCarAllowance">Car Allowance</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary mt-2" type="submit">Submit</button>
            </form>

            <p class="mt-5 mb-3 text-muted">&copy; 2017-2022</p>
        </main>



    </body>
</html>
