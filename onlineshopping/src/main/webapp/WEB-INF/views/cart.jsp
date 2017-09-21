<div class="container">

  <c:if test="${not empty message}">
      <div class="alert alert-info">
         <h3 class="text-center">
           ${message}
         </h3>
      </div>
  </c:if>

   <c:choose>
      <c:when test="${not empty cartLines}">
         <table id="cart" class="table table-hover table-condensed">
    				<thead>
						<tr>
							<th style="width:50%">Product</th>
							<th style="width:10%">Price</th>
							<th style="width:8%">Quantity</th>
							<th style="width:22%" class="text-center">Subtotal</th>
							<th style="width:10%"></th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach items="${cartLines}" var="cartline">
					    
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs"><img src="${images}/${cartline.product.code}.jpg" alt="${cartline.product.name}" class="img-responsive cartimg"/></div>
									<div class="col-sm-10">
										<h4 class="nomargin">${cartline.product.name}</h4>
										
										<c:if test="${cartline.available==false }">
										   <strong class="unavailable">(not available)</strong>
										</c:if>
										
										<p>Brand - ${cartline.product.brand}</p>
										<p>Description - ${cartline.product.description}</p>
									</div>
								</div>
							</td>
							<td data-th="Price"> <p>${cartline.buyingPrice} &#8364;</p>  </td>
							<td data-th="Quantity">
								<input type="number" id="count_${cartline.id}" min="1" max="3" class="form-control text-center" value="${cartline.productCount}">
							</td>
							<td data-th="Subtotal" class="text-center">${cartline.total} &#8364;</td>
							<td class="actions" data-th="">
								<button type="button" name="refreshCart" value="${cartline.id}" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-refresh"></span></button>
								<button class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-trash"></span></button>								
							</td>
						</tr>
					</c:forEach>
					
					
					</tbody>
					<tfoot>
						<tr class="visible-xs">
							<td class="text-center"><strong>Total ${userModel.cart.grandTotal} &#8364;</strong></td>
						</tr>
						<tr>
							<td><a href="#" class="btn btn-warning"><span class="glyphicon glyphicon-chevron-left"></span> Continue Shopping</a></td>
							<td colspan="2" class="hidden-xs"></td>
							<td class="hidden-xs text-center"><strong>Total ${userModel.cart.grandTotal} &#8364;</strong></td>
							<td><a href="#" class="btn btn-success btn-block">Checkout <span class="glyphicon glyphicon-chevron-right"></span></a></td>
						</tr>
					</tfoot>
				</table>
      </c:when>
      <c:otherwise>
         <div class="jumbotron">
            <div class="text-center">
               <h1>your cart is empty :(</h1>
            </div>
         </div>
       </c:otherwise>
   </c:choose>

	
</div>