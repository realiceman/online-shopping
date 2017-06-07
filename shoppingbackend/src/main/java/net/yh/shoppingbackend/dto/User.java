package net.yh.shoppingbackend.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_detail")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	private String email;
	@Column(name="contact_number")
	private String contactNumber;
	private String role;
	private String password;
	private boolean enabled = true;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private Cart cart;
	
	public Cart getCart() {
		return cart;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getFirstName() {
		return firstName;
	}
	public int getId() {
		return id;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPassword() {
		return password;
	}
	public String getRole() {
		return role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	/*
	 * logging and debugging
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", role=" + role + ", password=" + password + ", enabled="
				+ enabled + "]";
	}
	
	
	


}
