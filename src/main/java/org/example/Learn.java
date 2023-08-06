package org.example;

public class Learn {

  public Vehicle make(Vehicle vehicle){

    String[] words = vehicle.getModel().split(" ");

    String m = words[0];

    String version = "";

    for (int i = 1; i < words.length; i++) {
      if(test(words[i])){
        m += " " + words[i];
      }else{
        version += " " + words[i];
      }
    }

    vehicle.setModel(m.trim());
    vehicle.setVersion(version.trim());

    vehicle.setCategory( defineCategory(vehicle) );

    return vehicle;
  }

  public boolean test(String word){


    return false;
  }

  public String defineCategory(Vehicle vehicle){
    return "Carro";
  }
}
