<div class="container">

  <!-- breadcrumb -->
  <div class="row">
  
      <div class="col-xs-12">
         <ol class="breadcrumb">
         
            <li><a href="${contextRoot}/home">Home</a></li>
            <li><a href="${contextRoot}/show/all/products">Products</a></li>
            <li class="active">${product.name}</li>
         </ol>
      </div>
  </div>
  
  
  <div class="row">
  
   <!-- display image -->
    <div class="col-xs-12 col-sm-4">
        <div class="thumbnail">
           <img src="${images}/${product.code}.jpg" class="img img-responsive"/>
           
        </div>
    </div>
    
    <!-- description -->
    <div class="col-xs-12 col-sm-8">
       <h3>${product.name}</h3>
       <hr/>
       <p>${product.description}</p>
       <hr/>
       <h4>Price: <strong>${product.unitPrice}&#8364; </strong></h4>
       <hr/>
       <h6>Qty. Available: ${product.quantity}</h6>
       <a href="${contextRoot}/cart/add/${product.id}/product" class="btn btn-success"><span class="glyhicon-shopping-cart">Add to cart</span></a>
        <a href="${contextRoot}/show/all/products" class="btn btn-success">Back to products</a>
    </div>
  
  </div>

</div>