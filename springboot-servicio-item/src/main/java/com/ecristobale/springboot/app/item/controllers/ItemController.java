package com.ecristobale.springboot.app.item.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecristobale.springboot.app.item.models.Item;
import com.ecristobale.springboot.app.item.models.Product;
import com.ecristobale.springboot.app.item.models.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceFeign")//("serviceRestTemplate")
	private IItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listItems(){
		return itemService.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "alternativeShow")
	@GetMapping("/ver/{id}/{units}")
	public Item show(@PathVariable Long id, @PathVariable Integer units) {
		return itemService.findById(id, units);
	}
	
	public Item alternativeShow(Long id, Integer units) {
		Item item = new Item();
		Product product = new Product();
		item.setUnits(units);
		product.setId(id);
		product.setName("Alternative product");
		product.setPrice(new BigDecimal(100.00));
		item.setProduct(product);
		return item;
	}
}
