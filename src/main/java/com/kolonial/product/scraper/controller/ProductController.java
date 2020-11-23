package com.kolonial.product.scraper.controller;

import com.kolonial.product.scraper.model.Product;
import com.kolonial.product.scraper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
  @Autowired ProductService productService;

  /**
   * Http method that will return all products from our database
   *
   * @return List of Products
   */
  @GetMapping("/products")
  private List<Product> getAllProducts() {
    return productService.getAllProducts();
  }
}
