<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test="${loggedIn == null}">
    <c:redirect url="login.jsp" />
</c:if>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--        <link href="css/header.css" rel="stylesheet">-->
        <link rel="stylesheet" href="css/all.min.css">
        <link rel="stylesheet" href="css/reset-min.css">
        <link rel="stylesheet" href="css/algolia-min.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/docs.min.css">
        <link rel="stylesheet" href="css/index.css">
        <title>Employee Management</title>
    </head>

    <jsp:include page="menu.jsp"></jsp:include>

        <main class="px-4 py-2">
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
                                    <a href="DeleteEmployee?employeeId=${emp.getEmployeeId()}" class="text-decoration-none">
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
