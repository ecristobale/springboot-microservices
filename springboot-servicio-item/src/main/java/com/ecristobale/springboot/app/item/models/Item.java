package com.ecristobale.springboot.app.item.models;

import java.math.BigDecimal;

public class Item {

	private Product product;
	private Integer units;
	
	public Item() {}
	public Item(Product product, Integer units) {
		this.product = product;
		this.units = units;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
	public BigDecimal getTotal() {
		return product.getPrice().multiply(new BigDecimal(units));
	}
}
