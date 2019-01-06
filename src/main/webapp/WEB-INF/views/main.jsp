<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>" />
<script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/>" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
<div class="col-sm-8">
<table class="table table-striped table-hover">
<thead>
<tr class="bg-success"><th>First Name</th><th>Last Name</th><th>BirthDate</th><th>Profile Picture</th></tr>
</thead>
<c:forEach items="${users}" var="user" >
<tr>
<td>${user.getFirstName()}</td>
<td>${user.getLastName()}</td>
<td>${user.getBirthDate()}</td>
<td><img src="#" alt="Profile" ></img></td>
</tr>
</c:forEach>
</table>
</div>
<button name="sende" class="btn-primary" type="submit">Send E</button>
</body>
</html>