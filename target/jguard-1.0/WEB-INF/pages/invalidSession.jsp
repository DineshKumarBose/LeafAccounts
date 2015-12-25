<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title><spring:message code="label.pages.home.title"></spring:message></title>
</head>
<body>
<img src="images/leafsoft.png" alt="LeafSoft">
    <div class="container">
            <h1 class="alert alert-danger">
                Session Expired
            </h1>
            <a class="btn btn-primary" href="<c:url value="login.jsp" />">login</a>
    </div>
</body>

</html>