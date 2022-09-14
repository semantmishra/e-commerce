
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Purchase Entry</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="../commen_file.jsp"></jsp:include>
<style type="text/css">
*:focus{
	box-shadow:none !important;
}

</style>
</head>
<body class="bg-light">
<%@include file="../assest/nav.jsp" %>

<div class="container " style="margin-top:50px">
	<div class="jumbotron bg-white border-right border-top border-bottom shadow-sm" style="border-left:5px solid #47e20a">
		
		<center>
		<c:if test="${msg=='SUCCESS' }">
			<i class="fa fa-check-circle" style="font-size:100px;color:#47e20a"> </i>
		</c:if>
		<c:if test="${msg!='SUCCESS' }">
			<i class="fa fa-times-circle" style="font-size:100px;color:red"> </i>
		</c:if>
		<h2> ${msg } </h2>
		<p> PLEASE OPEN YOUR EMAIL MORE INFORMATION </p>
		<button class="btn btn-danger py-2 px-3"> <a class="text-decoration-none text-white" href="Home"> SHOP MORE </a>  </button>
		</center>
	</div>
</div>
<%@include file="../assest/footer.jsp" %>
</body>
</html>