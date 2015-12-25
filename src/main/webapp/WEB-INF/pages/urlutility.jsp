<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@page session="true"%>
<html>
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
        <title>Test</title>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/urlutil.js"></script>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="css/common.css" />
        <script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
    </head>
    <body>
		<div class="container">
    <img src="images/leafsoft.png" alt="LeafSoft">
    <p>
      Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
    </p>
  	</div>
<div id="orange"></div>
       <h4>Enter URL : </h4>
       <span><textarea id="url" class='ta4'>
		</textarea><br/></span><br>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<input type = "button"  class='fb9 urlbutton' value="encodeUrl" id="submit"/>
	<input type = "button"  class='fb9 urlbutton' value="decodeUrl" id="submit"/><br>
	<!-- csrt for log out-->
	 <form class="form-inline" action="${logoutUrl}" method="post">
      <p><input type="submit" value="Log out" class='fb9'/>
     <a href="changePassword"><input name="ChangePassword" class='fb9' type="button"
					value="ChangePassword" /></a>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>