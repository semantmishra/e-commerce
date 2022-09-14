$(document).ready(function(){
	$(".stock-update-btn").click(function(){
		$(".stock-update-btn-menu").collapse('toggle');
	});
	
	$(".homepage-design-btn").click(function(){
		$(".homepage-design-collapse").collapse('toggle');
	});
});

// dynamic request

$(document).ready(function(){
	dynamic_request($(".active").attr("access-link"));
	$(".collapse-item").each(function(){
		$(this).click(function(){
			var request_link = $(this).attr("access-link");
			dynamic_request(request_link);
		});
	});
});

// active tab 

$(document).ready(function(){
	$(".collapse-item").each(function(){
		$(this).click(function(){
		for(i = 0; i<$(".collapse-item").length;i++)
		{
			$(".collapse-item").removeClass("active text-white bg-dark");
		}
		$(this).addClass("active text-white bg-dark");
		});
	});
});

function dynamic_request(request_link){
	$.ajax({    
		type:"POST",
		url:"employee_panel/dynemic_page/"+request_link,
		xhr:function(){
			var request = new XMLHttpRequest();
			request .onprogress = function(e){
				var percentage = Math.floor((e.loaded*100)/e.total);
				var loader = `<center>
				<button class="btn btn-danger" style="font-size: 30px;">
				<i class="fa fa-circle-o-notch fa-spin"></i>
				Loading <span>${percentage} % </span>
				</button>
			</center>`;
			
			$(".page").html(loader);
			}
			return request;
		},
		beforeSend:function(){
			var loader = `<center>
				<button class="btn btn-danger" style="font-size: 30px;">
				<i class="fa fa-circle-o-notch fa-spin"></i>
				Loading <span>10%</span>
				</button>
			</center>`;
			
			$(".page").html(loader);
		},
		success:function(res){
			$(".page").html(res);
			
			if(request_link=="Branding_details.html")
			{
				branding_code();	
			}
			
			if(request_link=="create_category.html")
			{
				showCategory();	
			}
			if(request_link=="create_brands.html")
			{
				showList($(".category-drop"),"/ECommerce/category","");
				showList($(".display-brand"),"/ECommerce/category","");
					
			}
			
			if(request_link=="create_product.html")
			{
				showOnlyBrands($(".brands-drop"),"/ECommerce/brands","");					
			}
			
			if(request_link=="header_showcase.html")
			{
				header_showcase();					
			}
			
			if(request_link=="category_showcase.jsp")
			{
				category_showcase();					
			}
			
			if(request_link=="delivery_area.jsp")
			{
				delivery_area();					
			}
			
			if(request_link=="sales_report.jsp")
			{
				sales_report();					
			}
			
			if(request_link=="keyword.html")
			{
				keyword_planner();
			}

			
			
			
			
			$(".add-field-btn").click(function(){
			var plaseholder = $("input:first").attr("placeholder");
			var input = document.createElement("INPUT");
				input.type="text";
				input.name = "mobile";
				input.className = "form-control input mb-3 rounded-0 float-left";
				input.placeholder = plaseholder;
				input.required = "required";
				input.style.background="#f9f9f9";
				input.style.width = "90%";
			var icon = document.createElement("I");
				icon.className = "fa fa-trash btn btn-light rounded-0 float-left d-flex justify-content-center form-fontrol text-danger" ;
				icon.style.height="38px";
				icon.style.width="10%";
				
				$(icon).click(function(){
					$(this).prev().remove();
					$(this).remove();
				});
			$(".add-field-area").append(input);	
			$(".add-field-area").append(icon);	
			});
			
			$(".create-btn").click(function(e){
				e.preventDefault();
				let form = $(".create-category-form");
				$.ajax({
					type:"POST",
					url:"/ECommerce/category",
					data:$(form).serialize(),
					beforeSend:function(){
						$(".create-category-lodader").removeClass("d-none");
					},
					success:function(res){
						$(".create-category-lodader").addClass("d-none");
						
						if(res.trim()=="done")
						{
							let notice = document.createElement("DIV");
							notice.className=" alert alert-success";
							notice.innerHTML = "Success !";
							$(".create-category-notice").append(notice);
							setTimeout(function(){
								$(".create-category-notice").html("");
								$(".create-category-form").trigger("reset");
							},3000);
						}
						else{
							let notice = document.createElement("DIV");
							notice.className=" alert alert-warning" ;
							notice.innerHTML= " <b>faild</b> ";
							$(".create-category-notice").append(notice);
							setTimeout(function(){
								$(".create-category-notice").html("");
								$(".create-category-form").trigger("reset");
							},3000);	
						}
						showCategory();
					}
				});
				
			});
			
			// brand coding start
			//create brands add filds
			
			$(".add-brand-btn").click(function(){
				
			var plaseholder = $("input:first").attr("placeholder");
			var input = document.createElement("INPUT");
			
				input.type="text";
				input.name = "brand-name";
				input.className = "form-control input mb-3 rounded-0 float-left";
				input.placeholder = plaseholder;
				input.required = "required";
				input.style.background="#f9f9f9";
				input.style.width = "90%";
			var icon = document.createElement("I");
				icon.className = "fa fa-trash btn btn-light rounded-0 float-left d-flex justify-content-center form-fontrol text-danger" ;
				icon.style.height="38px";
				icon.style.width="10%";
				
				$(icon).click(function(){
					$(this).prev().remove();
					$(this).remove();
				});
				
			$(".brand-field-area").append(input);	
			$(".brand-field-area").append(icon);
				
			});
			// end add-brands fields
			
			// start create brands
			
			$(".create-brand-btn").click(function(e){
				e.preventDefault();
				let form = $(".create-brands-form");
				$.ajax({
					type:"POST",
					url:"/ECommerce/brands",
					data:$(form).serialize(),
					beforeSend:function(){
						
						$(".brands-loader").removeClass("d-none");
					},
					success:function(res){
						$(".brands-loader").addClass("d-none");
						
						if(res.trim()=="done")
						{
							let notice = document.createElement("DIV");
							notice.className=" alert alert-success";
							notice.innerHTML = "Success !";
							$(".brand-field-notice").append(notice);
							setTimeout(function(){
								$(".brand-field-notice").html("");
								$(".create-brands-form").trigger("reset");
							},3000);
						}
						else{
							let notice = document.createElement("DIV");
							notice.className=" alert alert-warning" ;
							notice.innerHTML= " <b>faild</b> ";
							$(".brand-field-notice").append(notice);
							setTimeout(function(){
								$(".brand-field-notice").html("");
								$(".create-brands-form").trigger("reset");
							},3000);	
						}
					}
				});
			});
			
			//end create brands
			
			
			// diplay brands list
			$(".display-brand").on("change",function(){
				$(".diplay-brands").removeClass("d-none");
				showBrands($(this).val());
				$(".diplay-brands").addClass("d-none");
			});
			
			//end display list
			
			
			// end brand coding
			
			
			//start product coding
			
			$(".create-product-btn").click(function(e){
				let form = document.getElementsByClassName("create-product-form")[0];//$(".create-product-form"); 
				e.preventDefault();
				let option = $(".brands-drop option");
				let category_name = "";
				for(let i = 0;i<option.length;i++)
				{
					if(option[i].innerHTML==$(".brands-drop").val())
					{
						category_name = $(option[i]).attr("c-name"); 
					}
				}
				let formdata = new FormData(form);
				formdata.append("category-name",category_name);
				$.ajax({
					type:"POST",
					url:"/ECommerce/products",
					
					data: formdata,//$(form).serialize(),
					processData:false,
					contentType:false,
					cache:false,
					xhr:function(){
						let res = new XMLHttpRequest();
						res.upload.onprogress= function(e){
							let perecent = Math.floor((e.loaded*100)/e.total);
							$(".create-products-progress-bar").css({
								width:perecent+"%"
							});
							
						}
						return res;
					},
					beforeSend:function(){
						$(".create-products-lodader").removeClass("d-none");
						$(".create-products-progress-bar").removeClass("d-none");
						
					},
					success:function(res){
						$(".create-products-lodader").addClass("d-none");
						$(".create-products-progress-bar").addClass("d-none");	
							$(".create-products-progress-bar").css({
								width:"0%",
							});
						if(res.trim()=="done")
						{
							
							$(".create-product-form").trigger("reset");
							
							setTimeout(function(){
							$("#sub-modal").modal();
							// send notoficatiin
							
							$(".send-btn").click(function(){
								$.ajax({
									type:"POST",
									url:"send-notification",
									beforeSend:function(){
										$(".send-btn").html("SENDING...");
									},
									sucess:function(res){
										console.log(res);
										if(res.trim()=="done")
										{
											console.log(res);
										$(".send-btn").html("DONE");
										setTimeout(function(){
											$("#sub-modal").modal({
												show:false
											});
										},2000);		
										}
										else{
											$(".send-btn").html(res);
										}
									
									}
								});
							});
						},2000);
						}
						else{
							alert(res);
							
						}
					}
				});
			});
			
			//end product coding
					 	
		}
		
	});
}


