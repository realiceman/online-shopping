<div class="container">

	<div class="row">

		<!-- display sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>


		<!-- display the actual products -->
		<div class="col-md-9">

			<!-- added breadcrumb component-->
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userclickAllProducts == true}">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>
						</ol>
					</c:if>
					
					<c:if test="${userclickCategoryProducts == true}">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category Products</li>
							<li class="active"> ${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>
		</div>
	</div>

</div>