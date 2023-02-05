<%-- 
    Document   : logout
    Created on : Dec 28, 2022, 10:05:36 AM
    Author     : Blake
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Kill the user's session -->
<% session.invalidate(); %>
<jsp:forward page="landingPage.jsp"/>
