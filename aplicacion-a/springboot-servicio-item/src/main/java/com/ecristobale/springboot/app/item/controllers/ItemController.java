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
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecristobale.springboot.app.item.models.Item;
import com.ecristobale.springboot.app.commons.models.entity.Product;
import com.ecristobale.springboot.app.item.models.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemController {
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")//("serviceRestTemplate")
	private IItemService itemService;
	
	@GetMapping("/list")
	public List<Item> listItems(){
		return itemService.findAll();
	}
	
	@Value("${configuracion.texto}")
	private String text;
	
	@HystrixCommand(fallbackMethod = "alternativeShow")
	@GetMapping("/show/{id}/{units}")
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
	
	@GetMapping("/get-config")
	public ResponseEntity<?> obtenerConfig(@Value("${server.port}") String port) {
		
		log.info(text);
		
		Map<String, String> json = new HashMap<>();
		json.put("texto", text);
		json.put("puerto", port);
		
		if(env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.puesto", env.getProperty("configuracion.autor.puesto"));
		}
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return itemService.save(product);
	}
	
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product update(@RequestBody Product product, @PathVariable Long id) {
		return itemService.update(product, id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemService.delete(id);
	}
}
