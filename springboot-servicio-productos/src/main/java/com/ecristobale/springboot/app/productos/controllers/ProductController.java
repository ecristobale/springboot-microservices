package com.ecristobale.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/products")
	public List<Product> listProducts(){
		return productService.findAll().stream().map(product -> {
			//product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			product.setPort(serverPort);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/products/{id}")
	public Product show(@PathVariable Long id) {
		Product product = productService.findById(id);
		//product.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		product.setPort(serverPort);
		return product;
	}
}
