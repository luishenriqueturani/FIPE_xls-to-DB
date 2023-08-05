package org.example;

import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
  public static void main(String[] args) {

    try {
      String fileName = "fipe.xls";
      String filePath = System.getProperty("user.home") + "/Documentos/";

      FileInputStream fis = new FileInputStream(new File(filePath + fileName));

      Workbook workbook;

      if(fileName.endsWith(".xlsz")){
        workbook = new XSSFWorkbook(fis);
      } else if (fileName.endsWith(".xls")) {
        workbook = new HSSFWorkbook(fis);
      } else {
        throw new IllegalArgumentException("Formato de arquivo inválido!" + filePath + fileName);
      }

      Sheet sheet = workbook.getSheetAt(0);

      Connector con = new Connector();

      con.getConnection();

      Learn learn = new Learn();

      List<Vehicle> vehicles = new ArrayList<>();

      for (Row row : sheet){

        List<YearPrice> yearPrices = new ArrayList<>();

        int i = 0;
        int year = 2024;
        for (Cell cell : row){
          if(i > 4){
            yearPrices.add(new YearPrice(
                year == 2024 ? 0 : year,
                Float.parseFloat( cell.toString().isEmpty() ? "0" : cell.toString() )
            ));
            year--;
          }

          i++;
        }

        Vehicle vehicle = new Vehicle(
            row.getCell(0).toString(),
            row.getCell(2).toString(),
            (ArrayList<YearPrice>) yearPrices,
            row.getCell(1).toString()
        );

        Vehicle newVehicle = learn.make(vehicle);

        vehicles.add( newVehicle );

      }

      Gson gson = new Gson();

      String json = gson.toJson(vehicles);

      String fileBase = filePath + "base.json";

      try (FileWriter writer = new FileWriter(fileBase)) {
        writer.write(json);
        System.out.println("JSON escrito no arquivo com sucesso!");
      } catch (IOException e) {
        e.printStackTrace();
      }

      workbook.close();
      fis.close();

      con.closeConnection();
    }catch (IOException e){
      e.printStackTrace();
    }



  }
}