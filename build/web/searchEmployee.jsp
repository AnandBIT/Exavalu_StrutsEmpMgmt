<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${loggedIn == null}">
    <c:redirect url="login.jsp" />
</c:if>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width,initial-scale=1" />

        <title>Employee Management Web Application</title>
        <link
            rel="canonical"
            href="https://getbootstrap.com/docs/5.3/examples/product/"
            />
        <link href="css/bootstrap.min.css" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
            <main class="px-4 py-2">
                <h1 class="text-center">Search Employee</h1>

                <form
                    class="row mx-0 bg-dark text-white py-3 px-1"
                    method="post"
                    action="SearchEmployee"
                    >
                    <div class="col">
                        <label for="firstName" class="form-label">First Name</label>
                        <input
                            type="text"
                            class="form-control"
                            placeholder="First Name"
                            name="firstName"
                            id="firstName"
                            />
                    </div>
                    <div class="col">
                        <label for="lastName" class="form-label">Last Name</label>
                        <input
                            type="text"
                            class="form-control"
                            placeholder="Last Name"
                            name="lastName"
                            id="lastName"
                            />
                    </div>
                    <div class="col">
                        <label for="gender" class="form-label">Gender</label>
                        <select name="gender" class="form-select" id="gender">
                            <option value="">Select a Gender</option>
                            <option value="M">Male (M)</option>
                            <option value="F">Female (F)</option>
                        </select>
                    </div>
                    <div class="col">
                        <label for="departmentId" class="form-label">Department</label>
                        <select name="departmentName" class="form-select" id="departmentId">
                            <option value="">Select a Department</option>
                        <c:forEach var="dept" items="${deptList}">
                            <option value="${dept.getDepartmentName()}">
                                ${dept.getDepartmentName()}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <label for="roleId" class="form-label">Role</label>
                    <select name="roleName" class="form-select" id="roleId">
                        <option value="">Select a Role</option>
                        <c:forEach var="role" items="${roleList}">
                            <option value="${role.getRoleName()}">
                                ${role.getRoleName()}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col d-flex align-items-end">
                    <button type="submit" class="btn btn-primary w-100">Search</button>
                </div>
            </form>

            <div class="pt-2 table-responsive">
                <table
                    class="table table-bordered border-primary rounded table-hover"
                    >
                    <thead class="table-dark">
                        <tr class="align-middle">
                            <th scope="col">Employee Id</th>
                            <th scope="col">First Name</th>
                            <th scope="col">Last Name</th>
                            <th scope="col">Address</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Gender</th>
                            <th scope="col">Age</th>
                            <th scope="col">Department</th>
                            <th scope="col">Role</th>
                            <th scope="col">Basic Salary</th>
                            <th scope="col">Car Allowance</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody class="table-group-divider border-primary">
                        <c:forEach var="emp" items="${empList}">
                            <tr>
                                <th scope="row">${emp.getEmployeeId()}</th>
                                <td>${emp.getFirstName()}</td>
                                <td>${emp.getLastName()}</td>
                                <td>${emp.getAddress()}</td>
                                <td>${emp.getPhone()}</td>
                                <td>${emp.getGender()}</td>
                                <td>${emp.getAge()}</td>
                                <td>${emp.getDepartmentName()}</td>
                                <td>${emp.getRoleName()}</td>
                                <td>${emp.getBasicSalary()}</td>
                                <td>${emp.getCarAllowance()}</td>
                                <td>
                                    <div class="d-flex gap-2 justify-content-center">
                                        <a href="PreEditEmployee?employeeId=${emp.getEmployeeId()}" class="text-decoration-none">
                                            <button class="btn btn-info">Edit</button>
                                        </a>
                                        <a href="PreEditEmployee?employeeId=${emp.getEmployeeId()}" class="text-decoration-none">
                                            <button class="btn btn-danger">Delete</button>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
    </body>
</html>
