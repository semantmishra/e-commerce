<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row animate__animated animate__zoomIn">
				<div class="col-md-3"></div>
				
				<div class="col-md-6 bg-white">
				<form class="set-area-form">
				<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/ecommerce" user="root"  password=""/>
				<sql:query dataSource="${db}" var="rs">    
					SELECT `id`, `name`FROM `countries`
				</sql:query>
					<div class="jumbotron bg-white py-3">
						<h4>SET DELIVERY LOCATION</h4>
						<select class="form-control mb-3 country" name="country">
							<option>Choose country</option>
							<c:forEach var="row" items="${rs.rows}">
								<option value="${row.id }">${row.name }</option>
							</c:forEach>
						</select>
						
						<select class="form-control mb-3 state" name="state">
							<option>Choose state</option>
						</select>
						
						<select class="form-control mb-3 city" name="city">
							<option>Choose city</option>
						</select>
						
						<input type="number" class="form-control mb-3 pincode" name="pincode" placeholder="Pincode" readonly="readonly">
						<input type="text" class="form-control mb-3 delivery-time" name="days" placeholder="Delivery in 5 to 10 days">
						<input type="hidden" value="insert" name="opt">
						<select class="form-control mb-3 payment-mode" name="payment-mode">
							<option>Choose payment mode</option>
							<option>online</option>
							<option>all</option>
						</select>
						<button type="submit" class="btn btn-primary">SET AREA</button>
					</div>
				</form>
			</div>
				
				<div class="col-md-3"></div>
			
			</div>
