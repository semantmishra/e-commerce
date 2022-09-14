// add cart

function add_cart(){
$(document).ready(function(){
	$(".cart-btn").each(function(){
		$(this).click(function(){

			let all_cookie = document.cookie.split(";");
			let temp = [];
			for(let i = 0;i<all_cookie.length;i++)
			{
				let cookie = all_cookie[i].split("=");
				if(cookie[0].trim() != "_au_")
				{
					temp[i] = cookie[0].trim(); 
				}else{
					if(cookie[1]!="")
					{
						temp = "matched";
					}
										
				}
			}
			if(temp == "matched")
			{
				let product_id = $(this).attr("product_id");

				$.ajax({
					type:"POST",
					url:"cart",
					data:{
						id : product_id,
						opt : "insert"
					},
					success:function(res){
						if(res.trim()=="done")
						{
							if($(".cart-notification").prop("nodeName") != undefined)
							{
								let no =  Number($(".cart-notification span").html().trim());
								no++ ;
								$(".cart-notification span").html(no);
							}
							else{
								let div = document.createElement("DIV");
								div.style.color = "white";
								div.style.backgroundColor = "red";
								div.style.width="25px";
								div.style.height="25px";
								div.style.borderRadius="50%";
								div.style.position = "absolute";
								div.style.top="-10px";
								div.style.left="25px";
								div.style.fontWeight="bold";
								div.style.zIndex="1000";
								div.className="text-center cart-notification";
								let span = document.createElement("span");
								span.innerHTML = "1";
								$(div).append(span);
								$(".cart-link").append(div);
							}
						}
						
						else{
							if(res.trim()=="exist")
							{
							alert("Product allready in your cart");	
							}else{
							alert(res);	
							}
							
						}
					}
				});
			}
			else{
				location = "signin.jsp";
			}
				
		});
	});
});
}
add_cart();

// delete cart 

$(document).ready(function(){
	$(".delete-cart-btn").each(function(){
	$(this).click(function(){
		let id = $(this).attr("id");
		let btn = this;
		$.ajax({
			type:"POST",
			url:"cart",
			data:{
				opt:"delete",
				id:id
			},
			success:function(res){
				if(res.trim()=="done")
				{
					let div = btn.parentElement.parentElement.parentElement
					$(div).addClass("animate__animated animate__zoomOut");
					setTimeout(function(){
						div.remove();
					},1000);
					
				}else{
					alert(res);
				}
			}
		});
	});
});	
});


// buy btn 

function buy_btn(){
$(document).ready(function(){
	$(".buy-btn").each(function(){
		$(this).click(function(){
		let product_id = $(this).attr("product_id");
		window.location = "buy-product?p_id="+product_id;	
		});
	});
});
}
buy_btn();
// purchase btn
function pucharse_btn(){
$(document).ready(function(){
	$(".puchase-btn").click(function(){
		let pay_mode = $("input[name='pay-mode']:checked").val();
		if(pay_mode)
		{
			let qnt = $(".quantity").val();
			let p_id = $(this).attr("p_id");
			if(pay_mode=="online")
			{	
				location = "pay?qnt="+qnt+"&p_id="+p_id+"&paymode="+pay_mode;
			}else{
				location = "pay?qnt="+qnt+"&p_id="+p_id+"&paymode="+pay_mode;
			}
		}
		else{
			alert("please Choose on Pay mode");
		}
	});
}); 
}
pucharse_btn();
//  check pincode  

//https://api.postalpincode.in/pincode/497225

$(document).ready(function(){
	$(".pincode-btn").click(function(){
		let pincode = $(".pincode-field").val();
		$.ajax({
			type:"POST",
			url:"delivery-area",
			data:{
				opt:"checkPin",
				pincode:pincode
			},
			success:function(res){
				$(".pincode-message").html(res);
			}
		});
	});
});

//stock control
$(document).ready(function(){
	$(".quantity").on("input",function(){
		let stock = Number($(".stock").html());
		let qnt = $(this).val();
		if(stock<qnt)
		{
			$(this).val(1);
			alert("Nigative Stock");
		}
	});	
});


// preview

$(document).ready(function(){
	$(".thumb-pic").each(function(){
		$(this).click(function(){
			let src = $(this).attr("src");
			$(".preview").attr("src",src);
			$(".preview").addClass("animate__animated animate__zoomIn");
			setTimeout(function(){$(".preview").removeClass("animate__animated animate__zoomIn");},1000);
			
		});
	});
});

