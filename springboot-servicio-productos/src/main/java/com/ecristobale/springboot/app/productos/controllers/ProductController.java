package com.ecristobale.springboot.app.productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ecristobale.springboot.app.productos.models.entity.Product;
import com.ecristobale.springboot.app.productos.models.service.IProductService;

@RestController
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@GetMapping("/products")
	public List<Product> listProducts(){
		return productService.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Product show(@PathVariable Long id) {
		return productService.findById(id);
	}
}
