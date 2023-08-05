package org.example;

public class YearPrice {
  private int year;
  private float price;

  public YearPrice(int year, float price) {
    this.year = year;
    this.price = price;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }
}
