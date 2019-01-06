<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="resources/css/signin.css"/>" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>" />
<script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/>" type="text/javascript"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>The Album ${album.getName()} </h3>
<div>
<table class="table table-striped table-hover" >
<thead>
<tr><th>Picture Comment</th><th>Picture Album</th><th>Picture Image</tr>
</thead>
<c:forEach items="${pics}" var="pic" >
<tr><td>${pic.getComment()}</td><td>${album.getName()}</td><td><img alt="Image" src='<c:catch>${pic.getImage()}</c:catch>' ></td><td><a href='<sp:url value="/album/deletepic/${pic.getPicId()}" ></sp:url>'>X</a></td></tr>
</c:forEach>
</table>
</div>
<div>
<form action="<sp:url value="/editprofile/"></sp:url>" method="post" enctype="multipart/form-data">
<label for="username" class="sr-only" >Picture Comment</label>
        <input type="text" id="inputName" name="comment" class="form-control" placeholder="Picture Comment" required autofocus>
 <button class="btn" name="Update" type="submit" >Update</button>
        File to Upload:  <input type="file" name="file"><br/>
</form>
</div>
</body>
</html>