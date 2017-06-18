package net.yh.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.yh.onlineshopping.model.UserModel;
import net.yh.shoppingbackend.dao.UserDAO;
import net.yh.shoppingbackend.dto.User;

@ControllerAdvice
public class GlobalController {
	private UserModel usermodel = null;
	
	@Autowired
	private HttpSession session;
	@Autowired
	private UserDAO userdao;
	
	
	@ModelAttribute("userModel")
    public UserModel getUserModel(){
		if(session.getAttribute("userModel")==null){
			//add the user model
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = userdao.getByEmail(authentication.getName());
			if(user!=null){
				//create a new UserModel object to pass the user details
				usermodel = new UserModel();
				usermodel.setId(user.getId());
				usermodel.setEmail(user.getEmail());
				usermodel.setRole(user.getRole());
				usermodel.setFullName(user.getFirstName()+" "+user.getLastName());
				if(usermodel.getRole().equals("USER")){
					//set cart
					usermodel.setCart(user.getCart());
					
				}
				//set usermodel in session
				session.setAttribute("userModel", usermodel);
				return usermodel;
			}
		}
		//already authenticated then return the session 
		return (UserModel) session.getAttribute("userModel");
	}
}