// filter product
$(document).ready(function(){
	$(".filter-btn").each(function(){
		
		$(this).click(function(){
			$(".filter-btn").each(function(){
				$(this).removeClass("btn-primary px-2 rounded-lg");	
			});
			
			$(this).addClass("btn-primary px-2 rounded-lg");
			let cat_name = $(this).attr("cat-name");
			let brand_name = $(this).attr("brand-name");
			$.ajax({
				type:"POST",
				url:"show-products",
				data:{
					cat_name:cat_name,
					brand_name:brand_name,
					opt:"search"
				},
				beforeSend:function(){
					$(".product-result").html("Loading...");
				},
				success:function(res){
					data = JSON.parse(res);
					if(data.length==0)
					{
						$(".product-result").html(" <h2> <i class='fa fa-shopping-cart'></i>Empty Stock</h2> ");						
					}
					else{
						show_data(data);
						add_cart();
						buy_btn();
					}
				}
			});
		});
	});	
});


// show data in html
function show_data(data){
	$(".product-result").html("");
						data.forEach(function(data){
							let div = document.createElement("DIV");
							div.className="text-center border shadow-sm p-3 mb-4";
							let img = document.createElement("IMG");
							img.src = data.thumb_pic;
							img.width = "250";
							img.height = "316";
							
							
							// brand
							
							let brand_span = document.createElement("SPAN");
							brand_span .className="text-uppercase font-weight-bold";
							brand_span.innerHTML="<br>"+data.brands;
							
							// title
							let title_span = document.createElement("SPAN");
							title_span .className="text-uppercase";
							title_span.innerHTML="<br>"+data.title;
							
							// price
							let price_span = document.createElement("SPAN");
							price_span .className="text-uppercase";
							price_span.innerHTML="<br> <i class='fa fa-rupee'></i> "+data.price+"<br>";
							
							let cart_btn = document.createElement("button");
							cart_btn.className="btn btn-danger cart-btn mt-3 mr-3";
							cart_btn.innerHTML="ADD CART";
							$(cart_btn).attr("product_id",data.id);
							
							let buy_btn = document.createElement("button");
							buy_btn.className="btn btn-primary buy-btn mt-3";
							buy_btn.innerHTML="BUY NOW";
							$(buy_btn).attr("product_id",data.id);
							
							
							$(div).append(img);
							$(div).append(brand_span);
							$(div).append(title_span);
							$(div).append(price_span);
							$(div).append(cart_btn);
							$(div).append(buy_btn);
						$(".product-result").append(div);	
						});	
				
}

// defaulte filter btn

$(document).ready(function(){
	
	let btn = $(".filter-btn");
	if(btn[0]!=undefined)
	{btn[0].click();}
	
});

// filter by price

$(document).ready(function(){
	$(".price-filter-btn").click(function(){
		
		let min = $(".min-price").val();
		let max = $(".max-price").val();
		let cat_name = $(this).attr("cat-name");
		$.ajax({
			type:"POST",
			url:"show-products",
			data:{
				cat_name :cat_name ,
				min:min,
				max:max,
				opt:"price-filter"
			},
			beforeSend:function(){
					$(".product-result").html("Loading...");
				},
			success:function(res){
				data = JSON.parse(res);
					if(data.length==0)
					{
						$(".product-result").html(" <h2> <i class='fa fa-shopping-cart'></i>Empty Stock</h2> ");						
					}
					else{
						show_data(data);
						add_cart();
						buy_btn();
					}
			}
		});
	});
}); 



// CHNAGE PASSWORD
$(document).ready(function(){
	$(".privacy-form").submit(function(e){
		e.preventDefault();
		
		let newpassword = $("#newpassword").val();
		let cpassword = $("#cpassword").val()
		if(newpassword==cpassword)
		{
			$.ajax({
			type:"POST",
			url:"profile",
			data:$(this).serialize(),
			beforeSend(){
				$(".privacy-form button").html("PLEASE WAIT...");
			},
			success:function(res){
				if(res.trim()=="done")
				{
					$(".privacy-form button").html("PASSWORD CHANGE SUCCESS !");
					setTimeout(function(){
						$(".privacy-form button").html("CHANGE PASSWORD");
					},3000);	
				}
				else{
					alert(res);
				$(".privacy-form button").html("CHANGE PASSWORD");
				}
			}
		});	
		}
		else{
			alert("New Password and COnfrim password not same");
		}
		
	});
});




// persanal infromation edit
$(document).ready(function(){
	$(".personal-form").submit(function(e){
		e.preventDefault();
		$.ajax({
			type:"POST",
			url:"profile",
			data:$(this).serialize(),
			beforeSend(){
				$(".personal-form button").html("PLEASE WAIT...");
			},
			success:function(res){
				if(res.trim()=="done")
				{
					$(".personal-form button").html("UPDATE SUCCESS !");
					setTimeout(function(){
						$(".personal-form button").html("UPDATE");
					},3000);	
				}
				else{
					alert(res);
				}
				
			}
		});
	});
});

// start

