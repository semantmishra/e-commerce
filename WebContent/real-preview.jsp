<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="common_files/css/bootstrap.min.css">
  <script src="common_files/js/jquery.min.js"></script>
  <script src="common_files/js/popper.min.js"></script>
  <script src="common_files/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
  <link rel="stylesheet" href="common_files/css/animate.min.css" />

</head>
<body>
	<div class="container-fluid p-0">
	<div class="carousel slide" data-ride="carousel" data-interval="500">
			<div class="carousel-inner">
				
					<div class="carousel-item active">
						<img alt="" src="sem.jpg" class="w-100">
						<div class="carousel-caption ${text_align } d-flex h-100" style="justify-content:${h_align};align-items:${v_align}">
						<div>
							${ html}
							
						</div>
						</div>
					</div>
			</div>
		</div>
		</div>

</body>
</html>
