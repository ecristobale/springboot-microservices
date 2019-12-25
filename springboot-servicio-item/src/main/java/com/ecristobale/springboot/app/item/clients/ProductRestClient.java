package com.ecristobale.springboot.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecristobale.springboot.app.item.models.Product;

@FeignClient(name = "servicio-productos")
public interface ProductRestClient {

	@GetMapping("/list")
	public List<Product> listProducts();
	
	@GetMapping("/show/{id}")
	public Product show(@PathVariable Long id);
}
