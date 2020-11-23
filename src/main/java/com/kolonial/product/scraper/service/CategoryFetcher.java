package com.kolonial.product.scraper.service;

import com.kolonial.product.scraper.model.Product;
import com.kolonial.product.scraper.utils.ElementsUtils;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import static com.kolonial.product.scraper.utils.ConstantsUtils.TAG_HREF;
import static com.kolonial.product.scraper.utils.ConstantsUtils.KOLONIAL_URL;
import static com.kolonial.product.scraper.utils.ConstantsUtils.PRODUCT_CATEGORY_HEADER;
import static com.kolonial.product.scraper.utils.ConstantsUtils.TAG_A;
import static com.kolonial.product.scraper.utils.ConstantsUtils.TAG_UL;

@NoArgsConstructor
public class CategoryFetcher {

  /**
   * Method to fetch all product from specific category. If this category has subcategories it will
   * fetch the products from then otherwise it will fetch the products from the category page.
   *
   * @param category - The Element containing the category gotten from the sidebar
   * @return ArrayList of Products - Converted all products fetched to the Product.class
   */
  public static ArrayList<Product> fetchCategory(Element category) {
    var productsList = new ArrayList<Product>();
    var categoryUrl = KOLONIAL_URL + category.select(TAG_A).attr(TAG_HREF);

    try {
      var categoryDoc = Jsoup.connect(categoryUrl).get();

      var subCategories =
          categoryDoc.getElementsByClass(PRODUCT_CATEGORY_HEADER).select(TAG_UL).select(TAG_A);

      if (subCategories.size() != 0) {
        productsList.addAll(SubCategoryFetcher.fetchSubCategoryProducts(subCategories));
      } else {
        productsList.addAll(ElementsUtils.genericFetchProducts(categoryDoc, categoryUrl));
      }

    } catch (IOException e) {
      System.out.println("failed to fetch category " + categoryUrl);
    }

    return productsList;
  }
}
