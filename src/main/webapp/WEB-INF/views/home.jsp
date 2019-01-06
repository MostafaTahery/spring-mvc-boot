<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
<title>Please Sign in or Sign up</title>
<link href="<c:url value="resources/css/signin.css"/>" rel="stylesheet">
<link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>" />
<script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/>" type="text/javascript"></script>


</head>

<body>
<h3>Welcome to Spring Album</h3>
<span class="col-md-12"></span>
 <div class="container align-middle">

      <form class="form-signin col-sm-4 col-md-offset-2" action="<sp:url value="/home/"></sp:url>" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputName" class="sr-only" >User Name</label>
        <input type="text" id="inputName" name="name_in" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only" >Password</label>
        <input type="password" id="inputPassword" name="pass_in" class="form-control" placeholder="Password" required>
        <button class="btn-primary" name="signup" type="submit">Sign in</button>
        <h3>Status ${statusin}</h3>
      </form>

   

      <form class="form-signin col-sm-4 col-md-offset-2" action="<sp:url value="/home/"></sp:url>" method="post">
        <h2 class="form-signin-heading">Or Sign Up</h2>
         <label for="inputName" class="sr-only">Full Name</label>
        <input type="Text" id="inputFullname" name="fullName" class="form-control" placeholder="Fullname" required autofocus>
        <label for="inputName" class="sr-only">User Name</label>
        <input type="Text" id="inputUsername" name="userName" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword1" name="passWord" class="form-control" placeholder="Password" required>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword2" name="pass2" class="form-control" placeholder="Retype Password" required>
        <label for="inputEmail" class="sr-only">Email</label>
        <input type="Email" id="inputEmail" name="email" class="form-control" placeholder="Email" required autofocus>
        <label for="inputDate" class="sr-only"  >Birth Date</label>
        <input type="date" id="inputDate" name="birthDate" class="form-control" placeholder="Birthdate" required>
        <button class="btn" name="signin" type="submit" >Sign up</button>
        <h3>Status ${statusup}</h3>
      </form>

    </div> <!-- /container  btn-lg btn-primary btn-block-->
</body>
</html>