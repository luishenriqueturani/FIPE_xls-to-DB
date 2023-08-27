package org.example;

import java.util.ArrayList;

public class Vehicle {
  private String brand;
  private String codFipe;
  private ArrayList<YearPrice> yearPrices;

  private float value;

  private String txtValue;

  private String model;

  private String version;

  private String category;

  private String id;

  private String wordtest;

  private String consult;

  private String ref;

  private int year;

  private String txtYear;

  private String auth;

  public Vehicle(String brand, String codFipe, ArrayList<YearPrice> yearPrices, String model) {
    this.brand = brand;
    this.codFipe = codFipe;
    this.yearPrices = yearPrices;
    this.model = model;
  }

  public Vehicle(String brand, String codFipe, String txtValue, String model, String category, String consult, String ref, String txtYear, String auth) {
    this.brand = brand;
    this.codFipe = codFipe;
    this.txtValue = txtValue;
    this.model = model;
    this.category = category;
    this.consult = consult;
    this.ref = ref;
    this.txtYear = txtYear;
    this.auth = auth;
  }

  //para quando é feita a busca de marca
  public Vehicle(String id){
    this.id = id;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getWordtest() {
    return wordtest;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }

  public String getConsult() {
    return consult;
  }

  public void setConsult(String consult) {
    this.consult = consult;
  }

  public void setWordtest(String wordtest) {
    this.wordtest = wordtest;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getRef() {
    return ref;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }

  public String getAuth() {
    return auth;
  }

  public void setAuth(String auth) {
    this.auth = auth;
  }

  public String getTxtValue() {
    return txtValue;
  }

  public void setTxtValue(String txtValue) {
    this.txtValue = txtValue;
  }

  public String getTxtYear() {
    return txtYear;
  }

  public void setTxtYear(String txtYear) {
    this.txtYear = txtYear;
  }

  @Override
  public String toString() {
    return "Vehicle{" +
        "brand='" + brand + '\'' +
        ", codFipe='" + codFipe + '\'' +
        ", value=" + value +
        ", model='" + model + '\'' +
        ", category='" + category + '\'' +
        ", consult='" + consult + '\'' +
        ", ref='" + ref + '\'' +
        ", year=" + year +
        ", auth='" + auth + '\'' +
        '}';
  }
}
