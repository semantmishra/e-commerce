<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Search Result - ${param.search }</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="../commen_file.jsp"></jsp:include>
<script src ="js/home.js"></script>
<style type="text/css">
*:focus{
	box-shadow:none !important;
}

</style>
</head>
<body class="bg-light">
<jsp:include page="../assest/nav.jsp"></jsp:include>
<div class="container-fluid" style="margin-top:50px">

	<div class="row">
		<div class="col-md-12 p-4 d-flex justify-content-between flex-wrap">
			${html }		
		</div>
	</div>

</div>
<jsp:include page="../assest/footer.jsp"></jsp:include>

</body>
</html>