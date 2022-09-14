<%@page import="Dao.CartDao"%>
<%@page import="Dao.UserLogin"%>
<%@page import="Beans.BrandingBean"%>
<%@page import="Dao.BrandingDao"%>
<%@page import="Dao.MenuDao"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="container-fluid bg-white shadow-sm">
	<%
	request.setAttribute("menus", new MenuDao().getMenu());
	request.setAttribute("branding",new BrandingDao().get());
	
	String user = (String)session.getAttribute("user");
	if(user!=null)
	{
		request.setAttribute("fullname",new UserLogin().getFullname(user).split(","));	
		request.setAttribute("cartItem",new CartDao().count(user));
	}
	
	%>
	
	<nav class="container navbar navbar-expand-sm bg-white">
	<a href="Home" class="navbar-brand text-uppercase border shadow-sm p-1">
	<img src="${branding.getBrand_logo()}" style="width:20px"/> 
	
	<small class="ml-1"> ${branding.getBrand_name()}</small>
	</a>
		<div class="collapse navbar-collapse" id ="menubox">
			<ul class="navbar-nav">
				<c:forEach items="${menus}" var = "menu">
					<li class="nav-item text-uppercase"> <a href="show-products?p=${menu}" class="nav-link text-dark">${menu}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="btn-group ml-auto border rounded-lg">
			<button type="button" class="btn border navbar-toggler" data-toggle="collapse" data-target="#menubox"> <i class="fa fa-bars"></i> </button>
			
			<button type="button" class="btn"><a class="cart-link" href="cart"> <i class="fa fa-shopping-cart"></i> </a> </button>
			<c:if test="${cartItem != 0 && cartItem != null}">
				<div class="text-center cart-notification" style="position:absolute;width:25px;height:25px;background-color:red; color:white;font-weight:bold;border-radius:50%;z-index:1000;left:25px;top:-10px">
					<span>${cartItem }</span>
				</div>
			</c:if>
			<button type="button" class="btn d-flex align-items-center">
			<input type="search" class="form-control mr-2 search" placeholder="Search produts here" style="width:300px; float:left" />
				<button class="btn search-icon">
					<i class="fa fa-search"></i>
				</button> 
			 </button>
			<button type="button" class="btn dropdown"> <i class="fa fa-user" data-toggle="dropdown"></i>
			<c:if test="${fullname[0]!=null }">
			<div class="dropdown-menu">
			<a href="profile" class="dropdown-item text-capitalize"> <i class="fa fa-user"></i> ${ fullname[0] } </a>
			<a href="Logout" class="dropdown-item"> <i class="fa fa-sign-in"></i> Sign out</a>
			
			</div>
			</c:if>
			<c:if test="${fullname[0]==null }">
			<div class="dropdown-menu">
			<a href="signup.jsp" class="dropdown-item"> <i class="fa fa-user"></i> Sign up</a>
			<a href="signin.jsp" class="dropdown-item"> <i class="fa fa-sign-in"></i> Sign in</a>
			</div>
			</c:if>
			</button>
			
			<div class="position-absolute bg-white search-hint" style="width:100%;z-index:3000;top:60px;">
				
			</div>
			
		</div>
	</nav>
	</div>