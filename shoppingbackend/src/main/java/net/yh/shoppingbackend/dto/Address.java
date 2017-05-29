package net.yh.shoppingbackend.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private User user;
	@Column(name="address_line_one")
	private String addressLineOne;
	@Column(name="address_line_two")
	private String addressLineTwo;
    private String city;
	private String state;
	private String country;
	@Column(name="postal_code")
	private String postalCode;
	private boolean shipping;
	private boolean billing;
	
	
	public String getAddressLineOne() {
		return addressLineOne;
	}
	public String getAddressLineTwo() {
		return addressLineTwo;
	}
	
	
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public int getId() {
		return id;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getState() {
		return state;
	}
	public User getUser() {
		return user;
	}
	public boolean isBilling() {
		return billing;
	}
	public boolean isShipping() {
		return shipping;
	}
	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}
	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}
	public void setBilling(boolean billing) {
		this.billing = billing;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public void setShipping(boolean shipping) {
		this.shipping = shipping;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	/*
	 * logging and debugging
	 */
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLineOne=" + addressLineOne + ", addressLineTwo=" + addressLineTwo
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", postalCode=" + postalCode
				+ ", shipping=" + shipping + ", billing=" + billing + "]";
	}
	
	
}
