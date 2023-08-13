package org.example.models;

import org.example.Vehicle;

import java.util.List;

public class Brand {
  private String brand;

  private List<Model> models;


  public Brand(String name, List<Model> models) {
    this.brand = name;
    this.models = models;
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    if(obj == null) return false;

    Vehicle v = (Vehicle) obj;

    if( !v.getBrand().equals( this.brand )) return false;

    for (Model m: this.models ) {
      if( m.getModel().equals( v.getWordtest() ) ) return true;
    }

    return false;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public List<Model> getModels() {
    return models;
  }

  public void setModels(List<Model> models) {
    this.models = models;
  }
}
