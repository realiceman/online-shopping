package net.yh.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.yh.onlineshopping.model.UserModel;
import net.yh.shoppingbackend.dao.CartLineDAO;
import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.CartLine;
import net.yh.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {
	
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

}
