
<%@page import="Dao.ShowRating"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>home</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="commen_file.jsp"></jsp:include>
<script src = "js/home.js"></script>
<style type="text/css">
*:focus{
	box-shadow:none !important;
}
.carousel-caption{
line-height:80px;
height:100%;
}
@media(max-width:768px){
	#top-slider h1{
	margin-top:20%;
	font-size:180% !important;
	}
	
	#top-slider h4{
	font-size:120% !important;
	}
	#top-slider button a{
	font-size:15px !important;
	}
	.carousel-caption{
	justify-content:center !important;
	}
}
@media(max-width:576px){
	#category-showcase img{
	width:80%;
	margin-left:10%;
	margin-right:10%;
	}	
}

</style>
</head>
<body class="bg-white">
<jsp:include page="assest/nav.jsp"></jsp:include>

	<div class="container-fluid p-0" style="margin-top:10px">
		<div class="carousel slide" data-ride="carousel" id="top-slider">
			<div class="carousel-inner">
				<c:forEach items="${header_showcasees }" var="header_showcase">
				
				<c:if test="${header_showcase.getH_align() eq 'center' }">
				<c:set var = "textAlign" scope = "page" value = "text-center"/>
				</c:if>
				<c:if test="${header_showcase.getH_align() != 'center' }">
				<c:set var = "textAlign" scope = "page" value = "text-left"/>
				</c:if>
				
					<div class="carousel-item carousel-item-control">
						<img alt="" src="${header_showcase.getTitle_image() }" class="w-100">
						<div class="carousel-caption ${textAlign } d-flex h-100" style="justify-content:${header_showcase.getH_align()};align-items:${header_showcase.getV_align()}">
						<div>
							<h1 style="color:${header_showcase.getTitle_color()};font-size:${header_showcase.getTitle_size()}">${header_showcase.getTitle_text() }</h1>
							<h4 style="color:${header_showcase.getSubtitle_color()};font-size:${header_showcase.getSubtitle_size()}">${header_showcase.getSubtitle_text() }</h4>
							${header_showcase.getButtons() }
							
						</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

<!-- start category showcase -->
<div class="container animate__animated animate__zoomIn" id="category-showcase">
<h4 class="my-4 text-center">CATEGORY SHOWCASE</h4>
	<div class=row>
		
		<div class="col-md-4">
			<div class="position-relative mb-3">
			<button class="btn bg-white px-2 shadow-lg border p-2" style="position:absolute;top:50%;left:50% ;transform:translate(-50%,-50%);z-index:1000"><a href="show-products?p=${category_showcase.getTopLeftLbl()}">${category_showcase.getTopLeftLbl() }</a></button>
				<img src="${category_showcase.getTopLeftImg() }" width="100%">
			</div>
			
			<div class="position-relative mb-3">
			<button class="btn bg-white px-2 shadow-lg border p-2" style="position:absolute;top:50%;left:50%; transform:translate(-50%,-50%);z-index:1000"><a href="show-products?p=${category_showcase.getBottomLeftLbl()}">${category_showcase.getBottomLeftLbl()} </a></button>
				<img src="${category_showcase.getBottomLeftImg()}" width="100%">
			</div>
		</div>
		
		<div class="col-md-4">
				<div class="position-relative mb-3">
					<button class="btn bg-white px-2 shadow-lg border p-2" style="position:absolute;top:50%;left:50% ;transform:translate(-50%,-50%);z-index:1000"><a href="show-products?p=${category_showcase.getCenterLbl()}">${category_showcase.getCenterLbl()}</a></button>
					<img src="${category_showcase.getCenterImg()}" width="100%">
				</div>
		</div>
		
		<div class="col-md-4">
			<div class="position-relative mb-3">
			<button class="btn bg-white px-2 shadow-lg border p-2" style="position:	absolute;top:50%;left:50% ;transform:translate(-50%,-50%);z-index:1000"><a href="show-products?p=${category_showcase.getTopRightLbl()}"> ${category_showcase.getTopRightLbl()}</a></button>
				<img src="${category_showcase.getTopRightImg()}" width="100%">
			</div>
			
			<div class="position-relative mb-3">
			<button class="btn bg-white px-2 shadow-lg border p-2" style="position:absolute;top:50%;left:50%; transform:translate(-50%,-50%);z-index:1000"><a href="show-products?p=${category_showcase.getBottomRightLbl()}">${category_showcase.getBottomRightLbl()}</a></button>
				<img src="${category_showcase.getBottomRightImg()}" width="100%">
			</div>
		</div>
		
	</div>
</div>
<!-- end category showcase -->

<div class="container-fluid">
<h4 class="text-center">PRODUCTS FOR YOU</h4>
	<div class="row"> 
	
	<c:forEach items="${products }" var="product" >
		<div class="col-md-3 py-5 text-center">
			<img alt="thumb_pic" src="${product.getThumb_pic() }"  width="250" height="316">
			<br>	
			<span class="text-uppercase font-weight-bold">${product.getBrands() }</span><br>
		
								<c:if test="${product.getRating()==0 }">
								<i class="star fa fa-star text-warning"></i>
								<i class="star fa fa-star-o text-warning"></i>
								<i class="star fa fa-star-o text-warning"></i>
								<i class="star fa fa-star-o text-warning"></i>
								<i class="star fa fa-star-o text-warning"></i>
								</c:if>
								<c:if test="${product.getRating()!=0 }">
									<c:forEach begin="0" end="${product.getRating()-1}">
									<i class="star fa fa-star text-warning"></i>
									</c:forEach>
									
									<c:if test="${product.getRating()!=5 }">
										<c:set var="restStar" value="${5- product.getRating()}" ></c:set>
																		
										<c:forEach begin="1" end="${restStar}">
										<i class="star fa fa-star-o text-warning" ></i>
										</c:forEach>
									</c:if>
																	
								</c:if>
			<br>
			<span>${product.getTitle() }</span>
			<br>
			<span><i class="fa fa-rupee"></i> ${product.getPrice() }</span>
			<br>
			<button type="button" product_id = "${product.getId() }" class="btn btn-danger mt-3 cart-btn"><i class="fa fa-shopping-cart"></i>ADD TO CART</button>
			<button type="button" product_id = "${product.getId() }" class=" buy-btn btn btn-primary mt-3">BUY NOW</button>
		</div>
		</c:forEach>
	
	</div>
</div>




<jsp:include page="assest/footer.jsp"></jsp:include>

<script>
$(document).ready(function(){
	let coro = document.querySelector(".carousel-item-control");
	$(coro).addClass("active");
});
</script>
</body>
</html>