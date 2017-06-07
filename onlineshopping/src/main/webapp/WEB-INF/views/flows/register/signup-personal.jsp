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
                  <sf:form class="form-horizontal" id="registerForm" method="POST" modelAttribute="user">
                  
                    <div class="form-group">
                       <label class="control-label col-md-4" >First Name:</label>
                       <div class="col-md-8">
                          <sf:input  type="text" path="firstName" class="form-control"  placeholder="First Name" /> 
                       </div>
                    </div>
                  
                    <div class="form-group">
                       <label class="control-label col-md-4" >Last Name:</label>
                       <div class="col-md-8">
                          <sf:input  type="text" path="lastName" class="form-control"  placeholder="Last Name" /> 
                       </div>
                    </div>
                    
                    <div class="form-group">
                       <label class="control-label col-md-4" >Email:</label>
                       <div class="col-md-8">
                          <sf:input  type="text" path="Email" class="form-control"  placeholder="abc@xyz.com" /> 
                       </div>
                    </div>
                    
                    <div class="form-group">
                       <label class="control-label col-md-4" >Contact Number:</label>
                       <div class="col-md-8">
                          <sf:input  type="text" path="contactNumber" class="form-control"  placeholder="xxxxxxxxxx" maxlength="10" /> 
                       </div>
                    </div>
                    
                     <div class="form-group">
                       <label class="control-label col-md-4" >Password:</label>
                       <div class="col-md-8">
                          <sf:input  type="text" path="password" class="form-control"  placeholder="Password" /> 
                       </div>
                    </div>
                    
                    <div class="form-group">
                     <label clas="control-label col-md-4">Select Role</label>
                     <div class="col-md-8">
                       <label class="radio-inline">
                         <sf:radiobutton path="role" value="USER" checked="checked"/>User
                       </label>
                       <label class="radio-inline">
                         <sf:radiobutton path="role" value="SUPPLIER" />Supplier
                       </label>
                     </div>
                    </div>
                    
                    
                    <div class="form-group">
                       <div class="col-md-offset-4 col-md-8">
                          <button type="submit" name="_eventId_billing"  class="btn btn-primary">
                             Next - Billing <span class="glyphicon glyphicon-chevron-right" ></span>
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
