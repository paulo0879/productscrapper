package com.kolonial.product.scraper.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ElementsUtilsTest {

    @Test
    void testGenericFetchProducts() throws IOException {
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
        var url = "https://kolonial.no/produkter/salg/";
        var productList = ElementsUtils.genericFetchProducts(doc, url);
        assertEquals(6, productList.size());
        assertTrue(productList.stream().allMatch(x -> productsName.contains(x.getName())));
    }

}