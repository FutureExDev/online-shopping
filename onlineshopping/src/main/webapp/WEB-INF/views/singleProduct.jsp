<div>
	<div class="row">
		<div class="col-xs-12">

			<ol class="breadcrumb">

				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/show/all/products">Products</a></li>
				<li class="active">${product.name}</li>
			</ol>

		</div>
	</div>


	<div class="row">

		<div class="col-xs-12 col-sm-4">
			<div class="thumbnail">
			<img src="${images}/${product.code}.jpg" class="img img-responsive" />
			</div>
		</div>

		<div class="col-xs-12 col-sm-8">

			<h3><strong>${product.name}</strong></h3>
			<hr/>

			<p>Product Description: ${product.description}</p>
			<hr/>

				<h4>Price: <strong>&#8377;${product.unitPrize}/-</strong></h4>
				<hr/>
				<h6>Quantity Available: ${product.quantity}</h6>
				
				<a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success">
				<span class="glyphicon glyphicon-shopping-cart"></span>Add To cart</a>
					<a href="${contextRoot}/show/all/products" class="btn btn-primary">Back</a>
				
				
		</div>

	</div>
</div>