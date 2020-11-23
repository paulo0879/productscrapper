package com.kolonial.product.scraper.service;

import com.kolonial.product.scraper.model.Product;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import static com.kolonial.product.scraper.utils.ConstantsUtils.TAG_CLASS;
import static com.kolonial.product.scraper.utils.ConstantsUtils.TAG_HREF;
import static com.kolonial.product.scraper.utils.ConstantsUtils.TAG_A;

@NoArgsConstructor
public class ProductFetcher {

  /**
   * Method to extract all products from the Elements to the Product.class format.
   *
   * @param products - Elements object containing products
   * @return ArrayList of Products - Converted products to the Product.class
   */
  public static ArrayList<Product> fetchProducts(Elements products) {
    var productList = new ArrayList<Product>();
    for (Element p : products) {
      productList.add(ProductFetcher.extractProduct(p));
    }
    return productList;
  }

  /**
   * Method to extract the product from the Element and convert it to Product.class
   *
   * @param product - Element object containing the product
   * @return Product.class from the product inside the Element object
   */
  private static Product extractProduct(Element product) {
    var available = true;
    if (product.attr(TAG_CLASS).contains("not-for-sale")) {
      available = false;
    }

    var price = product.select("p.price").text();
    var unitPrice = product.select("p.unit-price").text();
    var name = product.select("div.name-main").text();
    var nameExtra = product.select("div.name-extra").text();
    var productUrl = product.select(TAG_A).attr(TAG_HREF);
    var productId = Integer.parseInt(StringUtils.substringBetween(productUrl, "/produkter/", "-"));

    return new Product(productId, name, nameExtra, price, unitPrice, available);
  }
}
