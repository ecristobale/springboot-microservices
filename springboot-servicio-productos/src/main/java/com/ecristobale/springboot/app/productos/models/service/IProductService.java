package com.ecristobale.springboot.app.productos.models.service;

import java.util.List;

import com.ecristobale.springboot.app.productos.models.entity.Product;

public interface IProductService {

	public List<Product> findAll();
	public Product findById(Long id);
}
