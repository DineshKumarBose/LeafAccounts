<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="css/common.css">
<title>ResetPassword</title>
</head>
<body onload='document.forgetpassword.username.focus();'>
<div id='box' align="center">
<img src="images/leafsoft.png" alt="LeafSoft">
<h3>Enter UserName to reset you password :</h3>
<form:form action="forgetPassword" method="post" name='forgetpassword'>
<table>
<tr>
<td>UserName:</td>
<td><input type='text' name='username' class ='tb5' value=''></td>
</tr>
<tr>
<td colspan='2' align="center"><input name="submit"  class = 'fb9'  type="submit"
					value="Continue" /></td>
</tr>
</table>
</form:form>
</div>
</body>
</html>