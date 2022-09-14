<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Products | ${param.p }</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="../commen_file.jsp"></jsp:include>
<script type="text/javascript" src="js/home.js"></script>
<style type="text/css">
*:focus{
box-shadow:none !important;
}
</style>
</head>
<body class="bg-light">
<jsp:include page="../assest/nav.jsp"></jsp:include>
<div class="container-fluid" style="margin-top:50px">
<a href="${param.p }">${param.p }</a>
	<div class="row">
		<div class="col-md-3">
			<div class="bg-white w-100 p-4 border">
			
			
				<h5> Filter by Brands</h5>
				<div class="btn-group-vertical mb-4"> 
				<button cat-name="${param.p }" brand-name="all" class="filter-btn px-0 btn text-capitalize text-left"><i class="fa fa-angle-double-right"></i>  All</button>
				<c:forEach items="${s }" var="c">
				<button cat-name="${c.getC_name() }" brand-name="${c.getBrands() }" class="filter-btn px-0 btn text-capitalize text-left"><i class="fa fa-angle-double-right"></i>  ${c.getBrands() }</button>	
				</c:forEach>
				</div>
				
				<h5> Filter by Price</h5>
				<div class="btn-group-vertical bg-light border shadow-sm mb-4">
					<button class="btn">
						<input type="number" placeholder="minimum price" class="min-price form-control" >
					</button>
					
					<button class="btn">
						<input type="number" placeholder="maximum price" class="max-price form-control">
					</button>
					<button cat-name="${param.p }" class="btn price-filter-btn"> Get product </button>
				</div>
				
				<h5> Sort By </h5>
				<select class="form-control sort-by">
					<option value="recomended">recomended</option>
					<option value="high">High to low</option>
					<option value="low">Low to high</option>
					<option value="new">Newest</option>
				</select>
				
			</div>
		</div>
		<div class="col-md-9">
			<div class="bg-white w-100 p-4 border product-result d-flex flex-wrap justify-content-between">  

			</div>
		</div>
	
	</div>
</div>
<jsp:include page="../assest/footer.jsp"></jsp:include>

</body>
</html>