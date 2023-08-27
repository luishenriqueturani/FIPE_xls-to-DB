package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class ReadJSON {
  public static void main(String[] args) throws FileNotFoundException {

    File file = new File(System.getProperty("user.home") + "/Downloads/fipe/fipe.json");
    //findAllFilesInFolder(folder);

    Reader reader = new FileReader(file);

    Gson gson = new Gson();

    List<Vehicle> vehicles = gson.fromJson(reader, new TypeToken<List<Vehicle>>(){}.getType());

    Connector con = new Connector();

    VehicleDTO dto = new VehicleDTO(con.getConnection());

    Learn learn = new Learn();

    int success = 0;
    int fails = 0;

    String returns = "";

    for (Vehicle vehicle: vehicles) {
      vehicle.setYear( testInt(vehicle.getTxtYear()) ? Integer.parseInt(vehicle.getTxtYear()) : 0 );
      vehicle.setValue( valueStringToFloat(vehicle.getTxtValue() ) );

      Vehicle newVehicle = learn.make(vehicle);

      switch(dto.insert(newVehicle)){
        case 0:
          success++;
          break;
        case -1:
          returns += "Veículos nulo\n";
          fails++;
          break;
        case -2:
          returns += "Categoria nulo\n";
          fails++;
          break;
        case -3:
          returns += "Marca nulo\n";
          fails++;
          break;
        case -4:
          returns += "Modelo nulo\n";
          fails++;
          break;
        case -6:
          returns += "Falha ao inserir a categoria\n";
          fails++;
          break;
        case -8:
          returns += "Falha ao inserir a marca\n";
          fails++;
          break;
        case -10:
          returns += "Falha ao inserir modelo\n";
          fails++;
          break;
        case -11:
          returns += "Falha ao inserir versão\n";
          fails++;
          break;
      }

    }

    /*for (int i = 0; i < vehicles.size(); i++){
      //System.out.println( vehicles.get(i).toString() );
      //System.out.println( String.valueOf( valueStringToFloat( vehicles.get(i).getTxtValue() ) ) );

      System.out.println( testInt( vehicles.get(i).getTxtYear() ) ? vehicles.get(i).getTxtYear() : 0 );
      if(i == 10) break;
    }*/

    con.closeConnection();

    System.out.println("Sucessos: " + success + "\nFalhas: " + fails + "\nErros\n" + returns);

  }

  public static void findAllFilesInFolder(File folder) {
    for (File file : folder.listFiles()) {
      if (!file.isDirectory()) {
        System.out.println(file.getName());
      } else {
        findAllFilesInFolder(file);
      }
    }
  }

  public static float valueStringToFloat(String value){
    String cleared = value.replaceAll("[^\\d,]", "")
                          .replace(",", ".");

    return Float.parseFloat(cleared);
  }

  public static boolean testInt(String value){
    try {
      int val = Integer.parseInt(value);
      return true;
    }catch (Exception e){
      return false;
    }
  }
}