// category list

function showCategory(){
	$.ajax({
		type:"POST",
		url:"/ECommerce/category",
		data:{
			opt : "select"
		},
	success:function(response){
		$(".category-area").html("");
			 let data = JSON.parse(response);
		
			data.forEach(function (data){
				
				let id = data.id;
				let name = data.categoryName;
				let ul = document.createElement("UL");
				ul.className = "list-group";
				let li = document.createElement("LI");
				li.className = "list-group-item mb-3 border-0";
				ul.append(li);
				let div = document.createElement("DIV");
				div.className = "btn-group";
				li.append(div);
				
				let id_btn= document.createElement("BUTTON");
				id_btn.innerHTML = id;
				id_btn.className="btn text-info id";
				div.append(id_btn);
				
				let name_btn= document.createElement("BUTTON");
				name_btn.innerHTML = name;
				name_btn.className="btn text-info name";
				div.append(name_btn);
				
				let edit_btn = document.createElement("BUTTON");
				edit_btn.innerHTML = "<i class='fa fa-edit edit-icon'></i> <i class='save-icon d-none fa fa-save'></i>";
				edit_btn.className="btn text-info";
				div.append(edit_btn);
				
				let delete_btn= document.createElement("BUTTON");
				delete_btn.innerHTML = "<i class='fa fa-trash delete-icon'></i>";
				delete_btn.className="btn text-danger";
				div.append(delete_btn);
				
				$(".category-area").append(ul);
				
				// edit category name 
				
				edit_btn.onclick = function(){
					let parent  = this.parentElement;
					let id = parent.getElementsByClassName("id")[0];
					let cat_name = parent.getElementsByClassName("name")[0];
					let save_icon = parent.getElementsByClassName("save-icon")[0];
					let edit_icon = parent.getElementsByClassName("edit-icon")[0];
					
						cat_name.contentEditable = true;
						cat_name.focus();
						$(save_icon).removeClass("d-none");
						$(edit_icon).addClass("d-none");
						
						save_icon.onclick = function(){
							let change_name = cat_name.innerHTML.trim();
							$.ajax({
								type:"POST",
								url:"/ECommerce/category",
								data:{
									opt:"update",
									id:id.innerHTML.trim(),
									change_name:change_name
								},
								success:function(res){
									if(res.trim()=="done")
									{
										cat_name.contentEditable = false;
										$(save_icon).addClass("d-none");
										$(edit_icon).removeClass("d-none");
									}
									else{
										alert(res);
									}
								}
							});
					}
				}
				
				// delete category
				
				delete_btn.onclick = function(){
					let parent  = this.parentElement;
					let id = parent.getElementsByClassName("id")[0].innerHTML.trim();
					$.ajax({
						type:"POST",
						url:"/ECommerce/category",
						data:{
							opt:"delete",
							id:id
						},
						success:function(res){
							if(res.trim()=="done")
							{
								parent.parentElement.parentElement.remove();
							}else
							{
								alert(res);
							}
						}
					});
				}
			});
		}
	});
}

// get list in drop down

// category list

function showList(control,url,table){
	$.ajax({
		type:"POST",
		url:url,//"/ECommerce/category",
		data:{
			opt : "select",
			table:table
		},
	success:function(response){
		let data = JSON.parse(response);
		
			data.forEach(function (data){
				//let id = data.id;
				let name = data.categoryName;
				var option = document.createElement("option");
    			option.innerHTML = name; 
    			control.append(option);
			});
				
		}
	});
	
	
}

//showBrands($(".brands-drop"),"/ECommerce/brands","");
function showOnlyBrands(control,url,table){
	$.ajax({
		type:"POST",
		url:url,//"/ECommerce/category",
		data:{
			opt : "selectBrands",
			table:table
		},
	success:function(response){
		let data = JSON.parse(response);
			data.forEach(function (data){				
				var option = document.createElement("option");
    			option.innerHTML = data.brands;
				option.value=  data.brands;
				$(option).attr("c-name",data.c_name);
    			control.append(option);
			});		
		}
	});	
}

// Show brand list

