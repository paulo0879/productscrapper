package com.kolonial.product.scraper.service;

import com.kolonial.product.scraper.model.Product;
import com.kolonial.product.scraper.utils.ElementsUtils;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static com.kolonial.product.scraper.utils.ConstantsUtils.TAG_HREF;
import static com.kolonial.product.scraper.utils.ConstantsUtils.KOLONIAL_URL;

@NoArgsConstructor
public class SubCategoryFetcher {

  /**
   * Method to fetch all product from the subcategories.
   *
   * @param subCategories - The Elements containing the subcategories gotten from category page
   * @return ArrayList of Products - Converted all products fetched to the Product.class
   * @throws IOException - If the Jsoup is unable to get the url page as a Document.class
   */
  public static ArrayList<Product> fetchSubCategoryProducts(Elements subCategories)
      throws IOException {
    var productList = new ArrayList<Product>();

    for (Element subCategory : subCategories) {
      var subCategoryUrl = KOLONIAL_URL + subCategory.attr(TAG_HREF);
      var subCategoryDoc = Jsoup.connect(subCategoryUrl).get();

      productList.addAll(ElementsUtils.genericFetchProducts(subCategoryDoc, subCategoryUrl));
    }
    return productList;
  }
}
