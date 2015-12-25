<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/common.js"></script>
  <script>
  $(function() {
	  $("#dob" ).val("");
	  $("#dob" ).datepicker({ dateFormat: 'mm/dd/yy' });
  });
  </script>
<title>Registration</title>
</head>
<body onload='document.registration.username.focus();'>
<div id='box' align="center">
<img src="images/leafsoft.png" alt="LeafSoft" >
        <form:form action="register" method="post" commandName="userForm" name='registration'>
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>LEAFSOFT- Registration</h2></td>
                </tr>
                <tr>
                 <td colspan="2" align="center">
		            <c:if test="${not empty error}">
					<div id="error" class="error">${error}</div>
					</c:if>
					</td>
		        </tr>
                <tr>
                    <td>User Name:</td>
                    <td><form:input id='username' path="username" class ='tb5'/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><form:password id='password' path="password" class ='tb5'/></td>
                </tr>
                <tr>
                    <td>E-mail:</td>
                    <td><form:input id='email' path="email" class ='tb5'/></td>
                </tr>
                <tr>
                    <td>Date Of Birth:</td>
                    <td><form:input class ='tb5' id = "dob" path="dob" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input class = 'fb9' type="submit" id='register' value="Register" /></td>
                </tr>
            </table>
            <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
        </form:form>
    </div>
</body>
</html>