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
<style type="text/css">
*:focus{
	box-shadow:none !important;
}

</style>
</head>
<body class="bg-light">
<jsp:include page="assest/nav.jsp"></jsp:include>

<div class="container bg-white p-5 shadow-lg border" style="margin-top:50px">
<h2 class="text-center text-uppercase">Create an account</h2>
<hr>
	<div class="row">
		<div class="col-md-6">
			<form class="signup-form" >
				<div class="form-group">
				
					<label for="firstname">First name<sup class="text-danger">*</sup></label>
					<input type="hidden" value="insert" name="opt"> 
					<input type="text" name="firstname" id="firstname" class="form-control bg-light" placeholder="Enter First name" required="required" >
				</div>
				
				<div class="form-group">
					<label for="lastname">last name<sup class="text-danger">*</sup></label>
					<input type="text" name="lastname" id="lastname" class="form-control bg-light" placeholder="Enter last name" required="required" >
				</div>
				
				<div class="form-group">
					<label for="email">Email<sup class="text-danger">*</sup></label>
					<input type="email" name="email" id="email" class="form-control bg-light email" placeholder="Enter Email" required="required" >
				</div>
				
				<div class="form-group">
					<label for="mobile">Mobile<sup class="text-danger">*</sup></label>
					<input type="number" name="mobile" id="mobile" class="form-control bg-light" placeholder="Enter mobile" required="required" >
				</div>
				
				<div class="form-group">
					<label for="password">Password<sup class="text-danger">*</sup></label>
					<input type="password" name="password" id="password" class="form-control bg-light" placeholder="Enter password" required="required" >
				</div>
				
				<div class="form-group">
					<label for="address">Address<sup class="text-danger">*</sup></label>
					<textarea  name="address" id="address" required="required" class="form-control bg-light"></textarea>
				</div>
				
				<div class="form-group">
					<label for="state">State<sup class="text-danger">*</sup></label>
					<input type="text" name="state" id="state" class="form-control bg-light" placeholder="Enter state" required="required" >
				</div>
				
				<div class="form-group">
					<label for="country">Country<sup class="text-danger">*</sup></label>
					<input type="text" name="country" id="country" class="form-control bg-light" placeholder="Enter country" required="required" >
				</div>
				
				<div class="form-group">
					<label for="pincode">Pincode<sup class="text-danger">*</sup></label>
					<input type="number" name="pincode" id="pincode" class="form-control bg-light" placeholder="Enter pincode" required="required" >
				</div>
				
				<div class="form-group">
					<button class="btn btn-primary py-2 shadow-sm register-btn" type="submit">Register now</button>
				</div>
			
			</form>
			
			<form class="d-none otp-form">
				<div class="form-group">
					<div>
						<div class="btn-group border shadow-sm">
							<button type="button" class="btn btn-light">
								<input type="number" name="otp"  placeholder="9876543210" class="otp form-control">
							</button>
							<button type="button" class="btn btn-light verify-btn">
								VERIFY
							</button>
							<button type="button" class="btn btn-light resend-btn">
								RESEND
							</button>
						</div>
					</div>
				
				</div>
			</form>
			
			
		</div>
		<div class="col-md-6"></div>
	</div>
</div>

<jsp:include page="assest/footer.jsp"></jsp:include>


<script type="text/javascript">
$(document).ready(function(){
	$(".signup-form").submit(function(e){
		e.preventDefault();
		$.ajax({
			type:"POST",
			url:"/ECommerce/register",
			data : new FormData(this),

			processData:false,
			contentType:false,
			beforeSend:function(){
				$(".register-btn").html("Please wait...");
			},
			success:function(res){
				$(".register-btn").html("REGISTER NOW");
				if(res.trim()=="done")
				{
					$(".otp-form").removeClass("d-none");
					$(".signup-form").addClass("d-none");
					
					// verify otp
					$(".verify-btn").click(function(e){
						
						e.preventDefault();
						$.ajax({
							type:"POST",
							url:"/ECommerce/register",
							data:{
								otp:$(".otp").val(),
								opt:"otp-verify",
								email:$("#email").val()
							},
							beforeSend:function(){
								$(this).html("Please wait...");
							},
							success:function(res){
								console.log(res);
								if(res.trim()=="done")
								{
									window.location = "signin.jsp";	
								}
								else{
									$(".verify-btn").html(res);
									setTimeout(function(){
										$(".verify-btn").html("VERIFY");
										$(".otp").val("");
									},3000);
								}
							}
						});
				});
					
				// resend 
				
				$(".resend-btn").click(function(){
					
					$.ajax({
						type:"POST",
						url:"/ECommerce/register",
						data:{
							opt:"resendOtp",
							mobile:$("#mobile").val(),
							email:$("#email").val()
						},
						success:function(res){
							
							if(res.trim()=="done")
							{
								$(".resend-btn").html("OTP HAS BEEN SENDED");
								setTimeout(function(){
									$(".verify-btn").html("VERIFY");
									$(".resend-btn").html("Resend");
								},3000);	
							}
							else{
								$(".resend-btn").html(res);
								setTimeout(function(){
									$(".resend-btn").html("Resend");
								},3000);
							}
						}
					});
					
				});
			}
			else{
			alert(res);		
				}
			}
		});
	});
});
</script>
</body>
</html>