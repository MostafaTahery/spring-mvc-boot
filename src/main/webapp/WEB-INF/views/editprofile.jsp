<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
<title>Edit User</title>
<link href="<c:url value="resources/css/signin.css"/>" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>" />
<script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/>" type="text/javascript"></script>


</head>

<body>
<h3>Edit User: ${user.getId() }</h3>
<span class="col-md-12"></span>
 <div class="container align-middle">

<div class="col-sm-8">
<table class="table table-striped table-hover">
<thead>
<tr class="bg-success"><th>Full Name</th><th>Email</th><th>User Name</th><th>BirthDate</th><th>Profile Picture</th></tr>
</thead>
<tr>
<td>${user.getFullName()}</td>
<td>${user.getEmail()}</td>
<td>${user.getUserName()}</td>
<td>${user.getBirthDate()}</td>
<td><img src="#" alt="Profile Picture" ></img></td>
</tr>

</table>
</div>
   

      <form class="form-signin col-sm-4 col-md-offset-2" action="<sp:url value="/editprofile/"></sp:url>" method="post" enctype="multipart/form-data">
        <h2 class="form-signin-heading">Edit your Profile</h2>
         <label for="inputName" class="sr-only">Full Name</label>
        <input type="Text" id="inputFullname" name="FullName" value="${user.getFullName()}" class="form-control" placeholder="Fullname" required autofocus>
        <label for="inputName" class="sr-only">User Name</label>
        <input type="Text" id="inputUsername" name="UserName" value="${user.getUserName()}" class="form-control" placeholder="Username" required autofocus>
       
        <label for="inputEmail" class="sr-only">Email</label>
        <input type="Email" id="inputEmail" name="Email" value="${user.getEmail()}" class="form-control" placeholder="Email" required autofocus>
        <label for="inputBirthdate" class="sr-only">Birth Date</label>
        <input type="date" id="inputPassword" name="BirthDate" value="${user.getBirthDate()}"   class="form-control" placeholder="Birthdate" required>
        <button class="btn" name="Update" type="submit" >Update</button>
        File to Upload:  <input type="file" name="file"><br/>
       </form>
       
		
		
		
    </div> <!-- /container  btn-lg btn-primary btn-block-->
</body>
</html>