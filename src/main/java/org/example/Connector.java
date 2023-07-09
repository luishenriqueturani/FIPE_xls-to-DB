package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
  private Connection con = null;
  private String hostName = null;
  private String userName = null;
  private String password = null;
  private String url = null;
  private String jdbcDriver = null;
  private String dataBaseName = null;
  private String dataBasePrefix = null;
  private String dabaBasePort = null;

  public Connector() {
    super();
    hostName = "localhost";
    userName = "root";
    password = "";
    jdbcDriver = "org.mariadb.jdbc.Driver";
    dataBaseName = "fipe";
    dataBasePrefix = "jdbc:mariadb://";
    dabaBasePort = "3306";

    url = dataBasePrefix + hostName + ":"+dabaBasePort+"/" + dataBaseName;
  }

  public Connection getConnection(){
    try {
      if(con == null){
        Class.forName(jdbcDriver);

        con = DriverManager.getConnection(url, userName, password);
      }else if (con.isClosed()){
        con = null;
        return getConnection();
      }
    }catch (SQLException | ClassNotFoundException e){
      e.printStackTrace();
    }
    return con;
  }

  public void closeConnection(){
    if (con != null){
      try {
        con.close();
      }catch (SQLException e){
        e.printStackTrace();
      }
    }
  }

}
