<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="resources/css/signin.css"/>" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>" />
<script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/>" type="text/javascript"></script>
<title>Albums</title>
</head>
<body>
<nav>
<a>Welcome</a>
<a href='<sp:url value="/editprofile/"></sp:url>'>${username}</a>
<a href='<sp:url value="#"></sp:url>'>#</a>
</nav>
<div class="col-sm-8">
<table class="table table-striped table-hover">
<thead>
<tr class="bg-success"><th>Album Id</th><th>Album Name</th></tr>
</thead>
<c:forEach items="${albums}" var="album" >
<tr>
<td><a href='<sp:url value="/album/${album.getAlbumId()}"></sp:url>'> ${album.getName()}</a></td>

<td><img src="#" alt="Profile" ></img></td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>