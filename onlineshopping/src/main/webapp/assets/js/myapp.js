$(function() {

	$(window).load(function(){
		setTimeout(function() {
			$(".se-pre-con").fadeOut("slow");
		}, 500);			
	});	
	
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#allproducts').addClass('active');
		break;
	case 'Manage Product':
		$('#manageProduct').addClass('active');
		break;
	case 'User Cart':
		$('#userCart').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#allproducts').addClass('active');
		$('a_' + menu).addClass('active');
		break;
	}

	var $table = $('#productListTable');

	if ($table.length) {

		var jsonUrl = '';

		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({

					lengthMenu : [ [ 3, 5, -1 ], [ 3, 5, 'All' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''

					},
					columns : [

							{
								data : 'code',
								mRender : function(data, type, row) {

									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataimages"/>';
								}
							},
							{
								data : 'name'
							},

							{
								data : 'brand'
							},

							{
								data : 'unitPrize',
								mRender : function(data, type, row) {
									return '&#8377;' + data
								}
							},

							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out Of stock!</span>';
									}

									return data;
								}

							},
							{
								data : 'id',
								mRender : function(data, type, row) {

									var str = '';

									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									if (userRole == 'ADMIN') {
										str += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';

									} else {

										if (row.quantity < 1) {
											str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';

										} else {

											str += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ data
													+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';

										}
									}

									return str;
								}
							}

					]

				});

	}

	var $alert = $('.alert');

	if ($alert.length) {

		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)
	}

	$('.switch input[type="checkbox"]')
			.on(
					'change',
					function() {

						var checkbox = $(this);
						var checked = checkbox.prop('checked');
						var dMsg = (checked) ? 'You want to Active product?'
								: 'You want to deactivate product?';
						var value = checkbox.prop('value');

						bootbox
								.confirm({

									size : 'medium',
									title : 'Product Activation & DeActivation',
									message : dMsg,
									callback : function(confirmed) {
										if (confirmed) {
											console.log(value);
											bootbox
													.alert({
														size : 'medium',
														title : 'Infromation',
														message : 'You are going to perform operation on product '
																+ value
													});

										} else {

											checkbox.prop('checked', !checked);
										}
									}

								});

					});

	var $adminProudctTable = $('#adminProductTable');

	if ($adminProudctTable.length) {

		jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminProudctTable
				.DataTable({

					lengthMenu : [ [ 10, 20, -1 ], [ 10, 20, 'All' ] ],
					pageLength : 10,
					ajax : {
						url : jsonUrl,
						dataSrc : ''

					},
					columns : [
							{
								data : 'id'
							},

							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {

									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="adminDataTableImg"/>';
								}
							},
							{
								data : 'name'
							},

							{
								data : 'brand'
							},

							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out Of stock!</span>';
									}

									return data;
								}

							},
							{
								data : 'unitPrize',
								mRender : function(data, type, row) {
									return '&#8377;' + data
								}
							},
							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';

									str += '<label class="switch" >';
									if (data) {
										str += '<input type="checkbox" checked="checked" value="'
												+ row.id + '"/>';
									} else {
										str += '<input type="checkbox"  value="'
												+ row.id + '"/>';
									}
									str += '<div class="slider"></div></label>';

									return str;

								}

							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {

									var str = '';

									str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/product" class="btn btn-warning">';
									str += '<span class="glyphicon glyphicon-pencil"></span></a>';

									return str;
								}
							}

					],

					initComplete : function() {

						var api = this.api();

						api
								.$('.switch input[type="checkbox"]')
								.on(
										'change',
										function() {

											var checkbox = $(this);
											var checked = checkbox
													.prop('checked');
											var dMsg = (checked) ? 'You want to Active product?'
													: 'You want to deactivate product?';
											var value = checkbox.prop('value');

											bootbox
													.confirm({

														size : 'medium',
														title : 'Product Activation & DeActivation',
														message : dMsg,
														callback : function(
																confirmed) {
															if (confirmed) {
																console
																		.log(value);

																var activationUrl = window.contextRoot
																		+ '/manage/product/'
																		+ value
																		+ '/activation';

																$
																		.post(
																				activationUrl,
																				function(
																						data) {
																					bootbox
																							.alert({
																								size : 'medium',
																								title : 'Infromation',
																								message : data
																							});

																				});

															} else {

																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}

													});

										});

					}

				});

	}

	$categoryForm = $('#categoryForm');

	if ($categoryForm.length) {

		$categoryForm
				.validate({

					rules : {

						name : {
							required : true,
							minlength : 2
						},

						description : {
							required : true,

						}
					},
					messages : {
						name : {
							required : 'Please add the Category name!',
							minlength : 'The category name should not be less than 2 character!'
						},
						description : {
							required : 'Please add the Description of category!'
						}
					},

					errorElement : 'em',
					errorPlacement : function(error, element) {

						error.addClass('help-block');
						error.insertAfter(element);
					}

				});
	}

	$loginForm = $('#loginForm');

	if ($loginForm.length) {

		$loginForm.validate({

			rules : {

				username : {
					required : true,
					email : true
				},

				password : {
					required : true,

				}
			},
			messages : {
				username : {
					required : 'Please enter the Username!',
					email : 'Please enter valid email address!'
				},
				password : {
					required : 'Please enter password!'
				}
			},

			errorElement : 'em',
			errorPlacement : function(error, element) {

				error.addClass('help-block');
				error.insertAfter(element);
			}

		});
	}

	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');

	if (token.length > 0 && header.length > 0) {

		$(document).ajaxSend(function(e, xhr, options) {

			xhr.setRequestHeader(header, token);
		});

	}
	
	
	
	$('button[name="refreshCart"]').click(function(){
		
		
		var cartLineId=$(this).attr('value');
		
		var countElement=$('#count_'+cartLineId);
		
		var originalCount= countElement.attr('value');
		var currentCount=countElement.val(); 
		
		if(currentCount <1 || currentCount>3){
			
			countElement.val(originalCount);
			
			bootbox.alert({
				
				size:'medium',
				title:'Error',
				message:'Product count should be minimum 1 and maximum 3'
				
			});
			
		}
		else{
			
			var updateUrl=window.contextRoot+'/cart/'+cartLineId+'/update?count='+currentCount;
			
			window.location.href=updateUrl;
		}
		
		
		function errorPlacement(error, element) {
		
			error.addClass("help-block");
			
			error.insertAfter(element);
			element.parents(".validate").addClass("has-feedback");	

		}	
		
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

});