function showBrands(category){
	let opt = $(".display-brand").val();
	let all_option = $(".display-brand").html().replace("<option>Choose Category</option>").replace("<option>"+opt+"</option>");

	$.ajax({
		type:"POST",
		url:"/ECommerce/brands",
		data:{
			opt:"select",
			category:category
		},
	success:function(response){
		$(".brands-list-area").html("");
		
			let data = JSON.parse(response);
			let table  = document.createElement("table");
			table.width = "100%";
			table.border = "0";
			table.className="text-center";
			let top_tr = document.createElement("tr");
			
			let th_cat = document.createElement("th");
			th_cat.height = "40";
			th_cat.innerHTML="CATEGORY";
			th_cat.className="bg-danger text-light";
			
			let th_brands = document.createElement("th");
			th_brands.height = "40";
			th_brands.innerHTML="BRANDS";
			th_brands.className="bg-danger text-light";
			
			let th_edit = document.createElement("th");
			th_edit.height = "40";
			th_edit.innerHTML="EDIT";
			th_edit.className="bg-danger text-light";
			
			let th_delete = document.createElement("th");
			th_delete.height = "40";
			th_delete.innerHTML="DELETE";
			th_delete.className="bg-danger text-light";
			top_tr.append(th_cat);
			top_tr.append(th_brands);
			top_tr.append(th_edit);
			top_tr.append(th_delete);
			table.append(top_tr);
			if(data.length!=0)
			{
				data.forEach(function(data){
					let tr = document.createElement("tr");
					let td_cat = document.createElement("td");
					let td_brands = document.createElement("td");
					let td_edit = document.createElement("td");
					td_edit.innerHTML = "<i class='fa fa-edit brands-edit' id='"+data.id+"'></i> <i class='brands-save d-none fa fa-save' id='"+data.id+"'></i>";
					let td_delete = document.createElement("td");
					td_delete.innerHTML = "<i class='fa fa-trash brands-delete' id='"+data.id+"'></i>";
					td_cat.innerHTML = "<select disabled='disabled' class='border p-2 w-75 dynemic-c-name'><option>"+data.c_name+"</option>"+all_option+"</select>";
					td_brands.innerHTML = data.brands;
					table.append(tr);
					tr.append(td_cat);
					tr.append(td_brands);
					tr.append(td_edit);
					tr.append(td_delete);
					$(".brands-list-area").html(table);
					
				});
				
				//delete brands
				
				$(".brands-delete").each(function(){
					$(this).click(function(){
						let id = $(this).attr("id");
						let dele = this;
						$.ajax({
							type:"POST",
							url:"/ECommerce/brands",
							data:{
								opt:"delete",
								id:id
								},
						success:function(response){
								if(response.trim()=="done")
								{
									dele.parentElement.parentElement.remove();
								}else{alert(response);}
							}
						});
					});
				}); 
				
				// end delete brands
				
				// update bands 
				
				$(".brands-edit").each(function(){
					$(this).click(function(){
						$(this).addClass("d-none");
						let edit_icon = this;
						let id= $(this).attr("id");
		
						let row = this.parentElement.parentElement;
						let td = row.getElementsByTagName("TD");
						let select_tag = td[0].getElementsByClassName("dynemic-c-name")[0];
						select_tag.disabled = false;
						td[1].contentEditable = true;
						td[1].focus();
						var save_icon = td[2].getElementsByClassName("brands-save")[0];
						$(save_icon).removeClass("d-none");
						save_icon.onclick = function(){
														
								$.ajax({
								type:"POST",
								url:"/ECommerce/brands",
								data:{
									opt:"update",
									id:id,
									c_name:select_tag.value,
									b_name:td[1].innerHTML,
									
									},
									beforeSend:function(){
										
									},
								success:function(response){
									
									if(response.trim()=="done")
									{
										$(save_icon).addClass("d-none");
										$(edit_icon).removeClass("d-none");
										select_tag.disabled = true;
										td[1].contentEditable = false;
									
									}else{alert(response);}
								}
							});
						}
						
					});
				});
				
				// end update brands
			}
			else
			{
			$(".brands-list-area").html("<p class='text-danger'><b> No brands has been created yet in this category </b></p>"); 
			}
		
			 
			
			
		}
	});
}


// branding code 
function branding_code(){

$(document).ready(function(){
	$("#about").on("input",function(){
		let len = $(this).val().length;
		$(".about-us-count").html(len);
	});
});

	$("#privacy_policy").on("input",function(){
		let len = $(this).val().length;
		$(".privacy_policy_count").html(len);
	});
	$("#cookies_policy").on("input",function(){
		let len = $(this).val().length;
		$(".cookies_policy_count").html(len);
	});
	$("#terms_conditions").on("input",function(){
		let len = $(this).val().length;
		$(".terms_conditions_count").html(len);
	});


	// brandsing deatils 
$(document).ready(function(){
	$(".brands-form").submit(function(e){

		e.preventDefault();
		//let file = document.querySelector("#brands_logo");
		//console.log(file.files[0]);
		//let file_size = file.files[0].size;
		//if((200000)>file_size){
			$.ajax({
				type:"POST",
				url:"/ECommerce/branding",
				data:new FormData(this),
				processData:false,
				contentType:false,
				success:function(res){
					let html = "";
					if(res.trim()=="done")
					{
						 html = `<div class="alert alert-success" role="alert">
			  				Success ! Saved
							</div>`;	
					}
					if(res.trim()=="update")
					{
						 html = `<div class="alert alert-success" role="alert">
			  				Success ! Update
							</div>`;	
					}
					$(".branding-msg").html(html);
					setTimeout(function(){
						$(".branding-msg").html("");
					},5000);
					
					
						
					
				}	
			});
		//}
		//else{
		//alert("Upload 200 kb lessthen image");
		//}
		
		
	});
});
	
	
// branding details control 
$(document).ready(function(){
	$.ajax({
		type:"POST",
		url:"/ECommerce/branding",
		data:{opt : "selete"
			},
		success:function(res){
			let data = JSON.parse(res);
			if(data.id!=0)
				{
				$("input[name=brands_name]").val(data.brand_name);
				$("input[name=id]").val(data.id);
				$("input[name=domain_name]").val(data.domain_name);
				$("input[name=email]").val(data.email);
				$("input[name=facebook_url]").val(data.facebook_url);
				$("input[name=twitter_url]").val(data.twitter_url);
				$("input[name=address]").val(data.address);
				$("input[name=Phone]").val(data.phone);
				$("#about").val(data.about_us);
				$("#privacy_policy").val(data.privacy_policy);					
				$("#cookies_policy").val(data.cookies_policy);
				$("#terms_conditions").val(data.terms_policy);
				DesableControl(true);
				}
		}
	});
});

// enable control and update

$(document).ready(function(){
	$(".edit-branding").click(function(){
		DesableControl(false);
	});	
});


}
function DesableControl(val){
$("input[name=brands_name],input[name=brands_logo],input[name=domain_name],input[name=email],input[name=facebook_url],input[name=twitter_url],input[name=address],input[name=Phone]").prop("disabled",val);
		$("#about,#privacy_policy,#cookies_policy,#terms_conditions").prop("disabled",val);
		$("input[name=opt]").val("update");
		$(".btn-submit").prop("disabled",val);
}



