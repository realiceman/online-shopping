package net.yh.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.yh.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	
	@Autowired
	private CartService cartservice;
   
	@RequestMapping("/show")
	public ModelAndView showCart(){
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "user cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartservice.getCartLines());
		
		return mv;
	}
}
