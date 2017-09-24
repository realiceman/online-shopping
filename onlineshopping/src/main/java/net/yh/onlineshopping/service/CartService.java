package net.yh.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.yh.onlineshopping.model.UserModel;
import net.yh.shoppingbackend.dao.CartLineDAO;
import net.yh.shoppingbackend.dao.ProductDAO;
import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.CartLine;
import net.yh.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {
	
	@Autowired
	private ProductDAO productdao;
	
	@Autowired
	private CartLineDAO cartlinedao;
	
	@Autowired
	private HttpSession session;
	
	
	//return the cart of the user who has logged in
	private Cart getCart(){
		
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	
	//return the entire cart lines
	public List<CartLine> getCartLines(){
		Cart cart =this.getCart();
		return cartlinedao.list(cart.getId());
	}


	public String updateCartLine(int cartlineId, int count) {
		//fetch cartline
		CartLine cartline = cartlinedao.get(cartlineId);
		if(cartline == null){
			return "result=error";
		}
		else{
			Product product = cartline.getProduct();
			double oldTotal = cartline.getTotal();
			
			if(product.getQuantity()<= count){
				count = product.getQuantity();
			}
			cartline.setProductCount(count);
			cartline.setBuyingPrice(product.getUnitPrice());
			cartline.setTotal(product.getUnitPrice()*count);
			cartlinedao.update(cartline);
			
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartline.getTotal());
			cartlinedao.updateCart(cart);
			
			return "result=updated";
		}
		
	}


	public String deleteCartLine(int cartlineId) {
		CartLine cartline = cartlinedao.get(cartlineId);
		if(cartline == null){
			return "result=error";
		}
		else{
			//update cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal()-cartline.getTotal());
			cart.setCartLines(cart.getCartLines()-1);
			cartlinedao.updateCart(cart);
			
			//remove cartline
			cartlinedao.delete(cartline);
			return "result=deleted";
		}
		
	}


	public String addCartLine(int productId) {
		String response = null;
		Cart cart = this.getCart();
		
		CartLine cartline = cartlinedao.getByCartAndProduct(cart.getId(), productId);
		if(cartline==null){
			//add new cartline
			cartline = new CartLine();
			//fetch product
			Product product = productdao.get(productId);
			
			cartline.setCartId(cart.getId());
			cartline.setProduct(product);
			cartline.setBuyingPrice(product.getUnitPrice());
			cartline.setProductCount(1);
			cartline.setTotal(product.getUnitPrice());
			cartline.setAvailable(true);
			
			cartlinedao.add(cartline);
			
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal()+cartline.getTotal());
			
			cartlinedao.updateCart(cart);
			
			response = "result=added";
		}
		else{
			
			if(cartline.getProductCount() < 3){
				response = this.manageCartline(cartline.getId(), cartline.getProductCount()+1);
			}else{
				response="result=maximum";
			}
			
			
		}
		
		return response;
	}


	private String manageCartline(int cartlineId, int count) {
		CartLine cartline = cartlinedao.get(cartlineId);
		double oldTotal = cartline.getTotal();
		Product product = cartline.getProduct();
		
		if(product.getQuantity()<count){
			return "result=unavailable";
		}
		
		//update cartline
		cartline.setProductCount(count);
		cartline.setBuyingPrice(product.getUnitPrice());
		cartline.setTotal(product.getUnitPrice()*count);
		cartlinedao.update(cartline);
		//update cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartline.getTotal());
		cartlinedao.updateCart(cart);
		
		return "result=updated";
		
		
	}

}
