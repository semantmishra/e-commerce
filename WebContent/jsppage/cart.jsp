<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="../commen_file.jsp"></jsp:include>
<script src = "js/home.js"></script>
<style type="text/css">
*:focus{
	box-shadow:none !important;
}

</style>
</head>
<body class="bg-light">
<jsp:include page="../assest/nav.jsp"></jsp:include>

<div class="container" style="margin-top:80px;margin-bottom:80px;">
	<div class="row">
		<div class="col-md-8">
			<div class="bg-white">
				<c:forEach items="${carts }" var="cart">
				<div class="media border mb-3 shadow-sm p-2 rounded-lg">
				
					<div class="media-left mr-2">
						<img alt="" src="${cart.getThumb() }" width="100">		
					</div>
					
					<div class="media-body">
						<h5 class="text-capitalize p-0 m-0"> ${cart.getTitle() }  </h5>
						<span>${cart.getProduct_bands() }</span>
						<br>
						<span> <i class="fa fa-rupee"></i> ${cart.getPrice() }</span><br>
						
						<div class="btn-group shadow-sm mt-2">
							<button product_id="${cart.getProductId() }" id="${cart.getId() }" class="btn btn-primary delete-cart-btn"> <i class="fa fa-trash"></i> </button>
							<button product_id="${cart.getProductId() }" id="${cart.getId() }" class="btn btn-danger buy-btn">BUY NOW </button>
						
						</div>
					</div>
					
				</div>
				</c:forEach>

			<c:if test="${carts == '[]' }">
				<h1 class="text-center p-3" style="fornt-size:50px"> <i class="fa fa-shopping-cart"></i> Your cart is empty</h1>
			</c:if>
			</div>
		</div>
		<div class="col-md-4">
			<div class="bg-white">
				testing 2
			</div>
		</div>
	</div>
	
</div>

<jsp:include page="../assest/footer.jsp"></jsp:include>

</body>
</html>