package net.yh.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.yh.onlineshopping.utils.FileUploadUtility;
import net.yh.onlineshopping.validator.ProductValidator;
import net.yh.shoppingbackend.dao.CategoryDAO;
import net.yh.shoppingbackend.dao.ProductDAO;
import net.yh.shoppingbackend.dto.Category;
import net.yh.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categorydao;
	
	@Autowired
	private ProductDAO productdao;
	
	public static  Logger logger = LogManager.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
    public ModelAndView showManageProducts(@RequestParam(name="operation", required=false)String operation){
	
		ModelAndView mv = new ModelAndView("page");
	    mv.addObject("userClickManageProducts", true);
	    mv.addObject("title", "Manage Products");
	    Product nProduct = new Product();
	    nProduct.setSupplierId(1);
	    nProduct.setActive(true);
	    mv.addObject("product", nProduct);
	    
	    if(operation!=null){
	    	if(operation.equals("product")){
	    		mv.addObject("message", "Product submitted succesfully!");
	    	}
	    }
	    
	    return mv;
	
	}
	
	
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	 public ModelAndView showEditProducts(@PathVariable int id){
		
		ModelAndView mv = new ModelAndView("page");
	    mv.addObject("userClickManageProducts", true);
	    mv.addObject("title", "Manage Products");
	    //fetch the product from the db
	    Product nProduct = productdao.get(id);
	    mv.addObject("product", nProduct);
	    
	    return mv;
	
	}
	
	
	
	//handling manager product submission
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, 
			          BindingResult results, Model model, HttpServletRequest request){		
		//handle image validation for new products
		if(mProduct.getId()==0){ //if id is 0 so it's a new product (no hidden id sent from form after clicking to pencil) so we have to validate anyway the file
		  new ProductValidator().validate(mProduct, results);
		}else{ //else the id exists so it's an update=>1) we keep the file from product object and keep blank the name; 2) name is not blank so we need to validate again
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct, results);
			}
		}
		//check error(s)
		if(results.hasErrors()){
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for product submission");
			return "page";
		}
		
		logger.info(mProduct.toString());
		if(mProduct.getId()==0){  //if id is 0 so it's a new product (no hidden id sent from form after clicking to pencil) so added to db
		    productdao.add(mProduct);
		}else{                    //else we put the new info of the existing product 
			productdao.update(mProduct);
		}
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	

	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
		Product product = productdao.get(id);
		boolean isActive = product.isActive();
		product.setActive(!isActive);
		productdao.update(product);
		return (isActive)?
				"You have successfully deactivated the product with the id "+product.getId()
				:"You have successfully activated the product with the id "+product.getId();
	}
	
	
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categorydao.list();
	}
	
	
	
}
