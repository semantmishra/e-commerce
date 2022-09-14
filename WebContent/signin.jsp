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
<h2 class="text-center text-uppercase">login</h2>
<hr>
	<div class="row">
		<div class="col-md-6">
			<form class="signin-form">				
				<div class="form-group">
				
					<label for="email">Email<sup class="text-danger">*</sup></label>
					<input type="email" name="email" id="email" class="form-control bg-light" placeholder="Enter Email" required="required" >
						<input type="hidden" name="opt" id="email" value="login">
				</div>

				<div class="form-group">
					<label for="password">Password<sup class="text-danger">*</sup></label>
					<input type="password" name="password" id="password" class="form-control bg-light" placeholder="Enter password" required="required" >
				</div>
				
				<div class="form-group">
					<button class="btn btn-primary py-2 shadow-sm" type="submit">Login now</button>
				</div>
				
			<div class="alert alert-success login-form-notice d-none text-center"><b> Login Success please  waiting...</b></div>
			</form>
			
			<form class="otp-form d-none">
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
				<div class="alert alert-success otp-form-notice d-none"><b>Mobile number verified please login again</b></div>
			</form>
		</div>
		<div class="col-md-1"></div>
		<div class="col-md-5">
		<h4 class="text-uppercase">new customer</h4>
		<p> If you don't have an account please register with us</p>
		<a href="signup.jsp" class="btn btn-danger py-2 my-3">Create an account</a>
		</div>
	</div>
</div>

<jsp:include page="assest/footer.jsp"></jsp:include>
<script>
$(document).ready(function(){
	$(".signin-form").submit(function(e){
		e.preventDefault();
		$.ajax({
			type:"POST",
			url:"user-login",
			data:new FormData(this),
			processData:false,
			contentType:false,
			success:function(res){
				if(res.trim()=="pending")
				{
					$(".otp-form").removeClass("d-none");
					$(".signin-form").addClass("d-none");
					
					// verify otp
					$(".verify-btn").click(function(e){
						
						e.preventDefault();
						$.ajax({
							type:"POST",
							url:"user-login",
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
									$(".otp-form-notice").removeClass("d-none");
									setTimeout(function(){
									
									window.location = "signin.jsp";},4000);
										
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
								//mobile:$("#mobile").val(),
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
					if(res.trim()=="done")
					{
						$(".login-form-notice").removeClass("d-none");
						$(".login-form-notice").html("<b>Login success please wating ...</b>");
						setTimeout(function(){
							window.location = "Home";
							},4000);
					}
					else if(res.trim()=="invalid"){
						
						$(".login-form-notice").removeClass("d-none");
						$(".login-form-notice").html("<b>Invalid email or password</b>");
						setTimeout(function(){
							$(".login-form-notice").addClass("d-none");
							
							},4000);
					}
				}
			}
		});
	
	});
});
</script>



</body>
</html>