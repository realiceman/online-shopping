<div class="container">

    <div class="row">
    
       <div class="col-md-offset-2 col-md-8">
          
          <div class="panel panel-primary">
             
             <div class="panel-heading">
             
                <h4>Product Management</h4>
               
             </div>
             
             <div class="panel-body">
             
                  <!-- FORM ELEMENTS -->
                  <sf:form class="form-horizontal" modelAttribute="product">
                  
                    <div class="form-group">
                       <label class="control-label col-md-4" for="name">Enter Product Name:</label>
                       <div class="col-md-8">
                          <sf:input style="width:80%" type="text" path="name" id="name" placeholder="Product Name" /> 
                       </div>
                    </div>
                  
                    <div class="form-group">
                       <label class="control-label col-md-4" for="brand">Enter Brand Name:</label>
                       <div class="col-md-8">
                          <sf:input style="width:80%" type="text" path="brand" id="brand" placeholder="Brand Name" /> 
                       </div>
                    </div>
                    
                    <div class="form-group">
                       <label class="control-label col-md-4" for="description">Product description:</label>
                       <div class="col-md-8">
                          <sf:textarea style="width:80%" type="text" path="description" id="description" placeholder="Write a description" /> 
                       </div>
                    </div>
                    
                    <div class="form-group">
                       <label class="control-label col-md-4" for="unitPrice">Enter unit price:</label>
                       <div class="col-md-8">
                          <sf:input style="width:80%" type="text" path="unitPrice" id="unitPrice" placeholder="Unit price in euros" /> 
                       </div>
                    </div>
                    
                     <div class="form-group">
                       <label class="control-label col-md-4" for="quantity">Quantity available:</label>
                       <div class="col-md-8">
                          <sf:input style="width:80%" type="number" path="quantity" id="quantity" placeholder="Quantity available" /> 
                       </div>
                    </div>
                    
                    <div class="form-group">
                       <label class="control-label col-md-4" for="category_id">Select category:</label>
                       <div class="col-md-8">
                         <sf:select class="form-control" id="category_id" path="category_id"
                            items="${categories}"
                            itemLabel="name"
                            itemValue="id" />
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


</div>