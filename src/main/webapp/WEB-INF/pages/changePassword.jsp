<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<title>ChangePassword</title>
<link rel="stylesheet" type="text/css" href="css/common.css">
</head>
<body onload='document.changepassword.oldpassword.focus();'>

	<div id="box" align='center'>
	<img src="images/leafsoft.png" alt="LeafSoft">
		<h3>Change Password with existing password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='changepassword'
		  action="<c:url value='changePassword' />" method='POST'>

		  <table>
			<tr>
				<td colspan="2">CurrentPassword:</td>
				<td><input type='password' name='oldpassword' class ='tb5' value=''></td>
			</tr>
			<tr>
				<td colspan="2">NewPassword:</td>
				<td><input type='password' name='password' class ='tb5' /></td>
			</tr>
			<tr>
				<td colspan='1' align="center"><input name="submit" class="fb9" type="submit"
					value="ChangePassword" /></td>
			</tr>
		  </table>
		  <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		</form>
	</div>

</body>
</html>