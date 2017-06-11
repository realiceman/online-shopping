package net.yh.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import net.yh.onlineshopping.model.RegisterModel;
import net.yh.shoppingbackend.dao.UserDAO;
import net.yh.shoppingbackend.dto.Address;
import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.User;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userdao;
	
	
	public RegisterModel init(){
		
		return new RegisterModel();
	}
	
	

	public void addUser(RegisterModel registerModel, User user){
		registerModel.setUser(user);
	}
	
	
	public void addBilling(RegisterModel registerModel, Address billing){
		registerModel.setBilling(billing);
	}
	
	
	public String validateUser(User user, MessageContext error){
		String transitionValue = "success";
		//check password / confirm password
		if(!(user.getPassword().equals(user.getConfirmPassword()))){
			error.addMessage(new MessageBuilder().error().source("confirmPassword").defaultText("Password does not match the confirmed password").build());
			transitionValue="failure";
		}
		//check uniqueness of email id
		if(userdao.getByEmail(user.getEmail())!=null){
			error.addMessage(new MessageBuilder().error().source("email").defaultText("Email address is alreaday used!").build());
			transitionValue="failure";
		}
		
		return transitionValue;
	}
	
	
	public String saveAll(RegisterModel model){
		String transitionValue="success";
		//fetch user
		User user = model.getUser();
		if(user.getRole().equals("USER")){
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		//save user
		userdao.addUser(user);
		//get the address
		Address billing = model.getBilling();
		billing.setUser(user);
		billing.setBilling(true);
		//save address
		userdao.addAddress(billing);
		
		return transitionValue;
	}
}
