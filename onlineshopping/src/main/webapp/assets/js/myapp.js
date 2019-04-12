$(function(){
	
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
	default:
		$('#allproducts').addClass('active');
		$('a_'+menu).addClass('active');
		break;
	}
});