<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row animate__animated animate__zoomIn">
				<div class="col-md-12 d-flex justify-content-between">
					<div class="btn-group border bg-white shadow">
						<button class="btn bg-white">SORT BY</button>
						<button class="btn bg-white">
							<select class=" form-control sort-by">
							<option>All</option>
							<option value ="todays-sales">today's sales</option>
							<option value ="new-sales">Newest sales</option>
							<option value ="processing">Not Dispatched</option>
							<option value ="dispatched">Dispatched Products</option>
							<option value ="returned">Returned Products</option>
													
							</select>
						</button>
						
						<button class="btn btn-dark d-all">
							DISPATCH ALL
						</button>
						
					</div>
					
					<div class="btn-group border bg-white shadow">
						<button class="btn bg-white">EXPORT</button>
						<button class="btn bg-white">
							<select class=" form-control export-to">
							<option>Choose format</option>
							<option>PDF</option>
							<option>EXCEL</option>
							
							</select>
						</button>
					</div>
				</div>
				
			</div>
			<div class="row my-4 animate__animated animate__zoomIn">
				<div class="col-md-12">
				<sql:setDataSource var="db" url="jdbc:mysql://localhost/ecommerce" driver="com.mysql.jdbc.Driver" user="root" password="" />
						<sql:query var="q" dataSource="${db }">
				SELECT purchase.id as 'id',purchase.product_id as 'p_id', products.title as 'title', 
				purchase.qnt as 'qnt', (products.price * purchase.qnt) as 'price',users.address as 'Address',
				users.state as 'state', users.country as 'country',  users.pincode as 'pincode', 
				purchase.purchase_date as 'p_date', CONCAT(users.firstname,' ',users.lastname) as 'name',
				username,users.mobile as 'mobile',purchase.status as 'status',purchase.dispatched_date as 'date',purchase.deliver_date as 'd_date' FROM `purchase`
				LEFT JOIN users ON users.email = purchase.username
				LEFT JOIN products ON products.id = purchase.product_id
						</sql:query>
				<div class="table-responsive">
					<table class="w-100 puchase-table text-center text-center table table-bordered bg-white">
						
												
						<tr> 
							<td> S/NO </td>
							<td>PRODUCT_ID</td>
							<td>TITLE</td>
							<td>QUNTITY</td>
							<td>PRICE</td>
							<td>ADDRESS</td>
							<td>STATE</td>
							<td>COUNTRY</td>
							<td>PINCODE</td>
							<td>PURCHASE DATE</td>
							<td>CUSTOMER NAME</td>
							<td>USERNAME</td>
							<td>MOBILE</td>
							<td>STATUS</td>
							<td>ACTION</td>
						</tr>
						<c:forEach var="row" items="${q.rows }" >
						<tr>
						<td class="s-no"> ${row.id } </td> 
							<td> ${row.p_id } </td>
							<td>${row.title }</td>
							<td>${row.qnt }</td>
							<td>${row.price }</td>
							<td>${row.address }</td>
							<td>${row.state }</td>
							<td>${row.country }</td>
							<td>${row.pincode }</td>
							<td>${row.p_date }</td>
							<td>${row.name }</td>
							<td>${row.username }</td>
							<td>${row.mobile }</td>
							<td class="status">${row.status }</td>
							
							<td>
							<c:if test="${row.status=='processing' }">
							<button title="${row.title }" id="${row.id }" product-id="${row.p_id }" fullname="${row.name }" email="${row.username }" address="${row.address }" qnt="${row.qnt }" price="${row.price }" mobile="${row.mobile }" class="btn btn-primary dispatch-btn">DISPATCH</button>
							</c:if>
							
							<c:if test="${row.status=='dispatched' }">
							<button title="${row.title }" id="${row.id }" product-id="${row.p_id }" fullname="${row.name }" email="${row.username }" address="${row.address }" qnt="${row.qnt }" price="${row.price }" mobile="${row.mobile }" class="btn btn-danger delivered-btn">ALREADY DISPATCHED NO ${row.date } Delivered Now</button>
							</c:if>	
							<c:if test="${row.status=='delivered' }">
							<button title="${row.title }" id="${row.id }" product-id="${row.p_id }" fullname="${row.name }" email="${row.username }" address="${row.address }" qnt="${row.qnt }" price="${row.price }" mobile="${row.mobile }" class="btn btn-success ">ALREADY DELIVERD NO ${row.d_date }</button>
							</c:if>					
							
							
							</td>
							<td></td>
						</tr>
						
						</c:forEach>
					
					</table>
					</div>
					
				</div>
			</div>