// header showcase

function header_showcase(){
	$(document).ready(function(){
	$(".target").each(function(){
		$(this).click(function(e){
			let el = e.target;
			let index = $(el).index();
			sessionStorage.setItem("color_index",index);
			$(".target").each(function(){		
				$(this).css({
					border:"",
					boxShadow:"",
					padding:"",
					width:""
				});
			});
			
			$(this).css({
				border:"5px solid red",
				boxShadow:"0px,0px,0px,grey",
				padding:"2px",
				width:"fit-content"
			});
		});	
		
		$(this).on("dblclick",function(){
			$(".target").each(function(){		
				$(this).css({
					border:"",
					boxShadow:"",
					padding:"",
					width:""
				});
			});
		});
		
	});
	
	
	$(".color-selector").on("change",function(){
		let color = this.value;
		let num  =Number(sessionStorage.getItem("color_index"));// n;
		let el = document.getElementsByClassName("target")[num];
		el.style.color = color;	
	});
	
	$(".font-size").on("input",function(){
		let size = this.value;
		let num  =Number(sessionStorage.getItem("color_index"));// n;
		let el = document.getElementsByClassName("target")[num];
		el.style.fontSize = size+"%";
		
	});
});


$(document).ready(function(){
	$("#title-text").on("input",function(){
		$(".showcase-title").html(this.value);
	});	
	
	$("#subtitle-text").on("input",function(){
		$(".showcase-subtitle").html(this.value);
	});	
});


// title image upload 

$(document).ready(function(){
	$("#title-image").on("change",function(){
		let file = this.files[0];
		if(file.size<2000000)
		{
			let url = URL.createObjectURL(file);
			let image = new Image();
			image.src = url;
			image.onload = function(){
				let o_height = image.height;
				let o_width = image.width;
				//if(o_height==1920 && o_width==978)
				//{
					image.style.width = "100%";
					image.style.position = "absolute";
					image.style.top = "0";
					image.style.left = "0";
					$(".showcase-preview").append(image);
					
				//}
				//else{
					//alert("Upload 1920*978");
				//}
			}
			
			
			
		}
		else{
			alert("please upload less then 200 kb");
		}
	});
});

// header shocase 

$(document).ready(function(){
	$(".header-showcase-form").submit(function(e){
		e.preventDefault();
		let title = document.querySelector(".showcase-title");
		let subtitle = document.querySelector(".showcase-subtitle");
		let file = document.querySelector("#title-image").files[0];
		let title_size = "";
		let title_color = "";
		
		if(title.style.fontSize=="")
		{
			title_size = "300%";
		}
		else{ title_size = title.style.fontSize;
		}
		
		if(title.style.color=="")
		{
			title_color = "black";
		}else
		{
			title_color =title.style.color;
		}
		
		let subtitle_size = "";
		let subtitle_color = "";
		if(subtitle.style.fontSize=="")
		{
			subtitle_size = "200%";
		}else{ subtitle_size =title.style.fontSize;
		}
		
		if(subtitle.style.color=="")
		{
			subtitle_color = "black";
		}else
		{
			subtitle_color =title.style.color;
		}
		
		
		let flex_box = document.querySelector(".showcase-preview");
		let v_align = "";
		let h_align = "";
		
		if(flex_box.style.justifyContent=="")
		{
			h_align = "flex-start";
		}
		else{
			h_align = flex_box.style.justifyContent;
		}
		
		if(flex_box.style.alignItems=="")
		{
			v_align = "flex-start";
		}
		else{
			v_align  = flex_box.style.alignItems;
		}
		
		let formdata = new FormData();
		if($("#opt").val()=="insert")
		{
			formdata.append("opt",$("#opt").val());	
		}
		if($("#opt").val()=="update")
		{
			formdata.append("opt",$("#opt").val());	
		}
		formdata.append("title_size",title_size);
		formdata.append("id",$("#edit-title").val());
		
		formdata.append("title_size",title_size);
		formdata.append("title_color",title_color);
		formdata.append("subtitle_size",subtitle_size);
		formdata.append("subtitle_color",subtitle_color);
		formdata.append("v_align",v_align);
		formdata.append("h_align",h_align);
		formdata.append("title_text",title.innerHTML);
		formdata.append("subtitle_text",subtitle.innerHTML);
		formdata.append("buttons",$(".title-buttons").html().trim());
		formdata.append("file_data",file);
		formdata.append("options",$("#edit-title").val());
		
		$.ajax({
			type:"POST",
			url:"/ECommerce/header-showcase",
			data: formdata,
			processData:false,
			contentType:false,
			success:function(res){
			alert(res);
			}
		});
		
	});
});

// aligment 
$(document).ready(function(){
	$(".alignment").each(function(){
		$(this).click(function(){
			let align_position = $(this).attr("align-position");
			let align_value = $(this).attr("align-value");
			if(align_position=="h")
			{
				$(".showcase-preview").css({
					justifyContent:align_value
				});	
			}
			else if(align_position=="v")
			{
				$(".showcase-preview").css({
					alignItems:align_value
				});	
			}
			
			
		});
	});
});

// add btn

$(document).ready(function(){
	$(".add-btn").click(function(){
		let button = document.createElement("BUTTON");
		button.className = "btn mr-2 title-btn";
		let a = document.createElement("A");
		a.href = $(".btn-url").val();
		a.innerHTML = $(".btn-name").val();
		a.style.color =  $(".btn-textcolor").val();
		a.style.fontSize = $(".btn-size").val();
		button.style.backgroundColor = $(".btn-bgcolor").val();
		button.append(a);
		let title_button = document.querySelector(".title-buttons");
		let title_child = title_button.getElementsByTagName("BUTTON");
		let btn_length = title_child.length;
		if(btn_length==0 || btn_length==1)
		{
			$(".title-buttons").append(button);	
		}
		else{
		alert("only two allow");	
		}
		
	});
});

$(document).ready(function(){
	
	$(".real-preview-btn").click(function(e){
		e.preventDefault();
		let file = document.querySelector("#title-image").files[0];
		let formdata = new FormData();
		formdata.append("photo",file);
		let flex_box = document.querySelector(".showcase-preview");
		let v_align = "";
		let h_align = "";
		
		if(flex_box.style.justifyContent=="")
		{
			h_align = "flex-start";
		}
		else{
			h_align = flex_box.style.justifyContent;
		}
		
		if(flex_box.style.alignItems=="")
		{
			v_align = "flex-start";
		}
		else{
			v_align  = flex_box.style.alignItems;
		}
		
		formdata.append("html",$(".title-box").html().trim());
		formdata.append("v_align",v_align);
		formdata.append("h_align",h_align);
		
		$.ajax({
			type:"POST",
			url:"/ECommerce/real-preview",
			//url:"/ECommerce/real-preview.jsp",
			data: formdata,
			processData:false,
			contentType:false,
			success:function(res){
			let page = window.open("about:blank");
			page.document.open();
			page.document.write(res);
			page.document.close();
			
			}
		});
	});
});

$(document).ready(function(){
	$.ajax({
		type:"POST",
		url:"/ECommerce/Home",
		data:{
			opt:"getid"
		},
		success:function(res){
			let data = JSON.parse(res);
			data.forEach(function(data,i){
				let option = document.createElement("OPTION");
				option.value=data.id;
				option.innerHTML = ++i;
				$("#edit-title").append(option);	
			});
		}
	});
});


// edit tile

$(document).ready(function(){
	let showcase_preview = $(".showcase-preview").html();
	$("#edit-title").on("change",function(){
		if($(this).val()!="Choose Title")
		{
			$("#opt").val("update");
			let id = $(this).val();
			$.ajax({
				type:"POST",
				url:"/ECommerce/Home",
				data:{
					opt:"getByid",
					id:$(this).val()
				} ,
			
				success:function(res){
				$(".delete-title").removeClass("d-none");
				//delete title
				
				$(".delete-title").click(function(){
					$.ajax({
						type:"POST",
						url:"/ECommerce/Home",
						data:{
							opt:"delete",
							id:id
						} ,
						success:function(res){
							if(res.trim()=="done")
							{
								resetFrom(showcase_preview);
								let op = $("#edit-title option");
								for(let i = 0;i<=op.length;i++)
								{
									if($(op[i]).val() == id)
									{
										op[i].remove();
									}
								}
							}
							else{
								alert(res);
							}
						}
					});
				});
				
				
				$(".add-showcase-btn").html("Save edit");
				$(".add-showcase-btn").removeClass("bg-primary");
				$(".add-showcase-btn").addClass("bg-danger");
				let data = JSON.parse(res.trim());
				let image = document.createElement("IMG");
				image.src = data[0].title_image;
				image.style.width = "100%";
				image.style.position = "absolute";
				image.style.top = "0";
				image.style.left="0";
				$(".showcase-preview").append(image);
				$(".showcase-title").html(data[0].title_text);
				$(".showcase-title").css({
					color:data[0].title_color,
					fontSize:data[0].title_size
				});
				$(".showcase-subtitle").html(data[0].subtitle_text);
				$(".showcase-subtitle").css({
					color:data[0].subtitle_color,
					fontSize:data[0].subtitle_size
				});
				$(".title-buttons").html(data[0].buttons);
				$("#title-text").val(data[0].title_text);
				$("#subtitle-text").val(data[0].subtitle_text);
				
				// edit btn
				
				$(".title-btn").each(function(){
					$(this).click(function(){
						sessionStorage.setItem("btn_key",$(this).index());
						$(".delete-btn").removeClass("d-none");
						let url = $(this).children().attr("href");
						$(".btn-url").val(url);
						let name = $(this).children().html();
						$(".btn-name").val(name);
						
						
						let color = $(this).css("backgroundColor").replace("rgb(","").replace(")","");
						let rgb = color.split(",");
						let color_code = "";
						for(let i = 0;i<rgb.length;i++)
						{
							let hex_code = Number(rgb[i]).toString(16);
							color_code += hex_code.length ==1 ? "0"+hex_code : hex_code;
						}
						$(".btn-bgcolor").val("#"+color_code);
						
						
						let text_color = $(this).children().css("color").replace("rgb(","").replace(")","");
						let text_rgb = text_color.split(",");
						let text_color_code = "";
						for(let i = 0;i<text_rgb.length;i++)
						{
							let text_hex_code = Number(text_rgb[i]).toString(16);
							text_color_code += text_hex_code.length ==1 ? "0"+text_hex_code : text_hex_code;
						}
						$(".btn-textcolor").val("#"+text_color_code);
						
						let btn_size = $(this).children().css("fontSize");
						for(let i = 0;i<=$("btn-size").children().length;i++)
						{
							let option = $(".btn-size").children();
							if(option[i].value == btn_size)
							{
									option[i].selected = "selected";
							}
						}
					});
				});
				
					$(".btn-name").on("input",function(){
						let i_no  =Number(sessionStorage.getItem("btn_key"));
						let selected_btn = document.getElementsByClassName("title-btn")[i_no];
						selected_btn.getElementsByTagName("A")[0].innerHTML = this.value;
					});
					
					$(".btn-bgcolor").on("change",function(){
						let i_no  =Number(sessionStorage.getItem("btn_key"));
						let selected_btn = document.getElementsByClassName("title-btn")[i_no];
						selected_btn.style.backgroundColor = this.value;
					});
					
					
					$(".btn-textcolor").on("change",function(){
						let i_no  =Number(sessionStorage.getItem("btn_key"));
						let selected_btn = document.getElementsByClassName("title-btn")[i_no];
						selected_btn.getElementsByTagName("A")[0].style.color = this.value;
					});
					
					$(".btn-size").on("change",function(){
						let i_no  =Number(sessionStorage.getItem("btn_key"));
						let selected_btn = document.getElementsByClassName("title-btn")[i_no];
						selected_btn.getElementsByTagName("A")[0].style.fontSize = this.value;
					});
					
					$(".delete-btn").on("click",function(){
						let i_no  =Number(sessionStorage.getItem("btn_key"));
						let selected_btn = document.getElementsByClassName("title-btn")[i_no];
						selected_btn.remove();
						$(".btn-url,.btn-name").val("");
						$(".btn-bgcolor,.btn-textcolor").val("#000000");
						$(".btn-size option")[0].selected = "selected";
						
						$(".delete-btn").addClass("d-none");
					});
					
				
					
				}
			});
		}
		else{
			resetFrom(showcase_preview);
		}
	});
});

function resetFrom(html)
{
	$("#opt").val("insert");
	$(".add-showcase-btn").html("Add Showcase");
	$(".add-showcase-btn").addClass("bg-primary");
	$(".add-showcase-btn").removeClass("bg-danger");
	$(".header-showcase-form").trigger("reset");
	$(".showcase-preview").html(html);
	$(".delete-title").addClass("d-none");
	$("#edit-title option")[0].selected = "selected";
}

}


