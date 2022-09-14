<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Profile</title>
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
<jsp:include page="../assest/nav.jsp"></jsp:include>
<div class="container" style="margin-top:50px">
	<div class="row">
		<div class="col-md-12">
			<ul class="nav nav-tabs">
				<li class="nav-item">
				<a href="#personal" class="nav-link active" data-toggle="tab">
					PERSONAL
				</a>
				</li>
				
				<li class="nav-item">
				<a href="#privacy" class="nav-link" data-toggle="tab">
					PRAVACY
				</a>
				</li>
				
				<li class="nav-item">
				<a href="#purchase" class="nav-link" data-toggle="tab">
					PURCHASE HISTORY
				</a>
				</li>
			</ul>
			
			<div class="tab-content">
				<div class="tab-pane active" id="personal">
					<div class="jumbotron py-3 my-3 bg-white shadow-sm border-right border-top border-bottom" style="border-left:5px solid blue">
						<form class="personal-form">
							<div class="form-group">
								<label class="text-uppercase" for="firstname">First name</label>
								<input type="hidden" value="update" name="opt"> 
								<input type="text" value="${user.getFirstname() }" name="firstname" id="firstname" class="form-control " placeholder="Enter First name" required="required" >
							</div>
							
							<div class="form-group">
								<label class="text-uppercase" for="text-uppercaselastname">last name</label>
								<input type="text" value="${user.getLastname() }" name="lastname" id="lastname" class="form-control " placeholder="Enter last name" required="required" >
							</div>
							
							<div class="form-group">
								<label class="text-uppercase" for="email">Email</label>
								<input type="email" value="${user.getEmail() }"name="email" id="email" class="form-control  email" placeholder="Enter Email" required="required" >
							</div>
							
							<div class="form-group">
								<label class="text-uppercase" for="mobile">Mobile</label>
								<input type="number" value="${user.getMobile() }" name="mobile" id="mobile" class="form-control " placeholder="Enter mobile" required="required" >
							</div>
							
							<div class="form-group">
								<label class="text-uppercase" for="address">Address</label>
								<textarea  name="address" id="address" required="required" class="form-control ">${user.getAddress() }</textarea>
							</div>
							
							<div class="form-group">
								<label class="text-uppercase" for="state">State</label>
								<input type="text" name="state" value="${user.getState() }" id="state" class="form-control " placeholder="Enter state" required="required" >
							</div>
							
							<div class="form-group">
								<label class="text-uppercase" for="country">Country</label>
								<input type="text" name="country" value="${user.getCountry() }" id="country" class="form-control " placeholder="Enter country" required="required" >
							</div>
							
							<div class="form-group">
								<label class="text-uppercase" for="pincode">Pincode</label>
								<input type="number" name="pincode" value="${user.getPincode() }" id="pincode" class="form-control " placeholder="Enter pincode" required="required" >
							</div>
							
							<div class="form-group">
								<button class="btn btn-primary py-2 shadow-sm update-profile-btn" type="submit">UPDATE</button>
							</div>
						</form>
					
					</div>
				</div>
				
				<div class="tab-pane fade " id="privacy">
					
					<div class="jumbotron py-3 my-3 bg-white shadow-sm border-right border-top border-bottom" style="border-left:5px solid blue">
						<form class="privacy-form">
							<div class="form-group">
								<label class="text-uppercase" for="oldpassword">OLD PASSWORD</label>
								<input type="hidden" value="cp" name="opt"> 
								<input type="password" name="oldpassword" id="oldpassword" class="form-control " required="required" >
							</div>
							
							<div class="form-group">
								<label class="text-uppercase" for="newpassword">New PASSWORD</label> 
								<input type="password" name="newpassword" id="newpassword" class="form-control " required="required" >
							</div>
							
							<div class="form-group">
								<label class="text-uppercase" for="cpassword">CONFIRM PASSWORD</label> 
								<input type="password" name="cpassword" id="cpassword" class="form-control " required="required" >
							</div>
							
							<div class="form-group">
								<button class="btn btn-primary py-2 shadow-sm change-password-btn" type="submit">CHANGE PASSWORD</button>
							</div>
						</form>
					</div>
				</div>
				
				<div class="tab-pane fade" id="purchase">
					
					<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/ecommerce" password="" user="root" />
					
					<sql:query var="rs" dataSource="${db }">
				SELECT products.id as 'id' ,products.title as 'title',products.thumb_pic as 'thumb_pic',(products.price * purchase.qnt) as 'price',
				purchase.qnt as 'qnt',purchase.payment_mode as 'paymode',purchase.status as 'status',purchase.purchase_date as 'date',purchase.rating as 'rating'
				,purchase.comment as 'comment'    FROM `purchase` 
LEFT JOIN products on products.id = purchase.product_id WHERE purchase.username = '${sessionScope.user}'
					</sql:query>
					<c:forEach var="row" items="${rs.rows }">
					<div class="media border bg-white shadow-sm my-4 p-2">
					
						<img src="${row.thumb_pic }" class="mr-2" width="100">
						<div class="media-body">
							<h4 class="text-uppercase">${row.Title }</h4>
							<p class="p-0 m-0"> <i class="fa fa-rupee"></i> ${row.price }</p>
							<p class="p-0 m-0">Quntity : ${row.qnt }</p>
							<p class="p-0 m-0"> Pay Mode: ${row.paymode }</p>
							<p class="p-0 m-0">Status : ${row.status }</p>
							<p class="p-0 m-0">Date : ${row.date }</p>
							<c:if test="${row.status=='delivered' }">
								<c:if test="${row.rating==0 }">
								<h4 class="heding">PLEASE RATE THE PRODUCT</h4>
								
									<c:forEach begin="0" end="4" var="i">
									
									<i index="${i }" class="star fa fa-star-o text-warning" style="font-size:25px;margin-right:2px"></i>
									</c:forEach>
									<br>
									
									<div class="form-group comment-box">
										<lable for="comment">Comment</lable>
										<textarea maxlength="100" id="comment" class="form-control w-75" ></textarea>
										
									</div>
									
									<div class="form-group picture-box">
										<lable for="picture">Picture</lable>
										<input type="file" accept="image/*" id="picture" class="form-control w-75">
										
									</div>
									<p class="comment-info text-success font-weight-bold d-none"></p>
									<button p_id="${row.id }" class="my-3 btn btn-primary star-btn d-none">POST</button>
								</c:if>
								
								<c:if test="${row.rating!=0 }">
									<h4>YOUR RATING </h4>
									<c:forEach begin="0" end="${row.rating-1}" var="i">
									<i index="${i }" class="fa fa-star text-warning" style="pointer-events:none;font-size:25px;margin-right:2px"></i>
									</c:forEach>
									
									<c:if test="${row.rating!=5 }">
										<c:set var="restStar" value="${5- row.rating }" ></c:set>
										<c:set var="arestStar" value="${row.rating }" ></c:set>
									
									<c:forEach begin="1" end="${restStar}" var="i">
									<i index="${i }" class="fa fa-star-o text-warning" style="pointer-events:none;font-size:25px;margin-right:2px"></i>
									</c:forEach>
									</c:if>
									<br>
									<p>${row.comment }  </p>									
								</c:if>
							</c:if>
						</div>
					</div>
					</c:forEach>
				</div>
			
			</div>
		</div>
	</div>
</div>
<jsp:include page="../assest/footer.jsp"></jsp:include>
<script src="js/home.js"></script>
</body>
</html>