$(document).ready(function(){
	$(".star").each(function(){
		$(this).click(function(){
			let p_btn = $(this).next().next().next().next();
			console.log(p_btn);
		$(".star-btn").removeClass("d-none");
			let star = $(".star");
			let index = $(this).attr("index");
			index++;
			for(let i =0;i<5;i++)
			{
				$(star[i]).removeClass("fa-star text-warning");
				$(star[i]).addClass("fa fa-star-o text-warning");
				if(i<index)
				{
					$(star[i]).removeClass("fa fa-star-o");
					$(star[i]).addClass("fa fa-star text-warning");
				}	
			}
			
			$(".star-btn").click(function(e){
				let btn = this;
				
				if($("#comment").val()!="")
				{
					if($("#picture").val()!="")
					{
						let pic = document.querySelector("#picture").files[0];
						let formdata = new FormData();
						
						formdata.append("picture",pic);
						formdata.append("comment",$("#comment").val());
						formdata.append("rating",index);
						formdata.append("id",$(this).attr("p_id"));
						
						e.preventDefault();
						$.ajax({
							type:"POST",
							url:"rating",
							data:formdata,	
							processData:false,
							contentType:false,		
							success:function(res){
								if(res.trim()=="done")
								{
									$(btn).addClass("d-none");
									$(".picture-box").addClass("d-none");
									$(".comment-box").addClass("d-none");
									$(".comment-info").removeClass("d-none");
									$(".comment-info").html("Comment Post success !");
									setTimeout(function(){
									$(".heding").html("YOUR RATING");
									$(".comment-info").html($("#comment").val());
										
									},3000);
								}
								else{
									alert(res);		
								}
							}
						});			
					}else{
						alert("Please select image");
					}	
				}
				else{
					alert("Please write somthing Commet ");
				}
				});
		});
	});
});


// sort by

$(document).ready(function(){
	$(".sort-by").on("change",function(){
		let cat_name = "";
		let brand_name ="";
		$(".filter-btn").each(function(){
			if($(this).attr("class").indexOf("btn-primary")!=-1)
			{
				cat_name = $(this).attr("cat-name");
				brand_name =  $(this).attr("brand-name");
				
			}
		});
		
		let sort_by = $(this).val();
		$.ajax({
			type:"POST",
			url:"show-products",
			
			data:{
				cat_name:cat_name,
				brand_name:brand_name,
				sort_by:sort_by,
				opt:"sort-product"
			},
			beforeSend:function(){
					$(".product-result").html("Loading...");
				},
			success:function(res){
				data = JSON.parse(res);
					if(data.length==0)
					{
						$(".product-result").html(" <h2> <i class='fa fa-shopping-cart'></i>Empty Stock</h2> ");						
					}
					else{
						show_data(data);
						add_cart();
						buy_btn();
					}
				}
			});
	});
});


// subscribe btn coding

$(document).ready(function(){
	$(".subscribe-btn").click(function(){
		let email = $("input[name=subscribe-email]").val();
		$.ajax({
			type:"POST",
			url:"subscribe",
			data:{
				email:email,
				opt:"varify"
			},
			beforSend:function(){
				$(".scb").html("Please Wait...");
			},
			success:function(res){
				$(".scb").html("<b>SUBSCRIBE</b>");
				if(res.trim()!="fail")
				{
					let count = 3;
					function varify(){
						data = JSON.parse(res);
						code = data[1];
						let promt = window.prompt("Please visit in your email and enter OTP code");
						if(promt==code)
						{
							$.ajax({
									type:"POST",
									url:"subscribe",
									data:{
										email:email,
										opt:"save"
									},
									
									success:function(res){
										alert(res);
										}
								});
							
						}
						else if(promt==null || promt=="")
						{
							varify();
						}else{
							alert("Your verification is wrong");
							if(--count>0){
							varify();	
							}
									
						}
					}
					varify();
	
				}else{
					alert(res);
				}
				
				
			}
		});
	});
});

// ajax live search

$(document).ready(function(){
	$(".search").on("input",function(){
		let keyword = $(this).val();
		$.ajax({
			type:"POST",
			url:"ajax-live-search",
			data:{
				keyword:keyword
			},
			success:function(res){
				
				$(".search-hint").html(res);
				
				$(".search-tag").on("mouseover",function(){
					$(this).css({
						backgroundColor:"red",
						color:"white"
					});
				});
				
				$(".search-tag").on("mouseout",function(){
					$(this).css({
						backgroundColor:"inherit",
						color:"inherit",
						cursor:"pointer"
					});
				});
				
				$(".search-tag").each(function(){
					$(this).click(function(){
						let id = $(this).attr("id");
						$(".search").val($(this).html().trim());
						$(".search-hint").html("");
						location = "buy-product?p_id="+id;
						
					});
				});
				}
			});
		
	});
});


$(document).ready(function(){
	$(".search").on("keypress",function(e){
		if(e.keyCode==13 && $(this).val()!="")
		{
			let keyword = $(this).val().trim();
			location = "search-result?search="+keyword;
		}
	})
});

$(document).ready(function(){
	$(".search-icon").click(function(){
		if($(".search").val()!="")
		{
			let keyword = $(".search").val().trim();
			location = "search-result?search="+keyword;
		}
	})
});