// category showcase


function category_showcase(){
$(document).ready(function(){
	$(".upload-icon").each(function(){
		$(this).on("change",function(){
			
		
			let upload_icon = this;
			let dummy_pic = upload_icon.parentElement.parentElement.parentElement.getElementsByTagName("IMG")[0];
			var input = upload_icon.parentElement.parentElement.getElementsByTagName("INPUT")[1];
			var set_btn = upload_icon.parentElement.parentElement.getElementsByClassName("set-btn")[0];
			dummy_pic_width = dummy_pic.naturalWidth;
			dummy_pic_Height = dummy_pic.naturalHeight;
			
			var file = upload_icon.files[0];
			var url = URL.createObjectURL(file);
			var image = new Image();
			image.src = url;
			up_width = "";
			up_height = "";
			image.onload = function(){
				up_width = image.width;
				up_height = image.height;
				//if(dummy_pic_width== up_width && dummy_pic_Height == up_height)
				//{
					input.oninput = function(){
						if(this.value.length>=1)
						{
							set_btn.disabled = false;
							set_btn.onclick = function(){
								var formdata = new FormData();
								formdata.append("photo",file);
								formdata.append("text",input.value);
								formdata.append("direction",$(set_btn).attr("img-dir"));
								formdata.append("opt","IU");
								$.ajax({
									type:"POST",
									url:"category-showcase",
									data:formdata,
									processData:false,
									contentType:false,
									beforeSend:function(){
										set_btn.innerHTML="Please wait...";
									},
									success:function(res){
										if(res.trim()=="done")
										{
											dummy_pic.src = url;
											set_btn.innerHTML="SET";
											$(upload_icon.parentElement.parentElement).addClass("d-none");
											dummy_pic.ondblclick = function(){
												$(upload_icon.parentElement.parentElement).removeClass("d-none");
											}
											
										}
									}
								});
							}
						}
						else{
							set_btn.disabled = true;
						}
					}
						
				//}
				//else{
					//alert("Please upload"+dummy_pic_width+" / "+dummy_pic_Height);
				//}
			}
		});		
	});
});

// 
$(document).ready(function(){
	let img = $("img");
	for(let i = 0;i<img.length;i++)
	{
		if(img[i].src.indexOf("base64")!=-1)
		{
			let set_btn = img[i].parentElement.getElementsByClassName("set-btn")[0];
			set_btn.disabled = false;
		}
	}
});
}



