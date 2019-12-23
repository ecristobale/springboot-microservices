package com.ecristobale.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecristobale.springboot.app.item.models.Item;
import com.ecristobale.springboot.app.item.models.service.IItemService;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceFeign")//("serviceRestTemplate")
	private IItemService itemService;
	
	@GetMapping("/items")
	public List<Item> listItems(){
		return itemService.findAll();
	}
	
	@GetMapping("/items/{id}/{units}")
	public Item show(@PathVariable Long id, @PathVariable Integer units) {
		return itemService.findById(id, units);
	}
}
