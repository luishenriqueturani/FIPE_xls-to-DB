package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.models.Brand;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Learn {

  private List<Brand> learnedContent;

  public Learn() {
    this.learnedContent = getLearnedContent();
  }

  private List<Brand> getLearnedContent(){

    String path = System.getProperty("user.home") + "/Documentos/base.json";

    try (FileReader reader = new FileReader(path)) {
      Gson gson = new Gson();
      Type listType = new TypeToken<List<Brand>>(){}.getType();

      return gson.fromJson(reader, listType);

    } catch (IOException e) {
      e.printStackTrace();
    }


    return null;
  }

  public Vehicle make(Vehicle vehicle){

    String[] words = vehicle.getModel().split(" ");

    String model = words[0];

    String version = "";

    for (int i = 1; i < words.length; i++) {
      vehicle.setWordtest(words[i]);

      if(test(vehicle)){
        model += " " + words[i];
      }else{
        version += " " + words[i];
      }
    }

    vehicle.setModel(model.trim());
    vehicle.setVersion(version.trim());

    vehicle.setCategory( defineCategory(vehicle) );

    return vehicle;
  }

  public boolean test(Vehicle vehicle){
    for (Brand b: learnedContent ) {
      if(b.equals(vehicle)) return true;
    }
    return false;
  }

  public String defineCategory(Vehicle vehicle){
    return "Carro";
  }
}
