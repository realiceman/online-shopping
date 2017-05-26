<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container">

    <div class="row">
    
    <c:if test="${not empty message }">
	    <div class="col-xs-12">
	      <div class="alert alert-success alert-dismissible">
	         <button type="button" class="close" data-dismiss="alert">&times;</button>
	         ${message}
	      </div>
	    </div>
    </c:if>
    
       <div class="col-md-offset-2 col-md-8">
          
          <div class="panel panel-primary">
             
             <div class="panel-heading">
             
                <h4>Product Management</h4>
               
             </div>
             
             <div class="panel-body">
             
                  <!-- FORM ELEMENTS -->
                  <sf:form class="form-horizontal" modelAttribute="product" action="${contextRoot}/manage/products" 
                           method="POST"    enctype="multipart/form-data">
                  
                    <div class="form-group">
                       <label class="control-label col-md-4" for="name">Enter Product Name:</label>
                       <div class="col-md-8">
                          <sf:input style="width:80%" type="text" path="name" id="name" placeholder="Product Name" /> 
                          <sf:errors style="color:red;" path="name" cssClass="help-block" element="em" />
                       </div>
                    </div>
                  
                    <div class="form-group">
                       <label class="control-label col-md-4" for="brand">Enter Brand Name:</label>
                       <div class="col-md-8">
                          <sf:input style="width:80%" type="text" path="brand" id="brand" placeholder="Brand Name" /> 
                         <sf:errors style="color:red;"  path="brand" cssClass="help-block" element="em" />
                       </div>
                    </div>
                    
                    <div class="form-group">
                       <label class="control-label col-md-4" for="description">Product description:</label>
                       <div class="col-md-8">
                          <sf:textarea style="width:80%" type="text" path="description" id="description" placeholder="Write a description" /> 
                         <sf:errors style="color:red;"  path="description" cssClass="help-block" element="em" />
                       </div>
                    </div>
                    
                    <div class="form-group">
                       <label class="control-label col-md-4" for="unitPrice">Enter unit price:</label>
                       <div class="col-md-8">
                          <sf:input style="width:80%" type="text" path="unitPrice" id="unitPrice" placeholder="Unit price in euros" /> 
                         <sf:errors style="color:red;"  path="unitPrice" cssClass="help-block" element="em" />
                       </div>
                    </div>
                    
                     <div class="form-group">
                       <label class="control-label col-md-4" for="quantity">Quantity available:</label>
                       <div class="col-md-8">
                          <sf:input style="width:80%" type="number" path="quantity" id="quantity" placeholder="Quantity available" /> 
                       </div>
                    </div>
                    
                     <div class="form-group">
                       <label class="control-label col-md-4" for="file">Select an image:</label>
                       <div class="col-md-8">
                          <sf:input style="width:80%" type="file" path="file" id="file" class="form-control" /> 
                          <sf:errors path="file" style="color:red;" cssClass="help-block" element="em" />
                       </div>
                    </div>
                    
                    <div class="form-group">
                       <label class="control-label col-md-4" for="category_id">Select category:</label>
                       <div class="col-md-8">
                         <sf:select class="form-control" id="category_id" path="category_id"
                            items="${categories}"
                            itemLabel="name"
                            itemValue="id" />
                            <c:if test="${product.id == 0}">
                              <div class="text-right">
                               <br/>
                               <button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add Category</button>
                              </div>
                            </c:if>
                        </div>
                    </div>
                    
                    
                    <div class="form-group">
                       <div class="col-md-offset-4 col-md-8">
                          <input type="submit" name="submit" id="submit" value="Submit" class="btnbtn-primary"/>   
                         <!-- hidden fields for products -->
                          <sf:hidden path="id" />
                          <sf:hidden path="code" />
                          <sf:hidden path="supplierId" />
                          <sf:hidden path="active" />
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
			    <div class="col-xs-12">
			       <h3>Available Products</h3>
			       <hr/>
			    </div>
			    
			    <div class="col-xs-12">
			        <div style="overflow:auto">
			        
			        <table id="adminProductsTable" class="table table-striped table-bordered">
			            <thead>
			               <tr>
			                  <th>Id</th>
			                  <th>Code</th>
			                  <th>Name</th>
			                  <th>Brand</th>
			                  <th>Price</th>
			                  <th>Quantity</th>
			                  <th>Active</th>
			                  <th>Edit</th>
			               </tr>
			            </thead>
			        
			            
			            <tfoot>
			               <tr>
			                  <th>Id</th>
			                  <th>Code</th>
			                  <th>Name</th>
			                  <th>Brand</th>
			                  <th>Price</th>
			                  <th>Quantity</th>
			                  <th>Active</th>
			                  <th>Edit</th>
			               </tr>
			            </tfoot>
			       </table>
			        
			        </div>
			    </div>
			    
			</div>
			
			
			<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			    <!-- Modal Header -->
			      <div class="modal-header">
			         <button type="button" class="close" data-dismiss="modal">
			            <span>&times;</span>
			         </button>
			         <h4 class="modal-title">Add new category</h4>
			      </div>
			      <div class="modal-body">
			         <!-- category form -->
			           <sf:form modelAttribute="category" action="${contextRoot}/manage/category" method="POST" class="form-horizontal">
			               <div class="form-group">
			                 <label for="category_name" class="control-label col-md-4">Category name</label>
			                 <div class="col-md-8">
			                     <sf:input type="text" path="name" id="category_name" class="form-control"/>
			                 </div>
			               </div>
			               
			                <div class="form-group">
			                 <label for="category_description" class="control-label col-md-4">Category description</label>
			                 <div class="col-md-8">
			                     <sf:input cols="" rows="5" path="description" id="category_description" class="form-control"/>
			                 </div>
			               </div>
			               
			               <div class="form-group">
			                 <div class="col-md-offset-4 col-md-8">
			                     <input type="submit" value="Add category" class="btn btn-primary"/>
			                 </div>
			               </div>
			           </sf:form>
			      </div>
			    </div>
			  </div>
			</div>
</div>