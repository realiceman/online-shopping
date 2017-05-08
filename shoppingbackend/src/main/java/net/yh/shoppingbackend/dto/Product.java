package net.yh.shoppingbackend.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	private String name;
	private String brand;


	@JsonIgnore
	private String description;


	@Column(name="unit_price")
	private double unitPrice;
	private int quantity;
	@Column(name="is_active")
	@JsonIgnore
	private boolean active;
	@Column(name="category_id")
	@JsonIgnore
	private int category_id;
	@Column(name="supplier_id")
	@JsonIgnore
	private int supplierId;
	private int purchases;
	private int views;
	public Product() {
		this.code = UUID.randomUUID().toString().substring(26).toUpperCase();
	}
	public String getBrand() {
		return brand;
	}
	
	
	public int getCategory_id() {
		return category_id;
	}


	public String getCode() {
		return code;
	}


	public String getDescription() {
		return description;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public int getPurchases() {
		return purchases;
	}


	public int getQuantity() {
		return quantity;
	}


	public int getSupplierId() {
		return supplierId;
	}


	public double getUnitPrice() {
		return unitPrice;
	}


	public int getViews() {
		return views;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}


	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}


	public void setViews(int views) {
		this.views = views;
	}
	
	
}
