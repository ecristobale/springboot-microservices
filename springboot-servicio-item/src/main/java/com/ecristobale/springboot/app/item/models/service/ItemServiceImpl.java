package com.ecristobale.springboot.app.item.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecristobale.springboot.app.item.models.Item;

@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private RestTemplate clientRest;
	
	@Override
	public List<Item> findAll() {
		return null;
	}

	@Override
	public Item findById(Long id, Integer units) {
		return null;
	}

}
