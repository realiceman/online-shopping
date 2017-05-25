package net.yh.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.yh.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clas) {
		// TODO Auto-generated method stub
		return Product.class.equals(clas);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
        Product product = (Product) target;
        //check file is selected
        if(product.getFile()== null || product.getFile().getOriginalFilename().equals("")){
        	errors.rejectValue("file", null, "Please select an image file to upload!");
          return;
        }
        
        if(!(product.getFile().getContentType().equals("image/jpeg")||
        	 product.getFile().getContentType().equals("image/png") ||
        	 product.getFile().getContentType().equals("image/gif")
        		)){
        	errors.rejectValue("file", null, "Please use only an image file for upload!");
        	return;
        }
	}

}
