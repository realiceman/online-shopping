package net.yh.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.yh.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	
	@Autowired
	private CartService cartservice;
   
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name="result", required=false) String result){
		ModelAndView mv = new ModelAndView("page");
		
		if(result!=null){
			switch(result){
			case "updated":
			     mv.addObject("message", "Cartline updated successfully!");
			     break;
			case "added":
			     mv.addObject("message", "Cartline added successfully!");
			     break;
			case "deleted":
			     mv.addObject("message", "Cartline has been removed successfully!");
			     break;
			case "unavailable":
			     mv.addObject("message", "quantity limit has been reach, sorry !");
			     break;
			case "error":
				 mv.addObject("message", "Something went wrong!");
				 break;
			}
		}
		
		mv.addObject("title", "user cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartservice.getCartLines());
		
		return mv;
	}
	
	
	//var updateUrl = window.contextRoot+ '/cart/'+cartlineId+'/update?count='+currentCount;
	@RequestMapping("/{cartlineId}/update")
	public String updateCart(@PathVariable int cartlineId, @RequestParam int count){
		
		String response = cartservice.updateCartLine(cartlineId, count);
		return "redirect:/cart/show?"+response;
	}
	
	
	@RequestMapping("/{cartlineId}/delete")
	public String updateCart(@PathVariable int cartlineId){
		String response = cartservice.deleteCartLine(cartlineId);
		return "redirect:/cart/show?"+response;
	}
	
	
	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId){
		String response = cartservice.addCartLine(productId);
		return "redirect:/cart/show?"+response;
	}
	
}
