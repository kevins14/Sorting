package com.bridgephase.store;

import java.math.BigDecimal;

/*
 * Each Product contains upc (String), name (String), 
 * wholesalePrice (Number or subclass), retailPrice (Number or subclass), 
 * and quantity (Number or subclass).  Add standard getter and setters getName(), 
 * setName(), etc to the Product class.
 */
public class Product {
	String upc;
	String name;
	BigDecimal wsPrice;
	BigDecimal rePrice;
	int quantity;

	public Product() {
		upc = "";
		name = "";
		wsPrice = BigDecimal.ZERO;
		rePrice = BigDecimal.ZERO;
		quantity = 0;
	}

	public Product(String upc, String name, BigDecimal wsPrice, BigDecimal rePrice, int quantity) {

		this.upc = upc;
		this.name = name;
		this.wsPrice = wsPrice;
		this.rePrice = rePrice;
		this.quantity = quantity;

	}

	public String getUpc() {
		return this.upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getWsPrice() {
		return this.wsPrice;
	}

	public void setWsPrice(BigDecimal wsPrice) {
		this.wsPrice = wsPrice;
	}

	public BigDecimal getRePrice() {
		return this.rePrice;
	}

	public void setRePrice(BigDecimal rePrice) {
		this.rePrice = rePrice;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
