package com.ltp.globalsuperstore;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class Item {
  @NotBlank(message = "Category is required")
  private String category;

  @NotBlank(message = "Product name cannot be empty")
  private String productName;

  @Min(value = 0, message = "Product price cannot be less than 0")
  private Double price;

  @Min(value = 0, message = "Product discount cannot be less than 0")
  private Double discount;

  @Past(message = "Date must be of the past")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date orderDate;

  private String id;

  public Item() {
    this.id = UUID.randomUUID().toString();
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getProductName() {
    return this.productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getDiscount() {
    return this.discount;
  }

  public void setDiscount(Double discount) {
    this.discount = discount;
  }

  public Date getOrderDate() {
    return this.orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
