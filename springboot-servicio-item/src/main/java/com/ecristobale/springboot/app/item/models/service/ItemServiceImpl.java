package com.ecristobale.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecristobale.springboot.app.item.models.Item;
import com.ecristobale.springboot.app.item.models.Product;

@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private RestTemplate clientRest;
	
	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays.asList(clientRest.getForObject("http://localhost:8081/products", Product[].class));
		return products.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer units) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Product product = clientRest.getForObject("http://localhost:8081/products/{id}", Product.class, pathVariables);
		return new Item(product, units);
	}

}