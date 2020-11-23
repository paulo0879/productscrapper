package com.kolonial.product.scraper.service;

import com.kolonial.product.scraper.utils.ProductHTMLExampleUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.kolonial.product.scraper.utils.ConstantsUtils.PRODUCT_LIST_ITEM;
import static org.junit.jupiter.api.Assertions.*;

class ProductFetcherTest {

    @Test
    void testProductFetcher(){
        var productsName = new ArrayList<String>();
        productsName.add("Ansiktsmaske KN95");
        productsName.add("Blackcurrant & Blueberry");
        productsName.add("Ginger, Mango & Moringa");
        productsName.add("Infuso Collection");
        productsName.add("Raspberry & Pomegranat-te");
        productsName.add("Solbærte");

        // The productExample contains a string with HTML code from a Kolonial webpage
        // that has 6 products
        var productHTML = ProductHTMLExampleUtil.productExample;
        Document doc = Jsoup.parse(productHTML);
        var productElements = doc.getElementsByClass(PRODUCT_LIST_ITEM);

        var productList = ProductFetcher.fetchProducts(productElements);
        assertEquals(6, productList.size());
        assertTrue(productList.stream().allMatch(x -> productsName.contains(x.getName())));
    }

}