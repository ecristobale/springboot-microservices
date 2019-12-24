package com.ecristobale.springboot.app.item.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecristobale.springboot.app.item.models.Item;
import com.ecristobale.springboot.app.item.models.Product;
import com.ecristobale.springboot.app.item.models.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ItemController {
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	@Qualifier("serviceFeign")//("serviceRestTemplate")
	private IItemService itemService;
	
	@GetMapping("/listar")
	public List<Item> listItems(){
		return itemService.findAll();
	}
	
	@Value("${configuracion.texto}")
	private String text;
	
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
	
	@GetMapping("/obtener-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String port) {
		
		log.info(text);
		
		Map<String, String> json = new HashMap<>();
		json.put("texto", text);
		json.put("puerto", port);
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
}
