<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Cookes</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="commen_file.jsp"></jsp:include>
<style type="text/css">
*:focus{
	box-shadow:none !important;
}

</style>
</head>
<body class="bg-light">
<jsp:include page="assest/nav.jsp"></jsp:include>
<div class="container bg-white p-5 shadow-lg border" style="margin-top:50px">
<h2 class="text-center text-uppercase">Cookies policy</h2>
<hr>
${branding.getCookies_policy()}
</div>
<jsp:include page="assest/footer.jsp"></jsp:include>

</body>
</html>