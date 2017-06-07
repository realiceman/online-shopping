<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@include file="../shared/flows-header.jsp"%>

<div class="container">
		    <div class="row">
    
   
    
       <div class="col-md-offset-3 col-md-6">
          
          <div class="panel panel-primary">
             
             <div class="panel-heading">
             
                <h4>Sign Up - Personal</h4>
               
             </div>
             
             <div class="panel-body">
             
                  <!-- FORM ELEMENTS -->
                  <sf:form class="form-horizontal" id="billingForm" method="POST" modelAttribute="billing">
                  
                    <div class="form-group">
                       <label class="control-label col-md-4" for="addressLineOne" >Address line on</label>
                       <div class="col-md-8">
                          <sf:input  type="text" path="addressLineOne" class="form-control"  placeholder="Enter address line one" /> 
                       </div>
                    </div>
                  
                    <div class="form-group">
                       <label class="control-label col-md-4" >Address line two</label>
                       <div class="col-md-8">
                          <sf:input  type="text" path="addressLineTwo" class="form-control"  placeholder="Enter address line two" /> 
                       </div>
                    </div>
                    
                    <div class="form-group">
                       <label class="control-label col-md-4" >City</label>
                       <div class="col-md-8">
                          <sf:input  type="text" path="city" class="form-control"  placeholder="Enter city name" /> 
                       </div>
                    </div>
                    
                    <div class="form-group">
                       <label class="control-label col-md-4" >Postal code</label>
                       <div class="col-md-8">
                          <sf:input  type="text" path="postalCode" class="form-control"  placeholder="Enter postal code"  /> 
                       </div>
                    </div>
                    
                     <div class="form-group">
                       <label class="control-label col-md-4" >Country</label>
                       <div class="col-md-8">
                          <sf:input  type="text" path="country" class="form-control"  placeholder="Enter country name" /> 
                       </div>
                    </div>
                    
                    
                    <div class="form-group">
                       <div class="col-md-offset-4 col-md-8">
                         <!-- going back to personal infos -->
                          <button type="submit" name="_eventId_personal"  class="btn btn-primary">
                             <span class="glyphicon glyphicon-chevron-left" ></span> Previous - Personal
                          </button>   
                          
                        <!-- moving to confirm -->
                          <button type="submit" name="_eventId_confirm"  class="btn btn-primary">
                             Next - Confirm <span class="glyphicon glyphicon-chevron-right" ></span> 
                          </button> 
                        </div>
                    </div>
                  
                  </sf:form>  
                    
             </div>
             
          </div>
       
       </div>
    
    </div>
</div>




<%@include file="../shared/flows-footer.jsp"%>