package com.ecristobale.springboot.app.item.models.service;

import java.util.List;

import com.ecristobale.springboot.app.item.models.Item;
import com.ecristobale.springboot.app.item.models.Product;

public interface IItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer units);
	
	public Product save(Product product);
	public Product update(Product product, Long id);
	
	public void delete(Long id);
}
