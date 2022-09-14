<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Buy Product</title>
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

<c:set var="id" value="${param.p_id }"></c:set>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/ecommerce" user="root"  password=""/>

<sql:query dataSource="${db}" sql="SELECT `id`, `category_name`, `brands`, `title`, `description`, `price`, `quantity`,`thumb_pic`, `front_pic`, `top_pic`, `bottom_pic`, `left_pic`, `right_pic`FROM `products` WHERE `id` =${id }" var="rs">  
</sql:query> 

<div class="container" style="margin-top:80px;margin-bottom:80px;">
<c:forEach var="row" items="${rs.rows}">
<a href="#">${row.category_name }</a> > <a href="#">${row.brands }</a> >  <a href="#">${row.title }</a>



	<div class="row mt-3">
		<div class="col-md-6 bg-white py-4 mb-3" align="center">
			<img src="${row.thumb_pic }" width="300" class="preview">
			<br>
			<img src="${row.thumb_pic }" width="80" class="border shadow-sm thumb-pic">
			<img src="${row.front_pic }" width="80" class="border shadow-sm thumb-pic">
			<img src="${row.top_pic }" width="80" class="border shadow-sm thumb-pic">
			<img src="${row.bottom_pic }" width="80" class="border shadow-sm thumb-pic">
			<img src="${row.left_pic }" width="80" class="border shadow-sm thumb-pic">
			<img src="${row.right_pic}" width="80" class="border shadow-sm thumb-pic">
			
		</div>
		
		<div class="col-md-6 bg-white py-4" style="border-left:5px solid #F8F9FA">
			<h4 class="p-0 m-0 text-capitalize mt-2">${row.title }</h4>
			<p class="p-0 m-0 text-uppercase">${row.brands }</p>
			<p><i class="fa fa-rupee"></i>${row.price }</p>
			<h4 >Description</h4>
			${row.description }
			
			<h4>Quantity</h4>
			<c:if test="${stock<=5 && stock!=0 }">
				<p class="text-success font-weight-bold"> Only <span class="stock">${stock }</span> in Stocks  </p>
			</c:if>
			<c:if test="${stock>5}">
				<p class="text-success font-weight-bold d-none stock"> ${stock } </p>
			</c:if>
			<input type="number" class="form-control quantity mb-3" style="width:80px" value="1">
						
			<h4>Pay Mode</h4>
			<c:if test="${checkDeliveryPaymet.getPayment_mode()=='all'  }">
			<label><input type="radio" name="pay-mode" value="online"> ONLINE</label>
			<label><input type="radio" name="pay-mode" value="cod"> CASE ON DELIVERY</label>
			</c:if>
			<c:if test="${checkDeliveryPaymet.getPayment_mode()=='online'  }">
			<label><input type="radio" name="pay-mode" value="online"> ONLINE</label>
			</c:if>
			
			<br>
			<c:if test="${ProductEexist}">
			<button class="btn btn-danger cart-btn mt-3" product_id="${id }" ><i class="fa fa-shopping-cart"></i> ADD TO CART</button>
			</c:if>
			
			<c:if test="${checkDeliveryPaymet.getPincode()!=fullname[1] }">
			<button class="btn btn-info mt-3"> Whoops ! product delivery Not avilable in your area  </button>
			</c:if>
			
			<c:if test="${checkDeliveryPaymet.getPincode()==fullname[1]  }">
				<c:if test="${stock!=0 }">
				<button class="btn btn-primary puchase-btn mt-3" p_id="${id }">BUY NOW</button>
				</c:if>
				
				<c:if test="${stock==0 }">
					<button class="btn btn-light mt-3">OUT OF STOCK</button>
				</c:if>
			
			</c:if>
			<br>
			
			<h4 class="my-3"> CHECK PRODUCT AVAILABLITY</h4>
			<input type="number" class="form-control mb-3 w-75 pincode-field" placeholder="PINCODE" />
			<p class="pincode-message"></p>
			<button class="btn btn-warning py-2 px-3 pincode-btn"> PROCEED </button>
		</div>
		
		<div class="col-md-12 bg-white my-4 py-4">
			<h4>Product reviews</h4>
			
			<c:forEach var="rows" items="${ratings}">

				<div class="media pb-3">
					<img src="${rows.getPicture() }" class="border p-2 shadow-sm mr-2 rounded-circle" width="80" height="80" >
					<div class="media-body">
						<p class="m-0 p-0">${rows.getFullname() }</p>
						
									<c:forEach begin="0" end="${rows.getRating()-1}" var="i">
									<i index="${i }" class="star fa fa-star text-warning" style="pointer-events:none;font-size:25px;margin-right:2px"></i>
									</c:forEach>
									
									<c:if test="${rows.getRating()!=5 }">
										<c:set var="restStar" value="${5- rows.getRating() }" ></c:set>
										<c:set var="arestStar" value="${rows.getRating() }" ></c:set>
									
									<c:forEach begin="1" end="${restStar}" var="i">
									<i index="${i }" class="star fa fa-star-o text-warning" style="pointer-events:none;font-size:25px;margin-right:2px"></i>
									</c:forEach>
									</c:if>
									<p class="m-0 p-0">${rows.getComment() }</p>
						
					</div> 
				</div>
			</c:forEach>
	</div>
	</c:forEach>	
</div>

<jsp:include page="../assest/footer.jsp"></jsp:include>

</body>
</html>