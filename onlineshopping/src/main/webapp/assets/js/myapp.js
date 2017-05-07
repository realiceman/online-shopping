$(function(){
	//solving the active menu pb
	switch(menu){
	case 'About Us':
		$('#about').addClass("active");
		break;
	case 'Contact Us':
		$('#contact').addClass("active");
		break;
	case 'All Products':
		$('#listProducts').addClass("active");
		break;
	default:
		$('#listProducts').addClass("active");
	    $('#a_'+menu).addClass("active");
	    break;
	}
	

	
	
	
	var $table = $('#productListTable');
	
	//execute the code only where we have this table
	if($table.length){
	//	console.log("inside the table!");
		var jsonUrl = '';
		if(window.categoryId=''){
			jsonUrl = window.contextRoot+'/json/data/all/products';
		}else{
			jsonUrl = window.contextRoot+'/json/data/category/'+window.categoryId+'/products';
		}
		
		$table.DataTable({
			lengthMenu:[
			              [3,5,10,-1],
			              ["3 records","5 records","10 records", "all records"]
			            ],
			 pageLength:3,
			
			ajax:{
				url : jsonUrl,
				dataSrc : ''
			},
			columns:[
			          {
			        	data: 'name'
			          },
			          {
				        data: 'brand'
				       },
				       {
				    	 data: 'unitPrice'
				       },
				       {
					     data: 'quantity'
					    }
			         ]
			
		});
	}
});