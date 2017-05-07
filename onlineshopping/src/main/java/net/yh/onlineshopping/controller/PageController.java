package net.yh.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.yh.shoppingbackend.dao.CategoryDAO;
import net.yh.shoppingbackend.dto.Category;

@Controller
public class PageController {
	
	@Autowired
	private CategoryDAO categorydao;
	
	
	@RequestMapping(value={"/","/home","/index"})
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "youss shop");
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

}