// deliver area 

function delivery_area(){
// get state
$(document).ready(function(){
	$(".country").on("change",function(){
		let country = $(this).val();
		$(".state").html("");
		$.ajax({
			type:"POST",
			url:"delivery-area",
			data:{
				opt:"state",
				id:country
			},
			success:function(res){
				let data  = JSON.parse(res);
				data.forEach(function(data){
					let option = document.createElement("OPTION");
					option.value = data.id;
					option.innerHTML = data.name;
					$(".state").append(option);
				});
			}
		});
		
	});
});


//get city
$(document).ready(function(){
	$(".state").on("change",function(){
		let country = $(this).val();
		$(".city").html("");
		$.ajax({
			type:"POST",
			url:"delivery-area",
			data:{
				opt:"city",
				id:country
			},
			success:function(res){
				let data  = JSON.parse(res);
				data.forEach(function(data){
					let option = document.createElement("OPTION");
					option.value = data.name;
					option.innerHTML = data.name;
					$(".city").append(option);
				});
			}
		});
		
	});
});

//get pincode
$(document).ready(function(){
	$(".city").on("change",function(){
		let city = $(this).val();
		$.ajax({
			type:"GET",
			url:"https://api.postalpincode.in/postoffice/"+city,
			success:function(res){
				let len = res[0].PostOffice.length-1;
				$(".pincode").val(res[0].PostOffice[len].Pincode);
			}
		});
		
	});
});

// set area 

$(document).ready(function(){
	$(".set-area-form").submit(function(e){
		e.preventDefault();
		$.ajax({
			type:"POST",
			url:"delivery-area",
			data: $(this).serialize(),
			success:function(res){
				alert(res);
			}
		});
	});
});
}



// sales report

