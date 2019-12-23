package com.ecristobale.springboot.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecristobale.springboot.app.item.models.Product;

@FeignClient(name = "servicio-productos", url = "localhost:8081")
public interface ProductRestClient {

	@GetMapping("/products")
	public List<Product> listProducts();
	
	@GetMapping("/products/{id}")
	public Product show(@PathVariable Long id);
}
