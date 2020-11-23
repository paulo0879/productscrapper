package com.kolonial.product.scraper.repository;

import com.kolonial.product.scraper.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {}