function sales_report(){
$(document).ready(function(){
	$(".dispatch-btn").each(function(){
		$(this).click(function(){
			let clicked_btn = this;
			let id = $(this).attr("id");
			let email = $(this).attr("email");
			let address = $(this).attr("address");
			let mobile = $(this).attr("mobile");
			let title= $(this).attr("title");
			let qnt = $(this).attr("qnt");
			let price = $(this).attr("price");
			let fullname = $(this).attr("fullname");
			
			$.ajax({
				type:"POST",
				url:'dispatch',
				data:{
					opt:"dispatch",
					id:id,
					email:email,
					address:address,
					mobile:mobile,
					title:title,
					qnt:qnt,
					price:price,
					fullname:fullname
				},
				beforeSend:function(){
					$(clicked_btn).html("Wait...");	
				},
				success:function(res){
					$(clicked_btn).html("ORDER DISPATCHED");
					
					// show dispatch items
					let num = Number(sessionStorage.getItem("count"))+1
					sessionStorage.setItem("count",num);
					$(".d-all").html(num+" ITEM DISPATCHED");
					
					// show complete
					let all_item = Number($(".s-no").length);
					
					if(all_item == num)
						{
							$(".d-all").html("COMPLETE ");
							sessionStorage.removeItem("count");
							setTimeout(function(){
								$(".d-all").html("DISPATCH ALL");
							},2000);
						}
					
				}
			});
		});
	});
});

// deliver

$(document).ready(function(){
	$(".delivered-btn").each(function(){
		$(this).click(function(){
			let clicked_btn = this;
			let id = $(this).attr("id");
			let email = $(this).attr("email");
			let address = $(this).attr("address");
			let mobile = $(this).attr("mobile");
			let title= $(this).attr("title");
			let qnt = $(this).attr("qnt");
			let price = $(this).attr("price");
			let fullname = $(this).attr("fullname");
			
			$.ajax({
				type:"POST",
				url:'dispatch',
				data:{
					opt:"deliver",
					id:id,
					email:email,
					address:address,
					mobile:mobile,
					title:title,
					qnt:qnt,
					price:price,
					fullname:fullname
				},
				beforeSend:function(){
					$(clicked_btn).html("Wait...");	
				},
				success:function(res){
					$(clicked_btn).html("ORDER DELIVERED");						
				}
			});
		});
	});
});

// dispatech all
$(document).ready(function(){
	$(".d-all").click(function(){
	let status = $(".status");	
	let msg = "";
	for(let i = 0 ;i<status.length;i++)
		{
		if(status[i].innerHTML=="processing")
		{
			$(".d-all").html("Wait...");
			let btn = $(".dispatch-btn");
			sessionStorage.removeItem("count");
				for(let j = 0;j<btn.length;j++)
				{
				btn[j].click();
				}
		}else{
			msg = "No ttem"
			}
		}
	
	if(msg!="")
		{
			$(".d-all").html(msg);
			setTimeout(function(){
				$(".d-all").html("DISPATCH ALL");
			});
		}
	});
});

// export to 

$(document).ready(function(){
	$(".export-to").on("change",function(){
			if($(this).val()=="EXCEL")
			{
				location = "export-to-excel"	
			}
			else if($(this).val()=="PDF")
			{
				location = "export-to-pdf"	
			}
			
	});
});


// sort by
$(document).ready(function(){
	$(".sort-by").on("change",function(){
		if($(this).val()!="All")
		{
			let option = $(this).val();
			
			$.ajax({
				type:"POST",
				url:'sort-by',
				data:{
					option:option
				},
				beforeSend:function(){
						
				},
				success:function(res){
					data=JSON.parse(res);
					if(data.length!=0)
					{
						
						let table = document.createElement("table");
						table.className = "w-100 puchase-table text-center text-center table table-bordered bg-white";
						table.innerHTML = "<tr><td> S/NO </td><td>PRODUCT_ID</td><td>TITLE</td><td>QUNTITY</td><td>PRICE</td><td>ADDRESS</td><td>STATE</td><td>COUNTRY</td><td>PINCODE</td><td>PURCHASE DATE</td><td>CUSTOMER NAME</td><td>USERNAME</td><td>MOBILE</td><td>STATUS</td><td>ACTION</td></tr>";
						data.forEach(function(data){
							let tr =document.createElement("tr");
							let td_sno =document.createElement("td");
							td_sno.innerHTML = data.id
							let td_p_id =document.createElement("td");
							td_p_id.innerHTML = data.p_id
							let td_title =document.createElement("td");
							td_title.innerHTML = data.title
							let td_qnt =document.createElement("td");
							td_qnt.innerHTML = data.qnt
							let td_price =document.createElement("td");
							td_price.innerHTML = data.price
							let td_address =document.createElement("td");
							td_address.innerHTML = data.Address
							let td_state =document.createElement("td");
							td_state.innerHTML = data.state
							let td_country =document.createElement("td");
							td_country.innerHTML = data.country
							let td_pincode =document.createElement("td");
							td_pincode.innerHTML = data.pincode
							let td_purchase_date =document.createElement("td");
							td_purchase_date.innerHTML = data.p_date
							let td_name =document.createElement("td");
							td_name.innerHTML = data.name
							let td_username =document.createElement("td");
							td_username.innerHTML = data.username
							let td_mobile =document.createElement("td");
							td_mobile.innerHTML = data.mobile
							let td_status =document.createElement("td");
							td_status.innerHTML = data.status
							
							
							let td_btn =document.createElement("td");
							let btn_dis =document.createElement("button");
							let btn =document.createElement("button");
							let btn_d =document.createElement("button");
							if(data.status=="processing")
							{
								btn_dis.className = "btn btn-primary dispatch-btn";
								btn_dis.innerHTML = "DISPATCH";
								$(btn_dis).attr("title",data.title);
								$(btn_dis).attr("id",data.id);
								$(btn_dis).attr("product-id",data.p_id);
								$(btn_dis).attr("qnt",data.qnt);
								$(btn_dis).attr("price",data.price);
								$(btn_dis).attr("fullname",data.name);
								$(btn_dis).attr("address",data.Address);
								$(btn_dis).attr("mobile",data.mobile);
								td_btn.append(btn_dis);
								
							}
							if(data.status=="dispatched")
							{
								btn.className = "btn btn-danger delivered-btn";
								btn.innerHTML = "ALREADY DISPATCHED NO "+data.d_date+" Deliver Now";
								
								$(btn).attr("id",data.id);
								
								$(btn).attr("title",data.title);
								
								$(btn).attr("product-id",data.p_id);
								$(btn).attr("qnt",data.qnt);
								$(btn).attr("price",data.price);
								$(btn).attr("fullname",data.name);
								$(btn_dis).attr("address",data.Address);
								$(btn).attr("mobile",data.mobile);
							
								td_btn.append(btn);
								
							}
							
							if(data.status=="delivered")
							{
								btn_d.className = "btn btn-success";
								btn_d.innerHTML = "DELIVERT NO "+data.d_date;
								
								$(btn_d).attr("id",data.id);
								
								td_btn.append(btn_d);
								
							}
							
							
							tr.append(td_sno);
							tr.append(td_p_id);
							tr.append(td_title);
							tr.append(td_qnt);
							tr.append(td_price);
							tr.append(td_address);
							tr.append(td_state);
							tr.append(td_country);
							tr.append(td_pincode);
							tr.append(td_purchase_date);
							tr.append(td_name);
							tr.append(td_username);
							tr.append(td_mobile);
							tr.append(td_status);
							tr.append(td_btn);
							
							table.append(tr);
							
							btn_dis.onclick = function(){
								let clicked_btn = this;
								let id = $(this).attr("id");
								let email = $(this).attr("email");
								let address = $(this).attr("address");
								let mobile = $(this).attr("mobile");
								let title= $(this).attr("title");
								let qnt = $(this).attr("qnt");
								let price = $(this).attr("price");
								let fullname = $(this).attr("fullname");
								
								$.ajax({
									type:"POST",
									url:'dispatch',
									data:{
										id:id,
										email:email,
										address:address,
										mobile:mobile,
										title:title,
										qnt:qnt,
										price:price,
										fullname:fullname
									},
									beforeSend:function(){
										$(clicked_btn).html("Wait...");	
									},
									success:function(res){
										$(clicked_btn).html("ORDER DISPATCHED");
										
										// show dispatch items
										let num = Number(sessionStorage.getItem("count"))+1
										sessionStorage.setItem("count",num);
										$(".d-all").html(num+" ITEM DISPATCHED");
										
										// show complete
										let all_item = Number($(".s-no").length);
										
										if(all_item == num)
											{
												$(".d-all").html("COMPLETE ");
												sessionStorage.removeItem("count");
												setTimeout(function(){
													$(".d-all").html("DISPATCH ALL");
												},2000);
											}
										
									}
								});
							}
							
							// deliver
							
									btn.onclick = function(){
									let clicked_btn = this;
									let id = $(this).attr("id");
									let email = $(this).attr("email");
									let address = $(this).attr("address");
									let mobile = $(this).attr("mobile");
									let title= $(this).attr("title");
									let qnt = $(this).attr("qnt");
									let price = $(this).attr("price");
									let fullname = $(this).attr("fullname");
									
									$.ajax({
										type:"POST",
										url:'dispatch',
										data:{
											opt:"deliver",
											id:id,
											email:email,
											address:address,
											mobile:mobile,
											title:title,
											qnt:qnt,
											price:price,
											fullname:fullname
										},
										beforeSend:function(){
											$(clicked_btn).html("Wait...");	
										},
										success:function(res){
											$(clicked_btn).html("ORDER DELIVERED");						
										}
									});
								}
							
							
							
						});
						
						
						$(".table-responsive").html(table);
						

					}
					else{
						alert("Sales not available");
					}
					
				}
				
			});
		}
	});
});
}





// keyword planner

