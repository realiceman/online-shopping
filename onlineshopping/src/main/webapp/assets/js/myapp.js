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
		if(window.categoryId==''){
			jsonUrl = window.contextRoot+'/json/data/all/products';
		}else{
			console.log("window categoryID = "+ window.categoryId);
			jsonUrl = window.contextRoot+'/json/data/category/'+window.categoryId+'/products';
		}
		
		$table.DataTable({
			lengthMenu:[
			              [3,5,10,-1],
			              ["3 ","5 ","10 ", "all "]
			            ],
			 pageLength:3,
			
			ajax:{
				url : jsonUrl,
				dataSrc : ''
			},
			columns:[
			          {
			            data: 'code',
			            mRender: function(data,type,row){
			            	
			            	return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" style="height:100px;width:100px;"/>';
			            }
			          },
			          {
			        	data: 'name'
			          },
			          {
				        data: 'brand'
				       },
				       {
				    	 data: 'unitPrice',
				    	 mRender: function(data, type, row){
				    		 return '&#8364; '+data;
				    	 }
				       },
				       {
					     data: 'quantity'
					    },
					    {
					    	data: 'id',
					    	bSortable: false,
					    	mRender: function(data, type, row){
					    		var str = '';
					    		str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
					    		str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product"class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
					    		
					    		return str;
					    	}
					    	
					    }
			         ]
			
		});
	}
});