package com.ecristobale.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ecristobale.springboot.app.item.clients.ProductRestClient;
import com.ecristobale.springboot.app.item.models.Item;
import com.ecristobale.springboot.app.item.models.Product;

@Service("serviceFeign")
@Primary
public class ItemServiceFeignImpl implements IItemService {

	@Autowired
	private ProductRestClient feignClient;
	
	@Override
	public List<Item> findAll() {
		return feignClient.listProducts().stream().map(product -> new Item(product, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer units) {
		return new Item(feignClient.show(id), units);
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product update(Product product, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
