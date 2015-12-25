<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body onload='document.loginForm.username.focus();'>
	<div id="box" align='center'>
	<img src="images/leafsoft.png" alt="LeafSoft">
		<h2>Login with Username and Password</h2>

		<c:if test="${not empty error}">
			<div id="error" class="error">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
		  action="<c:url value='j_spring_security_check' />" method='POST'>

		  <table>
			<tr>
				<td colspan="2">User:</td>
				<td><input type='text' id='username' name='username' class ='tb5' value=''></td>
			</tr>
			<tr>
				<td colspan="2">Password:</td>
				<td><input type='password' id='password' name='password' class ='tb5' value=''/></td>
			</tr>
			<tr>
				<td ><input name="submit" type="submit" class = 'fb9'
					value="Login" /></td>
					<td><a href="register"><input name="Register" type="button"
					value="SignUp" class = 'fb9'/></a></td>
					<td><a href="forgetPassword"><input name="ForgetPassword" type="button"
					value="ForgetPassword" class = 'fb9'/></a></td>
			</tr>
		  </table>
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		</form>
	</div>

</body>
</html>