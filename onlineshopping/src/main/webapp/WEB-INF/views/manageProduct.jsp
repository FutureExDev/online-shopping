<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row">

		<c:if test="${not empty message}">
			<div class="col-xs-12">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>

					${message}
				</div>
			</div>
		</c:if>


		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel panel-heading">

					<h4>Product Management</h4>
				</div>

				<div class="panel panel-body">

					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/product" method="POST"
						enctype="multipart/form-data">
						<div class="form-group">

							<label class="control-label col-md-4" for="name">Enter
								Product Name:</label>
							<div class="col-md-8">

								<sf:input type="text" path="name" id="name"
									placeholder="Enter Product Name" class="form-control" />
								<!-- 	<em class="help-block">Please Enter the Product Name!</em> -->

								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="brand">Enter
								Brand Name:</label>
							<div class="col-md-8">

								<sf:input type="text" path="brand" id="brand"
									placeholder="Enter Brand Name" class="form-control" />
								<!-- <em class="help-block">Please Enter the Brand Name!</em> -->
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="description">Enter
								Description:</label>
							<div class="col-md-8">

								<sf:textarea path="description" id="description" row="4"
									placeholder="Enter Description" class="form-control"></sf:textarea>
								<!-- <em class="help-block">Please Enter Description!</em> -->
								<sf:errors path="description" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="unitPrize">Enter
								Unit Price:</label>
							<div class="col-md-8">

								<sf:input type="number" path="unitPrize" id="unitPrize"
									placeholder="Enter Unit Price" class="form-control" />
								<!-- <em class="help-block">Please Enter Price!</em> -->
								<sf:errors path="unitPrize" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">

							<label class="control-label col-md-4" for="quantity">Enter
								Quantity:</label>
							<div class="col-md-8">

								<sf:input type="number" path="quantity" id="quantity"
									placeholder="Enter Quantity" class="form-control" />
								<!-- <em class="help-block">Please Enter Price!</em> -->
							</div>
						</div>


						<div class="form-group">

							<label class="control-label col-md-4" for="file">Select
								the Image:</label>
							<div class="col-md-8">

								<sf:input type="file" path="file" id="file" class="form-control" />
								<!-- <em class="help-block">Please Enter Price!</em> -->

								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>



						<div class="form-group">

							<label class="control-label col-md-4" for="categoryId">Select
								Categoy:</label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId"
									path="categoryId" items="${categories}" itemLabel="name"
									itemValue="id" />

								<c:if test="${product.id==0}">
									<div class="text-right">
										<br />
										<button type="button" data-toggle="modal"
											data-target="#myCategoryModal" class="btn btn-xs btn-warning">Add
											Category</button>

									</div>
								</c:if>

							</div>
						</div>



						<div class="form-group">

							<div class="col-md-offset-4 col-md-8">

								<input type="submit" name="submit" id="submit" value="submit"
									class="btn btn-primary" />

								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />
							</div>
						</div>

					</sf:form>

				</div>
			</div>

		</div>
	</div>

	<div class="row">

		<div class="col-xs-12"></div>
		<div class="col-xs-12">


			<div class="container-fluid">
				<div class="table-responsive">
					<table id="adminProductTable"
						class="table table-stripped table-bordered">

						<thead>
							<tr>
								<th>Id</th>
								<th>&#160;</th>
								<th>Name</th>
								<th>Brand</th>
								<th>Quantity</th>
								<th>Unit Price</th>
								<th>Active</th>
								<th>Edit</th>

							</tr>

						</thead>

						<tbody>
							<%-- <tr>
							<td>4</td>
							<td><img class="adminDataTableImg"
								src="${contextRoot}/resources/images/PRDED0FF76071.jpg"
								alt="Lenovo"></td>
							<td>Lenovo</td>
							<td>4</td>
							<td>&#8377; 30000.00</td>
							<td><label class="switch"> <input type="checkbox"
									checked="checked" value="4" />
									<div class="slider"></div>
							</label></td>
							<td><a href="${contextRoot}/manage/4/product"
								class="btn btn-warning"> <span
									class="glyphicon glyphicon-pencil"></span>
							</a></td>
						</tr>

						<tr>
							<td>4</td>

							<td><img class="adminDataTableImg"
								src="${contextRoot}/resources/images/PRDED0FF76071.jpg"
								alt="Lenovo"></td>
							<td>Lenovo</td>
							<td>4</td>
							<td>&#8377; 30000.00</td>
							<td><label class="switch"> <input type="checkbox"
									value="4" />
									<div class="slider"></div>
							</label></td>
							<td><a href="${contextRoot}/manage/4/product"
								class="btn btn-warning"> <span
									class="glyphicon glyphicon-pencil"></span>
							</a></td>
						</tr>
 --%>

						</tbody>


					</table>
				</div>
			</div>
		</div>
	</div>



	<div class="modal fade" role="dialog" id="myCategoryModal"
		tabindex="-1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>

					<h4>Add New Category</h4>
				</div>
				<div class="modal-body">
					<sf:form id="categoryForm" modelAttribute="category"
						action="${contextRoot}/manage/category" method="POST"
						class="form-horizontal">

						<div class="form-group">
							<label for="category_name" class="control-label col-md-4">Category
								Name</label>
							<div class="col-md-8">
								<sf:input path="name" id="category_name" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="category_description" class="control-label col-md-4">Description</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="category_description"
									rows="5" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" value="Add Category"
									class="btn btn-primary" />
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>


</div>