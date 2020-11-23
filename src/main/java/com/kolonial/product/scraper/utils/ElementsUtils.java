package com.kolonial.product.scraper.utils;

import com.kolonial.product.scraper.model.Product;
import com.kolonial.product.scraper.service.ProductFetcher;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static com.kolonial.product.scraper.utils.ConstantsUtils.TAG_HREF;
import static com.kolonial.product.scraper.utils.ConstantsUtils.PRODUCT_LIST_ITEM;

@NoArgsConstructor
public class ElementsUtils {

  /**
   * Method that will get the content from the url and get all products from it and convert it to
   * Product.class If the webpage has pagination it will run through all 'Next Pages' until it got
   * all.
   *
   * @param doc - the HTML page gotten from Jsoup
   * @param url - The url of the webpage to fetch products
   * @return ArrayList of Products - Converted all products fetched to the Product.class
   * @throws IOException - If the Jsoup is unable to get the url page as a Document.class
   */
  public static ArrayList<Product> genericFetchProducts(Document doc, String url)
      throws IOException {
    var productsList = new ArrayList<Product>();
    var genericProductList = getProductListItem(doc);

    productsList.addAll(ProductFetcher.fetchProducts(genericProductList));

    var nextPage = ElementsUtils.getNextPage(doc);
    while (nextPage.size() > 0) {
      var pageUrl = url + nextPage.attr(TAG_HREF);

      doc = Jsoup.connect(pageUrl).get();
      genericProductList = getProductListItem(doc);

      productsList.addAll(ProductFetcher.fetchProducts(genericProductList));

      nextPage = getNextPage(doc);
    }
    return productsList;
  }

  /**
   * Method to get all Elements that has the class as 'product-list-item'
   *
   * @param doc - the HTML page gotten from Jsoup
   * @return Elements object that can be empty or not containing the Elements
   */
  private static Elements getProductListItem(Document doc) {
    return doc.getElementsByClass(PRODUCT_LIST_ITEM);
  }

  /**
   * Method to get in the Document the Element that contains the attribute 'title' with the value
   * 'Neste Side'
   *
   * @param doc - the HTML page gotten from Jsoup
   * @return Elements object that can be empty or not containing the Elements
   */
  private static Elements getNextPage(Document doc) {
    return doc.getElementsByAttributeValueContaining("title", "Neste Side");
  }
}
