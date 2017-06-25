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
	
 //to tackle the csrf token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length > 0){
		// set the token for the ajax request
		$(document).ajaxSend(function(e, xhr, options){
			xhr.setRequestHeader(header,token);
		});
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
					    	
					    		if(userRole == 'ADMIN'){
					    			str += '<a href="'+window.contextRoot+'/manage/'+data+'/product"class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
							    	
					    		}else{
					    		
						    		if(row.quantity < 1){
						    			console.log("quantité row :"+row.quantity);
						    			str += '<a href="javascript:void(0)"class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							    		
						    		}else{
						    			
						    				
						    				str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product"class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
								    	
						    		}
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
					    		str+=' <a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
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
	
	
	
	/***********VALIDATION CODE FOR CATEGORY   **********************/
	
	var $categoryForm = $("#categoryForm");
	
	if($categoryForm.length){
		$categoryForm.validate({
			  rules: {
				  name: {required: true, minlength: 2},
				  description: {required: true}
			  },
			  messages: {
				  name:{required: "Please add the category name!", minlength: "The category name should not be less than 2 characters!"},
			      description:{required: "Please add a description for the category!"}
			  },
			  errorElement: "em",
			  errorPlacement: function(error, element){
				  //add the class of help-block
				  error.addClass("help-block");
				  //add the error element after the input element
				  error.insertAfter(element);
			  }
		});
	}
	
	/***********END VALIDATION CODE FOR CATEGORY   **********************/
	
	

	/***********VALIDATION CODE FOR LOGINFORM   **********************/
	
	var $loginForm = $("#loginForm");
	
	if($loginForm.length){
		$loginForm.validate({
			  rules: {
				  username: {required: true, email: true},
				  password: {required: true}
			  },
			  messages: {
				  username:{required: "Please enter your email address!", email: "Please enter a vaild email address"},
			      password:{required: "Please enter your password!"}
			  },
			  errorElement: "em",
			  errorPlacement: function(error, element){
				  //add the class of help-block
				  error.addClass("help-block");
				  //add the error element after the input element
				  error.insertAfter(element);
			  }
		});
	}
	
	/***********END VALIDATION CODE FOR LOGINFORM   **********************/
	
	
});

