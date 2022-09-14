<%@page import="Dao.CategoryShowcaseDao"%>
<%
request.setAttribute("categoryShowcase", new CategoryShowcaseDao().getDefault());
%>
<div class="row animate__animated animate__zoomIn">
				<div class="col-md-4">
					<div class="position-relative">
						<div class="btn-group border shadow-sm position-absolute" style="width:352px;z-index:10">
							<button class="btn btn-dark position-relative">
							<input type="file" accept="iamge/*" class="upload-icon position-absolute" style="height:100%;width:100%;top:0;left:0;opacity:0">
							<i class="fa fa-upload"></i>
							</button>
							<button class="btn">
								<input type="text" value="${categoryShowcase.getTopLeftLbl() }" class="form-control upload-lable" placeholder="Mobile">
							</button>
							<button class="btn btn-dark set-btn" disabled="disabled" img-dir="top-left">
							SET
							</button>
							
						</div>
						<img src="${categoryShowcase.getTopLeftImg() }" alt="image" class="w-100 bm-3">						
					</div>
					
					<div class="position-relative">
						<div class="btn-group border shadow-sm position-absolute" style="width:352px;z-index:10">
							<button class="btn btn-dark position-relative">
							<input type="file" accept="iamge/*" class="upload-icon position-absolute" style="height:100%;width:100%;top:0;left:0;opacity:0">
							<i class="fa fa-upload"></i>
							</button>
							<button class="btn">
								<input type="text" value="${categoryShowcase.getBottomLeftLbl() }" class="form-control upload-lable" placeholder="Mobile">
							</button>
							<button class="btn btn-dark set-btn" disabled="disabled" img-dir="bottom-left">
							SET
							</button>
							
						</div>
						<img src="${categoryShowcase.getBottomLeftImg() }" alt="image" class="w-100 bm-3">						
					</div>
					
					
				</div>
				<div class="col-md-4">
					<div class="position-relative">
						<div class="btn-group border shadow-sm position-absolute" style="width:352px;z-index:10">
							<button class="btn btn-dark position-relative">
							<input type="file" accept="iamge/*" class="upload-icon position-absolute" style="height:100%;width:100%;top:0;left:0;opacity:0">
							<i class="fa fa-upload"></i>
							</button>
							<button class="btn">
								<input type="text" value="${categoryShowcase.getCenterLbl() }" class="form-control upload-lable" placeholder="Mobile">
							</button>
							<button class="btn btn-dark set-btn" disabled="disabled" img-dir="center">
							SET
							</button>
							
						</div>
						<img src="${categoryShowcase.getCenterImg() }" alt="image" class="w-100 bm-3">						
					</div>
					
				</div>
				<div class="col-md-4">
					<div class="position-relative">
						<div class="btn-group border shadow-sm position-absolute" style="width:352px;z-index:10">
							<button class="btn btn-dark position-relative">
							<input type="file" accept="iamge/*" class="upload-icon position-absolute" style="height:100%;width:100%;top:0;left:0;opacity:0">
							<i class="fa fa-upload"></i>
							</button>
							<button class="btn">
								<input type="text" ${categoryShowcase.getTopRightImg() } class="form-control upload-lable" placeholder="Mobile">
							</button>
							<button class="btn btn-dark set-btn" disabled="disabled" img-dir="top-right">
							SET
							</button>
							
						</div>
						<img src="${categoryShowcase.getTopRightImg() }" alt="image" class="w-100 bm-3">						
					</div>
					
					<div class="position-relative">
						<div class="btn-group border shadow-sm position-absolute" style="width:352px;z-index:10">
							<button class="btn btn-dark position-relative">
							<input type="file" accept="iamge/*" class="upload-icon position-absolute" style="height:100%;width:100%;top:0;left:0;opacity:0">
							<i class="fa fa-upload"></i>
							</button>
							<button class="btn">
								<input type="text" value="${categoryShowcase.getBottomRightLbl() }"  class="form-control upload-lable" placeholder="Mobile">
							</button>
							<button class="btn btn-dark set-btn" disabled="disabled" img-dir="bottom-right">
							SET
							</button>
							
						</div>
						<img src="${categoryShowcase.getBottomRightImg() }" alt="image" class="w-100 bm-3">						
					</div>
					
				</div>
			</div>