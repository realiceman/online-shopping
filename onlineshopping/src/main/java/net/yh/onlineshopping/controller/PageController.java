package net.yh.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.yh.onlineshopping.exception.ProductNotFoundException;
import net.yh.shoppingbackend.dao.CategoryDAO;
import net.yh.shoppingbackend.dao.ProductDAO;
import net.yh.shoppingbackend.dto.Category;
import net.yh.shoppingbackend.dto.Product;




@Controller
public class PageController {
	


public static  Logger logger = LogManager.getLogger(PageController.class);

	
	@Autowired
	private CategoryDAO categorydao;
	@Autowired
	private ProductDAO productdao;
	
	
	
	@RequestMapping(value={"/","/home","/index"})
	public ModelAndView index(){
		//PropertyConfigurator.configure("log4j.properties");
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "youss shop");
        logger.info("message depuis index");
		mv.addObject("categories", categorydao.list());
		mv.addObject("userclickHome", true);
		return mv;
	}
	
	
//	@RequestMapping(value="/test")
//	public ModelAndView test(@RequestParam(value="greeting", required=false) String greeting){
//		if(greeting ==null){
//			greeting = "hello there";
//		}
//		ModelAndView mv = new ModelAndView("page");
//		mv.addObject("greeting", greeting);
//		return mv;
//	}
	
	
	@RequestMapping(value="/test/{greeting}")
	public ModelAndView test(@PathVariable("greeting")String greeting){
		if(greeting ==null){
			greeting = "hello there";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);
		return mv;
	}
	
	
	@RequestMapping("/about")
	public ModelAndView about(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userclickAbout", true);
		return mv;
	}
	
	@RequestMapping("/contact")
	public ModelAndView contact(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userclickContact", true);
		return mv;
	}
	
	/*  all products*/
	
	@RequestMapping(value="/show/all/products")
	public ModelAndView showAllProducts(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("categories", categorydao.list());
		mv.addObject("userclickAllProducts", true);
		return mv;
	}
	
	/*all products from a category */
	@RequestMapping(value="/show/category/{id}/products")
	public ModelAndView showCatProducts(@PathVariable("id") int id){
		ModelAndView mv = new ModelAndView("page");
		//cat dao to fetch a single cat
		Category category = ((Category) categorydao.get(id));
		mv.addObject("title", category.getName());
		mv.addObject("categories", categorydao.list());
		//we pass the single cat in an object
		mv.addObject("category", category);
		mv.addObject("userclickCategoryProducts", true);
		return mv;
	}
	
	
	/* view single product */ 
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable("id") int id) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		Product product = productdao.get(id);
		     if(product==null) {
		    	 throw new ProductNotFoundException();
		     }
		//views count
		product.setViews(product.getViews()+1);
		productdao.update(product);
		//end view count
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userclickShowProduct", true);
		return mv;
	}
	
	
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name="error", required=false) String error, 
			@RequestParam(name="logout", required=false) String logout){
		ModelAndView mv = new ModelAndView("login");
		if(error!=null){
			mv.addObject("message", "Invalid username/password");
		}
		if(logout!=null){
			mv.addObject("logout", "You have been successfully logged out");
		}
		
		return mv;
	}
	
	
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied(){
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 - Access Denied");
		mv.addObject("errorTitle", "Haha got you ;) ! ");
		mv.addObject("errorDescription", "You are not authorized to view this page!");
		return mv;
	}
	
	
	/* Logout */

	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		//fetch authentication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

}
