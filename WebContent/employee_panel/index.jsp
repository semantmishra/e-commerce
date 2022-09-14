<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>home</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
  
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<link rel="stylesheet" href="employee_panel/css/index.css" >
<jsp:include page="../commen_file.jsp"></jsp:include>
</head>
<body>


	<div class="container-fluid">
		<div class="sidebar">
			<button class="active text-white bg-dark collapse-item btn w-100 text-left" access-link="Branding_details.html" style="font-size:20px">
				<i class="fa fa-image"></i>
				Branding details
			</button>
			
			<button class="collapse-item btn w-100 text-left" access-link="delivery_area.jsp" style="font-size:20px">
				<i class="fa fa-map-marker"></i>
				Delivery area
			</button>
			
			<button class="collapse-item btn w-100 text-left" access-link="keyword.html" style="font-size:20px">
				<i class="fa fa-tag"></i>
				Keyword planner
			</button>
			
			<button class="collapse-item btn w-100 text-left" access-link="sales_report.jsp" style="font-size:20px">
				<i class="fa fa-shopping-bag"></i>
				Sales Report
			</button>
			
				<button class="homepage-design-btn btn w-100 text-left" style="font-size:20px">
				<i class="fa fa-home"></i>
				Homepage design
				<i class="fa fa-angle-down close mt-2"></i>
			</button>
			
			<ul class="collapse homepage-design-collapse">
				<li class="border-left p-2 collapse-item" access-link="header_showcase.html">Header showcase</li>
				<li class="border-left p-2 collapse-item" access-link="category_showcase.jsp">Category showcase</li>
			</ul>
			
			
				<button class="stock-update-btn btn w-100 text-left" style="font-size:20px">
				<i class="fa fa-shopping-cart"></i>
				Stock Update
				<i class="fa fa-angle-down close mt-2"></i>
			</button>
			
			<ul class="collapse stock-update-btn-menu">
				<li class="border-left p-2 collapse-item" access-link="create_category.html">Create category</li>
				<li class="border-left p-2 collapse-item" access-link="create_brands.html">Create brands</li>
				<li class="border-left p-2 collapse-item" access-link="create_product.html">Create product</li>
			</ul>
		</div>
		<div class="page">

		</div>
		
	<div class="container">
		<div class="modal fade" id="sub-modal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title"> Do you want to send notification ?</h4>
					</div>
					
					<div class="modal-body d-flex justify-content-between">
					
						<div class="btn-group border shadow-sm">
						
						<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/ecommerce" user="root"  password=""/>
				<sql:query dataSource="${db}" var="rs">    
					SELECT count(id) as 'count' FROM `subscriber` 
				</sql:query>
							<button class="btn btn-danger rounded-0">SUBSCRIBE</button>
							<c:forEach items="${rs.rows }" var="row">
								<button class="btn btn-dark rounded-0">${row.count }</button>
							</c:forEach>
						</div>
							<button class="btn btn-primary send-btn">SEND</button>
					
					</div>
					
					<div class="modal-footer">
						<span class="d-block mx-auto">SEM NOTIFICATION</span>
					</div>
					
				</div>
				
			</div>
		</div>
	</div>
		
<script type="text/javascript">


</script>
 <script src="employee_panel/js/index.js"></script>
</body>
</html>
	