package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
  public static void main(String[] args) {

    try {

      String filePath = System.getProperty("user.home") + "/Documentos/testefipe.xls";

      FileInputStream fis = new FileInputStream(new File(filePath));

      Workbook workbook;

      if(filePath.endsWith(".xlsz")){
        workbook = new XSSFWorkbook(fis);
      } else if (filePath.endsWith(".xls")) {
        workbook = new HSSFWorkbook(fis);
      } else {
        throw new IllegalArgumentException("Formato de arquivo inválido!");
      }

      Sheet sheet = workbook.getSheetAt(0);

      Connector con = new Connector();

      con.getConnection();

      /*for (Row row : sheet){
        for (Cell cell : row){
          String cellValue = cell.toString();
          System.out.println("Célula: " + cellValue);
        }
      }*/

      workbook.close();
      fis.close();

      con.closeConnection();
    }catch (IOException e){
      e.printStackTrace();
    }



  }
}