function keyword_planner(){
// get category
$(document).ready(function(){
	$.ajax({
		type:"POST",
		url:"/ECommerce/category",
		data:{
			opt : "select"
		},
	success:function(response){
			 let data = JSON.parse(response);
		
			data.forEach(function (data){
				let option = document.createElement("OPTION");
				option.innerHTML=data.categoryName;
				
				let optionb = document.createElement("OPTION");
				optionb.innerHTML=data.categoryName;
				
				let optionc = document.createElement("OPTION");
				optionc.innerHTML=data.categoryName;
				
				$("#p-keyword").append(option);
				$(".brands-category").append(optionb);
				$("#produts-c-list").append(optionc);
				
			});
		}
	});
});


	
$(document).ready(function(){
	$(".brands-form").submit(function(e){
		e.preventDefault();
		if($("#brands-p-key").val()!="Choose primary keyword")
		{
			$.ajax({
				type:"POST",
				url:"keyword",
				data:$(this).serialize(),
				success:function(res){
					console.log(res);
				}
			});
		}
		else{
			alert("Select primary keyword");
		}
	});
});


$(document).ready(function(){
	$(".keyword-form").submit(function(e){
		e.preventDefault();
		if($("#p-keyword").val()!="Choose primary keyword")
		{
			$.ajax({
				type:"POST",
				url:"keyword",
				data:$(this).serialize(),
				success:function(res){
					console.log(res);
				}
			});
		}
		else{
			alert("Select primary keyword");
		}
	});
});

// get s_key
$(document).ready(function(){
	$("#p-keyword").on("change",function(){
		if($("#p-keyword").val()!="Choose primary keyword")
		{
			$.ajax({
				type:"POST",
				url:"keyword",
				data:{
					opt:"select",
					p_key:$(this).val()
				},
				success:function(res){
				$("#s-keyword").val(res.trim());
				}
			});
			
		}
	});
});

$(document).ready(function(){
	$("#brands-p-key").on("change",function(){
		if($("#brands-p-key").val()!="Choose primary keyword")
		{
			$.ajax({
				type:"POST",
				url:"keyword",
				data:{
					opt:"select",
					p_key:$(this).val()
				},
				success:function(res){
				$("#brands-s-keyword").val(res.trim());
				}
			});
			
		}
	});
});

$(document).ready(function(){
	$("#produts").on("change",function(){
		if($("#produts").val()!="Choose primary keyword")
		{
			$.ajax({
				type:"POST",
				url:"keyword",
				data:{
					opt:"select",
					p_key:$(this).val()
				},
				success:function(res){
				$("#produts-s-keyword").val(res.trim());
				}
			});
			
		}
	});
});

// get faild keyword 

$(document).ready(function(){
	
			$.ajax({
				type:"POST",
				url:"keyword",
				data:{
					opt:"getfaildkey"
				},
				success:function(res){
					data = JSON.parse(res);
					$(".count").html(data.length);
					data.forEach(function(data){
						let html = "<div class='alert alert-danger p-2 mr-2 tags'> "+data+" &nbsp; <i class='fa fa-times-circle' data-dismiss='alert'></i>  </div>";
						$(".faild-keyword").append(html);
					});
					
				}
			});
});

// copy to clipbord 
$(document).ready(function(){
	$(".copy-btn-category").click(function(){

		let tags = "";
		$(".tags").each(function(){
			tags +=$(this).text().trim()+" , ";
			
		});
		$(".s-category-keyword").val($(".s-category-keyword").val()+" "+tags);
	});
	
});

$(document).ready(function(){
	$(".copy-btn-brands").click(function(){

		let tags = "";
		$(".tags").each(function(){
			tags +=$(this).text().trim()+" , ";	
		});
		$(".s-brands-keyword").val($(".s-brands-keyword").val()+" "+tags);
	});
});

$(document).ready(function(){
	$(".copy-btn-produts").click(function(){

		let tags = "";
		$(".tags").each(function(){
			tags +=$(this).text().trim()+" , ";	
		});
		$(".s-produts-keyword").val($(".s-produts-keyword").val()+" "+tags);
	});
});




// get brands

$(document).ready(function(){
	$(".brands-category").on("change",function(){
		if($(this).val()!="Choose")
		{
		let category = $(this).val();	
		$.ajax({
			type:"POST",
			url:"/ECommerce/brands",
			data:{
				opt : "select",
				category:category
			},
		success:function(response){
			$("#brands-p-key").html("<option>Choose primary keyword</option>");
				 let data = JSON.parse(response);
			
				data.forEach(function (data){
					let option = document.createElement("OPTION");
					option.innerHTML=data.brands;	
					$("#brands-p-key").append(option);
				});
			}
		});
		
		}
		
	});
});


$(document).ready(function(){
	$("#produts-c-list").on("change",function(){
		if($(this).val()!="Choose")
		{
		let category = $(this).val();	
		$.ajax({
			type:"POST",
			url:"/ECommerce/brands",
			data:{
				opt : "select",
				category:category
			},
		success:function(response){
			$("#produts-brands").html("<option>Choose </option>");
				 let data = JSON.parse(response);
			
				data.forEach(function (data){
					let option = document.createElement("OPTION");
					option.innerHTML=data.brands;	
					$("#produts-brands").append(option);
				});
			}
		});
		
		}
		
	});
});

// get produts 
$(document).ready(function(){
	$("#produts-brands").on("change",function(){
		if($(this).val()!="Choose")
		{
		let brands = $(this).val();	
		$.ajax({
			type:"POST",
			url:"/ECommerce/products",
			data:{
				opt : "getproduts",
				brands:brands
			},
		success:function(response){
			$("#produts").html("<option>Choose primary keyword</option>");
			
				let data = JSON.parse(response);
			
				data.forEach(function (data){
					let option = document.createElement("OPTION");
					option.innerHTML=data;	
					$("#produts").append(option);
				});
			}
		});
		
		}
		
	});
});

// save produts keyword

$(document).ready(function(){
	$(".produts-form").submit(function(e){
		e.preventDefault();
		if($("#produts").val()!="Choose primary keyword")
		{
			$.ajax({
				type:"POST",
				url:"keyword",
				data:$(this).serialize(),
				success:function(res){
					console.log(res);
				}
			});
		}
		else{
			alert("Select primary keyword");
		}
	});
});

//delete keyword

$(document).ready(function(){
	$(".delete-keyword").click(function(){
		let tags = [];
		$(".tags").each(function(i){
			tags[i] = $(this).text().trim();
			
		});	
		$.ajax({
			type:"POST",
			url:"keyword",
			data:{
				opt : "dele",
				tag:JSON.stringify(tags)
			},
			success:function(res){
				if(res.trim()=="done")
				{
				location = location.href;
				}
			}
		});

		
		
	});
});
}