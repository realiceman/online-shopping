$(function(){
	//solving the active menu pb
	switch(menu){
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	default:
		$('#listProducts').addClass('active');
	    $('#a_'+menu).css('background-color', '#07c');
	    break;
	}
});