<%@ page import="model.User" %>
<html>
<head>
    <title>Student Record</title>
</head>
<body>
<%
    if (request.getAttribute("user") != null) {
        User student = (User) request.getAttribute("user");
%>

<h1>Student Record</h1>
<div>ID: <%= student.getId()%></div>
<div>First Name: <%= student.getFname()%></div>
<div>Last Name: <%= student.getLname()%></div>

<%
} else {
%>

<h1>No student record found.</h1>

<% } %>
</body>
</html>