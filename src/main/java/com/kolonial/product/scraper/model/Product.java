package com.kolonial.product.scraper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class Product {
  @Id private int id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String nameExtra;

  @Column(nullable = false)
  private String price;

  @Column(nullable = false)
  private String unitPrice;

  @Column(nullable = false)
  private boolean available;

  public Product() {}

  public Product(
      int id, String name, String nameExtra, String price, String unitPrice, boolean available) {
    this.id = id;
    this.name = name;
    this.nameExtra = nameExtra;
    this.price = price;
    this.unitPrice = unitPrice;
    this.available = available;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameExtra() {
    return nameExtra;
  }

  public void setNameExtra(String nameExtra) {
    this.nameExtra = nameExtra;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(String unitPrice) {
    this.unitPrice = unitPrice;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return id == product.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    var availableStr = "available";
    if (!available) {
      availableStr = "unavailable";
    }

    return "Product{"
        + "id="
        + id
        + ", name='"
        + name
        + " "
        + nameExtra
        + '\''
        + ", price='"
        + price
        + '\''
        + ", unitPrice='"
        + unitPrice
        + '\''
        + ", available="
        + availableStr
        + '}';
  }
}
