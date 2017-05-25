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
	case 'Manage Products':
		$('#manageProducts').addClass("active");
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
					     data: 'quantity',
					     mRender: function(data, type, row){
					    	 if(data < 1){
					    		 console.log("nothing");
					    		 return '<span style="color:red">Out of stock!</span>';
					    	 }
					    	 return data;
					     }
					    },
					    {
					    	data: 'id',
					    	bSortable: false,
					    	mRender: function(data, type, row){
					    		var str = '';
					    		str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
					    		if(row.quantity < 1){
					    			console.log("quantité row :"+row.quantity);
					    			str += '<a href="javascript:void(0)"class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						    		
					    		}else{
					    			str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product"class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						    		
					    		}
					    		
					    		return str;
					    	}
					    	
					    }
			         ]
			
		});
	}
	
	//dismissing the alert after 3 seconds
	var $alert = $('.alert');
      /* if true it means it's available */
	if($alert.length){
		setTimeout(function(){
			$alert.fadeOut('slow');
		},3000);
	}
	
	//
	$(".switch input[type='checkbox']").on("change", function(){
		var checkbox = $(this);
		var checked = checkbox.prop("checked"); //return true or false;
		var dMsg = (checked)?"Do you want to activate the product ?": "Do you want to deactivate the product ?";
	    var value = checkbox.prop("value");
	    bootbox.confirm({
	    	size: "medium",
	    	title: "Product activation & deactivation",
	    	message: dMsg,
	    	callback: function(confirmed){
	    		if(confirmed){
	    			console.log(value);
	    			bootbox.alert({
	    				size: "medium",
	    				title: "Information",
	    				message: "You are going to perform operation on product "+value
	    			});
	    		}else{
	    			checkbox.prop("checked", !checked);
	    		}
	    	}
	    });
	});
	
	/******************DATATABLE FOR ADMIN**************************/
	
var $adminProductsTable = $('#adminProductsTable');
	
	//execute the code only where we have this table
	if($adminProductsTable.length){
	//	console.log("inside the table!");
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		
		
		$adminProductsTable.DataTable({
			lengthMenu:[
			              [10,30,50,-1],
			              ["10 ","30 ","50 ", "all "]
			            ],
			 pageLength:30,
			
			ajax:{
				url : jsonUrl,
				dataSrc : ''
			},
			columns:[
			         {
			        	data: 'id' 
			         },
			         
			         
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
					     data: 'quantity',
					     mRender: function(data, type, row){
					    	 if(data < 1){
					    		 console.log("nothing");
					    		 return '<span style="color:red">Out of stock!</span>';
					    	 }
					    	 return data;
					     }
					    },
					    
					    {
					    	data: 'active',
					    	bSortable: false,
					    	mRender: function(data, type, row){
					    	  var str ='';
					    	  str+='<label class="switch">';
					    	  if(data){	
			                    str+='<input type="checkbox" checked="checked" value="'+row.id+'"/>';
			                 
					    	  }else{
					    		  str+='<input type="checkbox"  value="'+row.id+'"/>';
				                     
					    	  }
					    	  str+='<div class="slider"></div>';
					    	  str+='</label>';
					    	  
					    	  return str;
					    	}
					    	
					    },
					    {
					    	data: 'id',
					    	bSortable: false,
					    	mRender: function(data, type, row){
					    		var str = '';
					    		str+=' <a href="${contextRoot}/manage/'+data+'/product" class="btn btn-warning">';
				                str+='<span class="glyphicon glyphicon-pencil"></span>';
					            str+='</a>';
					            
					            return str;
					    	}
					    }
			         ],
			         initComplete: function(){
			        	 var api = this.api(); //DataTables has an extensive API which can be used to access the data contained in a table and otherwise manipulate the table after the table initialisation has completed
			        		api.$(".switch input[type='checkbox']").on("change", function(){
			        			var checkbox = $(this);
			        			var checked = checkbox.prop("checked"); //return true or false; if the toggle is not checked,  if i click on the toggle it becomes true, 
			        			                                        //so it will ask to confirm if i want to activate it 
			        			var dMsg = (checked)?"Do you want to activate the product ?": "Do you want to deactivate the product ?"
			        		    var value = checkbox.prop("value");
			        		    bootbox.confirm({
			        		    	size: "medium",
			        		    	title: "Product activation & deactivation",
			        		    	message: dMsg,
			        		    	callback: function(confirmed){
			        		    		if(confirmed){
			        		    			console.log(value);
			        		    			
			        		    			var activationUrl = window.contextRoot+'/manage/product/'+value+'/activation';
			        		    			$.post(activationUrl, function(data){
			        		    				bootbox.alert({
				        		    				size: "medium",
				        		    				title: "Information",
				        		    				message: data
				        		    			});
			        		    			});
			        		    			
			        		    			
			        		    		}else{
			        		    			checkbox.prop("checked", !checked);
			        		    		}
			        		    	}
			        		    });
			        		});
			         }
			
		});
	}
	
	
	/******************END DATABLE FOR ADMIN************************/
	
});

