<div class="container">

	<div class="row">

		<div class="col-md-3">

			<%@ include file="./shared/sidebar.jsp"%>
		</div>

		<div class="col-md-9">

			<div class="row">

				<div class="col-lg-12">


					<c:if test="${userClickAllProducts == true}">

						<script>
							window.categoryId = '';
						</script>
						<ol class="breadcrumb">

							<li class="breadcrumb-item active"><a
								href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">All Products</li>

						</ol>
					</c:if>


					<c:if test="${userClickCategoryProducts == true}">

						<script>
							window.categoryId = '${category.id}';
						</script>
						<ol class="breadcrumb">

							<li class="breadcrumb-item active"><a
								href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">Category</li>
							<li class="breadcrumb-item active">${category.name}</li>

						</ol>
					</c:if>
				</div>
			</div>

			<div class="row">

				<div class="col-md-12">

					<div class="container-fluid">

						<div class="table-responsive">
							<table id="productListTable" class="table table-striped">
								<thead>
									<tr>
										<th></th>
										<th>Name</th>
										<th>Brand</th>
										<th>Price</th>
										<th>Quatity</th>
										<th></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>

				</div>

			</div>

		</div>

	</div>

</div>