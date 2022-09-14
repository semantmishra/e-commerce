<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="container-fluid bg-white border-top py-3" style="margin-top: 100px">
	<div class="container d-flex justify-content-between">
		<div class="input-group w-50">
			<input type="email" name="subscribe-email" placeholder="example@gmail.com" class="form-control">
			
			<div class="input-group-append btn p-0 m-0 subscribe-btn">
				<span class="input-group-text bg-danger text-white scb"><b>SUBSCRIBE</b></span>
			</div>
		</div>
		<div class="btn-group">
			<button class="btn btn-dark border">FOLLOW US</button>
			<button class="btn border px-3"><a href="${branding.getFacebook_url()}"><i class="fa fa-facebook"></i></a></button>
			<button class="btn border px-3"><a href="${branding.getTwitter_url()}"><i class="fa fa-twitter"></i></a></button>
		</div>
	</div>
</div>

<div class="container-fluid bg-dark">
		<div class="row">
			<div class="col-md-3">
				<h5 class="text-light">CATEGORY </h5>
				<c:forEach items="${menus}" var = "menu">
					<a href="#" class="d-block py-2 text-capitlize">${menu}</a>
				</c:forEach>
				
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-3">
				<h5 class="text-light">POLICIES</h5>
				<a href="Privacy.jsp" class="d-block py-2"> Privacy policy </a>
				<a href="Cookies.jsp" class="d-block py-2"> Cookies policy </a>
				<a href="Terms.jsp" class="d-block py-2"> Terms & conditions</a>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-4">
				<h5 class="text-light"> CONTACTS </h5>
				<p class="text-light"> Venue : ${branding.getAddress()}</p>
				<p class="text-light"> Call : ${branding.getPhone()} </p>
				<p class="text-light"> Email : ${branding.getEmail()}</p>
				<p class="text-light"> Website : ${branding.getDomain_name()} </p>
			</div>
		</div>
	</div>
