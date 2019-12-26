package com.ecristobale.springboot.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ecristobale.springboot.app.commons.models.entity.Product;

public interface ProductDao extends CrudRepository<Product, Long> {

}
