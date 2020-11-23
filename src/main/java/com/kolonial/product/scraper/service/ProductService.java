package com.kolonial.product.scraper.service;

import com.kolonial.product.scraper.model.Product;
import com.kolonial.product.scraper.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
  @Autowired ProductRepository productRepository;

  /**
   * Method to get all products from the database
   *
   * @return List of Products
   */
  public List<Product> getAllProducts() {
    var produsts = new ArrayList<Product>();
    productRepository.findAll().forEach(product -> produsts.add(product));
    return produsts;
  }

  /**
   * Method to save/update a single product in the database
   *
   * @param product
   */
  public void saveOrUpdate(Product product) {
    productRepository.save(product);
  }

  /**
   * Method to save/update a list of products in the database
   *
   * @param products
   */
  public void saveOrUpdateProducts(List<Product> products) {
    productRepository.saveAll(products);
  }
}
