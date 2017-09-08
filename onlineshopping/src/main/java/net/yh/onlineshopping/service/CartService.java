package net.yh.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.yh.onlineshopping.model.UserModel;
import net.yh.shoppingbackend.dao.CartLineDAO;
import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.CartLine;

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

}
