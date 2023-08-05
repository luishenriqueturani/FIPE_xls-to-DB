package org.example;

import java.util.ArrayList;

public class Vehicle {
  private String brand;
  private String codFipe;
  private ArrayList<YearPrice> yearPrices;

  private String model;

  private String version;

  private String category;


  public Vehicle(String brand, String codFipe, ArrayList<YearPrice> yearPrices, String model) {
    this.brand = brand;
    this.codFipe = codFipe;
    this.yearPrices = yearPrices;
    this.model = model;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getCodFipe() {
    return codFipe;
  }

  public void setCodFipe(String codFipe) {
    this.codFipe = codFipe;
  }

  public ArrayList<YearPrice> getYearPrices() {
    return yearPrices;
  }

  public void setYearPrices(ArrayList<YearPrice> yearPrices) {
    this.yearPrices = yearPrices;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "Vehicle{" +
        "brand='" + brand + '\'' +
        ", codFipe='" + codFipe + '\'' +
        ", yearPrices=" + yearPrices +
        '}';
  }
}
