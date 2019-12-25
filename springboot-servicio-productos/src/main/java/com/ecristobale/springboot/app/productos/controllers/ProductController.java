package com.ecristobale.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecristobale.springboot.app.productos.models.entity.Product;
import com.ecristobale.springboot.app.productos.models.service.IProductService;

@RestController
public class ProductController {

	//@Autowired
	//private Environment env;
	
	@Value("${server.port}")
	private Integer serverPort;
	
	@Autowired
	private IProductService productService;
	
	@GetMapping("/list")
	public List<Product> listProducts(){
		return productService.findAll().stream().map(product -> {
			//product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			product.setPort(serverPort);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/show/{id}")
	public Product show(@PathVariable Long id) {
		Product product = productService.findById(id);
		//product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		product.setPort(serverPort);

//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		return product;
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return productService.save(product);
	}
	
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product update(@RequestBody Product product, @PathVariable Long id) {
		Product productDB = productService.findById(id);
		productDB.setName(product.getName());
		productDB.setPrice(product.getPrice());
		return productService.save(productDB);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		productService.deleteById(id);
	}
}
