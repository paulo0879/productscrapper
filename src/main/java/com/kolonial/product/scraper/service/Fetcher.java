package com.kolonial.product.scraper.service;

import com.kolonial.product.scraper.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.kolonial.product.scraper.utils.ConstantsUtils.KATEGORIER;
import static com.kolonial.product.scraper.utils.ConstantsUtils.KOLONIAL_URL;
import static com.kolonial.product.scraper.utils.ConstantsUtils.NAVBAR_CATEGORY_SIDEBAR;
import static com.kolonial.product.scraper.utils.ConstantsUtils.PARENT_CATEGORY;

@Service
@Slf4j
public class Fetcher {
  @Autowired ProductService productService;

  /**
   * Method that will scrap the Kolonial website to get all products from all categories/subcategories.
   * After scrape all products it will save then in the database.
   * Has a scheduller to run from 4 to 4 hours.
   */
  @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
  public void startFetcher() {
    log.info("Product Scraper started.");
    try {
      // Here we create a document object and use JSoup to fetch the website
      var doc = Jsoup.connect(KOLONIAL_URL + KATEGORIER).get();

      // Get the list of categories in the sidebar
      var categorySidebar = doc.getElementById(NAVBAR_CATEGORY_SIDEBAR);
      var categories = categorySidebar.getElementsByClass(PARENT_CATEGORY);

      // Create an empty list to put all the products that will be scraped from the website
      var productsList = new ArrayList<Product>();

      // This for will loop through all categories and fetch the products
      for (Element category : categories) {
        productsList.addAll(CategoryFetcher.fetchCategory(category));
      }

      // Removing all duplicated products from our list base on the product Id
      var productsListWithoutDuplicate =
          productsList.stream().distinct().collect(Collectors.toList());

      // Add the products in our database
      productService.saveOrUpdateProducts(productsListWithoutDuplicate);
      log.info(
          "Product Scraper successfully scraped "
              + productsListWithoutDuplicate.size()
              + " products.");
    } catch (IOException e) {
      e.printStackTrace();
      log.error(e.getMessage());
    }
  }
}
