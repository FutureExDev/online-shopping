<%@ taglib prefix="sercurity"
	uri="http://www.springframework.org/security/tags"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item" id="home"><a class="nav-link"
					href="${contextRoot}/home">Home <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item" id="about"><a class="nav-link"
					href="${contextRoot}/about">About</a></li>
				<li class="nav-item" id="allproducts"><a class="nav-link"
					href="${contextRoot}/show/all/products">View Products</a></li>
				<li class="nav-item" id="contact"><a class="nav-link"
					href="${contextRoot}/contact">Contact</a></li>
					<sercurity:authorize access="hasAuthority('ADMIN')">
				<li class="nav-item" id="manageProduct"><a class="nav-link"
					href="${contextRoot}/manage/product">Manage Product</a></li>
					</sercurity:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right">

				<sercurity:authorize access="isAnonymous()">

					<li class="nav-item" id="register"><a class="nav-link"
						href="${contextRoot}/register">Sign Up</a></li>
					<li class="nav-item" id="login"><a class="nav-link"
						href="${contextRoot}/login">Login</a></li>
				</sercurity:authorize>
				
				<sercurity:authorize access="isAuthenticated()">
				<li class="dropDown" id="userCart"><a href="javascript:void(0)"
					class="btn btn-default dropdown-toggle" id="dropdownMenu1"
					data-toggle="dropdown"> ${userModel.fullName} <!-- <span class="caret"></span> -->
				</a>

					<ul class="dropdown-menu">
						<sercurity:authorize access="hasAuthority('USER')">
						<li><a href="${contextRoot}/cart/show"> <span
								class="glyphicon glyphicon-shopping-cart"></span> <span
								class="badge">${userModel.cart.cartLines}</span> - &#8377;
								${userModel.cart.grandTotal}
						</a></li>
						<li class="divider" role="separator"></li>
						</sercurity:authorize>
						<li><a href="${contextRoot}/perform-logout">Logout</a></li>

					</ul></li>
					</sercurity:authorize>

			</ul>


		</div>
	</div>
</nav>

<script>

window.userRole='${userModel.role}';
</script>


