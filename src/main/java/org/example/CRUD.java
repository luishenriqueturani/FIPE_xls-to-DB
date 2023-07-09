package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CRUD {
  private Connection con;

  public CRUD(Connection con){
    this.con = con;
  }

  /**
   *
   * @param table
   * @param columns
   * @param values
   * @return UUID
   */
  public UUID insert(String table, String[] columns, String[] values){
    if(table == null) return null;
    if(columns.length == 0 ) return null;
    if(values.length == 0) return null;
    if(columns.length != values.length) return null;

    String query = "INSERT INTO " + table + "(";

    for(String column : columns){
      query += column + ",";
    }

    query = Util.removeLastChar(query) + ") VALUES(";
    for (String val: values) {
      query += "?,";
    }

    query = Util.removeLastChar(query) + ");";

    try (PreparedStatement stmt = con.prepareStatement(query)) {

      for (int i = 1; i <= values.length; i++ ){
        stmt.setString(i, values[i -1]);
      }

      stmt.execute();

      try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
        if(generatedKeys.next()){
          return (UUID) generatedKeys.getObject(1);
        }
      }


    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  /**
   *
   * @param table
   * @param columns
   * @param values
   * @param conditionalColumn
   * @param conditionalValues
   * @return int
   */
  public int update(String table, String[] columns, String[] values, String conditionalColumn, String conditionalValues){
    if(table == null) return -1;
    if(columns.length == 0 ) return -2;
    if(values.length == 0) return -3;
    if(columns.length != values.length) return -4;
    if(conditionalColumn == null) return -5;
    if(conditionalValues == null) return -6;

    String query = "UPDATE " + table + " SET ";

    for (String column: columns ) {
      query += column + " = ?,";
    }
    query = Util.removeLastChar(query) + " WHERE " + conditionalColumn + " = ? ;";

    try (PreparedStatement stmt = con.prepareStatement(query)){

      int cont = 1;
      for (String value: values ) {
        stmt.setString(cont, value);
        cont++;
      }

      stmt.setString(cont, conditionalValues);

      return stmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   *
   * @param tableName
   * @param conditionColumn
   * @param conditionValue
   * @return int
   */
  public int deleteData(String tableName, String conditionColumn, String conditionValue) {
    String query = "DELETE FROM " + tableName + " WHERE " + conditionColumn + " = ?";

    try (PreparedStatement statement = con.prepareStatement(query)) {
      statement.setString(1, conditionValue);

      return statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  

}
